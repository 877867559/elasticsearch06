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

package org.elasticsearch.transport.local;

import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.threadpool.scaling.ScalingThreadPool;
import org.elasticsearch.transport.*;
import org.elasticsearch.util.io.stream.StreamInput;
import org.elasticsearch.util.io.stream.StreamOutput;
import org.elasticsearch.util.io.stream.Streamable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SimpleLocalTransportTests {

    private ThreadPool threadPool;

    private TransportService serviceA;
    private TransportService serviceB;
    private DiscoveryNode serviceANode;
    private DiscoveryNode serviceBNode;

    @Before
    public void setUp() {
        threadPool = new ScalingThreadPool();

        serviceA = new TransportService(new LocalTransport(threadPool), threadPool).start();
        serviceANode = new DiscoveryNode("A", serviceA.boundAddress().publishAddress());

        serviceB = new TransportService(new LocalTransport(threadPool), threadPool).start();
        serviceBNode = new DiscoveryNode("B", serviceB.boundAddress().publishAddress());
    }

    @After
    public void tearDown() {
        serviceA.close();
        serviceB.close();

        threadPool.shutdown();
    }

    @Test public void testHelloWorld() {
        serviceA.registerHandler("sayHello", new BaseTransportRequestHandler<StringMessage>() {
            @Override public StringMessage newInstance() {
                return new StringMessage();
            }

            @Override public void messageReceived(StringMessage request, TransportChannel channel) {
                System.out.println("got message: " + request.message);
                assertThat("moshe", equalTo(request.message));
                try {
                    channel.sendResponse(new StringMessage("hello " + request.message));
                } catch (IOException e) {
                    e.printStackTrace();
                    assertThat(e.getMessage(), false, equalTo(true));
                }
            }
        });

        TransportFuture<StringMessage> res = serviceB.submitRequest(serviceANode, "sayHello",
                new StringMessage("moshe"), new BaseTransportResponseHandler<StringMessage>() {
                    @Override public StringMessage newInstance() {
                        return new StringMessage();
                    }

                    @Override public void handleResponse(StringMessage response) {
                        System.out.println("got response: " + response.message);
                        assertThat("hello moshe", equalTo(response.message));
                    }

                    @Override public void handleException(RemoteTransportException exp) {
                        exp.printStackTrace();
                        assertThat("got exception instead of a response: " + exp.getMessage(), false, equalTo(true));
                    }
                });

        try {
            StringMessage message = res.get();
            assertThat("hello moshe", equalTo(message.message));
        } catch (Exception e) {
            assertThat(e.getMessage(), false, equalTo(true));
        }

        System.out.println("after ...");
    }

    @Test public void testErrorMessage() {
        serviceA.registerHandler("sayHelloException", new BaseTransportRequestHandler<StringMessage>() {
            @Override public StringMessage newInstance() {
                return new StringMessage();
            }

            @Override public void messageReceived(StringMessage request, TransportChannel channel) throws Exception {
                System.out.println("got message: " + request.message);
                assertThat("moshe", equalTo(request.message));
                throw new RuntimeException("bad message !!!");
            }
        });

        TransportFuture<StringMessage> res = serviceB.submitRequest(serviceANode, "sayHelloException",
                new StringMessage("moshe"), new BaseTransportResponseHandler<StringMessage>() {
                    @Override public StringMessage newInstance() {
                        return new StringMessage();
                    }

                    @Override public void handleResponse(StringMessage response) {
                        assertThat("got response instead of exception", false, equalTo(true));
                    }

                    @Override public void handleException(RemoteTransportException exp) {
                        assertThat("bad message !!!", equalTo(exp.getCause().getMessage()));
                    }
                });

        try {
            res.txGet();
            assertThat("exception should be thrown", false, equalTo(true));
        } catch (Exception e) {
            assertThat("bad message !!!", equalTo(e.getCause().getMessage()));
        }

        System.out.println("after ...");

    }

    private class StringMessage implements Streamable {

        private String message;

        private StringMessage(String message) {
            this.message = message;
        }

        private StringMessage() {
        }

        @Override public void readFrom(StreamInput in) throws IOException {
            message = in.readUTF();
        }

        @Override public void writeTo(StreamOutput out) throws IOException {
            out.writeUTF(message);
        }
    }

}