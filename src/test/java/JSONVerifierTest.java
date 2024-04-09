import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JSONVerifierTest {

    @Test
    public void testVerifyJSONWithExample1() throws IOException {
        List<Boolean> result = JSONVerifier.verifyJSON("src/main/resources/exampleFile.json");
        assertEquals(1, result.size());
        assertEquals(false, result.getFirst());
    }

    @Test
    public void testVerifyJSONWithExample2() throws IOException {
        List<Boolean> result = JSONVerifier.verifyJSON("src/main/resources/exampleFile2.json");
        assertEquals(3, result.size());
        assertEquals(false, result.getFirst());
        assertEquals(false, result.get(1));
        assertEquals(true, result.get(2));
    }

    @Test
    public void testVerifyJSON_InvalidFile() throws IOException {
        assertThrows(FileNotFoundException.class, () -> {
            JSONVerifier.verifyJSON("nonexistentFile.json");
        });
    }

    @Test
    public void testVerifyJSON_EmptyFile() throws IOException {

        IOException exception = assertThrows(IOException.class, () -> {
            JSONVerifier.verifyJSON("src/main/resources/emptyFile.json");
        });

        String expectedMessage = "Invalid JSON format: PolicyDocument or Statement field is missing";
        String actualMessage = exception.getMessage();
        assert actualMessage.contains(expectedMessage);
    }

    @Test
    public void testVerifyJSON_NoStatements() throws IOException {
        List<Boolean> result = JSONVerifier.verifyJSON("src/main/resources/noStatementsFile.json");
        assertTrue(result.isEmpty(), "Expected result list to be empty for JSON file with no statements");
    }

    @Test
    public void testVerifyJSON_StatementsMissingResourceField() throws IOException {
        List<Boolean> result = JSONVerifier.verifyJSON("src/main/resources/statementsMissingResource.json");
        assertEquals(2, result.size(), "Expected result list size to match number of statements");
        assertEquals(true, result.getFirst());
        assertEquals(false, result.get(1));
    }
}