package com.aysoft.onionproject.infrastructure.seedwork.util;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import java.io.IOException;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

public final class FileUtils {

    static public String[] findFilesPaths(String pattern, String filename, String extension, int levelsToSkip, String delimiter) {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(FileUtils.class.getClassLoader());
        try {
            Resource[] resources = resolver.getResources(pattern);
            return stream(resources)
                    .filter(resource -> resource.getFilename().startsWith(filename) && resource.getFilename().endsWith(extension))
                    .map(resource -> {
                        try {
                            String[] path = resource.getURL().getPath().split("/");
                            return stream(path)
                                    .skip(path.length - levelsToSkip)
                                    .map(line -> line.endsWith(extension) ? line.substring(0, filename.length()) : line)
                                    .collect(joining(delimiter));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }).toArray(String[]::new);
        } catch (Exception ex) {
            throw new RuntimeException("Error when trying to find file resources!", ex);
        }
    }

    private FileUtils() {
        throw new AssertionError("No 'FileUtils' instances for you!");
    }
}
