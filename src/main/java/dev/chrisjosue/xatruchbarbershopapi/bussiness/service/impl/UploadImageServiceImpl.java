package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.UploadImageService;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.ImageDto;
import dev.chrisjosue.xatruchbarbershopapi.storage.StorageRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadImageServiceImpl implements UploadImageService {
    private final StorageRepository storageRepository;

    public UploadImageServiceImpl(@Qualifier("cloudinaryStorage") StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public ImageDto upload(MultipartFile file) {
        String url = storageRepository.uploadImage(file);
        return new ImageDto(url);
    }
}
