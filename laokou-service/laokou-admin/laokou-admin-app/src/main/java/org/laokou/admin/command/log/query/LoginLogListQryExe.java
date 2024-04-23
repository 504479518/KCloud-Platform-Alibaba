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
import org.laokou.admin.dto.log.LoginLogListQry;
import org.laokou.admin.dto.log.clientobject.LoginLogCO;
import org.laokou.admin.gatewayimpl.database.LoginLogMapper;
import org.laokou.admin.gatewayimpl.database.dataobject.LoginLogDO;
import org.laokou.common.i18n.dto.Datas;
import org.laokou.common.i18n.dto.PageQuery;
import org.laokou.common.i18n.dto.Result;
import org.laokou.common.mybatisplus.template.TableTemplate;
import org.laokou.common.security.utils.UserUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static org.laokou.common.i18n.common.DatasourceConstant.BOOT_SYS_LOGIN_LOG;
import static org.laokou.common.i18n.common.DatasourceConstant.TENANT;

/**
 * 查询登录日志列表执行器.
 *
 * @author laokou
 */
@Component
@RequiredArgsConstructor
public class LoginLogListQryExe {

	private final LoginLogMapper loginLogMapper;

	private final Executor executor;

	/**
	 * 执行查询登录日志列表.
	 * @param qry 查询登录日志列表参数
	 * @return 登录日志列表
	 */
	@DS(TENANT)
	@SneakyThrows
	@DataFilter(tableAlias = BOOT_SYS_LOGIN_LOG)
	public Result<Datas<LoginLogCO>> execute(LoginLogListQry qry) {
		LoginLogDO loginLogDO = convert(qry);
		PageQuery page = qry.time().page().ignore();
		List<String> dynamicTables = TableTemplate.getDynamicTables(qry.getStartTime(), qry.getEndTime(),
				BOOT_SYS_LOGIN_LOG);
		CompletableFuture<List<LoginLogDO>> c1 = CompletableFuture
			.supplyAsync(() -> loginLogMapper.selectListByCondition(dynamicTables, loginLogDO, page), executor);
		CompletableFuture<Long> c2 = CompletableFuture
			.supplyAsync(() -> loginLogMapper.selectObjCount(dynamicTables, loginLogDO, page), executor);
		CompletableFuture.allOf(List.of(c1, c2).toArray(CompletableFuture[]::new)).join();
		return Result.ok(Datas.of(c1.get().stream().map(this::convert).toList(), c2.get()));
	}

	private LoginLogDO convert(LoginLogListQry qry) {
		LoginLogDO loginLogDO = new LoginLogDO();
		loginLogDO.setUsername(qry.getUsername());
		loginLogDO.setTenantId(UserUtil.getTenantId());
		loginLogDO.setStatus(qry.getStatus());
		return loginLogDO;
	}

	private LoginLogCO convert(LoginLogDO loginLogDO) {
		return LoginLogCO.builder()
			.id(loginLogDO.getId())
			.createDate(loginLogDO.getCreateDate())
			.username(loginLogDO.getUsername())
			.ip(loginLogDO.getIp())
			.address(loginLogDO.getAddress())
			.browser(loginLogDO.getBrowser())
			.os(loginLogDO.getOs())
			.status(loginLogDO.getStatus())
			.type(loginLogDO.getType())
			.message(loginLogDO.getMessage())
			.build();
	}

}
