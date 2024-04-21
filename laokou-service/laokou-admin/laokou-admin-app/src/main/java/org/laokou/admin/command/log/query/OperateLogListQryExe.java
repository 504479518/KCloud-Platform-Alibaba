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

package org.laokou.admin.command.log.query;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.laokou.admin.domain.annotation.DataFilter;
import org.laokou.admin.dto.log.OperateLogListQry;
import org.laokou.admin.dto.log.clientobject.OperateLogCO;
import org.laokou.admin.gatewayimpl.database.OperateLogMapper;
import org.laokou.admin.gatewayimpl.database.dataobject.OperateLogDO;
import org.laokou.common.i18n.dto.Datas;
import org.laokou.common.i18n.dto.PageQuery;
import org.laokou.common.i18n.dto.Result;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static org.laokou.common.i18n.common.DatasourceConstant.BOOT_SYS_OPERATE_LOG;
import static org.laokou.common.i18n.common.DatasourceConstant.TENANT;

/**
 * 查询操作日志列表执行器.
 *
 * @author laokou
 */
@Component
@RequiredArgsConstructor
public class OperateLogListQryExe {

	private final OperateLogMapper operateLogMapper;

	private final Executor executor;

	/**
	 * 执行查询操作日志列表.
	 * @param qry 查询操作日志列表参数
	 * @return 操作日志列表
	 */
	@SneakyThrows
	@DS(TENANT)
	@DataFilter(tableAlias = BOOT_SYS_OPERATE_LOG)
	public Result<Datas<OperateLogCO>> execute(OperateLogListQry qry) {
		OperateLogDO operateLogDO = convert(qry);
		PageQuery page = qry.page();
		CompletableFuture<List<OperateLogDO>> c1 = CompletableFuture
			.supplyAsync(() -> operateLogMapper.selectListByCondition(operateLogDO, page), executor);
		CompletableFuture<Long> c2 = CompletableFuture
			.supplyAsync(() -> operateLogMapper.selectObjCount(Collections.emptyList(), operateLogDO, page), executor);
		CompletableFuture.allOf(List.of(c1, c2).toArray(CompletableFuture[]::new)).join();
		return Result.ok(Datas.of(c1.get().stream().map(this::convert).toList(), c2.get()));
	}

	private OperateLogDO convert(OperateLogListQry qry) {
		OperateLogDO operateLogDO = new OperateLogDO();
		operateLogDO.setStatus(qry.getStatus());
		operateLogDO.setModuleName(qry.getModuleName());
		return operateLogDO;
	}

	private OperateLogCO convert(OperateLogDO operateLogDO) {
		return OperateLogCO.builder()
			.id(operateLogDO.getId())
			.name(operateLogDO.getName())
			.uri(operateLogDO.getUri())
			.methodName(operateLogDO.getMethodName())
			.requestType(operateLogDO.getRequestType())
			.requestParams(operateLogDO.getRequestParams())
			.userAgent(operateLogDO.getUserAgent())
			.ip(operateLogDO.getIp())
			.address(operateLogDO.getAddress())
			.status(operateLogDO.getStatus())
			.operator(operateLogDO.getOperator())
			.errorMessage(operateLogDO.getErrorMessage())
			.takeTime(operateLogDO.getTakeTime())
			.createDate(operateLogDO.getCreateDate())
			.build();
	}

}
