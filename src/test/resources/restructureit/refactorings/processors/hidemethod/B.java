package restructureit.refactorings.processors.hidemethod;

public class B extends A {

	public void method() {
		dontHideSubClassUses();
	}
}
