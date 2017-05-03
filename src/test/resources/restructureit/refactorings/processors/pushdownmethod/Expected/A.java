package restructureit.refactorings.processors.pushdownmethod;

public class A {
	
	private String password;
	
	public void dontPushDownPrivateField () {
		password = "password";
		System.out.println(password);
	}
	
}

