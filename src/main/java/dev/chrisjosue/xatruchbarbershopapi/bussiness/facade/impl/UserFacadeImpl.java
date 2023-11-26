package dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.adapter.UserAdapter;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.UserFacade;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.user.UserRequestToDomainMapper;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.user.DomainToUserDtoMapper;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.UserService;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.LoginRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.UserRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserFacadeImpl implements UserFacade {
    private final UserService userService;
    private final UserRequestToDomainMapper userRequestToDomainMapper;
    private final DomainToUserDtoMapper domainToUserDtoMapper;
    private final UserAdapter userAdapter;

    @Override
    public void signUp(UserRequest userRequest) {
        var userDomain = userRequestToDomainMapper.authUserToDomain(userRequest);
        var userSet = userAdapter.setUserToSave(userDomain);
        userService.save(userSet);
    }

}
