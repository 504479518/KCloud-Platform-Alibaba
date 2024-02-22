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

package org.laokou.admin.domain.oss;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.laokou.common.i18n.common.exception.SystemException;
import org.laokou.common.i18n.dto.AggregateRoot;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author laokou
 */
@Data
@SuperBuilder
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
@Schema(name = "Oss", description = "OSS")
public class Oss extends AggregateRoot<Long> {

	@Schema(name = "name", description = "OSS名称")
	private String name;

	@Schema(name = "endpoint", description = "OSS的终端地址")
	private String endpoint;

	@Schema(name = "region", description = "OSS的区域")
	private String region;

	@Schema(name = "accessKey", description = "OSS的访问密钥")
	private String accessKey;

	@Schema(name = "secretKey", description = "OSS的用户密钥")
	private String secretKey;

	@Schema(name = "bucketName", description = "OSS的桶名")
	private String bucketName;

	@Schema(name = "pathStyleAccessEnabled", description = "路径样式访问 1已开启 0未启用")
	private Integer pathStyleAccessEnabled;

	public void checkName(long count) {
		if (count > 0) {
			throw new SystemException("OSS名称已存在，请重新填写");
		}
	}

}
