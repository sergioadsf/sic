package br.com.conectasol;


import java.io.Closeable;
import java.io.IOException;

public class CloseUtil {

	private CloseUtil() {
	}

	public static final void close(Closeable... cs) {
		for (Closeable c : cs) {
			if (c != null)
				try {
					c.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
