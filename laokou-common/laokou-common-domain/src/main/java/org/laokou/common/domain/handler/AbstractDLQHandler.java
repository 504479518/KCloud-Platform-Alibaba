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

package org.laokou.common.domain.handler;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.ThreadContext;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.laokou.common.domain.support.DomainEventPublisher;

import java.nio.charset.StandardCharsets;

import static org.laokou.common.i18n.common.constant.TraceConstant.TRACE_ID;

/**
 * 监听死信队列.
 *
 * @author laokou
 */
@RequiredArgsConstructor
public abstract class AbstractDLQHandler implements RocketMQListener<MessageExt> {

	private final DomainEventPublisher domainEventPublisher;

	@Override
	public void onMessage(MessageExt messageExt) {
		String traceId = messageExt.getProperty(TRACE_ID);
		ThreadContext.put(TRACE_ID, traceId);
		String msg = new String(messageExt.getBody(), StandardCharsets.UTF_8);
		try {
			domainEventPublisher.publish(messageExt.getTopic(), messageExt.getTags(), msg);
		}
		finally {
			ThreadContext.clearMap();
		}
	}

}
