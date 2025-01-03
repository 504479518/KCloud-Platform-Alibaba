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

package org.laokou.admin.menu.convertor;

import org.laokou.admin.menu.dto.clientobject.MenuCO;
import org.laokou.admin.menu.dto.clientobject.MenuTreeCO;
import org.laokou.admin.menu.gatewayimpl.database.dataobject.MenuDO;
import org.laokou.admin.menu.model.MenuE;
import org.laokou.common.core.utils.ConvertUtil;

import java.util.List;

/**
 * 菜单转换器.
 *
 * @author laokou
 */
public final class MenuConvertor {

	private MenuConvertor() {
	}

	public static MenuDO toDataObject(MenuE menuE) {
		MenuDO menuDO = new MenuDO();
		menuDO.setId(menuE.getId());
		menuDO.setPid(menuE.getPid());
		menuDO.setPermission(menuE.getPermission());
		menuDO.setType(menuE.getType());
		menuDO.setName(menuE.getName());
		menuDO.setPath(menuE.getPath());
		menuDO.setIcon(menuE.getIcon());
		menuDO.setSort(menuE.getSort());
		menuDO.setHidden(menuE.getHidden());
		menuDO.setStatus(menuE.getStatus());
		menuDO.setUrl(menuE.getUrl());
		return menuDO;
	}

	public static MenuCO toClientObject(MenuDO menuDO) {
		MenuCO co = new MenuCO();
		co.setId(menuDO.getId());
		co.setPid(menuDO.getPid());
		co.setPermission(menuDO.getPermission());
		co.setType(menuDO.getType());
		co.setName(menuDO.getName());
		co.setPath(menuDO.getPath());
		co.setIcon(menuDO.getIcon());
		co.setSort(menuDO.getSort());
		co.setHidden(menuDO.getHidden());
		co.setStatus(menuDO.getStatus());
		co.setUrl(menuDO.getUrl());
		return co;
	}

	public static List<MenuCO> toClientObjects(List<MenuDO> list) {
		return list.stream().map(MenuConvertor::toClientObject).toList();
	}

	public static MenuTreeCO toClientObj(MenuDO menuDO) {
		MenuTreeCO co = new MenuTreeCO();
		co.setId(menuDO.getId());
		co.setPid(menuDO.getPid());
		co.setName(menuDO.getName());
		co.setPath(menuDO.getPath());
		return co;

	}

	public static List<MenuTreeCO> toClientObjs(List<MenuDO> list) {
		return list.stream().map(MenuConvertor::toClientObj).toList();
	}

	public static MenuE toEntity(MenuCO menuCO) {
		return ConvertUtil.sourceToTarget(menuCO, MenuE.class);
	}

}
