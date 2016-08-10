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
				
				if(inputLine.contains("<!-- 分红送配 begin -->")){
					start = true;
					
				}
				if(inputLine.contains("<!-- 分红送配 end -->")){
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
	 * 获取收盘价
	 * Swing有html parser
	 * Jsoup也可以用
	 * 目前选择正则表达式直接从String里摘出来
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
	 * 获取名称
	 * @param inputLine
	 * @return
	 */
	private String getStockName(String inputLine) {
		// TODO <title>浦发银行(600000)_融资分红_证券之星</title>
		int x = inputLine.indexOf("<title>");
		int y = inputLine.indexOf("(");
		String ret = inputLine.substring(x+7, y)+ ": "; //need 2 space, 1 space will be omitted
		return ret;
		
	}

	/**
	 * 获取当年的分红
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
	 * 从String中获取price
	 * 使用正则表达式的方法
	 * @param aLine
	 * @return
	 */
	private String getPrice(String aLine){
		String price = " ";
		Pattern patternPrice = Pattern.compile("\\d+\\.\\d+"); //价格匹配模式=>>15.73 
		Matcher matcher = patternPrice.matcher(aLine);
		if(matcher.find()){
			price = matcher.group() + ": ";
		}
		
		return price;
	}
	/**
	 * 获取分红排名，暂时不用
	 * @param inputLine
	 * @return
	 */
	private String getRank(String inputLine) {
		// TODO 获取分红排名
		int x = inputLine.indexOf("派");
		int y = inputLine.indexOf("位");
		String ret=inputLine.substring(x, y);
		ret = ret.substring(ret.length()-3,ret.length());
		return ret;
	}

}
