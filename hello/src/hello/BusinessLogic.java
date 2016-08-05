package hello;

public class BusinessLogic {
	private int modifier;
	
	public BusinessLogic(int mod){
		modifier = mod;
	}
	
	public void setModifier(int mod){
		modifier = mod;
	}
	
	public int getModifier(){
		return modifier;
	}
	
	// some business operations;
	public int calculation1(int arg){
		return arg*modifier;
	}
	
	public int calculation2(int arg){
		return arg+modifier;
	}
	

}
