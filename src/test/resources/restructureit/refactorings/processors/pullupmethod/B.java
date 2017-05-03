package restructureit.refactorings.processors.pullupmethod;

public class B extends A {
	String name = "";
	int num = 1;
	
	public int pullUp(String param) {
		param = name;
		return 1;
	}
	
	public int dontPullUpDifferentBody(String param) {
		param = "";
		pullUp(param);
		return 0;
	}
	
	public int dontPullUpDifferentParams(String param) {
		param = "";
		return 0;
	}
	
	public int dontPullUpDifferentFieldsInClass(int param) {
		param = num;
		return param;
	}
}
