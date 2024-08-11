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

package org.laokou.generator;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.laokou.common.core.utils.JacksonUtil;
import org.laokou.common.core.utils.TemplateUtil;
import org.laokou.common.i18n.utils.ResourceUtil;
import org.laokou.generator.domain.TableE;
import org.laokou.generator.domain.TableV;
import org.laokou.generator.repository.TableColumnDO;
import org.laokou.generator.repository.TableDO;
import org.laokou.generator.repository.TableMapper;
import org.laokou.generator.service.TableService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author laokou
 */
@Slf4j
@SpringBootTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class TableTest {

	private final TableMapper tableMapper;

	private final TableService tableService;

	@Test
	void testTable() {
		List<TableDO> tables = tableMapper.selectObjs(Set.of("boot_sys_user"));
		log.info("获取表：{}", JacksonUtil.toJsonStr(tables));
	}

	@Test
	void testTableColumn() {
		List<TableColumnDO> tableColumns = tableMapper.selectColumns(Set.of("boot_sys_user"));
		log.info("获取字段：{}", JacksonUtil.toJsonStr(tableColumns));
	}

	@Test
	void testClass() {
		TableE tableE = new TableE(Set.of("boot_sys_menu"), "boot_sys_");
		TableV tableV = tableService.list(tableE).getFirst();
		String str = JacksonUtil.toJsonStr(tableV);
		log.info("{}", str);
		Map<String, Object> map = JacksonUtil.toMap(tableV, String.class, Object.class);
		map.put("packageName", "org.laokou");
		map.put("moduleName", "iot");
		map.put("author", "laokou");
		log.info("{}", getContent(map));
	}

	@SneakyThrows
	private String getContent(Map<String, Object> map) {
		try (InputStream inputStream = ResourceUtil.getResource("templates/do.ftl").getInputStream()) {
			byte[] bytes = inputStream.readAllBytes();
			String template = new String(bytes, StandardCharsets.UTF_8);
			return TemplateUtil.getContent(template, map);
		}
	}

}
