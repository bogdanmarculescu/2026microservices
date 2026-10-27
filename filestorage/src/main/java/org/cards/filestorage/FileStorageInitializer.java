package org.cards.filestorage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Component
public class FileStorageInitializer implements ApplicationRunner {
    private final Path root;

    public FileStorageInitializer(
            @Value("${file.storage.path:/data/files}") String path)
            throws IOException {
        this.root = Path.of(path).toAbsolutePath().normalize();
        Files.createDirectories(root);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(
                "classpath*:seedData/*"
        );

        for (Resource resource : resources) {
            if (!resource.exists() || !resource.isReadable()) {
                continue;
            }

            String filename = Path.of(resource.getFilename())
                    .getFileName().toString();

            Path destination = root.resolve(filename).normalize();

            if (!destination.startsWith(root)) {
                throw new IOException("Invalid seed filename");
            }

            // Never overwrite an existing file.
            if (Files.exists(destination)) {
                continue;
            }

            try (InputStream input = resource.getInputStream()) {
                Files.copy(input, destination);
            }
        }
    }
}
