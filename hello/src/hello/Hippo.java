package hello;

public class Hippo extends Animal{

	public Hippo(){
		super();
		System.out.println("Making a Hippo...");
		
	}
	
	public Hippo(String name){
		super(name);
		System.out.println("Making a named Hippo...");
	}
}
