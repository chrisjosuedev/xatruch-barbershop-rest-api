package dev.chrisjosue.xatruchbarbershopapi.bussiness.facade;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.UserRequest;

public interface UserFacade {
    void signUp(UserRequest userRequest);
}
