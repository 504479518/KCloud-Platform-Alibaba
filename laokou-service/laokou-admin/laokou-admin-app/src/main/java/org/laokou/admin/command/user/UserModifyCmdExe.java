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

package org.laokou.admin.command.user;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import org.laokou.admin.convertor.UserConvertor;
import org.laokou.admin.domain.gateway.UserGateway;
import org.laokou.admin.dto.user.UserModifyCmd;
import org.springframework.stereotype.Component;

import static org.laokou.common.i18n.common.DSConstant.TENANT;

/**
 * 修改用户执行器.
 *
 * @author laokou
 */
@Component
@RequiredArgsConstructor
public class UserModifyCmdExe {

	private final UserGateway userGateway;

	private final UserConvertor userConvertor;

	/**
	 * 执行修改用户.
	 * @param cmd 修改用户参数
	 */
	@DS(TENANT)
	public void executeVoid(UserModifyCmd cmd) {
		userGateway.modify(userConvertor.toEntity(cmd.getUserCO()));
	}

}
