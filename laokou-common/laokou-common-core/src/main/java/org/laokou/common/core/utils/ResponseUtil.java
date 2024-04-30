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

package org.laokou.common.core.utils;

import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * 响应工具类.
 *
 * @author laokou
 */
public class ResponseUtil {

	/**
	 * 响应给视图解析.
	 * @param response 响应对象
	 * @param obj 对象
	 */
	@SneakyThrows
	public static void response(HttpServletResponse response, Object obj) {
		response.setStatus(HttpStatus.OK.value());
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
		try (PrintWriter writer = response.getWriter()) {
			writer.write(JacksonUtil.toJsonStr(obj));
			writer.flush();
		}
	}

}
