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

package org.laokou.admin.domain.packages;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.laokou.common.i18n.common.exception.SystemException;
import org.laokou.common.i18n.dto.AggregateRoot;

import java.util.List;

/**
 * @author laokou
 */
@Data
@Schema(name = "Package", description = "套餐")
public class Package extends AggregateRoot<Long> {

	@Schema(name = "name", description = "套餐名称")
	private String name;

	@Schema(name = "menuIds", description = "菜单IDS")
	private List<Long> menuIds;

	public void checkName(long count) {
		if (count > 0) {
			throw new SystemException("套餐名称已存在，请重新填写");
		}
	}

}
