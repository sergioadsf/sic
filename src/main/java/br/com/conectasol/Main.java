package br.com.conectasol;

import java.io.File;

public class Main {

	private static final String PATH = "/home/sergio/Downloads/arquivos_para_indexar/";
	private static final String OUT = "/home/sergio/Downloads/arquivos_para_indexar/saida/";

	public Main() {
	}

	public static void main(String[] args) throws Exception {
		PDFExtractor pdfExtractor;
		File file = new File(PATH);
		for (File f : file.listFiles()) {
			String name = f.getName();
			if (f.isFile() && name.contains(".pdf")) {
				pdfExtractor = new PDFExtractor(f);
				pdfExtractor.save(OUT + name.replaceAll(".pdf", ".txt"));
			}
		}
	}

}
