package dev.chrisjosue.xatruchbarbershopapi.storage;

import dev.chrisjosue.xatruchbarbershopapi.common.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class LocalStorage implements StorageRepository {
    private static final String[] VALID_IMAGE_EXT = { "image/jpg", "image/png", "image/jpeg" };
    private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads/images";

    @Override
    public String uploadImage(MultipartFile file) {
        try {
            boolean contains = Arrays.asList(VALID_IMAGE_EXT).contains(Objects.requireNonNull(file.getContentType()));
            if (!contains) throw new BadRequestException("Invalid Extension, only .jpg, .jpeg and .png allowed.", "image");

            String newImageName = UUID.randomUUID().toString().concat("." + file.getContentType().split("/")[1]);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, newImageName);

            Files.write(fileNameAndPath, file.getBytes());

            return fileNameAndPath.toUri().toString();
        } catch (IOException ioException) {
            throw new BadRequestException(ioException.getMessage(), "image");
        }
    }
}
