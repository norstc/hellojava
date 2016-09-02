package hello;

public class SingletonDemo {

	private static SingletonDemo instance = null;
	private SingletonDemo(){
		System.out.println("we have a instance");
	}
	public static SingletonDemo getInstance(){
		if (instance == null){
			instance = new SingletonDemo();
			return instance;
		}else{
			return instance;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SingletonDemo instance = SingletonDemo.getInstance();

	}

}
