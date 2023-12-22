package nl.novi.techiteasy.controllers;


import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.exceptions.TelevisionNameTooLongException;
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
    public ResponseEntity<Object> recordNotFound(RecordNotFoundException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

    }

   // Deze exception handler vangt elke TelevisionNameTooLongException op die naar de gebruiker gegooid wordt en returned daar voor in de plaats een ResponseEntity met de Message en de NOT_FOUND-status (404)
        @ExceptionHandler(value = TelevisionNameTooLongException.class)
        public ResponseEntity<String> handleValidationException(TelevisionNameTooLongException exception) {

            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);

        }


    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    }

