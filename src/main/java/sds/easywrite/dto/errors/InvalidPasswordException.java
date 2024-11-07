package sds.easywrite.dto.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;
import sds.easywrite.constants.ErrorConstants;

@SuppressWarnings("java:S110") // Inheritance tree of classes should not be too deep
public class InvalidPasswordException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public InvalidPasswordException() {
        super(ErrorConstants.INVALID_PASSWORD_TYPE, "Incorrect password", Status.BAD_REQUEST);
    }
}
