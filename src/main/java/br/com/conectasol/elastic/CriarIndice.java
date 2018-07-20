package br.com.conectasol.elastic;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;

import br.com.conectasol.CloseUtil;

public class CriarIndice {

	public static void main(String[] args) throws IOException{
		final RestHighLevelClient client = new RestHighLevelClient(
		        RestClient.builder(
		                new HttpHost("localhost", 9200, "http")));
		
		CreateIndexRequest request = new CreateIndexRequest("proposta");

		request.settings(Settings.builder().put("index.number_of_shards", 3).put("index.number_of_replicas", 2));
		
		request.mapping("proposta", 
		        "{\n" +
		        "    \"properties\": {\n" +
		        "      \"message\": {\n" +
		        "        \"type\": \"text\"\n" +
		        "      }\n" +
		        "    }\n" +
		        "}", 
		        XContentType.JSON);
		
		client.indices().create(request);
		CloseUtil.close(client);
	}
}
