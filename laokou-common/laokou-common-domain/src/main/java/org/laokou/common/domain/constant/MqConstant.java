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

package org.laokou.common.domain.constant;

import static org.laokou.common.i18n.common.constant.Constant.DLQ;

/**
 * @author laokou
 */
public final class MqConstant {

	public static final String LAOKOU_CREATE_EVENT_TOPIC = "laokou_create_event_topic";

	public static final String LAOKOU_CREATE_EVENT_CONSUMER_GROUP = "laokou_create_event_consumer_group";

	public static final String LAOKOU_MODIFY_EVENT_CONSUMER_GROUP = "laokou_modify_event_consumer_group";

	public static final String LAOKOU_MODIFY_EVENT_TOPIC = "laokou_modify_event_topic";

	public static final String LAOKOU_MODIFY_EVENT_CONSUMER_GROUP_DLQ = DLQ + "laokou_modify_event_consumer_group";

	public static final String LAOKOU_CREATE_EVENT_CONSUMER_GROUP_DLQ = DLQ + "laokou_create_event_consumer_group";

	private MqConstant() {

	}

}
