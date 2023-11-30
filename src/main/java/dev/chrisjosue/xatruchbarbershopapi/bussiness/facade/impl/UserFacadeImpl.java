package dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.setter.UserSetter;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.UserFacade;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.user.UserRequestToDomainMapper;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.app.UserService;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserFacadeImpl implements UserFacade {
    private final UserService userService;
    private final UserRequestToDomainMapper userRequestToDomainMapper;
    private final UserSetter userSetter;

    @Override
    public void signUp(UserRequest userRequest) {
        var userDomain = userRequestToDomainMapper.toDomain(userRequest);
        var userSet = userSetter.setUserToSave(userDomain);
        userService.save(userSet);
    }

}
