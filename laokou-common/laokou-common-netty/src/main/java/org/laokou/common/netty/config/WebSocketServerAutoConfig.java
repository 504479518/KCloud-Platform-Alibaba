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

import io.netty.channel.ChannelInitializer;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.UnorderedThreadPoolEventExecutor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

// @formatter:off
/**
 * @author laokou
 */
@AutoConfiguration
@ConditionalOnProperty(havingValue = "true", matchIfMissing = true, prefix = "spring.websocket-server", name = "enabled")
public class WebSocketServerAutoConfig {

    @Bean(name = "webSocketServer", initMethod = "start", destroyMethod = "stop")
    public Server webSocketServer(SpringWebSocketServerProperties springWebSocketServerProperties,
                                  ChannelInitializer<?> webSocketServerChannelInitializer) {
        return new WebSocketServer(springWebSocketServerProperties.getIp(), springWebSocketServerProperties.getPort(),
                webSocketServerChannelInitializer, springWebSocketServerProperties.getBossCoreSize(),
                springWebSocketServerProperties.getWorkerCoreSize());
    }

    @Bean
    public EventExecutorGroup webSocketEventExecutorGroup(SpringWebSocketServerProperties springWebSocketServerProperties) {
        return new UnorderedThreadPoolEventExecutor(springWebSocketServerProperties.getCorePoolSize(), new DefaultThreadFactory("webSocketHandler"));
    }

}
// @formatter:on
