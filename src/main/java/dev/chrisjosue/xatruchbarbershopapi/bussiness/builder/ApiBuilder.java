package dev.chrisjosue.xatruchbarbershopapi.bussiness.builder;

import dev.chrisjosue.xatruchbarbershopapi.common.enums.Responses;
import org.springframework.http.ResponseEntity;

public interface ApiBuilder {
    ResponseEntity<Object> build(int httpCode, String message, Object object, Responses responses);
}
