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

package org.laokou.admin.service;

import lombok.RequiredArgsConstructor;
import org.laokou.admin.api.MessagesServiceI;
import org.laokou.admin.dto.message.*;
import org.laokou.admin.dto.message.clientobject.MessageCO;
import org.laokou.admin.command.message.MessageCreateCmdExe;
import org.laokou.admin.command.message.MessageReadCmdExe;
import org.laokou.admin.command.message.query.MessageGetQryExe;
import org.laokou.admin.command.message.query.MessageListQryExe;
import org.laokou.admin.command.message.query.MessageUnreadCountGetQryExe;
import org.laokou.admin.command.message.query.MessageUnreadListQryExe;
import org.laokou.common.i18n.dto.Datas;
import org.laokou.common.i18n.dto.Result;
import org.springframework.stereotype.Service;

/**
 * 消息管理.
 *
 * @author laokou
 */
@Service
@RequiredArgsConstructor
public class MessagesServiceImpl implements MessagesServiceI {

	private final MessageCreateCmdExe messageCreateCmdExe;

	private final MessageListQryExe messageListQryExe;

	private final MessageReadCmdExe messageReadCmdExe;

	private final MessageGetQryExe messageGetQryExe;

	private final MessageUnreadListQryExe messageUnreadListQryExe;

	private final MessageUnreadCountGetQryExe messageUnreadCountGetQryExe;

	/**
	 * 新增消息.
	 * @param cmd 新增消息参数
	 */
	@Override
	public void create(MessageCreateCmd cmd) {
		messageCreateCmdExe.executeVoid(cmd);
	}

	/**
	 * 查询消息列表.
	 * @param qry 查询消息列表参数
	 * @return 消息列表
	 */
	@Override
	public Result<Datas<MessageCO>> findList(MessageListQry qry) {
		return messageListQryExe.execute(qry);
	}

	/**
	 * 读取消息.
	 * @param cmd 读取消息参数
	 * @return 读取结果
	 */
	@Override
	public Result<MessageCO> read(MessageReadCmd cmd) {
		return messageReadCmdExe.execute(cmd);
	}

	/**
	 * 根据ID查看消息.
	 * @param qry 根据ID查看消息参数
	 * @return 消息
	 */
	@Override
	public Result<MessageCO> findById(MessageGetQry qry) {
		return messageGetQryExe.execute(qry);
	}

	/**
	 * 查询未读消息列表.
	 * @param qry 查询未读消息列表参数
	 * @return 未读消息列表
	 */
	@Override
	public Result<Datas<MessageCO>> findUnreadList(MessageUnreadListQry qry) {
		return messageUnreadListQryExe.execute(qry);
	}

	/**
	 * 查看未读消息数.
	 * @return 未读消息数
	 */
	@Override
	public Result<Integer> findUnreadCount() {
		return messageUnreadCountGetQryExe.execute();
	}

}
