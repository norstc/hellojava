package hello;

class Foo{
	static int x;
	public void go(){
		System.out.println(x);
	}
}

class Foo2{
	int x;
	public static void go(int x){
		System.out.println(x);
		
	}
}

class Foo4{
	static final int x = 12;
	public void go(){
		System.out.println(x);
	}
}


class Foo5{
	static final int x = 12;
	public void go(final int x){
		System.out.println(x);
	}
}

class Foo6{
	int x = 12;
	public static void go(final int x){
		System.out.println(x);
	}
}


class Foo3{
	final int x = 0;
	public void go(){
		System.out.println(x);
	}
}


public class FinalDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
