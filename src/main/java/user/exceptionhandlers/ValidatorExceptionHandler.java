package user.exceptionhandlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import user.response.BaseResponse;

import java.util.List;

@ControllerAdvice
public class ValidatorExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        StringBuilder errorDescription = new StringBuilder();
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        for (FieldError error : errors) {
            errorDescription.append("Parameter: ").append(error.getField()).append(" Error Message : ").append(error.getDefaultMessage()).append(", ");
        }

        BaseResponse response = new BaseResponse();
        response.setDescription(errorDescription.toString());

        return handleExceptionInternal(ex, response, headers, HttpStatus.BAD_REQUEST, request);
    }

}
