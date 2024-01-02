package dev.chrisjosue.xatruchbarbershopapi.storage;

import com.cloudinary.Cloudinary;
import dev.chrisjosue.xatruchbarbershopapi.common.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static dev.chrisjosue.xatruchbarbershopapi.common.utils.constant.ImageStorageConstant.UPLOAD_IMAGE_CLOUDINARY_DIRECTORY;
import static dev.chrisjosue.xatruchbarbershopapi.common.utils.constant.ImageStorageConstant.VALID_IMAGE_EXT;

@Component("cloudinaryStorage")
@Slf4j
@RequiredArgsConstructor
public class CloudinaryStorage implements StorageRepository {
    private final Cloudinary cloudinary;

    @Override
    public String uploadImage(MultipartFile file) {
        try {
            boolean contains = Arrays.asList(VALID_IMAGE_EXT).contains(Objects.requireNonNull(file.getContentType()));
            if (!contains)
                throw new BadRequestException("Extension inválida, solo .jpg, .jpeg and .png permitida.", "image");

            String newImageName = UUID.randomUUID().toString();

            return this.cloudinary.uploader()
                    .upload(file.getBytes(),
                            Map.of("public_id", UPLOAD_IMAGE_CLOUDINARY_DIRECTORY.concat(newImageName))
                    )
                    .get("url")
                    .toString();
        } catch (IOException ioException) {
            throw new BadRequestException(ioException.getMessage(), "image");
        }
    }
}
