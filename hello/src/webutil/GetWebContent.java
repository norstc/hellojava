package webutil;

import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
		dividendInfo =code + ": "; // start with stock code 
		ArrayList<String> dividendLines = new ArrayList<String>();
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
				
				if(inputLine.contains("<!-- �ֺ����� begin -->")){
					start = true;
					
				}
				if(inputLine.contains("<!-- �ֺ����� end -->")){
					start = false;
					end = true;
				}
				if(start && !end){
					dividendLines.add(inputLine);
					//System.out.println(inputLine);
					
				}
			
				
							
				
			}
			
			in.close();
			dividendInfo += getDivident(dividendLines);
			
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
	 * Ŀǰѡ��������ʽֱ�Ӵ�String��ժ����
	 * @param inputLine
	 * @return
	 */
	private String getStockClose(String inputLine) {
		// TODO  <p id="stockclose" class="stock"><span id="stock_quoteinfo_xj" class="red">15.73</span><span class="img"></span></p>
		String ret = "---";
		
		ret = getPrice(inputLine);
		
		
		return ret;
	}

	/**
	 * ��ȡ����
	 * @param inputLine
	 * @return
	 */
	private String getStockName(String inputLine) {
		// TODO <title>�ַ�����(600000)_���ʷֺ�_֤ȯ֮��</title>
		int x = inputLine.indexOf("<title>");
		int y = inputLine.indexOf("(");
		String ret = inputLine.substring(x+7, y)+ ": "; //need 2 space, 1 space will be omitted
		return ret;
		
	}

	/**
	 * ��ȡ����ķֺ�
	 * @param dividendLines
	 * @return
	 */
	private String getDivident(ArrayList<String> dividendLines) {
		// TODO find the dividend from the dividend Lines
		String ret = "0" ;
		String line;

		for(int i = 0;i<dividendLines.size();i++){
			line = dividendLines.get(i);
			if(line.contains("2016")){
				ret = getPrice(line);
			}
		}
		
		return ret;
	}

	/**
	 * ��String�л�ȡprice
	 * ʹ��������ʽ�ķ���
	 * @param aLine
	 * @return
	 */
	private String getPrice(String aLine){
		String price = " ";
		Pattern patternPrice = Pattern.compile("\\d+\\.\\d+"); //�۸�ƥ��ģʽ=>>15.73 
		Matcher matcher = patternPrice.matcher(aLine);
		if(matcher.find()){
			price = matcher.group() + ": ";
		}
		
		return price;
	}
	/**
	 * ��ȡ�ֺ���������ʱ����
	 * @param inputLine
	 * @return
	 */
	private String getRank(String inputLine) {
		// TODO ��ȡ�ֺ�����
		int x = inputLine.indexOf("��");
		int y = inputLine.indexOf("λ");
		String ret=inputLine.substring(x, y);
		ret = ret.substring(ret.length()-3,ret.length());
		return ret;
	}

}
