package restructureit.refactorings.utils.templates;

import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtFieldAccess;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtType;
import spoon.reflect.reference.CtTypeReference;
import spoon.template.AbstractTemplate;
import spoon.template.Local;
import spoon.template.Parameter;
import spoon.template.Substitution;
import spoon.template.Template;

public class GetterInvocationTemplate<_FieldType_> extends AbstractTemplate {

		@Parameter
		CtTypeReference<?> _FieldType_;

		@Parameter("_field_")
		String __field_;

		@Parameter
		String _Field_;

		@Parameter
		CtExpression<GetterInvocationTemplate<_FieldType_>> _target_;
		
		@Parameter
		CtFieldAccess fieldReference;

		@SuppressWarnings("unchecked")
		@Local
		public GetterInvocationTemplate(CtTypeReference<?> type, String field,
				CtFieldAccess fieldReference) {
			_FieldType_ = type;
			__field_ = field;
			_Field_ = field.substring(0,1).toUpperCase() + field.substring(1);
			_target_ = (CtExpression) fieldReference.getTarget();
			this.fieldReference = fieldReference;
		}

		@Local
		_FieldType_ _field_;

		public _FieldType_ get_Field_() {
			return _field_;
		}

		@Local
		void getterInvocation() {
			_target_.S().get_Field_();
		}

		@Override
		public CtStatement apply(CtType targetType) {
			return Substitution.substituteMethodBody(fieldReference.getParent(CtClass.class), this, "getterInvocation");
		}

	}
