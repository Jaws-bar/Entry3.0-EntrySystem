package com.entry.entrydsm.image.controller;

import com.entry.entrydsm.support.AcceptanceTest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;

public class ImageUploadAcceptanceTest extends AcceptanceTest {

    private static final String API_PROFILE_IMAGE_URL = "/api/me/profile/image";
    private MockMultipartFile mockPng;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        File file = temporaryFolder.newFile("temporary.png");
        mockPng = new MockMultipartFile("file", file.getName(), "image/png", "content".getBytes());
    }

    @Test
    public void upload() throws Exception {
        requestFileWithAuth(API_PROFILE_IMAGE_URL, mockPng)
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void uploadWithoutAuth() throws Exception {
        requestFile(API_PROFILE_IMAGE_URL, mockPng)
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    public void uploadWithoutFileExtension() throws Exception {
        MockMultipartFile mockPng = new MockMultipartFile("file",
                "test",
                "image/png",
                "asdf".getBytes());
        requestFileWithAuth(API_PROFILE_IMAGE_URL, mockPng)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    public void uploadEmptyFile() throws Exception {
        MockMultipartFile mockPng = new MockMultipartFile("file",
                "test.png",
                "image/png",
                "".getBytes());
        requestFileWithAuth(API_PROFILE_IMAGE_URL, mockPng)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    public void uploadWithWrongContentType() throws Exception {
        MockMultipartFile mockPng = new MockMultipartFile("file",
                "test.png",
                "text/html",
                "asdf".getBytes());
        requestFileWithAuth(API_PROFILE_IMAGE_URL, mockPng)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
