package dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.UploadFileFacade;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.UploadImageService;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.impl.UploadImageServiceImpl;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.ImageDto;
import dev.chrisjosue.xatruchbarbershopapi.storage.CloudinaryStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class UploadFileFacadeImpl implements UploadFileFacade {
    private final UploadImageService uploadImageService;

    @Override
    public ImageDto uploadImage(MultipartFile file) {
        return uploadImageService.upload(file);
    }
}
