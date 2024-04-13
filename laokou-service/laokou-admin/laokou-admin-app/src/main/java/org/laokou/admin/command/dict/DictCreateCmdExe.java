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

package org.laokou.admin.command.dict;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import org.laokou.admin.domain.dict.Dict;
import org.laokou.admin.domain.gateway.DictGateway;
import org.laokou.admin.dto.dict.DictCreateCmd;
import org.laokou.admin.dto.dict.clientobject.DictCO;
import org.laokou.common.core.utils.IdGenerator;
import org.springframework.stereotype.Component;

import static org.laokou.common.i18n.common.DatasourceConstant.TENANT;

/**
 * 新增字典执行器.
 *
 * @author laokou
 */
@Component
@RequiredArgsConstructor
public class DictCreateCmdExe {

	private final DictGateway dictGateway;

	/**
	 * 执行新增字典.
	 * @param cmd 新增字典参数
	 */
	@DS(TENANT)
	public void executeVoid(DictCreateCmd cmd) {
		dictGateway.create(convert(cmd.getDictCO()));
	}

	private Dict convert(DictCO dictCO) {
		return Dict.builder()
			.id(IdGenerator.defaultSnowflakeId())
			.value(dictCO.getValue())
			.label(dictCO.getLabel())
			.type(dictCO.getType())
			.remark(dictCO.getRemark())
			.sort(dictCO.getSort())
			.build();
	}

}
