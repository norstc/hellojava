package webutil;

import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
	
	/**
	 * this is real method to find the content from net
	 */

	
	public String findIt(){
		String dividendInfo = null; //the result will send back
		dividendInfo =code + " "; // start with stock code 
		boolean start= false;
		boolean end = false;
		try {
			URL target = new URL("http://stock.quote.stockstar.com/dividend_"+code+".shtml");
			BufferedReader in = new BufferedReader(
					new InputStreamReader(target.openStream()));
			
			String inputLine;
			while((inputLine = in.readLine()) != null){
				
				//get the stock name
				if(inputLine.contains("<title>")){
					dividendInfo = dividendInfo + getStockName(inputLine);
					//System.out.println(dividendInfo);
				}
				
				//get the stock close price
				if(inputLine.contains("stockclose")){
					dividendInfo += getStockClose(inputLine);
				}
				//get the stock dividend
				List<String> dividendLines = new ArrayList<String>();
				if(inputLine.contains("<!-- �ֺ����� begin -->")){
					start = true;
					
				}
				if(inputLine.contains("<!-- �ֺ����� end -->")){
					start = false;
					end = true;
				}
				if(start && !end){
					dividendLines.add(inputLine);
					
				}
				if(!start && end){
					end = false;
					dividendInfo += getDivident(dividendLines);
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
		
		//System.out.println(dividendInfo);
		setResult(dividendInfo);
		return getResult();
	}

	/**
	 * ��ȡ���̼�
	 * Swing��html parser
	 * JsoupҲ������
	 * ������
	 * @param inputLine
	 * @return
	 */
	private String getStockClose(String inputLine) {
		// TODO  <p id="stockclose" class="stock"><span id="stock_quoteinfo_xj" class="red">15.73</span><span class="img"></span></p>
		String ret = 15.73+ "  ";
		
		return ret;
	}

	private String getStockName(String inputLine) {
		// TODO <title>�ַ�����(600000)_���ʷֺ�_֤ȯ֮��</title>
		int x = inputLine.indexOf("<title>");
		int y = inputLine.indexOf("(");
		String ret = inputLine.substring(x+7, y)+ "  "; //need 2 space, 1 space will be omitted
		//System.out.println(ret);
		return ret;
		
	}

	private String getDivident(List<String> dividendLines) {
		// TODO find the dividend from the dividend Lines
		for(String line:dividendLines){
			System.out.println(line);
		}
		String ret = "we 'll make it happen" ;
		return ret;
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
