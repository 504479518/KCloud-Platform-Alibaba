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

package org.laokou.admin.dto.index;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.laokou.common.i18n.dto.CommonCommand;

/**
 * @author laokou
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "IndexGetQry", description = "查看索引命令请求")
public class IndexGetQry extends CommonCommand {

	@Schema(name = "indexName", description = "索引名称")
	private String indexName;

}
