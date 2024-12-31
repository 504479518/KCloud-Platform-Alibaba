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

package org.laokou.logstash.common.support;

import org.laokou.common.core.utils.ThreadUtil;
import org.laokou.common.i18n.common.constant.StringConstant;
import org.laokou.common.i18n.utils.DateUtil;

import java.util.concurrent.ExecutorService;

public abstract class AbstractTraceLogStorage implements TraceLogStorage {

	protected static final String TRACE_INDEX = "laokou_trace";

	protected static final ExecutorService EXECUTOR = ThreadUtil.newVirtualTaskExecutor();

	protected String getIndexName() {
		return TRACE_INDEX + StringConstant.UNDER + DateUtil.format(DateUtil.nowDate(), DateUtil.YYYYMMDD);
	}

}
