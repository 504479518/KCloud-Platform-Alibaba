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

package org.laokou.admin.actionimpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.laokou.admin.domain.action.ResourceAuditAction;
import org.laokou.admin.gatewayimpl.database.ResourceAuditMapper;
import org.laokou.admin.gatewayimpl.database.dataobject.ResourceAuditDO;
import org.laokou.common.i18n.utils.DateUtil;
import org.springframework.stereotype.Component;

/**
 * @author laokou
 */
@Slf4j
@Component("resourceAuditAction")
@RequiredArgsConstructor
public class ResourceAuditActionImpl implements ResourceAuditAction {

	private final ResourceAuditMapper resourceAuditMapper;

	@Override
	public boolean create(Long businessKey, String title, String remark, String code, String url, Long resourceId,
			Long userId, Long tenantId, Long deptId, String deptPath) {
		log.info("{}", convert(businessKey, title, remark, code, url, resourceId, userId, tenantId, deptId, deptPath));
		// resourceAuditMapper.insertOne(convert(businessKey,title,remark,code,url,resourceId,userId,tenantId,deptId,deptPath));
		return true;
	}

	@Override
	public boolean compensateCreate(Long businessKey) {
		resourceAuditMapper.deleteOneById(businessKey);
		return true;
	}

	private ResourceAuditDO convert(Long businessKey, String title, String remark, String code, String url,
			Long resourceId, Long userId, Long tenantId, Long deptId, String deptPath) {
		ResourceAuditDO resourceAuditDO = new ResourceAuditDO();
		resourceAuditDO.setId(businessKey);
		resourceAuditDO.setResourceId(resourceId);
		resourceAuditDO.setTitle(title);
		resourceAuditDO.setRemark(remark);
		resourceAuditDO.setCode(code);
		resourceAuditDO.setUrl(url);
		resourceAuditDO.setCreateDate(DateUtil.now());
		resourceAuditDO.setUpdateDate(DateUtil.now());
		resourceAuditDO.setTenantId(tenantId);
		resourceAuditDO.setCreator(userId);
		resourceAuditDO.setEditor(userId);
		resourceAuditDO.setDeptId(deptId);
		resourceAuditDO.setDeptPath(deptPath);
		return resourceAuditDO;
	}

}
