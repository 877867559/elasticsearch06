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

package org.elasticsearch.index.cache;

import com.google.inject.Inject;
import org.elasticsearch.index.AbstractIndexComponent;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.cache.filter.FilterCache;
import org.elasticsearch.index.cache.filter.none.NoneFilterCache;
import org.elasticsearch.index.settings.IndexSettings;
import org.elasticsearch.util.settings.Settings;

import static org.elasticsearch.util.settings.ImmutableSettings.Builder.*;

/**
 * @author kimchy (shay.banon)
 */
public class IndexCache extends AbstractIndexComponent {

    private final FilterCache filterCache;

    public IndexCache(Index index) {
        this(index, EMPTY_SETTINGS, new NoneFilterCache(index, EMPTY_SETTINGS));
    }

    @Inject public IndexCache(Index index, @IndexSettings Settings indexSettings, FilterCache filterCache) {
        super(index, indexSettings);
        this.filterCache = filterCache;
    }

    public FilterCache filter() {
        return filterCache;
    }
}
