

package restructureit.refactorings.utils.templates;


public class GetterInvocationTemplate<_FieldType_> extends spoon.template.AbstractTemplate {
    @spoon.template.Parameter
    spoon.reflect.reference.CtTypeReference<?> _FieldType_;

    @spoon.template.Parameter(value = "_field_")
    java.lang.String __field_;

    @spoon.template.Parameter
    java.lang.String _Field_;

    @spoon.template.Parameter
    spoon.reflect.code.CtExpression<restructureit.refactorings.utils.templates.GetterInvocationTemplate> _target_;

    @spoon.template.Parameter
    spoon.reflect.code.CtFieldAccess fieldReference;

    @java.lang.SuppressWarnings(value = "unchecked")
    @spoon.template.Local
    public GetterInvocationTemplate(spoon.reflect.reference.CtTypeReference<?> type, java.lang.String field, spoon.reflect.code.CtFieldAccess fieldReference) {
        _FieldType_ = type;
        __field_ = field;
        _Field_ = (field.substring(0, 1).toUpperCase()) + (field.substring(1));
        _target_ = ((spoon.reflect.code.CtExpression) (fieldReference.getTarget()));
        this.fieldReference = fieldReference;
    }

    @spoon.template.Local
    _FieldType_ _field_;

    @restructureit.refactorings.utils.templates.Getter
    public _FieldType_ get_Field_() {
        return _field_;
    }

    void getterInvocation() {
        _target_.S().get_Field_();
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtElement apply(spoon.reflect.declaration.CtType arg0) {
        return null;
    }
}

