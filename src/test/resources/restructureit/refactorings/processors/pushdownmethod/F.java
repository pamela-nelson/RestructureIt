package restructureit.refactorings.processors.pushdownmethod;

public class F {
	E e = new E();
	
	public void setupE() {
		e.pushDownMethod("name");
	}
}
