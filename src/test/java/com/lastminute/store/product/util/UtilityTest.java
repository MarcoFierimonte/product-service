package com.lastminute.store.product.util;

import com.lastminute.store.product.exception.ReadFileException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilityTest {

    @Nested
    class HappyPath {
        @Test
        void readFileShouldReturnAListOfString(@TempDir Path tempDir) throws IOException {
            // given
            Path tmpPath = tempDir.resolve("tmp-test.txt");
            Files.write(tmpPath, Arrays.asList("line0", "line1", "line2", "line3"));

            // when
            List<String> actual = Utility.readFile(tmpPath.toFile());

            // then
            assertThat(actual).containsExactly("line0", "line1", "line2", "line3");
        }
    }

    @Nested
    class UnhappyPath {
        @Test
        void shouldReturnAnEmptyListIfTheFileDoesNotExist() {
            // given
            File input = new File("not_existing_file");
            // when
            Exception exception = assertThrows(
                    ReadFileException.class,
                    () -> Utility.readFile(input));

            // then
            assertTrue(exception.getMessage()
                                .contains("not_existing_file"));
        }
    }
}