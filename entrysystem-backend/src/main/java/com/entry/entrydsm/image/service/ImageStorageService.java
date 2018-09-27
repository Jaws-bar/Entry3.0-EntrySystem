package com.entry.entrydsm.image.service;

import com.entry.entrydsm.image.exception.FileNotFoundException;
import com.entry.entrydsm.image.exception.InvalidFileException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
//@Profile("local")
@Service
public class ImageStorageService implements FileUploadService {

    private static final String PROFILE_IMAGE_SRC = "/api/me/profile/image";

    private final String imageStoragePath;

    private final String fullImageStoragePath;


    public ImageStorageService(@Value("${user.home}") String userHomePath,
                               @Value("${local.storage.imageStoragePath}") String imageStoragePath) {
        this.imageStoragePath = imageStoragePath;
        this.fullImageStoragePath = userHomePath + imageStoragePath;
    }

    @PostConstruct
    private void init() throws IOException {
        Path rootDirPath = Paths.get(fullImageStoragePath);
        if (!Files.exists(rootDirPath)) {
            Files.createDirectories(rootDirPath);
        }
    }

    @Override
    public String store(MultipartFile file, String fileName) throws IOException, InvalidFileException {
        validateImage(file);

        String filePath = getFilePath(fullImageStoragePath, fileName);
        File targetFile = new File(filePath);

        Files.deleteIfExists(Paths.get(targetFile.getPath()));

        writeFile(targetFile, file.getInputStream());
        return fileName;
    }

    private String getFilePath(String path, String fileName) {
        return String.format("%s/%s", path, fileName);
    }

    private void writeFile(File target, InputStream inputStream) throws IOException {
        try (InputStream is = inputStream; FileOutputStream fos = new FileOutputStream(target)) {
            IOUtils.copy(is, fos);
        } catch (IOException e) {
            Files.deleteIfExists(Paths.get(target.getName()));
            throw e;
        }
    }

    public Resource loadAsResource(String filename) throws FileNotFoundException {
        Path file = resolvePath(filename);
        Resource resource;
        try {
            resource = new UrlResource(file.toUri());
        } catch (MalformedURLException e) {
            throw new FileNotFoundException(e);
        }

        if (resource.exists() || resource.isReadable()) {
            return resource;
        }

        throw new FileNotFoundException();
    }

    private Path resolvePath(String filename) {
        return Paths.get(fullImageStoragePath).resolve(filename);
    }

    private void validateImage(MultipartFile file) throws InvalidFileException {
        if (file.isEmpty() || !file.getContentType().startsWith("image/")) {
            throw new InvalidFileException("올바르지 않은 확장자 또는 파일이 비어있습니다.");
        }
    }

    public void dropIfExists(String fileName) throws IOException {
        Files.deleteIfExists(Paths.get(getFilePath(fullImageStoragePath, fileName)));
    }
}
