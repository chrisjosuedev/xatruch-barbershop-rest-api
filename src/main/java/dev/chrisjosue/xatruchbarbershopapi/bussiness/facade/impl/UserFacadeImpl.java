package dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.user.DomainToUserDtoMapper;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.cases.UserCases;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.UserFacade;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.user.UserRequestToDomainMapper;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.UserService;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.PasswordUpdateRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.UserRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.UserUpdateRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {
    private final UserService userService;
    private final UserCases userCases;
    private final UserRequestToDomainMapper userRequestToDomainMapper;
    private final DomainToUserDtoMapper domainToUserDtoMapper;

    @Override
    public void signUp(UserRequest userRequest) {
        var userDomain = userRequestToDomainMapper.toDomain(userRequest);
        var userSet = userCases.setUserToSave(userDomain);
        userService.save(userSet);
    }

    @Override
    public UserDto update(Long id, UserUpdateRequest userUpdateRequest) {
        var userToUpdate = userService.findById(id);
        var userSet = userCases.setUserToUpdate(userUpdateRequest, userToUpdate);
        var userUpdated = userService.update(userSet);
        return domainToUserDtoMapper.toDto(userUpdated);
    }

    @Override
    public UserDto findById(Long id) {
        return domainToUserDtoMapper.toDto(userService.findById(id));
    }

    @Override
    public void updatePassword(Long id, PasswordUpdateRequest passwordUpdateRequest) {
        var userToUpdate = userService.findById(id);
        var userSet = userCases.setUserToUpdatePassword(passwordUpdateRequest, userToUpdate);
        userService.update(userSet);
    }
}
