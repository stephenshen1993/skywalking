/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.skywalking.oap.server.storage.plugin.zipkin.elasticsearch;

import org.apache.skywalking.oap.server.core.CoreModule;
import org.apache.skywalking.oap.server.core.storage.query.ITraceQueryDAO;
import org.apache.skywalking.oap.server.library.module.ServiceNotProvidedException;
import org.apache.skywalking.oap.server.storage.plugin.elasticsearch.StorageModuleElasticsearchProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZipkinStorageModuleElasticsearchProvider extends StorageModuleElasticsearchProvider {

    private static final Logger logger = LoggerFactory.getLogger(ZipkinStorageModuleElasticsearchProvider.class);
    private ZipkinTraceQueryEsDAO traceQueryEsDAO;

    @Override
    public String name() {
        return "zipkin-elasticsearch";
    }

    @Override
    public void prepare() throws ServiceNotProvidedException {
        super.prepare();
        traceQueryEsDAO = new ZipkinTraceQueryEsDAO(elasticSearchClient);
        this.registerServiceImplementation(ITraceQueryDAO.class, traceQueryEsDAO);
    }

    @Override
    public void notifyAfterCompleted() {
        super.notifyAfterCompleted();
    }

    @Override
    public String[] requiredModules() {
        return new String[] {CoreModule.NAME};
    }
}
