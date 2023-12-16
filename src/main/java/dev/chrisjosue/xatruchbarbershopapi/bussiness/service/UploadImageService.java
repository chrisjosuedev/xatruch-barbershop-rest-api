package dev.chrisjosue.xatruchbarbershopapi.bussiness.service;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.ImageDto;
import org.springframework.web.multipart.MultipartFile;

public interface UploadImageService {
    ImageDto upload(MultipartFile file);
}
