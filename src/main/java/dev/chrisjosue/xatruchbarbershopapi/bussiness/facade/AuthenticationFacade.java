package dev.chrisjosue.xatruchbarbershopapi.bussiness.facade;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.AuthenticationDtoResponse;

public interface AuthenticationFacade {
    AuthenticationDtoResponse authenticateUser(String username, String password);
}
