package restructureit.refactorings.processors.pullupmethod;

public class C extends A {
    String name = "";

    int num = 1;

    public int dontPullUpDifferentBody(String param) {
    	pullUp(param);
        return 1;
    }

    public int dontPullUpDifferentParams(int param) {
        param = 1;
        return param;
    }

    public int dontPullUpDifferentFieldsInClass(int param) {
        param = num;
        return param;
    }
}

