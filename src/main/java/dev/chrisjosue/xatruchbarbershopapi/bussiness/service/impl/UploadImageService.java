package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.impl;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.ImageDto;
import dev.chrisjosue.xatruchbarbershopapi.storage.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UploadImageService {
    private final StorageRepository storageRepository;

    public ImageDto upload(MultipartFile file) {
        String url = storageRepository.uploadImage(file);
        return new ImageDto(url);
    }
}
