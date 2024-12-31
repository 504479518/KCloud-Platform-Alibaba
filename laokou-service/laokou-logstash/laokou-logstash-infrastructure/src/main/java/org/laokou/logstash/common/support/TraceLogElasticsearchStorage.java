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

package org.laokou.logstash.common.support;

import lombok.RequiredArgsConstructor;
import org.laokou.common.elasticsearch.template.ElasticsearchTemplate;
import org.laokou.logstash.gateway.database.dataobject.TraceLogIndex;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class TraceLogElasticsearchStorage extends AbstractTraceLogStorage {

	private final ElasticsearchTemplate elasticsearchTemplate;

	@Override
	public CompletableFuture<Void> batchSave(Map<String, Object> map) {
		return elasticsearchTemplate.asyncCreateIndex(getIndexName(), TRACE_INDEX, TraceLogIndex.class, EXECUTOR)
			.thenAcceptAsync(res -> elasticsearchTemplate.asyncBulkCreateDocument(getIndexName(), map, EXECUTOR),
					EXECUTOR);
	}

	@Override
	public CompletableFuture<Void> save(Object obj) {
		throw new UnsupportedOperationException();
	}

}
