package agh.ea;

public class Main {

	public static void main(String[] args){
		
		XLastovka xl = new XLastovka();
		xl.generate(100, (long) (0.5 * 60 * 1E9), 10000);
		
	}
}
