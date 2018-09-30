package com.entry.entrydsm.image.service;

import com.entry.entrydsm.image.exception.FileNotFoundException;
import com.entry.entrydsm.image.exception.InvalidFileException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageStorageServiceTest {
    private ImageStorageService imageStorageService;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();


    private String tempFileName = "test.png";

    @Before
    public void setUp() throws Exception {
        imageStorageService = new ImageStorageService(temporaryFolder.getRoot().getPath(), "");
    }

    @Test
    public void putObject() throws IOException, InvalidFileException {
        File tempFile = temporaryFolder.newFile();
        InputStream fis = new FileInputStream(tempFile);
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", tempFile.getName(), "image/png", fis) {
            @Override
            public boolean isEmpty() {
                return false;
            }
        };
        String response = imageStorageService.store(mockMultipartFile, tempFileName);

        assertThat(temporaryFolder.getRoot().listFiles()).hasSize(2);
        assertThat(response).isEqualTo(tempFileName);
    }

    @Test
    public void putObjectAlreadyExist() throws IOException, InvalidFileException {
        File tempFile = temporaryFolder.newFile();
        InputStream fis = new FileInputStream(tempFile);
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", tempFile.getName(), "image/png", fis) {
            @Override
            public boolean isEmpty() {
                return false;
            }
        };
        String response1 = imageStorageService.store(mockMultipartFile, tempFileName);
        String response2 = imageStorageService.store(mockMultipartFile, tempFileName);

        assertThat(temporaryFolder.getRoot().listFiles()).hasSize(2);
        assertThat(response1).isEqualTo(tempFileName);
        assertThat(response2).isEqualTo(tempFileName);
    }

    @Test
    public void loadAsResource() throws FileNotFoundException, IOException {
        File tempFile = temporaryFolder.newFile(tempFileName);
        Resource resource = imageStorageService.loadAsResource(tempFileName);

        assertThat(resource.exists()).isTrue();
    }

    @Test(expected = FileNotFoundException.class)
    public void loadAsResourceDoesNotExist() throws FileNotFoundException {
        imageStorageService.loadAsResource("not_exist.png");
    }
}