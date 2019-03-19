/*
 * Licensed to Elastic Search and Shay Banon under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. Elastic Search licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.index.query.json;

import org.elasticsearch.util.json.JsonBuilder;

import java.io.IOException;

/**
 * A query that applies a filter to the results of another query.
 *
 * @author kimchy (shay.banon)
 */
public class FilteredJsonQueryBuilder extends BaseJsonQueryBuilder {

    private final JsonQueryBuilder queryBuilder;

    private final JsonFilterBuilder filterBuilder;

    private float boost = -1;

    /**
     * A query that applies a filter to the results of another query.
     *
     * @param queryBuilder  The query to apply the filter to
     * @param filterBuilder The filter to apply on the query
     */
    public FilteredJsonQueryBuilder(JsonQueryBuilder queryBuilder, JsonFilterBuilder filterBuilder) {
        this.queryBuilder = queryBuilder;
        this.filterBuilder = filterBuilder;
    }

    /**
     * Sets the boost for this query.  Documents matching this query will (in addition to the normal
     * weightings) have their score multiplied by the boost provided.
     */
    public FilteredJsonQueryBuilder boost(float boost) {
        this.boost = boost;
        return this;
    }

    @Override protected void doJson(JsonBuilder builder, Params params) throws IOException {
        builder.startObject(FilteredJsonQueryParser.NAME);
        builder.field("query");
        queryBuilder.toJson(builder, params);
        builder.field("filter");
        filterBuilder.toJson(builder, params);
        if (boost != -1) {
            builder.field("boost", boost);
        }
        builder.endObject();
    }
}
