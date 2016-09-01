package hello;

public class Animal {
	private String name;
	
	public String getName(){
		return name;
	}
	public Animal(){
		System.out.println("Making an animal");
	}
	
	public Animal(String name){
		System.out.println("Making an named animal");
		this.name = name;
	}
	public void eat(){
		System.out.println("eat some");
	}
}
