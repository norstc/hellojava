package webutil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GetWebContentTest {

	 GetWebContent get1 = new GetWebContent();
//	 GetWebContent get2 = new GetWebContent("600005");
//	 GetWebContent get3= new GetWebContent("600660");
	 static String FILE_NAME;
	 static String OUTPUT_FILE_NAME;
	 static Charset ENCODING = StandardCharsets.UTF_8;
	 ArrayList<String> lines = new ArrayList<String>(); //buffer to store the stockCode
	 ArrayList<String> dividendResult = new ArrayList<String>(); //buffer to store the dividend
	 

	public static void main(String[] args)  throws IOException{
		// TODO Auto-generated method stub
		GetWebContentTest getWebContentTest = new GetWebContentTest();
		
		System.out.println("we are testing the GetWebContent class");

		//get the res folder path
		String absolutePathOfRes = getWebContentTest.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		absolutePathOfRes = absolutePathOfRes.substring(1,absolutePathOfRes.lastIndexOf("/")); //bin:=>E:/zjh/java/repo/GitHub/git/hello/hello/bin
		absolutePathOfRes = absolutePathOfRes.substring(0,absolutePathOfRes.lastIndexOf("/")); //project:=> E:/zjh/java/repo/GitHub/git/hello/hello/
		FILE_NAME = absolutePathOfRes+"/res/stock-code-list.txt";
		OUTPUT_FILE_NAME = absolutePathOfRes+"/res/stock-dividend.txt";
		
		//System.out.println(FILE_NAME);
		//System.out.println(OUTPUT_FILE_NAME);
		//find the cool info from web
		
//		getWebContentTest.get1.findIt();
//		System.out.println(getWebContentTest.get1.getResult());
	
//		getWebContentTest.get2.findIt();
//		System.out.println(getWebContentTest.get2.getResult());
//		
//		getWebContentTest.get3.findIt();
//		System.out.println(getWebContentTest.get3.getResult());
		
		//read and write file 
		
		getWebContentTest.readLargeTextFile(FILE_NAME);
		for(String line: getWebContentTest.lines){
			getWebContentTest.get1.setCode(line);
			getWebContentTest.get1.findIt();
			System.out.println(getWebContentTest.get1.getResult());
			getWebContentTest.dividendResult.add(getWebContentTest.get1.getResult());
			
		}
		//getWebContentTest.writeLargeTextFile(OUTPUT_FILE_NAME,getWebContentTest.dividendResult);

		getWebContentTest.writeFile(OUTPUT_FILE_NAME, getWebContentTest.dividendResult);
		
	}

	// small files
	List<String> readSmallTextFile(String aFileName) throws IOException {
		Path path = Paths.get(aFileName);
		return Files.readAllLines(path,ENCODING);
		
	}
	
	void writeSmallTextFile(List<String> aLines, String aFileName) throws IOException {
		Path path = Paths.get(aFileName);
		Files.write(path, aLines, ENCODING);
	}
	
	//for large files, use some buffer
	
	void readLargeTextFile(String aFileName) throws IOException{
		Path path = Paths.get(aFileName);
		try (Scanner scanner = new Scanner(path, ENCODING.name())){
			while (scanner.hasNextLine()){
				//process each line
				//log(scanner.nextLine());
				lines.add(scanner.nextLine());
			}
			
		}
	}
	
	/**
	 * 向文件中写入ArrayList<String>, 使用UTF_8，会产生中文乱码，暂停使用
	 * @param aFileName
	 * @param aLines
	 * @throws IOException
	 */
	void writeLargeTextFile(String aFileName, ArrayList<String> aLines) throws IOException{
		Path path = Paths.get(aFileName);
		try(BufferedWriter writer = Files.newBufferedWriter(path, ENCODING)){
			for(String line: aLines){
				writer.write(line);
				writer.newLine();
			}
		}
	}
	
	/**
	 * 向文件中写String， 使用gbk编码
	 * @param fileName
	 * @param aLine
	 */
	void writeFile(String fileName, String aLine){
		try{
			File f = new File(fileName);
			if(!f.exists()){
				f.createNewFile();
			}
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f),"gbk");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(aLine);
			writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 向文件中写ArrayList<String>,使用gbk编码
	 * @param fileName
	 * @param aLines
	 */
	void writeFile(String fileName, ArrayList<String> aLines){
		try{
			File f = new File(fileName);
			if(!f.exists()){
				f.createNewFile();
			}
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f),"gbk");
			BufferedWriter writer = new BufferedWriter(write);
			for(String line:aLines){
				writer.write(line);
				writer.newLine();
			}
			
			writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * wrapper for System.out.println
	 * @param aMsg
	 */
	private static void log(Object aMsg){
		System.out.println(String.valueOf(aMsg));
	}
	
}
