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
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.laokou.common.core.utils.JacksonUtil;
import org.laokou.common.domain.model.DomainEventA;
import org.laokou.common.domain.service.DomainEventService;
import org.laokou.common.i18n.dto.DefaultDomainEvent;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

import static org.apache.rocketmq.spring.annotation.ConsumeMode.CONCURRENTLY;
import static org.apache.rocketmq.spring.annotation.MessageModel.CLUSTERING;
import static org.laokou.common.domain.constant.MqConstant.LAOKOU_MODIFY_EVENT_CONSUMER_GROUP;
import static org.laokou.common.domain.constant.MqConstant.LAOKOU_MODIFY_EVENT_TOPIC;
import static org.laokou.common.i18n.common.constant.StringConstant.EMPTY;

/**
 * @author laokou
 */
@Slf4j
@Component
@RequiredArgsConstructor
@RocketMQMessageListener(consumerGroup = LAOKOU_MODIFY_EVENT_CONSUMER_GROUP, topic = LAOKOU_MODIFY_EVENT_TOPIC,
		messageModel = CLUSTERING, consumeMode = CONCURRENTLY)
public class ModifyDomainEventHandler implements RocketMQListener<MessageExt> {

	private final DomainEventService domainEventService;

	@Override
	public void onMessage(MessageExt message) {
		String msg = new String(message.getBody(), StandardCharsets.UTF_8);
		DefaultDomainEvent defaultDomainEvent = JacksonUtil.toBean(msg, DefaultDomainEvent.class);
		domainEventService.update(new DomainEventA(EMPTY, defaultDomainEvent));
	}

}
