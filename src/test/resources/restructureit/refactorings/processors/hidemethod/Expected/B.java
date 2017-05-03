package restructureit.refactorings.processors.hidemethod;

public class B extends A {

	private void method() {
		dontHideSubClassUses();
	}
}
