package hello;


class fat{
	static int x = 1;
}
interface face{
	int x = 0;
}
public class InterfaceDemo extends fat implements face{
	
	public static void main(String[] args){
		System.out.println("super x is " + fat.x); //print the super class x
		System.out.println("interface x is " + face.x); //print the interface x
	}
}
