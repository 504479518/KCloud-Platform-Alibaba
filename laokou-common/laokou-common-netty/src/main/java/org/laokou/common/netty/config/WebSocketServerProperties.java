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

package org.laokou.common.netty.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * WebSocketServer属性配置.
 *
 * @author laokou
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.websocket-server")
public class WebSocketServerProperties {

	/**
	 * 监听核心线程数.
	 */
	private Integer bossCoreSize = 1;

	/**
	 * 读写核心线程数.
	 */
	private Integer workerCoreSize = 8;

	/**
	 * IP.
	 */
	private String ip;

	/**
	 * 端口.
	 */
	private int port;

	/**
	 * 应用名称.
	 */
	private String appName;

}
