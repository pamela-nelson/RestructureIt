

package restructureit.test.refactorings.encapsulatefieldtests;


public class EncapsulateFieldOriginal_Test1 {
    private java.lang.String name = "EncapsulateField";

    private final java.lang.String password = "secrets";

    public void changeName(java.lang.String newName) {
        newName += "Refactoring";
        name = newName;
    }

    public void changeOriginalName(java.lang.String newName) {
        java.lang.String temp = get_Field_();
        name = temp + "refactoring";
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.String getPassword() {
        return password;
    }
}

