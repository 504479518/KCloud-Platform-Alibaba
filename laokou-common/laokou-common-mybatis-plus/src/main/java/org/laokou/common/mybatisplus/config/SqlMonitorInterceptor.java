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

package org.laokou.common.mybatisplus.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.jdbc.PreparedStatementLogger;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.laokou.common.core.utils.IdGenerator;
import org.laokou.common.core.utils.SpringContextUtil;
import org.laokou.common.i18n.utils.DateUtil;
import org.laokou.common.i18n.utils.StringUtil;
import org.laokou.common.mybatisplus.handler.event.SqlLogEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static org.laokou.common.i18n.common.constants.StringConstant.EMPTY;
import static org.laokou.common.i18n.common.constants.StringConstant.SPACE;

/**
 * @see PreparedStatementLogger
 * @author laokou
 */
@Slf4j
@Intercepts({
		@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class SqlMonitorInterceptor implements Interceptor {

	private Properties properties;

	@Override
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		long start = IdGenerator.SystemClock.now();
		Object obj = invocation.proceed();
		long time = (IdGenerator.SystemClock.now() - start);
		Object target = invocation.getTarget();
		if (target instanceof StatementHandler statementHandler) {
			String sql = getSql(invocation, statementHandler);
			SpringContextUtil.publishEvent(new SqlLogEvent("SQL日志", getAppName(), sql, time, DateUtil.now()));
			sql = StringUtil.isNotEmpty(sql)
					? String.format("Consume Time：%s ms \nExecute SQL：%s\n", time, sql.replaceAll("\\s+", SPACE))
					: EMPTY;
			log.info("\n{}", sql);
		}
		return obj;
	}

	private String getSql(Invocation invocation, StatementHandler statementHandler) throws SQLException {
		Connection connection = (Connection) invocation.getArgs()[0];
		PreparedStatement preparedStatement = connection.prepareStatement(statementHandler.getBoundSql().getSql());
		statementHandler.getParameterHandler().setParameters(preparedStatement);
		String str = preparedStatement.toString();
		int index = str.indexOf("wrapping");
		System.out.println(str.substring(index + 11));
		return "";
	}

	private String getAppName() {
		return properties.getProperty("appName");
	}

	private long getMillis() {
		return Long.parseLong(properties.getProperty("millis"));
	}

}
