package com.entry.entrydsm.image.service;


import com.entry.entrydsm.image.exception.InvalidFileException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {
    String store(MultipartFile file, String fileName) throws IOException, InvalidFileException;
}
