/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sqlist;

import com.sqlist.util.Factory;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer011;

import java.util.Properties;

/**
 * Skeleton for a Flink Streaming Job.
 *
 * <p>For a tutorial how to write a Flink streaming application, check the
 * tutorials and examples on the <a href="http://flink.apache.org/docs/stable/">Flink Website</a>.
 *
 * <p>To package your application into a JAR file for execution, run
 * 'mvn clean package' on the command line.
 *
 * <p>If you change the name of the main class (with the public static void main(String[] args))
 * method, change the respective entry in the POM.xml file (simply search for 'mainClass').
 */
public class StreamingJob {

	public static void main(String[] args) throws Exception {

		String consumerIp;
		String consumerTopic;
		String consumerGroup;
		String consumerZookeeperIp;
		String producerIp;
		String producerTopic;
		String implementClass;

		try {
			// --consumerIp 192.168.56.101:9092 --consumerTopic test --consumerGroup test-group --consumerZookeeperIp 192.168.56.101:2181 --producerIp 192.168.56.101:9092 --producerTopic test-output --implementClass org.test.OperatingData
			final ParameterTool params = ParameterTool.fromArgs(args);
			consumerIp = params.get("consumerIp");
			consumerTopic = params.get("consumerTopic");
			consumerGroup = params.get("consumerGroup");
			consumerZookeeperIp = params.get("consumerZookeeperIp");
			producerIp = params.get("producerIp");
			producerTopic = params.get("producerTopic");
			// "org.test.OperatingDataImpl"
			implementClass = params.get("implementClass");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("No specified.");
			return;
		}

		System.out.println(consumerIp + ", " + consumerTopic + ", " + consumerGroup + ", " + consumerZookeeperIp + ", " +
				producerIp + ", " + producerTopic + ", " + implementClass);
		OperatingData operatingData = Factory.getComponent(implementClass);
		if (operatingData == null) {
			throw new IllegalArgumentException("implements class 错误");
		}

		// set up the streaming execution environment
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		Properties consumerProps = new Properties();
		consumerProps.put("bootstrap.servers", consumerIp);
		consumerProps.put("zookeeper.connect", consumerZookeeperIp);
		consumerProps.put("group.id", consumerGroup);

		DataStreamSource<String> dataStreamSource
				= env.addSource(new FlinkKafkaConsumer011<>(consumerTopic, new SimpleStringSchema(), consumerProps));

		DataStream returnSource = operatingData.operate(dataStreamSource);

		returnSource.print();

		Properties producerProps = new Properties();
		producerProps.put("bootstrap.servers", producerIp);
		returnSource.addSink(new FlinkKafkaProducer011<>(producerTopic, new SimpleStringSchema(), producerProps));

		// execute program
		env.execute("Flink Streaming Java API Skeleton");
	}
}
