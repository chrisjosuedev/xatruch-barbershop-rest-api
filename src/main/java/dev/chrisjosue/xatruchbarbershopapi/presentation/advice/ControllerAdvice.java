package dev.chrisjosue.xatruchbarbershopapi.presentation.advice;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.builder.ApiBuilder;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.errors.ErrorToDtoMapper;
import dev.chrisjosue.xatruchbarbershopapi.common.enums.Responses;
import dev.chrisjosue.xatruchbarbershopapi.common.exceptions.*;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.ErrorDetailResponseDto;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.properties.Field;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ControllerAdvice {
    private final ErrorToDtoMapper errorMapper;
    private final ApiBuilder apiBuilder;

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> exception(Exception exception) {
        var errorDto = errorMapper.errorToDto("Excepción en la ejecución.", exception.getMessage());
        return newErrorExceptionsResponse(List.of(errorDto), 500, "Error en la ejecución de la acción en el servidor.");
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<Object> businessException(BusinessException businessException) {
        var errorDto = errorMapper.errorToDto(businessException.getField(), businessException.getMessage());
        return newErrorExceptionsResponse(List.of(errorDto), 400, "Error al procesar acción.");
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<Object> accessDeniedException(AccessDeniedException accessDeniedException) {
        var errorDto = errorMapper.errorToDto("permissions", accessDeniedException.getMessage());
        return newErrorExceptionsResponse(List.of(errorDto), 403, "Error al intentar acceder a un recurso.");
    }

    @ExceptionHandler(value = ConflictException.class)
    public ResponseEntity<Object> conflictException(ConflictException conflictException) {
        var errorDto = errorMapper.errorToDto(conflictException.getField(), conflictException.getMessage());
        return newErrorExceptionsResponse(List.of(errorDto), conflictException.getHttpStatus().value(), "Conflictos en el recurso.");
    }

    @ExceptionHandler(value = InternalServerException.class)
    public ResponseEntity<Object> conflictException(InternalServerException internalServerException) {
        var errorDto = errorMapper.errorToDto(internalServerException.getField(), internalServerException.getMessage());
        return newErrorExceptionsResponse(List.of(errorDto), internalServerException.getHttpStatus().value(), "Conflictos en el recurso.");
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> badRequestException(BadRequestException badRequestException) {
        var errorDto = errorMapper.errorToDto(badRequestException.getField(), badRequestException.getMessage());
        return newErrorExceptionsResponse(List.of(errorDto), badRequestException.getHttpStatus().value(), "Error en el procesamiento.");
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        var errorDto = errorMapper.errorToDto(resourceNotFoundException.getField(), resourceNotFoundException.getMessage());
        return newErrorExceptionsResponse(List.of(errorDto), resourceNotFoundException.getHttpStatus().value(), "Error intentando encontrar el recurso.");
    }

    @ExceptionHandler(value = ForbiddenException.class)
    public ResponseEntity<Object> forbiddenException(ForbiddenException forbiddenException) {
        var errorDto = errorMapper.errorToDto(forbiddenException.getField(), forbiddenException.getMessage());
        return newErrorExceptionsResponse(List.of(errorDto), forbiddenException.getHttpStatus().value(), "Error intentando acceder el recurso.");
    }

    @ExceptionHandler(value = MethodNotAllowedException.class)
    public ResponseEntity<Object> methodNotAllowedException(MethodNotAllowedException methodNotAllowedException) {
        var errorDto = errorMapper.errorToDto(methodNotAllowedException.getField(), methodNotAllowedException.getMessage());
        return newErrorExceptionsResponse(List.of(errorDto), methodNotAllowedException.getHttpStatus().value(), "Error al intentar realizar petición.");
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<Object> badCredentialsException(BadCredentialsException badCredentialsException) {
        var errorDto = errorMapper.errorToDto("password", badCredentialsException.getMessage());
        return newErrorExceptionsResponse(List.of(errorDto), 403, "Error intentando autenticar al usuario.");
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<Object> userNotFoundException(UsernameNotFoundException usernameNotFoundException) {
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
        var errorDetails = methodArgumentNotValidException.getBindingResult().getAllErrors();
        List<ErrorDetailResponseDto> errors = errorDetails
                .stream()
                .map(errorMapper -> {
                    if (errorMapper instanceof FieldError fieldError) {
                        return new ErrorDetailResponseDto(fieldError.getField(), fieldError.getDefaultMessage());
                    }
                    return new ErrorDetailResponseDto(errorMapper.getCode(), errorMapper.getDefaultMessage());
                })
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
