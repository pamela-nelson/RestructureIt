package restructureit.test.refactorings.encapsulatefieldtests;

public class EncapsulateFieldOriginal_Test1 {
    public String name = "EncapsulateField";
    public final String password = "secrets";

    public void changeName(String newName) {
        newName += "Refactoring";
        name = newName;
    }

    public void changeOriginalName(String newName) {
        String temp = name;
        name = temp + "refactoring";
    }
}

