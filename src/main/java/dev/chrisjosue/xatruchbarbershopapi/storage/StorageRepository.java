package dev.chrisjosue.xatruchbarbershopapi.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageRepository {
    String uploadImage(MultipartFile file);
}
