package dev.chrisjosue.xatruchbarbershopapi.common;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

@RequiredArgsConstructor
public class ResponseBuilder {
    public static ResponseEntity<Object> responseHandler(int httpCode, String message, Object objData) {
        HttpStatus httpStatus = HttpStatus.valueOf(httpCode);
        if (objData == null) objData = Collections.emptyList();
        ApiResponse response = new ApiResponse(httpStatus.name(), httpStatus.value(), message, objData);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> responseHandler(int httpCode, String message) {
        HttpStatus httpStatus = HttpStatus.valueOf(httpCode);
        ApiResponse response = new ApiResponse(httpStatus.name(), httpStatus.value(), message, Collections.emptyList());
        return new ResponseEntity<>(response, httpStatus);
    }
}
