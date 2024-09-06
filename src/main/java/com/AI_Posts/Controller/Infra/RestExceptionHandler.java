package com.AI_Posts.Controller.Infra;

import com.AI_Posts.Exception.User.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<RestErrorMessage> userNotFoundHandler(UserNotFoundException exception){
        RestErrorMessage erro = new RestErrorMessage(HttpStatus.NOT_FOUND,exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

}
