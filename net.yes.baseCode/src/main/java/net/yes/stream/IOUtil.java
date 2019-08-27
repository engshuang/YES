package net.yes.stream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class IOUtil {
	
	public static void dump(InputStream src, OutputStream dest) throws IOException{
		try (InputStream input = src; OutputStream output = dest) {
			byte[] data = new byte[1024];
			int length = -1;
			while ((length = input.read(data)) != -1) {
				output.write(data, 0, length);
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		URL url = new URL("https://docs.spring.io/spring-boot/docs/2.1.7.RELEASE/reference/pdf/spring-boot-reference.pdf");
		InputStream src = url.openStream();
		OutputStream dest = new FileOutputStream("E:\\tmp\\spring-boot-reference.pdf");
		IOUtil.dump(src, dest);
	}

}
