package com.zdcf.search;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.Resource;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.zdcf.search.repository")
@PropertySource(value = "classpath:com/zdcf/config/elasticsearch.properties")
public class ESConfig {
	@Resource
	private Environment environment;

	@Value("${hosts}")
	private String esHosts;

	@Value("${cluster.name}")
	private String clusterName;
	@Bean
	public Client client() throws NumberFormatException, UnknownHostException {
		Settings settings = Settings.settingsBuilder().put("cluster.name", clusterName)// 设置集群名称
				.put("tclient.transport.sniff", true).build();// 自动嗅探整个集群的状态，把集群中其它机器的ip地址加到客户端中

		TransportClient client = TransportClient.builder().settings(settings).build();
		String[] nodes = esHosts.split(",");
		for (String node : nodes) {
			if (node.length() > 0) {// 跳过为空的node（当开头、结尾有逗号或多个连续逗号时会出现空node）
				String[] hostPort = node.split(":");
				client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(hostPort[0]),
						Integer.parseInt(hostPort[1])));

			}
		}
		
//		TransportClient client = new TransportClient()
//                .addTransportAddress(new InetSocketTransportAddress(
//                        "localhost", 9200));

		return client;
	}

	@Bean
	public ElasticsearchOperations elasticsearchTemplate() throws Exception {
		return new ElasticsearchTemplate(client());
	}
}
