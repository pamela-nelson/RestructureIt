

package restructureit.refactorings.utils.templates;


public class FieldAccessTemplate<_FieldType_> implements spoon.template.Template {
    @spoon.template.Parameter
    spoon.reflect.reference.CtTypeReference<?> _FieldType_;

    @spoon.template.Parameter(value = "_field_")
    java.lang.String __field_;

    @spoon.template.Parameter
    java.lang.String _Field_;

    @spoon.template.Parameter
    spoon.reflect.code.CtExpression<_FieldType_> _setExpression_;

    @spoon.template.Parameter
    spoon.reflect.code.CtExpression<restructureit.refactorings.utils.templates.FieldAccessTemplate<_FieldType_>> _target_;

    @java.lang.SuppressWarnings(value = "unchecked")
    @spoon.template.Local
    public FieldAccessTemplate(spoon.reflect.reference.CtTypeReference<?> type, java.lang.String field, spoon.reflect.code.CtFieldAccess<_FieldType_> fieldAccess, spoon.reflect.code.CtExpression<_FieldType_> setExpression) {
        _FieldType_ = type;
        __field_ = field;
        char[] chars = field.toCharArray();
        chars[0] = java.lang.Character.toUpperCase(chars[0]);
        _Field_ = new java.lang.String(chars);
        if (fieldAccess != null) {
            _target_ = ((spoon.reflect.code.CtExpression<restructureit.refactorings.utils.templates.FieldAccessTemplate<_FieldType_>>) (fieldAccess.getTarget()));
            if ((!(fieldAccess.getVariable().isStatic())) && ((_target_) == null)) {
                _target_ = ((spoon.reflect.code.CtExpression<restructureit.refactorings.utils.templates.FieldAccessTemplate<_FieldType_>>) (fieldAccess.getFactory().Code().createThisAccess(fieldAccess.getVariable().getDeclaringType())));
            }
        }
        _setExpression_ = setExpression;
    }

    @spoon.template.Local
    _FieldType_ _field_;

    @restructureit.refactorings.utils.templates.Setter
    public void set_Field_(_FieldType_ _field_) {
        this._field_ = _field_;
    }

    @restructureit.refactorings.utils.templates.Getter
    public _FieldType_ get_Field_() {
        return _field_;
    }

    @spoon.template.Local
    void setterInvocation() {
        _target_.S().set_Field_(_setExpression_.S());
    }

    @spoon.template.Local
    void getterInvocation() {
        _target_.S().get_Field_();
    }

    @java.lang.Override
    public spoon.reflect.declaration.CtElement apply(spoon.reflect.declaration.CtType targetType) {
        return null;
    }
}

