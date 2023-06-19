package onlinebookstore.bookstoreservice.common.exceptions;

import onlinebookstore.bookstoreservice.common.exceptions.custom.NotFoundException;
import onlinebookstore.bookstoreservice.common.exceptions.custom.UnAuthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String >> handleMethodArgsNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> res = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(err -> {
            String field = ((FieldError)err).getField();
            String message = err.getDefaultMessage();
            res.put(field, message);
        });

        return new ResponseEntity<Map<String, String>>(res, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<Map<String, String>>handleDataNotFoundException(UnAuthorizedException ex){

        Map<String, String> res = new HashMap<>();
        res.put("message", ex.getMessage());

        return new ResponseEntity<Map<String, String>>(res, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>>handleDataNotFoundException(NotFoundException ex){

        Map<String, String> res = new HashMap<>();
        res.put("message", ex.getMessage());

        return new ResponseEntity<Map<String, String>>(res, HttpStatus.BAD_REQUEST);
    }
}
