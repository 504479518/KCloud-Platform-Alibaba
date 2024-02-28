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

import lombok.extern.slf4j.Slf4j;
import org.laokou.admin.domain.action.ResourceAction;
import org.springframework.stereotype.Component;

/**
 * @author laokou
 */
@Slf4j
@Component("resourceAction")
public class ResourceActionImpl implements ResourceAction {

	@Override
	public boolean modify(Long businessKey, Integer status) {
		log.info(">>>>>>>>>>>>>>>状态:{}", status);
		return true;
	}

	@Override
	public boolean compensateModify(Long businessKey, Integer status) {
		return true;
	}

}
