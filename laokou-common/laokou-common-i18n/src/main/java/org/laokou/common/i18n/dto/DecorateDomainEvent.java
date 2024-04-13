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

package org.laokou.common.i18n.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.laokou.common.i18n.common.EventStatusEnum;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author laokou
 */
@Data
@SuperBuilder
@NoArgsConstructor(access = PRIVATE)
@Schema(name = "DecorateDomainEvent", description = "装饰领域事件")
public class DecorateDomainEvent extends DomainEvent<Long> {

	public DecorateDomainEvent(Long id, EventStatusEnum eventStatus, String sourceName) {
		super(id, eventStatus, sourceName);
	}

}
