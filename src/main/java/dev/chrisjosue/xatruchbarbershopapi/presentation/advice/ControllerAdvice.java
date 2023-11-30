package dev.chrisjosue.xatruchbarbershopapi.presentation.advice;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.builder.ApiBuilder;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.errors.ErrorToDtoMapper;
import dev.chrisjosue.xatruchbarbershopapi.common.enums.Responses;
import dev.chrisjosue.xatruchbarbershopapi.common.exceptions.ConflictException;
import dev.chrisjosue.xatruchbarbershopapi.common.exceptions.ResourceNotFoundException;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.ErrorDetailResponseDto;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvice {
    private final ErrorToDtoMapper errorMapper;
    private final ApiBuilder apiBuilder;

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> exception(Exception exception) {
        var errorDto = errorMapper.errorToDto("Excepción en la ejecución.", exception.getMessage());
        return newErrorExceptionsResponse(List.of(errorDto), 500, "Error en la ejecución de la acción en el servidor.");
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<Object> accessDeniedException(AccessDeniedException accessDeniedException) {
        var errorDto = errorMapper.errorToDto("permissions", accessDeniedException.getMessage());
        return newErrorExceptionsResponse(List.of(errorDto), 403, "Error al intentar acceder a un recurso.");
    }

    @ExceptionHandler(value = ConflictException.class)
    public ResponseEntity<Object> conflictException(ConflictException conflictException) {
        var errorDto = errorMapper.errorToDto(conflictException.getField(), conflictException.getMessage());
        return newErrorExceptionsResponse(List.of(errorDto), 409, "Conflictos en el recurso.");
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        var errorDto = errorMapper.errorToDto(resourceNotFoundException.getField(), resourceNotFoundException.getMessage());
        return newErrorExceptionsResponse(List.of(errorDto), 404, "Error intentando encontrar el recurso.");
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<Object> badCredentialsException(BadCredentialsException badCredentialsException) {
        var errorDto = errorMapper.errorToDto("password", badCredentialsException.getMessage());
        return newErrorExceptionsResponse(List.of(errorDto), 403, "Error intentando autenticar al usuario.");
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<Object>userNotFoundException(UsernameNotFoundException usernameNotFoundException) {
        var errorDto = errorMapper.errorToDto("email", usernameNotFoundException.getMessage());
        return newErrorExceptionsResponse(List.of(errorDto), 403, "Error intentando autenticar al usuario.");
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException mismatchException) {
        var errorDto = errorMapper.errorToDto(mismatchException.getClass().getSimpleName(), mismatchException.getMessage());
        return newErrorExceptionsResponse(List.of(errorDto), 400, "Error en el tipo de argumento.");
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        var errorDetails = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        List<ErrorDetailResponseDto> errors = errorDetails
                .stream()
                .map(errorMapper -> new ErrorDetailResponseDto(errorMapper.getField(), errorMapper.getDefaultMessage()))
                .toList();
        return newErrorExceptionsResponse(errors, 400, "Argumentos inválidos.");
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Object> constraintViolationException(ConstraintViolationException constraintViolationException) {
        var errorDetails = constraintViolationException.getConstraintViolations();
        List<ErrorDetailResponseDto> errors = errorDetails
                .stream()
                .map(errorMapper -> new ErrorDetailResponseDto(
                        errorMapper.getPropertyPath().toString(),
                        errorMapper.getMessage()))
                .toList();
        return newErrorExceptionsResponse(errors, 400, "Error intentando guardar datos.");
    }

    private ResponseEntity<Object> newErrorExceptionsResponse(List<ErrorDetailResponseDto> errors, int statusCode, String globalMessage) {
        return apiBuilder.build(
                statusCode,
                globalMessage,
                errors,
                Responses.ERRORS);
    }
}
