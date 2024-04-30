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

package org.laokou.admin.domain.gateway;

import io.swagger.v3.oas.annotations.media.Schema;
import org.laokou.admin.domain.oss.File;
import org.laokou.admin.domain.oss.Oss;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author laokou
 */
@Schema(name = "OssGateway", description = "OSS网关")
public interface OssGateway {

	/**
	 * 新增OSS.
	 * @param oss OSS对象
	 */
	void create(Oss oss);

	/**
	 * 修改OSS.
	 * @param oss OSS对象
	 */
	void modify(Oss oss);

	/**
	 * 根据IDS删除OSS.
	 * @param ids IDS
	 */
	void remove(Long[] ids);

	File upload(MultipartFile mf);

}
