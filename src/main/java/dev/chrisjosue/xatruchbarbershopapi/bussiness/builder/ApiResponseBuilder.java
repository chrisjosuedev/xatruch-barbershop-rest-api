package dev.chrisjosue.xatruchbarbershopapi.bussiness.builder;

import dev.chrisjosue.xatruchbarbershopapi.common.enums.Responses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ApiResponseBuilder implements ApiBuilder {

    public ResponseEntity<Object> build(int httpCode, String message,
                                                  Object object,
                                                  Responses responses) {
        HttpStatus httpStatus = HttpStatus.valueOf(httpCode);
        Map<String, Object> customResponse = new LinkedHashMap<>();
        customResponse.put("httpStatus", httpStatus);
        customResponse.put("httpStatusCode", httpStatus.value());
        customResponse.put("message", message);

        if (object == null) return new ResponseEntity<>(customResponse, httpStatus);

        customResponse.put(responses.getType(), object);
        return new ResponseEntity<>(customResponse, httpStatus);
    }

}
