package net.yes.file;

import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
	
	public static void main(String[] agrs){
		saveAsFileWriter("test");
	}

	private static   String file = "D:/mdoor.txt";
	//保存字符串到文件中
	private static void saveAsFileWriter(String content) {

	 FileWriter fwriter = null;
	 try {
	  fwriter = new FileWriter(file);
	  fwriter.write(content);
	 } catch (IOException ex) {
	  ex.printStackTrace();
	 } finally {
	  try {
	   fwriter.flush();
	   fwriter.close();
	  } catch (IOException ex) {
	   ex.printStackTrace();
	  }
	 }
	}
}
