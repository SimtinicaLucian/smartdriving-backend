package ro.softwarelabz.smartdriving.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.postgresql.util.PSQLException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.softwarelabz.smartdriving.controller.response.Response;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Response> handleAccessDeniedException(AccessDeniedException ex) {
        var error = ErrorItem.builder()
                .code(HttpStatus.FORBIDDEN.value())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(Response.error(error), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ConstraintViolationException.class, PSQLException.class, DataIntegrityViolationException.class})
    public ResponseEntity<Response> handle(Exception e) {
        ErrorItem error = ErrorItem.builder()
                .code(400)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(Response.error(error), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({IllegalArgumentException.class, BindException.class})
    public ResponseEntity<Response> handleAssertValidationException(Exception e) {
        log.info("Validation failed for request.", e);
        ErrorItem customError = ErrorItem.builder()
                .code(400)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(Response.error(customError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> genericException(Exception e) {
        log.error("Unhandled exception with message: {}", e.getMessage(), e);
        return new ResponseEntity(Response
                .error(ErrorItem.builder()
                        .code(500)
                        .message(e.getMessage())
                        .build()
                ), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Data
    @Builder
    @RequiredArgsConstructor
    @AllArgsConstructor
    public static class ErrorItem {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private int code;
        private String message;
    }

    public static class ErrorResponse {
        private List<ErrorItem> errors = new ArrayList<>();

        public List<ErrorItem> getErrors() {
            return errors;
        }

        public void setErrors(List<ErrorItem> errors) {
            this.errors = errors;
        }

        public void addError(ErrorItem error) {
            this.errors.add(error);
        }

    }
}
