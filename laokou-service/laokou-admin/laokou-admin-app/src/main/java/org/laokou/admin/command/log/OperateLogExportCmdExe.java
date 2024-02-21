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

package org.laokou.admin.command.log;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import org.laokou.admin.domain.annotation.DataFilter;
import org.laokou.admin.dto.log.OperateLogExportCmd;
import org.laokou.admin.gatewayimpl.database.OperateLogMapper;
import org.laokou.admin.gatewayimpl.database.dataobject.OperateLogDO;
import org.laokou.common.core.utils.SpringContextUtil;
import org.laokou.common.security.utils.UserUtil;
import org.springframework.stereotype.Component;

import static org.laokou.common.i18n.common.DatasourceConstants.BOOT_SYS_OPERATE_LOG;
import static org.laokou.common.i18n.common.DatasourceConstants.TENANT;

/**
 * 导出操作日志执行器.
 *
 * @author laokou
 */
@Component
@RequiredArgsConstructor
public class OperateLogExportCmdExe {

	/**
	 * 执行导出操作日志.
	 * @param cmd 导出操作日志参数
	 */
	@DS(TENANT)
	@DataFilter(tableAlias = BOOT_SYS_OPERATE_LOG)
	public void executeVoid(OperateLogExportCmd cmd) {
		OperateLogMapper operateLogMapper = SpringContextUtil.getBean(OperateLogMapper.class);
		// ExcelUtil.doExport(Collections.emptyList(), cmd.getResponse(),
		// buildOperateLog(cmd), cmd, operateLogMapper,
		// OperateLogExcel.class);
	}

	/**
	 * 构建操作日志数据对象.
	 * @param cmd 导出操作日志参数
	 * @return 操作日志数据对象
	 */
	private OperateLogDO buildOperateLog(OperateLogExportCmd cmd) {
		OperateLogDO operateLogDO = new OperateLogDO();
		operateLogDO.setTenantId(UserUtil.getTenantId());
		operateLogDO.setModuleName(cmd.getModuleName());
		operateLogDO.setStatus(cmd.getStatus());
		return operateLogDO;
	}

}
