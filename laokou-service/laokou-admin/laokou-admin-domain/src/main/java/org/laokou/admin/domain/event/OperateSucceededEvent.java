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

package org.laokou.admin.domain.event;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.laokou.admin.domain.log.OperateLog;
import org.laokou.common.core.context.UserContextHolder;

import static org.laokou.common.i18n.common.NumberConstants.SUCCESS;

/**
 * @author laokou
 */
@Data
@SuperBuilder
@Schema(name = "OperateLogEvent", description = "操作日志事件")
public class OperateSucceededEvent extends OperateEvent {

	public OperateSucceededEvent(OperateLog operateLog, HttpServletRequest request, UserContextHolder.User user,
			String appName) {
		super(operateLog, request, user, appName, SUCCESS);
	}

}
