package net.yes.stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.yes.JsonUtil.JsonUtil;
import net.yes.JsonUtil.JsonUtil.SipFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class ImgHelper {

	private final static int FILE_SIZE = 1024;

	/**
	 * 将byte数组以Base64方式编码为字符串
	 * 
	 * @param bytes
	 *            待编码的byte数组
	 * @return 编码后的字符串
	 * */
	public static String encode(byte[] bytes) {
		return new BASE64Encoder().encode(bytes);
	}

	/**
	 * 将以Base64方式编码的字符串解码为byte数组
	 * 
	 * @param encodeStr
	 *            待解码的字符串
	 * @return 解码后的byte数组
	 * @throws IOException
	 * */
	public static byte[] decode(String encodeStr) throws IOException {
		byte[] bt = null;
		BASE64Decoder decoder = new BASE64Decoder();
		bt = decoder.decodeBuffer(encodeStr);
		return bt;
	}

	/**
	 * 将两个byte数组连接起来后，返回连接后的Byte数组
	 * 
	 * @param front
	 *            拼接后在前面的数组
	 * @param after
	 *            拼接后在后面的数组
	 * @return 拼接后的数组
	 * */
	public static byte[] connectBytes(byte[] front, byte[] after) {
		byte[] result = new byte[front.length + after.length];
		System.arraycopy(front, 0, result, 0, after.length);
		System.arraycopy(after, 0, result, front.length, after.length);
		return result;
	}

	/**
	 * 将图片以Base64方式编码为字符串
	 * 
	 * @param imgUrl
	 *            图片的绝对路径（例如：D:\\jsontest\\abc.jpg）
	 * @return 编码后的字符串
	 * @throws IOException
	 * */
	public static String encodeImage(String imgUrl) throws IOException {
		FileInputStream fis = new FileInputStream(imgUrl);
		byte[] rs = new byte[fis.available()];
		fis.read(rs);
		fis.close();
		return encode(rs);
	}

	// 图片上传
	public static String upLoadFile(byte[] image) {
		String pathName = "D:\\\\2001.jpg";
		File targetFile = new File(pathName);
		InputStream in = new BufferedInputStream(
				new ByteArrayInputStream(image), FILE_SIZE);
		OutputStream out = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(targetFile),
					FILE_SIZE);
			while (in.read(image) > 0) {
				out.write(image);
			}
		} catch (IOException ex) {
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException ex) {
			}
		}
		return pathName; 
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str;
		try {
			str = encodeImage("D:\\\\2000.jpg");
			SipFile file1 = new SipFile();
			file1.setName("test");
			file1.setPath(str);
//			System.out.println(JsonUtil.toJson(file1, true));

			String json = JsonUtil.toJson(file1, false);
			SipFile file = JsonUtil.jsonToObject(json, SipFile.class);
//			System.out.println(file.getName());
			System.out.println(json);
//			System.out.println(str);
//			upLoadFile(decode(file.getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
