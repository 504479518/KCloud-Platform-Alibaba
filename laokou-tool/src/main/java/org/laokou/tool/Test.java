/*
 * Copyright (c) 2022-2025 KCloud-Platform-IoT Author or Authors. All Rights Reserved.
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

package org.laokou.tool;

import org.laokou.common.core.utils.FileUtil;
import org.laokou.common.i18n.utils.ObjectUtil;
import org.laokou.common.i18n.utils.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;

import static org.laokou.common.i18n.common.constant.StringConstant.UNDER;

public class Test {

	public static void main(String[] args) throws IOException {
		String str = "C:\\Users\\aixot\\Desktop\\1.txt";
		try (BufferedReader reader = FileUtil.newBufferedReader(str)) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] s = line.trim().split("\\s+");
				String s1 = s[0];
				String s3 = s[3];
				String s4 = s[4];
				System.out.println("// " + s3);
				System.out.println(s1.substring(0,1).toUpperCase() + s1.substring(1) + " " + getStr(s4) + " `json:\""+s1+"\"`");
				System.out.println();
			}
		}
	}

	private static String getStr(String str) {
		if (ObjectUtil.equals("varchar", str) || ObjectUtil.equals(str, "String") || ObjectUtil.equals("mediumtext", str)) {
			return "string";
		}
		if (ObjectUtil.equals("datetime", str) || ObjectUtil.equals("date", str)) {
			return "time.Time";
		}
		if (ObjectUtil.equals("Bigint", str)) {
			return "int64";
		}
		if (ObjectUtil.equals("decimal", str)) {
			return "float64";
		}
		if (ObjectUtil.equals("int", str)) {
			return "int";
		}
		return "";
	}

}
