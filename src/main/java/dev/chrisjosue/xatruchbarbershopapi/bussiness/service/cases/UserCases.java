package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.cases;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.PasswordUpdateRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.UserUpdateRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.User;

public interface UserCases {
    User setUserToSave(User user);
    User setUserToUpdate(UserUpdateRequest userUpdateRequest, User user);
    User setUserToUpdatePassword(PasswordUpdateRequest passwordUpdateRequest, User user);
}
