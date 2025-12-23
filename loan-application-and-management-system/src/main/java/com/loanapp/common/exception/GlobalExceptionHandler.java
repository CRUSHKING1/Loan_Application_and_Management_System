package com.loanapp.common.exception;




import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorInfo> handleResourceNotFound(ResourceNotFoundException ex) {
//
//        ErrorInfo errorInfo = new ErrorInfo(
//                ex.getMessage(),
//                HttpStatus.NOT_FOUND.value()
//        );
//
//        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(BusinessException.class)
//    public ResponseEntity<ErrorInfo> handleBusinessException(BusinessException ex) {
//
//        ErrorInfo errorInfo = new ErrorInfo(
//                ex.getMessage(),
//                HttpStatus.BAD_REQUEST.value()
//        );
//
//        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> handleGenericException(Exception ex) {

        ErrorInfo errorInfo = new ErrorInfo(
                "Internal Server Error",
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
