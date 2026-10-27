package org.cards.filestorage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FilestorageApplicationTests {

    @Test
    void contextLoads() {
    }

    @TempDir
    Path tempDir;

    private FileStorageService storage;

    @BeforeEach
    void setUp() throws IOException {
        storage = new FileStorageService(tempDir.toString());
    }

    @Test
    void storeAndloadFile() throws IOException{
        //Create contend and convert to multipart file
        byte[] content = "test image content".getBytes(StandardCharsets.UTF_8);

        MockMultipartFile upload = new MockMultipartFile(
                "file",
                "ace_of_spades.svg",
                "image/svg+xml",
                content
        );
        //Upload
        String filename = storage.store(upload);

        assertEquals("ace_of_spades.svg", filename);
        assertTrue(Files.exists(tempDir.resolve(filename)));

        //Retrieve and check it's the same
        Resource result = storage.load(filename);

        assertTrue(result.exists());
        assertArrayEquals(content, result.getContentAsByteArray());
    }

    @Test
    void loadMissingFileThrowsException() {
        assertThrows(
                NoSuchFileException.class,
                () -> storage.load("missing.svg")
        );
    }



}
