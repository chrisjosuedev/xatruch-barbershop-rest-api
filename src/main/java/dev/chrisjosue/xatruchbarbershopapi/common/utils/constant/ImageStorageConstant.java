package dev.chrisjosue.xatruchbarbershopapi.common.utils.constant;

public class ImageStorageConstant {
    public static final String[] VALID_IMAGE_EXT = { "image/jpg", "image/png", "image/jpeg" };
    public static final String UPLOAD_IMAGE_LOCAL_DIRECTORY = System.getProperty("user.dir") + "/uploads/images";
    public static final String UPLOAD_IMAGE_CLOUDINARY_DIRECTORY = "barber-app/";
}
