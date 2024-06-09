/*
 * Copyright (c) 2022-2024 KCloud-Platform-IoT Author or Authors. All Rights Reserved.
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

package org.laokou.admin;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.SneakyThrows;
import org.laokou.common.core.annotation.EnableTaskExecutor;
import org.laokou.common.nacos.annotation.EnableRouter;
import org.laokou.common.nacos.filter.ShutdownFilter;
import org.laokou.common.redis.annotation.EnableRedisRepository;
import org.laokou.common.security.annotation.EnableSecurity;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.core.context.SecurityContextHolder;

import java.net.InetAddress;

/**
 * 启动类. exposeProxy=true => 使用Cglib代理，在切面中暴露代理对象，进行方法增强（默认Cglib代理）
 *
 * @author laokou
 */
@SpringBootApplication(exclude = { SecurityFilterAutoConfiguration.class }, scanBasePackages = "org.laokou")
@EnableDiscoveryClient
@EnableConfigurationProperties
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableEncryptableProperties
@EnableFeignClients
@EnableRedisRepository
@ServletComponentScan(basePackageClasses = { ShutdownFilter.class })
@EnableSecurity
@EnableTaskExecutor
@EnableRouter
public class AdminApp {

	@SneakyThrows
	public static void main(String[] args) {
		System.setProperty("ip", InetAddress.getLocalHost().getHostAddress());
		// SpringSecurity 子线程读取父线程的上下文
		System.setProperty(SecurityContextHolder.SYSTEM_PROPERTY, SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
		// 因为nacos的log4j2导致本项目的日志不输出的问题
		// 配置关闭nacos日志
		System.setProperty("nacos.logging.default.config.enabled", "false");
		// -Dtls.enable=true -Dtls.client.authServer=true
		// -Dtls.client.trustCertPath=d:\\nacos.crt
		new SpringApplicationBuilder(AdminApp.class).web(WebApplicationType.SERVLET).run(args);
	}

}
