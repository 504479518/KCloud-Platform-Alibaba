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

package org.laokou.common.domain.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.laokou.common.core.utils.ConvertUtil;
import org.laokou.common.core.utils.JacksonUtil;
import org.laokou.common.domain.repository.DomainEventDO;
import org.laokou.common.domain.service.DomainEventService;
import org.laokou.common.i18n.dto.DecorateDomainEvent;
import org.laokou.common.i18n.dto.DomainEvent;
import org.laokou.common.i18n.utils.LogUtil;
import org.springframework.dao.DataIntegrityViolationException;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.laokou.common.i18n.common.EventStatusEnums.CONSUME_FAILED;
import static org.laokou.common.i18n.common.EventStatusEnums.CONSUME_SUCCEED;

/**
 * @author laokou
 */
@Slf4j
@RequiredArgsConstructor
public abstract class AbstractDomainEventRocketMQListener implements RocketMQListener<MessageExt> {

	private final DomainEventService domainEventService;

	@Override
	public void onMessage(MessageExt message) {
		List<DomainEvent<Long>> events = new ArrayList<>(1);
		String msg = new String(message.getBody(), StandardCharsets.UTF_8);
		DomainEventDO eventDO = JacksonUtil.toBean(msg, DomainEventDO.class);
		try {
			// 处理领域事件
			handleDomainEvent(ConvertUtil.sourceToTarget(eventDO, DecorateDomainEvent.class), eventDO.getEventType(),
					eventDO.getAttribute());
			// 消费成功
			events.add(new DecorateDomainEvent(eventDO.getId(), CONSUME_SUCCEED, eventDO.getSourceName()));
		}
		catch (Exception e) {
			log.error("错误信息：{}，详情见日志", LogUtil.result(e.getMessage()), e);
			if (e instanceof DataIntegrityViolationException) {
				// 消费成功（数据重复直接改为消费成功）
				events.add(new DecorateDomainEvent(eventDO.getId(), CONSUME_SUCCEED, eventDO.getSourceName()));
			}
			else {
				// 消费失败
				events.add(new DecorateDomainEvent(eventDO.getId(), CONSUME_FAILED, eventDO.getSourceName()));
			}
		}
		finally {
			domainEventService.modify(events);
		}
	}

	protected abstract void handleDomainEvent(DecorateDomainEvent evt, String eventType, String attribute);

}
