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

package org.laokou.admin.dto.dict.clientobject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.laokou.common.i18n.dto.ClientObject;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author laokou
 */
@Data
@Builder
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
@Schema(name = "DictCO", description = "字典")
public class DictCO extends ClientObject {

	@Schema(name = "id", description = "ID")
	private Long id;

	@Schema(name = "label", description = "字典标签")
	private String label;

	@Schema(name = "type", description = "字典类型")
	private String type;

	@Schema(name = "value", description = "字典值")
	private String value;

	@Schema(name = "remark", description = "字典备注")
	private String remark;

	@Schema(name = "createDate", description = "创建时间")
	private LocalDateTime createDate;

	@Schema(name = "sort", description = "字典排序")
	private Integer sort;

}
