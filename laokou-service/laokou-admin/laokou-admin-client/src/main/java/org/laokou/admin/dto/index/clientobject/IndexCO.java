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

package org.laokou.admin.dto.index.clientobject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.laokou.common.i18n.dto.ClientObject;

/**
 * @author laokou
 */
@Data
@Schema(name = "IndexCO", description = "索引")
public class IndexCO extends ClientObject {

	@Schema(name = "indexName", description = "索引名称")
	private String indexName;

	@Schema(name = "indexAlias", description = "索引别名")
	private String indexAlias;

	public IndexCO(String indexName, String indexAlias) {
		this.indexName = indexName;
		this.indexAlias = indexAlias;
	}

}
