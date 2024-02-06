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

package org.laokou.admin.command.user.query;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import org.laokou.admin.dto.user.UserGetQry;
import org.laokou.admin.dto.user.clientobject.UserCO;
import org.laokou.admin.gatewayimpl.database.RoleMapper;
import org.laokou.admin.gatewayimpl.database.UserMapper;
import org.laokou.admin.gatewayimpl.database.UserRoleMapper;
import org.laokou.admin.gatewayimpl.database.dataobject.UserDO;
import org.laokou.common.i18n.dto.Result;
import org.laokou.common.i18n.utils.ObjectUtil;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.laokou.common.i18n.common.DatasourceConstants.TENANT;
import static org.laokou.common.i18n.common.SuperAdminEnums.YES;

/**
 * 查看用户执行器.
 *
 * @author laokou
 */
@Component
@RequiredArgsConstructor
public class UserGetQryExe {

	private final UserMapper userMapper;
	private final RoleMapper roleMapper;
	private final UserRoleMapper userRoleMapper;

	/**
	 * 执行查看用户.
	 * @param qry 查看用户参数
	 * @return 用户
	 */
	@DS(TENANT)
	public Result<UserCO> execute(UserGetQry qry) {
		UserDO userDO = userMapper.selectById(qry.getId());
		return Result.of(convert(userDO));
	}

	private UserCO convert(UserDO userDO) {
		return UserCO.builder().id(userDO.getId())
				.deptId(userDO.getDeptId())
				.deptPath(userDO.getDeptPath())
				.superAdmin(userDO.getSuperAdmin())
				.roleIds(getRoleIds(userDO))
				.status(userDO.getStatus())
				.id(userDO.getId())
				.build();
	}

	private List<Long> getRoleIds(UserDO userDO) {
		if (isSuperAdministrator(userDO.getSuperAdmin())) {
			return roleMapper.selectRoleIds();
		}
		return userRoleMapper.selectRoleIdsUserId(userDO.getId());
	}

	private boolean isSuperAdministrator(Integer superAdmin) {
		return ObjectUtil.equals(YES.ordinal(), superAdmin);
	}
}
