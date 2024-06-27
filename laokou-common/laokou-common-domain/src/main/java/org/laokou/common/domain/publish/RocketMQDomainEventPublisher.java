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

package org.laokou.common.domain.publish;

import lombok.RequiredArgsConstructor;
import org.laokou.common.core.utils.IdGenerator;
import org.laokou.common.rocketmq.template.RocketMqTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RocketMQDomainEventPublisher implements DomainEventPublisher {

	private final RocketMqTemplate rocketMqTemplate;

	@Override
	public <T> void publish(String topic, T payload) {
		rocketMqTemplate.sendAsyncMessage(topic, payload, String.valueOf(IdGenerator.defaultSnowflakeId()));
	}

}
