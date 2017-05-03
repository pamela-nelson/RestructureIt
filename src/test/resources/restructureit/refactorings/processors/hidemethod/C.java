package restructureit.refactorings.processors.hidemethod;

public class C {
	A a = new A();
	
	public void useA() {
		a.dontHideOutsideClassUses();
	}
}
