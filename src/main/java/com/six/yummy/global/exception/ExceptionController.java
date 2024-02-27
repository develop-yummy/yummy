package com.six.yummy.global.exception;

import com.six.yummy.global.exception.dto.ExceptionDto;
import javax.xml.crypto.Data;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler({NotFoundUserException.class, NotFoundMenuException.class,
        NotFoundReviewException.class, NotFoundRestaurantException.class,
        NotFoundCartItemException.class})
    public ResponseEntity<ExceptionDto> NotFoundException(NotFoundException e) {
        return ResponseEntity.badRequest()
            .body(new ExceptionDto(e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler({ValidateUserException.class, ValidateReviewException.class})
    public ResponseEntity<ExceptionDto> ValidateException(Exception e) {
        return ResponseEntity.badRequest()
            .body(new ExceptionDto(e.getMessage(), HttpStatus.FORBIDDEN));
    }


    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionDto> NullPointerException(Exception e) {
        return ResponseEntity.badRequest()
            .body(new ExceptionDto(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler({DataIntegrityViolationReviewException.class, DataIntegrityViolationLikeException.class})
    public ResponseEntity<ExceptionDto> DataIntegrityViolationException(
        Exception e) {
        return ResponseEntity.badRequest()
            .body(new ExceptionDto(e.getMessage(),HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(NotFoundFollowException.class)
    public ResponseEntity<ExceptionDto> NotFoundDataIntegrityViolationException(Exception e) {
        return ResponseEntity.badRequest()
            .body(new ExceptionDto(e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(DataIntegrityViolationFollowException.class)
    public ResponseEntity<ExceptionDto> DataIntegrityViolationFollowException(Exception e) {
        return ResponseEntity.badRequest()
            .body(new ExceptionDto(e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(NotMatchRestaurantException.class)
    public ResponseEntity<ExceptionDto> NotMatchRestaurantException(Exception e){
        return ResponseEntity.badRequest()
            .body(new ExceptionDto(e.getMessage(), HttpStatus.BAD_REQUEST));
    }
}