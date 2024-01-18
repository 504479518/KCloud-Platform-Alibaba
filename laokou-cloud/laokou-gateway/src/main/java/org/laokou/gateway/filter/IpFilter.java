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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.laokou.gateway.support.ip.Ip;
import org.laokou.gateway.support.ip.IpProperties;
import org.laokou.gateway.support.ip.Label;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.handler.predicate.RemoteAddrRoutePredicateFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * IP过滤器. see {@link RemoteAddrRoutePredicateFactory}.
 *
 * @author laokou
 */
@Component
@Slf4j
@RefreshScope
@RequiredArgsConstructor
public class IpFilter implements GlobalFilter, Ordered {

	private final Ip whiteIp;

	private final Ip blackIp;

	private final IpProperties ipProperties;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		if (ipProperties.isEnabled()) {
			return validate(exchange, ipProperties.getLabel(), chain);
		}
		else {
			return chain.filter(exchange);
		}
	}

	@Override
	public int getOrder() {
		return HIGHEST_PRECEDENCE + 1000;
	}

	/**
	 * 校验IP并响应.
	 * @param exchange 服务网络交换机
	 * @param label 标签
	 * @param chain 链式过滤器
	 * @return 响应结果
	 */
	private Mono<Void> validate(ServerWebExchange exchange, String label, GatewayFilterChain chain) {
		Label instance = Label.getInstance(label.toUpperCase());
		return switch (instance) {
			case WHITE -> whiteIp.validate(exchange, chain);
			case BLACK -> blackIp.validate(exchange, chain);
		};
	}

}
