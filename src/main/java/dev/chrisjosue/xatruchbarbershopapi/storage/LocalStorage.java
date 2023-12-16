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

import static dev.chrisjosue.xatruchbarbershopapi.common.utils.constant.ImageStorageConstant.*;

@Component("localStorage")
@RequiredArgsConstructor
public class LocalStorage implements StorageRepository {

    @Override
    public String uploadImage(MultipartFile file) {
        try {
            boolean contains = Arrays.asList(VALID_IMAGE_EXT).contains(Objects.requireNonNull(file.getContentType()));
            if (!contains) throw new BadRequestException("Extension inválida, solo .jpg, .jpeg and .png permitida.", "image");

            String newImageName = UUID.randomUUID().toString().concat("." + file.getContentType().split("/")[1]);
            Path fileNameAndPath = Paths.get(UPLOAD_IMAGE_LOCAL_DIRECTORY, newImageName);

            Files.write(fileNameAndPath, file.getBytes());

            return fileNameAndPath.toUri().toString();
        } catch (IOException ioException) {
            throw new BadRequestException(ioException.getMessage(), "image");
        }
    }
}
