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

package org.laokou.admin.command.role;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import org.laokou.admin.domain.gateway.RoleGateway;
import org.laokou.admin.domain.role.Role;
import org.laokou.admin.dto.role.RoleModifyCmd;
import org.laokou.admin.dto.role.clientobject.RoleCO;
import org.springframework.stereotype.Component;

import static org.laokou.common.i18n.common.DatasourceConstants.TENANT;

/**
 * 修改角色执行器.
 *
 * @author laokou
 */
@Component
@RequiredArgsConstructor
public class RoleModifyCmdExe {

	private final RoleGateway roleGateway;

	/**
	 * 执行修改角色.
	 * @param cmd 修改角色参数
	 */
	@DS(TENANT)
	public void executeVoid(RoleModifyCmd cmd) {
		roleGateway.modify(convert(cmd.getRoleCO()));
	}

	private Role convert(RoleCO roleCO) {
		return Role.builder()
			.id(roleCO.getId())
			.name(roleCO.getName())
			.sort(roleCO.getSort())
			.menuIds(roleCO.getMenuIds())
			.deptIds(roleCO.getDeptIds())
			.build();
	}

}
