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

package org.laokou.admin.command.menu.query;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import org.laokou.admin.dto.menu.clientobject.RouterCO;
import org.laokou.admin.gatewayimpl.database.MenuMapper;
import org.laokou.admin.gatewayimpl.database.dataobject.MenuDO;
import org.laokou.common.core.utils.TreeUtil;
import org.laokou.common.i18n.dto.Result;
import org.laokou.common.redis.utils.RedisUtil;
import org.laokou.common.security.utils.UserDetail;
import org.laokou.common.security.utils.UserUtil;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.laokou.common.i18n.common.DSConstant.TENANT;

/**
 * @author laokou
 */
@Component
@RequiredArgsConstructor
public class MenuListRouterQryExe {

	private final MenuMapper menuMapper;

	private final RedisUtil redisUtil;

	@DS(TENANT)
	public Result<List<RouterCO>> execute() {
		return Result.ok(getRouter());
	}

	private List<RouterCO> getRouter() {
		// String language = LocaleContextHolder.getLocale().getLanguage();
		// String menuTreeKey =
		// RedisKeyUtil.getMenuTreeKey(UserContextHolder.get().getToken(), language);
		// Object obj = redisUtil.get(menuTreeKey);
		// if (ObjectUtil.isNotNull(obj)) {
		// return ((RouterCO) obj).getChildren();
		// }
		RouterCO co = buildTreeNode(getMenuList().stream().map(this::convert).toList());
		// redisUtil.set(menuTreeKey, co, RedisUtil.HOUR_ONE_EXPIRE);
		return co.getChildren();
	}

	private RouterCO convert(MenuDO menuDO) {
		return null;
	}

	private List<MenuDO> getMenuList() {
		UserDetail user = UserUtil.user();
		if (user.isSuperAdministrator()) {
			return menuMapper.selectObjects();
		}
		return menuMapper.selectListByUserId(user.getId());
	}

	private RouterCO buildTreeNode(List<RouterCO> list) {
		return TreeUtil.buildTreeNode(list, RouterCO.class);
	}

}
