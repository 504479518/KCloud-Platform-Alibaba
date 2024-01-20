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

package org.laokou.admin.api;

import org.laokou.admin.dto.common.clientobject.OptionCO;
import org.laokou.admin.dto.source.clientobject.SourceCO;
import org.laokou.admin.dto.source.*;
import org.laokou.common.i18n.dto.Datas;
import org.laokou.common.i18n.dto.Result;

import java.util.List;

/**
 * 数据源管理.
 * @author laokou
 */
public interface SourcesServiceI {

	/**
	 * 新增数据源
	 * @param cmd 新增数据源参数
	 * @return 新增结果
	 */
	Result<Boolean> insert(SourceInsertCmd cmd);

	/**
	 * 修改数据源
	 * @param cmd 修改数据源参数
	 * @return 修改结果
	 */
	Result<Boolean> update(SourceUpdateCmd cmd);

	/**
	 * 根据ID删除数据源
	 * @param cmd 根据ID删除数据源参数
	 * @return 删除结果
	 */
	Result<Boolean> deleteById(SourceDeleteCmd cmd);

	/**
	 * 根据ID查看数据源
	 * @param qry 根据ID查看数据源参数
	 * @return 数据源
	 */
	Result<SourceCO> getById(SourceGetQry qry);

	/**
	 * 查询数据源列表
	 * @param qry 查询数据源列表参数
	 * @return 数据源列表
	 */
	Result<Datas<SourceCO>> list(SourceListQry qry);

	/**
	 * 查询数据源下拉框选择项列表
	 * @param qry 查询数据源下拉框选择项列表参数
	 * @return 数据源下拉框选择项列表
	 */
	Result<List<OptionCO>> optionList(SourceOptionListQry qry);

}
