/*
 * Copyright (c) 2022-2024 KCloud-Platform-Alibaba Author or Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.laokou.gateway.filter;

import io.micrometer.common.lang.NonNullApi;
import lombok.RequiredArgsConstructor;
import org.laokou.common.crypto.utils.RsaUtil;
import org.laokou.common.i18n.dto.Result;
import org.laokou.common.i18n.utils.ObjectUtil;
import org.laokou.common.i18n.utils.StringUtil;
import org.laokou.common.i18n.utils.ValidatorUtil;
import org.laokou.common.nacos.utils.ReactiveRequestUtil;
import org.laokou.common.nacos.utils.ReactiveResponseUtil;
import org.laokou.gateway.annotation.Auth;
import org.laokou.gateway.config.GatewayExtProperties;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import static com.alibaba.nacos.api.common.Constants.ALL_PATTERN;
import static org.laokou.common.i18n.common.ErrorCodes.ACCOUNT_PASSWORD_ERROR;
import static org.laokou.common.i18n.common.OAuth2Constants.PASSWORD;
import static org.laokou.common.i18n.common.OAuth2Constants.USERNAME;
import static org.laokou.common.i18n.common.RouterConstants.API_URL_PREFIX;
import static org.laokou.common.i18n.common.ValCodes.OAUTH2_PASSWORD_REQUIRE;
import static org.laokou.common.i18n.common.ValCodes.OAUTH2_USERNAME_REQUIRE;

/**
 * API过滤器.
 *
 * @author laokou
 */
@NonNullApi
@RequiredArgsConstructor
public class ApiFilter implements WebFilter {

	private static final String API_PATTERN = API_URL_PREFIX + ALL_PATTERN;

	private final RequestMappingHandlerMapping requestMappingHandlerMapping;

	private final GatewayExtProperties gatewayExtProperties;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		return requestMappingHandlerMapping.getHandler(exchange)
				.switchIfEmpty(chain.filter(exchange))
				.flatMap(handler -> {
					ServerHttpRequest request = exchange.getRequest();
					String requestURL = ReactiveRequestUtil.getRequestURL(request);
					if (ReactiveRequestUtil.pathMatcher(requestURL, API_PATTERN)) {
						if (handler instanceof HandlerMethod handlerMethod) {
							if (handlerMethod.hasMethodAnnotation(Auth.class)) {
								Auth auth = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), Auth.class);
								Assert.isTrue(ObjectUtil.isNotNull(auth), "@Auth is null");
								return validate(exchange, request, auth, chain);
							}
						}
					}
					return chain.filter(exchange);
				});
	}

	/**
	 * 校验账号和密码.
	 * @param exchange 服务网络交换机
	 * @param request 请求对象
	 * @param auth auth注解
	 * @param chain 链式过滤器
	 * @return 响应结果
	 */
	private Mono<Void> validate(ServerWebExchange exchange, ServerHttpRequest request, Auth auth,
			WebFilterChain chain) {
		String username = ReactiveRequestUtil.getParamValue(request, USERNAME);
		String password = ReactiveRequestUtil.getParamValue(request, PASSWORD);
		if (StringUtil.isEmpty(username)) {
			// 账号不能为空
			return ReactiveResponseUtil.response(exchange,
					Result.fail(ValidatorUtil.getMessage(OAUTH2_USERNAME_REQUIRE)));
		}
		if (StringUtil.isEmpty(password)) {
			// 密码不能为空
			return ReactiveResponseUtil.response(exchange,
					Result.fail(ValidatorUtil.getMessage(OAUTH2_PASSWORD_REQUIRE)));
		}
		try {
			String privateKey = RsaUtil.getPrivateKey();
			username = RsaUtil.decryptByPrivateKey(username, privateKey);
			password = RsaUtil.decryptByPrivateKey(password, privateKey);
		}
		catch (Exception e) {
			// 账号或密码错误
			return ReactiveResponseUtil.response(exchange, Result.fail(ACCOUNT_PASSWORD_ERROR));
		}
		String pwd;
		String name;
		if (gatewayExtProperties.isEnabled()) {
			pwd = gatewayExtProperties.getPassword();
			name = gatewayExtProperties.getUsername();
		}
		else {
			pwd = auth.password();
			name = auth.username();
		}
		if (!name.equals(username) || !pwd.equals(password)) {
			// 账号或密码错误
			return ReactiveResponseUtil.response(exchange, Result.fail(ACCOUNT_PASSWORD_ERROR));
		}
		return chain.filter(exchange);
	}

}
