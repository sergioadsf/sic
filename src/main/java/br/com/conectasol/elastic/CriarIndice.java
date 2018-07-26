package br.com.conectasol.elastic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;

import br.com.conectasol.CloseUtil;

public class CriarIndice {

	public static void main(String[] args) throws IOException{
		final RestHighLevelClient client = new RestHighLevelClient(
		        RestClient.builder(
		                new HttpHost("localhost", 9200, "http")));
		
		CreateIndexRequest request = new CreateIndexRequest("proposta");

		request.settings(Settings.builder().put("index.number_of_shards", 3).put("index.number_of_replicas", 2));
		
		
		Map<String, Object> jsonMap = new HashMap<>();
		Map<String, Object> message = new HashMap<>();
		message.put("type", "text");
		Map<String, Object> name = new HashMap<>();
		name.put("type", "text");
		Map<String, Object> properties = new HashMap<>();
		properties.put("message", message);
		properties.put("name", name);
		Map<String, Object> proposta = new HashMap<>();
		proposta.put("properties", properties);
		jsonMap.put("proposta", proposta);
		request.mapping("proposta", jsonMap);
//		
//		request.mapping("proposta", 
//		        "{\n" +
//		        "    \"properties\": {\n" +
//		        "      \"message\": {\n" +
//		        "        \"type\": \"text\"\n" +
//		        "      }\n" +
//		        "    }\n" +
//		        "}", 
//		        XContentType.JSON);
		
		client.indices().create(request);
		CloseUtil.close(client);
	}
}
