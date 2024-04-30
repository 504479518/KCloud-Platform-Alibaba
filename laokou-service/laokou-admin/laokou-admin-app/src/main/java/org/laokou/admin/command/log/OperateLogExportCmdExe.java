/*
 * Copyright (c) 2022-2024 KCloud-Platform-IOT Author or Authors. All Rights Reserved.
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

package org.laokou.admin.command.log;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import org.laokou.admin.common.utils.ExcelUtil;
import org.laokou.admin.domain.annotation.DataFilter;
import org.laokou.admin.dto.log.OperateLogExportCmd;
import org.laokou.admin.dto.log.clientobject.OperateLogExcel;
import org.laokou.admin.gatewayimpl.database.OperateLogMapper;
import org.laokou.admin.gatewayimpl.database.dataobject.OperateLogDO;
import org.laokou.common.security.utils.UserUtil;
import org.springframework.stereotype.Component;

import java.util.Collections;

import static org.laokou.common.i18n.common.DatasourceConstant.BOOT_SYS_OPERATE_LOG;
import static org.laokou.common.i18n.common.DatasourceConstant.TENANT;

/**
 * 导出操作日志执行器.
 *
 * @author laokou
 */
@Component
@RequiredArgsConstructor
public class OperateLogExportCmdExe {

	private final OperateLogMapper operateLogMapper;

	/**
	 * 执行导出操作日志.
	 * @param cmd 导出操作日志参数
	 */
	@DS(TENANT)
	@DataFilter(tableAlias = BOOT_SYS_OPERATE_LOG)
	public void executeVoid(OperateLogExportCmd cmd) {
		ExcelUtil.doExport(Collections.emptyList(), cmd.getResponse(),
				new OperateLogDO(cmd.getModuleName(), cmd.getStatus(), UserUtil.getTenantId()), cmd, operateLogMapper,
				OperateLogExcel.class);
	}

}
