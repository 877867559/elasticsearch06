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

package org.elasticsearch.action.search;

import org.elasticsearch.ElasticSearchIllegalArgumentException;

/**
 * Search type represent the manner at which the search operation is executed.
 *
 * @author kimchy (shay.banon)
 */
public enum SearchType {
    /**
     * Same as {@link #QUERY_THEN_FETCH}, except for an initial scatter phase which goes and computes the distributed
     * term frequencies for more accurate scoring.
     */
    DFS_QUERY_THEN_FETCH((byte) 0),
    /**
     * The query is executed against all shards, but only enough information is returned (not the document content).
     * The results are then sorted and ranked, and based on it, only the relevant shards are asked for the actual
     * document content. The return number of hits is exactly as specified in size, since they are the only ones that
     * are fetched. This is very handy when the index has a lot of shards (not replicas, shard id groups).
     */
    QUERY_THEN_FETCH((byte) 1),
    /**
     * Same as {@link #QUERY_AND_FETCH}, except for an initial scatter phase which goes and computes the distributed
     * term frequencies for more accurate scoring.
     */
    DFS_QUERY_AND_FETCH((byte) 2),
    /**
     * The most naive (and possibly fastest) implementation is to simply execute the query on all relevant shards
     * and return the results. Each shard returns size results. Since each shard already returns size hits, this
     * type actually returns size times number of shards results back to the caller.
     */
    QUERY_AND_FETCH((byte) 3);

    /**
     * The default search type ({@link #QUERY_THEN_FETCH}.
     */
    public static final SearchType DEFAULT = QUERY_THEN_FETCH;

    private byte id;

    SearchType(byte id) {
        this.id = id;
    }

    /**
     * The internal id of the type.
     */
    public byte id() {
        return this.id;
    }

    /**
     * Constructs search type based on the internal id.
     */
    public static SearchType fromId(byte id) {
        if (id == 0) {
            return DFS_QUERY_THEN_FETCH;
        } else if (id == 1) {
            return QUERY_THEN_FETCH;
        } else if (id == 2) {
            return DFS_QUERY_AND_FETCH;
        } else if (id == 3) {
            return QUERY_AND_FETCH;
        } else {
            throw new ElasticSearchIllegalArgumentException("No search type for [" + id + "]");
        }
    }
}
