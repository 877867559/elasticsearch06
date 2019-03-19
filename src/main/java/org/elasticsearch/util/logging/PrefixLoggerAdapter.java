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

package org.elasticsearch.util.logging;

import org.slf4j.Logger;
import org.slf4j.Marker;

/**
 * A Logger that wraps another logger and adds the provided prefix to every log
 * message.
 *
 * @author kimchy (Shay Banon)
 */
// TODO is there a way to do this without String concatenation?
public class PrefixLoggerAdapter implements Logger {

    private final String prefix;

    private final Logger logger;

    public PrefixLoggerAdapter(String prefix, Logger logger) {
        this.prefix = prefix;
        this.logger = logger;
    }

    public String prefix() {
        return this.prefix;
    }

    private String wrap(String s) {
        return prefix + s;
    }

    public String getName() {
        return logger.getName();
    }

    public final boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    public final void trace(String s) {
        logger.trace(wrap(s));
    }

    public final void trace(String s, Object o) {
        logger.trace(wrap(s), o);
    }

    public final void trace(String s, Object o, Object o1) {
        logger.trace(wrap(s), o, o1);
    }

    public final void trace(String s, Object[] objects) {
        logger.trace(wrap(s), objects);
    }

    public final void trace(String s, Throwable throwable) {
        logger.trace(wrap(s), throwable);
    }

    public final boolean isTraceEnabled(Marker marker) {
        return logger.isTraceEnabled(marker);
    }

    public final void trace(Marker marker, String s) {
        logger.trace(marker, wrap(s));
    }

    public final void trace(Marker marker, String s, Object o) {
        logger.trace(marker, wrap(s), o);
    }

    public final void trace(Marker marker, String s, Object o, Object o1) {
        logger.trace(marker, wrap(s), o, o1);
    }

    public final void trace(Marker marker, String s, Object[] objects) {
        logger.trace(marker, wrap(s), objects);
    }

    public final void trace(Marker marker, String s, Throwable throwable) {
        logger.trace(marker, wrap(s), throwable);
    }

    public final boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    public final void debug(String s) {
        logger.debug(wrap(s));
    }

    public final void debug(String s, Object o) {
        logger.debug(wrap(s), o);
    }

    public final void debug(String s, Object o, Object o1) {
        logger.debug(wrap(s), o, o1);
    }

    public final void debug(String s, Object[] objects) {
        logger.debug(wrap(s), objects);
    }

    public final void debug(String s, Throwable throwable) {
        logger.debug(wrap(s), throwable);
    }

    public final boolean isDebugEnabled(Marker marker) {
        return logger.isDebugEnabled(marker);
    }

    public final void debug(Marker marker, String s) {
        logger.debug(marker, wrap(s));
    }

    public final void debug(Marker marker, String s, Object o) {
        logger.debug(marker, wrap(s), o);
    }

    public final void debug(Marker marker, String s, Object o, Object o1) {
        logger.debug(marker, wrap(s), o, o1);
    }

    public final void debug(Marker marker, String s, Object[] objects) {
        logger.debug(marker, wrap(s), objects);
    }

    public final void debug(Marker marker, String s, Throwable throwable) {
        logger.debug(marker, wrap(s), throwable);
    }

    public final boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    public final void info(String s) {
        logger.info(wrap(s));
    }

    public final void info(String s, Object o) {
        logger.info(wrap(s), o);
    }

    public final void info(String s, Object o, Object o1) {
        logger.info(wrap(s), o, o1);
    }

    public final void info(String s, Object[] objects) {
        logger.info(wrap(s), objects);
    }

    public final void info(String s, Throwable throwable) {
        logger.info(wrap(s), throwable);
    }

    public final boolean isInfoEnabled(Marker marker) {
        return logger.isInfoEnabled(marker);
    }

    public final void info(Marker marker, String s) {
        logger.info(marker, wrap(s));
    }

    public final void info(Marker marker, String s, Object o) {
        logger.info(marker, wrap(s), o);
    }

    public final void info(Marker marker, String s, Object o, Object o1) {
        logger.info(marker, wrap(s), o, o1);
    }

    public final void info(Marker marker, String s, Object[] objects) {
        logger.info(marker, wrap(s), objects);
    }

    public final void info(Marker marker, String s, Throwable throwable) {
        logger.info(marker, wrap(s), throwable);
    }

    public final boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    public final void warn(String s) {
        logger.warn(wrap(s));
    }

    public final void warn(String s, Object o) {
        logger.warn(wrap(s), o);
    }

    public final void warn(String s, Object[] objects) {
        logger.warn(wrap(s), objects);
    }

    public final void warn(String s, Object o, Object o1) {
        logger.warn(wrap(s), o, o1);
    }

    public final void warn(String s, Throwable throwable) {
        logger.warn(wrap(s), throwable);
    }

    public final boolean isWarnEnabled(Marker marker) {
        return logger.isWarnEnabled(marker);
    }

    public final void warn(Marker marker, String s) {
        logger.warn(marker, wrap(s));
    }

    public final void warn(Marker marker, String s, Object o) {
        logger.warn(marker, wrap(s), o);
    }

    public final void warn(Marker marker, String s, Object o, Object o1) {
        logger.warn(marker, wrap(s), o, o1);
    }

    public final void warn(Marker marker, String s, Object[] objects) {
        logger.warn(marker, wrap(s), objects);
    }

    public final void warn(Marker marker, String s, Throwable throwable) {
        logger.warn(marker, wrap(s), throwable);
    }

    public final boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    public final void error(String s) {
        logger.error(wrap(s));
    }

    public final void error(String s, Object o) {
        logger.error(wrap(s), o);
    }

    public final void error(String s, Object o, Object o1) {
        logger.error(wrap(s), o, o1);
    }

    public final void error(String s, Object[] objects) {
        logger.error(wrap(s), objects);
    }

    public final void error(String s, Throwable throwable) {
        logger.error(wrap(s), throwable);
    }

    public final boolean isErrorEnabled(Marker marker) {
        return logger.isErrorEnabled(marker);
    }

    public final void error(Marker marker, String s) {
        logger.error(marker, wrap(s));
    }

    public final void error(Marker marker, String s, Object o) {
        logger.error(marker, wrap(s), o);
    }

    public final void error(Marker marker, String s, Object o, Object o1) {
        logger.error(marker, wrap(s), o, o1);
    }

    public final void error(Marker marker, String s, Object[] objects) {
        logger.error(marker, wrap(s), objects);
    }

    public final void error(Marker marker, String s, Throwable throwable) {
        logger.error(marker, wrap(s), throwable);
    }
}
