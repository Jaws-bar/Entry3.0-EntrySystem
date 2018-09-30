package com.entry.entrydsm.image.controller;

import com.entry.entrydsm.common.config.AuthRequired;
import com.entry.entrydsm.common.response.RestResponse;
import com.entry.entrydsm.image.exception.FileNotFoundException;
import com.entry.entrydsm.image.exception.InvalidFileException;
import com.entry.entrydsm.image.service.ImageStorageService;
import com.entry.entrydsm.user.domain.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
public class ProfileImageController {

    private static final int IMAGE_MAX_SIZE = 5 * 1024 * 1024;

    @Autowired
    private ImageStorageService imageStorageService;


    @ResponseBody
    @AuthRequired
    @Transactional
    @PostMapping("/api/me/profile/image")
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse<String> handleFileUpload(User user, @RequestPart("file") MultipartFile file) throws IOException, InvalidFileException {
        if (file.getSize() > IMAGE_MAX_SIZE) {
            throw new InvalidFileException("이미지 최대 용량을 초과했습니다. (5MB)");
        }
        imageStorageService.dropIfExists(user.getInfo().getImgPath());
        String fileName = imageStorageService.store(file, String.format("%s.%s", RandomStringUtils.randomAlphabetic(25), extractFileExtension(file)));
        user.getInfo().setImgPath(fileName);
        return RestResponse.success(fileName);
    }

    @GetMapping("/images/{filePath}")
    public ResponseEntity<Resource> getImage(@PathVariable String filePath) throws FileNotFoundException {
        Resource file = imageStorageService.loadAsResource(filePath);
        return ResponseEntity.ok()
                .header("Content-Type", "image/*")
                .body(file);
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResponse<?> handleIOException(IOException exception) {
        return RestResponse.error("파일 저장 실패").build();
    }

    @ExceptionHandler(InvalidFileException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResponse<?> handleInvalidFileException(InvalidFileException exception) {
        return RestResponse.error(exception.getMessage()).build();
    }

    private String extractFileExtension(MultipartFile file) throws InvalidFileException {
        int dotIndex = file.getOriginalFilename().lastIndexOf(".");
        if (dotIndex == -1) {
            throw new InvalidFileException("");
        }
        return file.getOriginalFilename().substring(dotIndex + 1);
    }
}