package finalproject.EcommerceApp.exception;

import finalproject.EcommerceApp.dto_response.ApiResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseDTO<Void>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponseDTO.fail(ex.getMessage()));
    }

    @ExceptionHandler(UnauthorizedOperationException.class)
    public ResponseEntity<ApiResponseDTO<Void>> handleUnauthorizedOperationException(UnauthorizedOperationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponseDTO.fail(ex.getMessage()));
    }

    @ExceptionHandler(ExternalServiceException.class)
    public ResponseEntity<ApiResponseDTO<Void>> handleExternalServiceException(ExternalServiceException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponseDTO.fail(ex.getMessage()));
    }
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiResponseDTO<Void>> handleIllegalStateException(IllegalStateException ex) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponseDTO.fail(ex.getMessage()));
    }
    @ExceptionHandler(InsufficientInventoryException.class)
    public ResponseEntity<ApiResponseDTO<Void>> handleInsufficientInventoryException(InsufficientInventoryException ex) {
        return ResponseEntity.status(HttpStatus.MULTI_STATUS).body(ApiResponseDTO.fail(ex.getMessage()));
    }


}
