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

package org.laokou.auth;

import org.junit.jupiter.api.Test;
import org.laokou.auth.gateway.MenuGateway;
import org.laokou.auth.model.UserE;

import java.util.Set;

/**
 * 菜单网关测试.
 *
 * @author laokou
 */
class MenuGatewayImplTest implements MenuGateway {

	@Test
	void test() {
		UserE user = new UserE();
		Set<String> permissions = getPermissions(user);
		assert permissions.contains("sys:user:page");
	}

	@Override
	public Set<String> getPermissions(UserE user) {
		return Set.of("sys:user:page");
	}

}
