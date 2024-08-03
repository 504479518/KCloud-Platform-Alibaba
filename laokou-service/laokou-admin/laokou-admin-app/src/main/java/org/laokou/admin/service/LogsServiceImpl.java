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

package org.laokou.admin.service;

import lombok.RequiredArgsConstructor;
import org.laokou.admin.api.LogsServiceI;
import org.laokou.admin.command.log.LoginLogExportCmdExe;
import org.laokou.admin.command.log.OperateLogExportCmdExe;
import org.laokou.admin.command.log.query.LoginLogPageQryExe;
import org.laokou.admin.command.log.query.OperateLogListQryExe;
import org.laokou.admin.dto.log.LoginLogExportCmd;
import org.laokou.admin.dto.log.LoginLogPageQry;
import org.laokou.admin.dto.log.OperateLogExportCmd;
import org.laokou.admin.dto.log.OperateLogListQry;
import org.laokou.admin.dto.log.clientobject.LoginLogCO;
import org.laokou.admin.dto.log.clientobject.OperateLogCO;
import org.laokou.common.i18n.dto.Datas;
import org.laokou.common.i18n.dto.Result;
import org.springframework.stereotype.Service;

/**
 * 日志管理.
 *
 * @author laokou
 */
@Service
@RequiredArgsConstructor
public class LogsServiceImpl implements LogsServiceI {

	private final OperateLogListQryExe operateLogListQryExe;

	private final OperateLogExportCmdExe operateLogExportCmdExe;

	private final LoginLogPageQryExe loginLogPageQryExe;

	private final LoginLogExportCmdExe loginLogExportCmdExe;

	/**
	 * 查询操作日志列表.
	 * @param qry 查询操作日志列表参数
	 * @return 操作日志列表
	 */
	@Override
	public Result<Datas<OperateLogCO>> findOperateList(OperateLogListQry qry) {
		return operateLogListQryExe.execute(qry);
	}

	/**
	 * 导出操作日志.
	 * @param cmd 导出操作日志参数
	 */
	@Override
	public void exportOperate(OperateLogExportCmd cmd) {
		operateLogExportCmdExe.executeVoid(cmd);
	}

	/**
	 * 查询登录日志列表.
	 * @param qry 查询登录日志列表参数
	 * @return 登录日志列表
	 */
	@Override
	public Result<Datas<LoginLogCO>> pageLogin(LoginLogPageQry qry) {
		return loginLogPageQryExe.execute(qry);
	}

	/**
	 * 导出登录日志.
	 * @param cmd 导出登录日志参数
	 */
	@Override
	public void exportLogin(LoginLogExportCmd cmd) {
		loginLogExportCmdExe.executeVoid(cmd);
	}

}
