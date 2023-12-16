package dev.chrisjosue.xatruchbarbershopapi.storage;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class CloudinaryStorage implements StorageRepository {
    @Override
    public String uploadImage(MultipartFile file) {
        return null;
    }
}
