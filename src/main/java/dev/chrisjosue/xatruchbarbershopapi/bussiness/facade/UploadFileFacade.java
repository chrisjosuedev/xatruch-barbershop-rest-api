package dev.chrisjosue.xatruchbarbershopapi.bussiness.facade;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.ImageDto;
import org.springframework.web.multipart.MultipartFile;

public interface UploadFileFacade {
    ImageDto uploadImage(MultipartFile file);
}
