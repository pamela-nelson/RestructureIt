package restructureit.refactorings.processors.pushdownmethod;

public class B extends A {
	String name;
	
	public void method() {
		pushDownMethod(name);
	}
	
	public void pushDownMethod(String name) {
		System.out.println(name);
	}
}

