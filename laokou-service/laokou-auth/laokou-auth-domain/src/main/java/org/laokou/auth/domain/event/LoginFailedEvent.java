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

package org.laokou.auth.domain.event;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.laokou.auth.domain.model.auth.AuthA;

import static org.laokou.common.i18n.common.NumberConstant.FAIL;

/**
 * @author laokou
 */
@Data
@SuperBuilder
@Schema(name = "LoginFailedEvent", description = "登录失败事件")
public class LoginFailedEvent extends LoginEvent {

	public LoginFailedEvent(AuthA authA, HttpServletRequest request, String message, String sourceName, String appName,
			String type) {
		super(authA, request, message, sourceName, appName, type, FAIL);
	}

}
