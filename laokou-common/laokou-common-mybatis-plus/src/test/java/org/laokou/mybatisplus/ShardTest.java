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

package org.laokou.mybatisplus;

import lombok.extern.slf4j.Slf4j;
import org.laokou.common.i18n.utils.DateUtil;

import java.time.LocalDate;

/**
 * @author laokou
 */
@Slf4j
public class ShardTest {

	public static void main(String[] args) {
		String tablePrefix = "boot_sys_login_log_";
		LocalDate localDate = LocalDate.of(2022, 1, 1);
		for (int i = 0; i < 936; i++) {
			LocalDate now = DateUtil.plusMonths(localDate, i);
			String table = tablePrefix + DateUtil.format(now, DateUtil.YYYYMM);
			System.out.printf("alter table %s add column `event_id` bigint(21) comment '事件ID';%n", table);
			System.out.printf("alter table %s add unique idx_event_id(`event_id`) comment '事件ID_唯一索引';%n", table);
		}
	}

}
