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

import io.netty.bootstrap.AbstractBootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioChannelOption;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;

/**
 * @author laokou
 */
public class TcpServer extends AbstractServer {

	public TcpServer(String ip, int port, ChannelInitializer<?> channelInitializer, int bossCoreSize,
			int workerCoreSize) {
		super(ip, port, channelInitializer, bossCoreSize, workerCoreSize);
	}

	@Override
	protected AbstractBootstrap<?, ?> init() {
		// boss负责监听端口
		boss = new NioEventLoopGroup(bossCoreSize, new DefaultThreadFactory("boss", true));
		// work负责线程读写
		worker = new NioEventLoopGroup(workerCoreSize, new DefaultThreadFactory("worker", true));
		// 配置引导
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		// 绑定线程组
		return serverBootstrap.group(boss, worker)
			// 指定通道
			.channel(NioServerSocketChannel.class)
			// 延迟发送
			.childOption(NioChannelOption.TCP_NODELAY, true)
			// 请求队列最大长度（如果连接建立频繁，服务器处理创建新连接较慢，可以适当调整参数）
			.option(NioChannelOption.SO_BACKLOG, 1024)
			// tcp处理类
			.childHandler(channelInitializer);
	}

	@Override
	public void send(String clientId, Object obj) {

	}

}
