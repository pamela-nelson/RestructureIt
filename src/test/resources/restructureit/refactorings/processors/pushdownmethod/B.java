package restructureit.refactorings.processors.pushdownmethod;

public class B extends A {
	String name;
	
	public void method() {
		pushDownMethod(name);
	}
}

