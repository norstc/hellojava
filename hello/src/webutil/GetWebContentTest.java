package webutil;

public class GetWebContentTest {

	static GetWebContent get1 = new GetWebContent();
	static GetWebContent get2 = new GetWebContent("600005");
	static GetWebContent get3= new GetWebContent("600660");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("we are testing the GetWebContent class");
		System.out.println(get1.getCode() );
		get1.findIt();
		System.out.println(get2.getCode() );
		get2.findIt();
		System.out.println(get3.getCode() );
		get3.findIt();
	}

}
