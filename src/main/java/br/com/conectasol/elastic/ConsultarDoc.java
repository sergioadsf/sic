package br.com.conectasol.elastic;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import br.com.conectasol.CloseUtil;

public class ConsultarDoc {

	public static void main(String[] args) {
		RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));

		try {

			SearchRequest searchRequest = new SearchRequest("proposta");
//		searchRequest.types("docs");
			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//		searchSourceBuilder.query(QueryBuilders.matchAllQuery());
			searchSourceBuilder.query(QueryBuilders.termQuery("message", "Marco"));
			searchRequest.source(searchSourceBuilder);
			SearchResponse searchResponse;
			searchResponse = client.search(searchRequest);
			RestStatus status = searchResponse.status();
			TimeValue took = searchResponse.getTook();
			Boolean terminatedEarly = searchResponse.isTerminatedEarly();
			boolean timedOut = searchResponse.isTimedOut();
			SearchHits hits = searchResponse.getHits();
			long totalHits = hits.getTotalHits();
			float maxScore = hits.getMaxScore();
			SearchHit[] searchHits = hits.getHits();
			for (SearchHit hit : searchHits) {
				String index = hit.getIndex();
				String type = hit.getType();
				String id = hit.getId();
				float score = hit.getScore();
				Map<String, Object> sourceAsMap = hit.getSourceAsMap();
				String name = (String) sourceAsMap.get("name");
				System.out.println(name);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseUtil.close(client);
		}
	}
}
