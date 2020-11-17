package ru.relex.miniBooking.rest.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.relex.miniBooking.rest.exception.NoSuchObjectException;
import ru.relex.miniBooking.rest.model.ErrorModel;
import ru.relex.miniBooking.services.validation.UserValidationErrors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class BookingExceptionHandler {
    @ExceptionHandler(NoSuchObjectException.class)
    ResponseEntity<?> handleNoSuchObjectException ( NoSuchObjectException e ) {
        return ResponseEntity.status ( HttpStatus.NOT_FOUND ).body ( e );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<List<ErrorModel>> handleConstraintViolationException ( ConstraintViolationException e ) {
        final Set<ConstraintViolation<?>> errors = e.getConstraintViolations ( );
        final var errorModels = errors.stream ( ).
                map ( error -> new ErrorModel ( error.getMessage ( ), UserValidationErrors.getMessageByCode ( error.getMessage ( ) ) ) )
                .collect ( Collectors.toList ( ) );
        return ResponseEntity.status ( HttpStatus.BAD_REQUEST ).body ( errorModels );
    }
}
