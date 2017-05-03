package restructureit.refactorings.processors.hidemethod;

public class C {
	A a = new A();
	
	private void useA() {
		a.dontHideOutsideClassUses();
	}
}
