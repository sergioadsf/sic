package br.com.conectasol.elastic;

import java.io.File;
import java.util.UUID;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import br.com.conectasol.CloseUtil;
import br.com.conectasol.PDFExtractor;

public class IndexarDoc {

	private static final String PATH = "/home/sergio/Downloads/arquivos_para_indexar/";

	public static void main(String[] args) throws Exception {
		final RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));

		PDFExtractor pdfExtractor;
		File file = new File(PATH);
		int i = 1;
		for (File f : file.listFiles()) {
			String name = f.getName();
			if (f.isFile() && name.contains(".pdf")) {
				pdfExtractor = new PDFExtractor(f);
				
				IndexRequest request = new IndexRequest("proposta", "proposta", UUID.randomUUID().toString()).source("message", pdfExtractor.getDocumentText());
//				pdfExtractor.save(OUT + name.replaceAll(".pdf", ".txt"));
				client.index(request);
			}
		}

		CloseUtil.close(client);
	}
}
