package dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.UploadFileFacade;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.impl.UploadImageService;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.ImageDto;
import dev.chrisjosue.xatruchbarbershopapi.storage.LocalStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class UploadFileFacadeImpl implements UploadFileFacade {
    @Override
    public ImageDto uploadImage(MultipartFile file) {
        var uploadImage = new UploadImageService(new LocalStorage());
        return uploadImage.upload(file);
    }
}
