package voting.exception;

import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * Created by andrius on 2/6/17.
 */
public class CandidateMultiErrorsException extends RuntimeException {

    private List<ObjectError> errors;

    public CandidateMultiErrorsException(String message, List<ObjectError> errors) {
        super(message);
        this.errors = errors;
    }

    public CandidateMultiErrorsException(String message, Throwable cause, List<ObjectError> errors) {
        super(message, cause);
        this.errors = errors;
    }

    public List<ObjectError> getErrors() {
        return errors;
    }

    public void setErrors(List<ObjectError> errors) {
        this.errors = errors;
    }
}
