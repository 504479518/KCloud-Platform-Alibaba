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

package org.laokou.admin.command.resource;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.laokou.admin.common.utils.EventUtil;
import org.laokou.admin.dto.resource.ResourceTransferTaskCmd;
import org.laokou.common.i18n.dto.Result;
import org.springframework.stereotype.Component;

/**
 * 转办资源任务流程执行器.
 *
 * @author laokou
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ResourceTransferTaskCmdExe {

	// private final TasksFeignClient tasksFeignClient;

	private final EventUtil eventUtil;

	/**
	 * 执行转办资源任务流程.
	 * @param cmd 转办资源任务流程参数
	 * @return 执行转办结果
	 */
	@GlobalTransactional(rollbackFor = Exception.class)
	public Result<Boolean> execute(ResourceTransferTaskCmd cmd) {
		/*
		 * log.info("资源转办任务分布式事务 XID：{}", RootContext.getXID()); Result<Boolean> result =
		 * tasksFeignClient.transfer(toCmd(cmd)); // 发送消息 if (result.success()) {
		 * publishMessage(cmd); } return result;
		 */
		return null;
	}

	/**
	 * 推送转办消息.
	 * @param cmd 转办资源任务流程参数
	 */
	public void publishMessage(ResourceTransferTaskCmd cmd) {
		// domainEventPublisher.publish(eventUtil.toAuditMessageEvent(cmd.getUserId().toString(),
		// cmd.getBusinessKey(),
		// cmd.getInstanceName(), null));
	}

	/**
	 * 转换为任务转办命令请求.
	 * @param cmd 资源转办任务流程参数
	 * @return 任务转办命令请求
	 */
	/*
	 * private TaskTransferCmd toCmd(ResourceTransferTaskCmd cmd) { TaskTransferCmd c =
	 * new TaskTransferCmd(); c.setTaskId(cmd.getTaskId());
	 * c.setToUserId(cmd.getUserId()); c.setUserId(UserUtil.getUserId()); return c; }
	 */

}
