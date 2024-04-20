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

package org.laokou.admin.gatewayimpl.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.laokou.common.mybatisplus.mapper.BaseDO;

/**
 * @author laokou
 */
@Data
@TableName("boot_sys_resource_audit")
@Schema(name = "ResourceAuditDO", description = "资源审批")
public class ResourceAuditDO extends BaseDO {

	@Schema(name = "resourceId", description = "资源ID")
	private Long resourceId;

	@Schema(name = "title", description = "资源名称")
	private String title;

	@Schema(name = "url", description = "资源的URL")
	private String url;

	@Schema(name = "code", description = "资源类型 audio音频 video视频  image图片")
	private String code;

	@Schema(name = "remark", description = "资源备注")
	private String remark;

}
