package restructureit.refactorings.processors.hidemethod;

public class A {
	private void hideMethod() {
		System.out.print("hide");
	}
	
	public void dontHideSubClassUses() {
		System.out.print("don't hide");
	}
	
	public void dontHideOutsideClassUses() {
		System.out.print("don't hide");
	}
}
