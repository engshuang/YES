package org.net.yes.springboot.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {
	private static final Logger log = LoggerFactory.getLogger(HelloRestController.class);

	@RequestMapping("/hello")
	public String hello() {
		return "Hello. All your base are belong to us.";
	}

	@RequestMapping("/download")
	public void download(HttpServletResponse response) throws IOException {
		URL url = new URL(
				"https://docs.spring.io/spring-boot/docs/2.1.7.RELEASE/reference/pdf/spring-boot-reference.pdf");
		InputStream src = url.openStream();
		response.reset();
		response.setContentType("application/octet-stream;charset=utf-8");
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String(("springBoot.pdf").getBytes(), "iso-8859-1"));
		OutputStream dest = response.getOutputStream();
		this.dump(src, dest);
	}

	public void dump(InputStream src, OutputStream dest) throws IOException {
		try (InputStream input = src; OutputStream output = dest) {
			byte[] data = new byte[1024];
			int length = -1;
			while ((length = input.read(data)) != -1) {
				output.write(data, 0, length);
			}
		}

	}
}
