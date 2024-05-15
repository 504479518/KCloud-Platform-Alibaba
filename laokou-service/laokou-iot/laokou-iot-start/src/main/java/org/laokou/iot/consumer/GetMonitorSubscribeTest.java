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

package org.laokou.iot.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.laokou.mqtt.annotation.MqttMessageListener;
import org.laokou.mqtt.config.MqttListener;
import org.laokou.mqtt.template.MqttTemplate;

import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
@MqttMessageListener(topic = "/55/D1PGLPG58KZ2/monitor/get")
public class GetMonitorSubscribeTest implements MqttListener {

	private final MqttTemplate mqttTemplate;

	@Override
	public void onMessage(MqttMessage message) {
		log.info("订阅实时监测消息：{}，已被接收，正在处理中", new String(message.getPayload(), StandardCharsets.UTF_8));
		String str = """
				{
				   "field":"test_value"
				 }
				""";
		mqttTemplate.send("/55/D1PGLPG58KZ2/monitor/post", str);
	}

}
