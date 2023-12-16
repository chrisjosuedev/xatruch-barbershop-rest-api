package dev.chrisjosue.xatruchbarbershopapi.bussiness.facade;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.PasswordUpdateRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.UserRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.UserUpdateRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.ImageDto;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.UserDto;
import org.springframework.web.multipart.MultipartFile;

public interface UserFacade {
    UserDto findById(Long id);
    void signUp(UserRequest userRequest);
    UserDto update(Long id, UserUpdateRequest userUpdateRequest);
    void updatePassword(Long id, PasswordUpdateRequest passwordUpdateRequest);
    UserDto updateProfilePicture(String url, Long id);
}
