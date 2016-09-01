package hello;

import java.util.Calendar;
import java.util.Date;

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

		int a = 1;
		double b = 2222.2233;
		String x = String.format("the int is %d, the double is %.3f", a, b);
		System.out.println(x);
		System.out.println(String.format("now is %tc", new Date())); //complete date and time >>now is 星期二 八月 23 09:04:06 CST 2016
		System.out.println(String.format("%tr", new Date())); //>> 09:04:06 上午
		Date date = new Date(); //a time-stamp, for everything else  , use Calendar
		System.out.println(String.format("%tA, %tB, %td", date, date, date)); //>>星期二, 八月, 23
		System.out.println(String.format("use one arg only: %tA, %<tB, %<tD", date)); //>>星期二, 八月, 08/23/16
		
		Calendar cal = Calendar.getInstance();
		
		cal.set(2006, 0,7,15,40); //set time to Jan 7 2004 at 15:40
		System.out.println("the frake time is >> "+cal.getTime()); //the frake time is >> Sat Jan 07 15:40:48 CST 2006
		System.out.println("old hour "  + cal.get(cal.HOUR_OF_DAY));
		long day1 = cal.getTimeInMillis();
		System.out.println("time in millis " + day1); //>>time in millis 1136619648978
		day1 += 1000*60*60;
		cal.setTimeInMillis(day1);
		System.out.println("new hour "  + cal.get(cal.HOUR_OF_DAY)); //.>>new hour 16
		
		cal.add(cal.DATE, 12);
		System.out.println("add  12 day>> " + cal.getTime()); //add  12 day>> Thu Jan 19 16:40:48 CST 2006
		
		
	}

}
