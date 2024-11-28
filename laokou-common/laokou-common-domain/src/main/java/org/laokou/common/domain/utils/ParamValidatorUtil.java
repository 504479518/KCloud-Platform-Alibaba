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

package org.laokou.common.domain.utils;

import org.laokou.common.core.utils.RegexUtil;
import org.laokou.common.i18n.common.exception.ParamException;
import org.laokou.common.i18n.utils.StringUtil;
import org.laokou.common.i18n.utils.ValidatorUtil;

/**
 * @author laokou
 */
public final class ParamValidatorUtil {

	public static void validateNotEmpty(String value, String errorCode) {
		if (StringUtil.isEmpty(value)) {
			throw new ParamException(errorCode, ValidatorUtil.getMessage(errorCode));
		}
	}

	public static void validateRegex(RegexUtil.Type type, String value, String errorCode) {
		if (validateRegex(type, value)) {
			throw new ParamException(errorCode, ValidatorUtil.getMessage(errorCode));
		}
	}

	public static boolean validateRegex(RegexUtil.Type type, String value) {
		return switch (type) {
			case MAIL -> !RegexUtil.mailRegex(value);
			case MOBILE -> !RegexUtil.mobileRegex(value);
		};
	}

}
