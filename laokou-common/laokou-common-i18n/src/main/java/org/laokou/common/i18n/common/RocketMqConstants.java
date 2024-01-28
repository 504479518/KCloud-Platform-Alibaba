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

package org.laokou.common.i18n.common;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author laokou
 */
@Schema(name = "RocketMqConstants", description = "RocketMQ消息常量")
public final class RocketMqConstants {

	private RocketMqConstants() {
	}

	@Schema(name = "LAOKOU_MESSAGE_TOPIC", description = "分布式链路主题")
	public static final String LAOKOU_MESSAGE_TOPIC = "laokou_message_topic";

	@Schema(name = "LAOKOU_LOGIN_LOG_TOPIC", description = "登录日志主题")
	public static final String LAOKOU_LOGIN_LOG_TOPIC = "laokou_login_log_topic";

	@Schema(name = "LAOKOU_NOTICE_MESSAGE_TAG", description = "通知消息标签")
	public static final String LAOKOU_NOTICE_MESSAGE_TAG = "notice";

	@Schema(name = "LAOKOU_REMIND_MESSAGE_TAG", description = "提醒消息标签")
	public static final String LAOKOU_REMIND_MESSAGE_TAG = "remind";

	@Schema(name = "LAOKOU_REMIND_MESSAGE_CONSUMER_GROUP", description = "提醒消息消费者组")
	public static final String LAOKOU_REMIND_MESSAGE_CONSUMER_GROUP = "laokou_remind_message_consumer_group";

	@Schema(name = "LAOKOU_NOTICE_MESSAGE_CONSUMER_GROUP", description = "通知消息消费者组")
	public static final String LAOKOU_NOTICE_MESSAGE_CONSUMER_GROUP = "laokou_notice_message_consumer_group";

	@Schema(name = "LAOKOU_LOGIN_LOG_CONSUMER_GROUP", description = "通知消息消费者组")
	public static final String LAOKOU_LOGIN_LOG_CONSUMER_GROUP = "laokou_login_log_consumer_group";

	@Schema(name = "TOPIC_TAG", description = "主题与标签的分隔符")
	public static final String TOPIC_TAG = "%s:%s";

}
