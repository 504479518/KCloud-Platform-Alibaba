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

package org.laokou.gateway.ip;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import static org.laokou.common.i18n.common.PropertiesConstants.IP_PREFIX;

/**
 * IP配置.
 *
 * @author laokou
 */
@Data
@Component
@ConfigurationProperties(prefix = IP_PREFIX)
public class IpProperties {

	/**
	 * 标签，默认黑名单.
	 */
	private String label = Label.BLACK.getValue();

	/**
	 * 黑/白名单开关，默认不开启.
	 */
	private boolean enabled = false;

}
