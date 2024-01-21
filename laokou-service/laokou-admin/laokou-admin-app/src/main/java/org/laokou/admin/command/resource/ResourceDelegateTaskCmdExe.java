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

package org.laokou.admin.command.resource;

import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.laokou.admin.common.event.DomainEventPublisher;
import org.laokou.admin.common.utils.EventUtil;
import org.laokou.admin.dto.resource.ResourceDelegateTaskCmd;
import org.laokou.admin.dto.resource.TaskDelegateCmd;
import org.laokou.admin.gatewayimpl.rpc.TasksFeignClient;
import org.laokou.common.i18n.dto.Result;
import org.laokou.common.security.utils.UserUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import static org.laokou.common.i18n.common.SysConstants.THREAD_POOL_TASK_EXECUTOR_NAME;

/**
 * 委派资源任务流程执行器.
 *
 * @author laokou
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ResourceDelegateTaskCmdExe {

	private final TasksFeignClient tasksFeignClient;

	private final DomainEventPublisher domainEventPublisher;

	private final EventUtil eventUtil;

	/**
	 * 执行委派资源任务流程.
	 * @param cmd 委派资源任务流程参数
	 * @return 执行委派结果
	 */
	@GlobalTransactional(rollbackFor = Exception.class)
	public Result<Boolean> execute(ResourceDelegateTaskCmd cmd) {
		log.info("资源委派任务分布式事务 XID：{}", RootContext.getXID());
		Result<Boolean> result = tasksFeignClient.delegate(toCmd(cmd));
		// 发送消息
		if (result.success()) {
			publishMessage(cmd);
		}
		return result;
	}

	/**
	 * 推送委派消息.
	 * @param cmd 委派资源任务流程参数
	 */
	@Async(THREAD_POOL_TASK_EXECUTOR_NAME)
	public void publishMessage(ResourceDelegateTaskCmd cmd) {
		domainEventPublisher.publish(eventUtil.toHandleMessageEvent(cmd.getUserId().toString(), cmd.getBusinessKey(),
				cmd.getInstanceName(), null));
	}

	/**
	 * 转换为委派任务流程命令请求.
	 * @param cmd 委派资源任务流程参数
	 * @return 委派任务流程命令请求
	 */
	private TaskDelegateCmd toCmd(ResourceDelegateTaskCmd cmd) {
		TaskDelegateCmd c = new TaskDelegateCmd();
		c.setTaskId(cmd.getTaskId());
		c.setUserId(UserUtil.getUserId());
		c.setToUserId(cmd.getUserId());
		return c;
	}

}
