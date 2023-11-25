package dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.adapter.UserAdapter;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.AuthFacade;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.AuthRequestMapper;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.UserDtoMapper;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.AuthService;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.UserRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthFacadeImpl implements AuthFacade {
    private final AuthService authService;
    private final AuthRequestMapper authRequestMapper;
    private final UserDtoMapper userDtoMapper;
    private final UserAdapter userAdapter;

    @Override
    public UserDto save(UserRequest userRequest) {
        var userDomain = authRequestMapper.authUserToDomain(userRequest);
        var userSet = userAdapter.setUserToSave(userDomain);
        var userCreated = authService.save(userSet);

        /**
         * Authenticate user when SignUp
         */

        return userDtoMapper.toDto(userCreated);
    }
}
