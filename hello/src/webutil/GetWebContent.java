package webutil;

import java.net.*;
import java.io.*;


public class GetWebContent {
	
	private String code;
	private String result;
	
	public GetWebContent(){
		setCode("600000");
	}

	public GetWebContent(String code){
		setCode(code);
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getResult() {
		return result;
	}

	private void setResult(String result) {
		this.result = result;
	}
	
	//this is real method to find the content from net
	public String findIt(){
		String ret = null;
		boolean start= false;
		boolean end = false;
		try {
			URL target = new URL("http://stock.quote.stockstar.com/dividend_"+code+".shtml");
			BufferedReader in = new BufferedReader(
					new InputStreamReader(target.openStream()));
			
			String inputLine;
			while((inputLine = in.readLine()) != null){
				if(inputLine.contains("<!-- �ֺ����� begin -->")){
					start = true;
					ret = getDivident(inputLine);
				}
				if(inputLine.contains("<!-- �ֺ����� end -->")){
					start = false;
					end = true;
				}
				if(start && !end){
					System.out.println(inputLine);
				}
			}
			
			in.close();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		setResult(  ret);
		return getResult();
	}

	private String getDivident(String inputLine) {
		// TODO Auto-generated method stub
		return inputLine;
	}

	private String getRank(String inputLine) {
		// TODO ��ȡ�ֺ�����
		int x = inputLine.indexOf("��");
		int y = inputLine.indexOf("λ");
		String ret=inputLine.substring(x, y);
		ret = ret.substring(ret.length()-3,ret.length());
		return ret;
	}

}
