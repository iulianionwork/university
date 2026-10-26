package university.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFound(NotFoundException notFound) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> accessDenied(AccessDeniedException accessDenied) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(accessDenied.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> illegalState(IllegalStateException illegalState) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(illegalState.getMessage());
    }

}

