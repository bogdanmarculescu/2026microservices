package org.cards.filestorage;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final FileStorageService storage;

    public FileController(FileStorageService storage) {
        this.storage = storage;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> upload(
            @RequestParam("file") MultipartFile file)
            throws IOException {

        String id = storage.store(file);

        return ResponseEntity.ok(Map.of(
                "id", id,
                "originalFilename", file.getOriginalFilename(),
                "size", file.getSize(),
                "contentType", file.getContentType()
        ));
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> download(
            @PathVariable String filename) throws IOException {

        Resource resource = storage.load(filename);

        String contentType = org.springframework.http.MediaType
                .APPLICATION_OCTET_STREAM_VALUE;

        try {
            String detected = java.nio.file.Files.probeContentType(
                    java.nio.file.Path.of(filename)
            );

            if (detected != null) {
                contentType = detected;
            }
        } catch (IOException ignored) {
            // Keep the binary fallback.
        }

        return ResponseEntity.ok()
                .contentType(
                        org.springframework.http.MediaType.parseMediaType(contentType)
                )
                .body(resource);
    }

    @DeleteMapping("/{filename}")
    public ResponseEntity<Void> delete(
            @PathVariable String filename) throws IOException {

        storage.delete(filename);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NoSuchFileException.class)
    public ResponseEntity<Void> notFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Void> badRequest() {
        return ResponseEntity.badRequest().build();
    }

}
