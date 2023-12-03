package nl.novi.techiteasy.controllers;

import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
    public class ExceptionController {

    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> exception(RecordNotFoundException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

    }

    //    Deze exception handler vangt elke IndexOutOfBoundsException (deze exception komt uit de java.utils package) op die naar de gebruiker gegooid wordt en returned daar voor in de plaats een ResponseEntity met de Message en de NOT_FOUND-status (404)

    @ExceptionHandler(value = IndexOutOfBoundsException.class)
    public ResponseEntity<Object> exception(IndexOutOfBoundsException exception) {

        return new ResponseEntity<>("Dit id staat niet in de database", HttpStatus.NOT_FOUND);

    }

   // Deze exception handler vangt elke TelevisionNameTooLongException op die naar de gebruiker gegooid wordt en returned daar voor in de plaats een ResponseEntity met de Message en de NOT_FOUND-status (404)
        @ExceptionHandler(value = ValidationException.class)
        public ResponseEntity<String> handleValidationException(ValidationException exception) {

            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);

        }


    }

