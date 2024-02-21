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

package org.laokou.admin.command.tenant;

import lombok.RequiredArgsConstructor;
import org.laokou.admin.domain.gateway.TenantGateway;
import org.laokou.admin.domain.tenant.Tenant;
import org.laokou.admin.dto.tenant.TenantCreateCmd;
import org.laokou.admin.dto.tenant.clientobject.TenantCO;
import org.laokou.common.core.utils.IdGenerator;
import org.springframework.stereotype.Component;

/**
 * 新增租户执行器.
 *
 * @author laokou
 */
@Component
@RequiredArgsConstructor
public class TenantCreateCmdExe {

	private final TenantGateway tenantGateway;

	/**
	 * 执行新增租户.
	 * @param cmd 新增租户参数
	 */
	public void executeVoid(TenantCreateCmd cmd) {
		tenantGateway.create(convert(cmd.getTenantCO()));
	}

	private Tenant convert(TenantCO tenantCO) {
		return Tenant.builder()
			.id(IdGenerator.defaultSnowflakeId())
			.name(tenantCO.getName())
			.label(tenantCO.getLabel())
			.packageId(tenantCO.getPackageId())
			.sourceId(tenantCO.getSourceId())
			.build();
	}

}
