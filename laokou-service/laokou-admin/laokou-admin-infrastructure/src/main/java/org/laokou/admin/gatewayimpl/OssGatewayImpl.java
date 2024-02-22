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

package org.laokou.admin.gatewayimpl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.laokou.admin.config.driver.AmazonS3StorageDriver;
import org.laokou.admin.convertor.OssConvertor;
import org.laokou.admin.domain.gateway.OssGateway;
import org.laokou.admin.domain.oss.File;
import org.laokou.admin.domain.oss.Oss;
import org.laokou.admin.gatewayimpl.database.OssLogMapper;
import org.laokou.admin.gatewayimpl.database.OssMapper;
import org.laokou.admin.gatewayimpl.database.dataobject.OssDO;
import org.laokou.admin.gatewayimpl.database.dataobject.OssLogDO;
import org.laokou.common.algorithm.template.Algorithm;
import org.laokou.common.algorithm.template.select.PollSelectAlgorithm;
import org.laokou.common.core.utils.CollectionUtil;
import org.laokou.common.core.utils.ConvertUtil;
import org.laokou.common.i18n.common.exception.SystemException;
import org.laokou.common.i18n.utils.LogUtil;
import org.laokou.common.i18n.utils.StringUtil;
import org.laokou.common.mybatisplus.utils.TransactionalUtil;
import org.laokou.common.redis.utils.RedisKeyUtil;
import org.laokou.common.redis.utils.RedisUtil;
import org.laokou.common.security.utils.UserUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.laokou.common.i18n.common.StringConstants.EMPTY;

/**
 * OSS管理.
 *
 * @author laokou
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OssGatewayImpl implements OssGateway {

	private final OssMapper ossMapper;

	private final OssLogMapper ossLogMapper;

	private final TransactionalUtil transactionalUtil;

	private final OssConvertor ossConvertor;

	private final RedisUtil redisUtil;

	/**
	 * 新增OSS.
	 * @param oss OSS对象
	 */
	@Override
	public void create(Oss oss) {
		long count = ossMapper.selectCount(Wrappers.lambdaQuery(OssDO.class).eq(OssDO::getName, oss.getName()));
		oss.checkName(count);
		OssDO ossDO = ossConvertor.toDataObject(oss);
		create(ossDO);
	}

	/**
	 * 修改OSS.
	 * @param oss OSS对象
	 */
	@Override
	public void modify(Oss oss) {
		oss.checkNullId();
		long count = ossMapper.selectCount(
				Wrappers.lambdaQuery(OssDO.class).eq(OssDO::getName, oss.getName()).ne(OssDO::getId, oss.getId()));
		oss.checkName(count);
		OssDO ossDO = ossConvertor.toDataObject(oss);
		ossDO.setVersion(ossMapper.selectVersion(ossDO.getId()));
		modify(ossDO);
	}

	/**
	 * 根据IDS删除OSS.
	 * @param ids IDS
	 */
	@Override
	public void remove(Long[] ids) {
		transactionalUtil.defaultExecuteWithoutResult(r -> {
			try {
				ossMapper.deleteBatchIds(Arrays.asList(ids));
			}
			catch (Exception e) {
				String msg = LogUtil.result(e.getMessage());
				log.error("错误信息：{}，详情见日志", msg, e);
				r.setRollbackOnly();
				throw new SystemException(msg);
			}
		});
	}

	@Override
	public File upload(MultipartFile mf) {
		File file = new File(mf);
		file.checkSize();
		file.setUrl(getUrl(file));
		return file;
	}

	private String getUrl(File file) {
		String url = Optional.ofNullable(ossLogMapper.selectOne(Wrappers.lambdaQuery(OssLogDO.class).eq(OssLogDO::getMd5, file.getMd5()).select(OssLogDO::getUrl))).orElse(new OssLogDO()).getUrl();
		if (StringUtil.isNotEmpty(url)) {
			return url;
		}
		Algorithm algorithm = new PollSelectAlgorithm();
		OssDO ossDO = algorithm.select(getOssListCache(), EMPTY);
		return new AmazonS3StorageDriver(ossConvertor.convertEntity(ossDO)).upload(file);
	}

	private List<OssDO> getOssListCache() {
		Long tenantId = UserUtil.getTenantId();
		String ossConfigKey = RedisKeyUtil.getOssConfigKey(tenantId);
		List<Object> objList = redisUtil.lGetAll(ossConfigKey);
		if (CollectionUtil.isNotEmpty(objList)) {
			return ConvertUtil.sourceToTarget(objList, OssDO.class);
		}
		else {
			List<OssDO> result = ossMapper.selectList(Wrappers.lambdaQuery(OssDO.class).eq(OssDO::getTenantId, tenantId));
			if (CollectionUtil.isEmpty(result)) {
				throw new SystemException("请配置OSS");
			}
			List<Object> objs = new ArrayList<>(result);
			redisUtil.delete(ossConfigKey);
			redisUtil.lSet(ossConfigKey, objs, RedisUtil.HOUR_ONE_EXPIRE);
			return result;
		}
	}

	/**
	 * 新增OSS.
	 * @param ossDO OSS数据模型
	 */
	private void create(OssDO ossDO) {
		transactionalUtil.defaultExecuteWithoutResult(r -> {
			try {
				ossMapper.insert(ossDO);
			}
			catch (Exception e) {
				String msg = LogUtil.result(e.getMessage());
				log.error("错误信息：{}，详情见日志", msg, e);
				r.setRollbackOnly();
				throw new SystemException(msg);
			}
		});
	}

	/**
	 * 修改OSS.
	 * @param ossDO OSS数据模型
	 */
	private void modify(OssDO ossDO) {
		transactionalUtil.defaultExecuteWithoutResult(r -> {
			try {
				ossMapper.updateById(ossDO);
			}
			catch (Exception e) {
				String msg = LogUtil.result(e.getMessage());
				log.error("错误信息：{}，详情见日志", msg, e);
				r.setRollbackOnly();
				throw new SystemException(msg);
			}
		});
	}

}
