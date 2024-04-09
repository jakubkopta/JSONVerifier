import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONVerifier {
    public static void main(String[] args) throws IOException {

        String filePath = "src/main/resources/exampleFile2.json";

        System.out.println(verifyJSON(filePath));
    }

    public static List<Boolean> verifyJSON (String filePath) throws IOException {

        List<Boolean> result = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(new File(filePath));
        JsonNode policyDocumentNode = rootNode.get("PolicyDocument");

        if (policyDocumentNode != null && policyDocumentNode.has("Statement")) {

            JsonNode statementsNode = policyDocumentNode.get("Statement");

            for (JsonNode statement : statementsNode) {
                JsonNode resourceNode = statement.get("Resource");
                if (resourceNode != null) {
                    String resourceValue = resourceNode.asText();
                    result.add(!resourceValue.equals("*"));
                } else {
                    result.add(true);
                }
            }
        } else {
            throw new IOException("Invalid JSON format: PolicyDocument or Statement field is missing");
        }

        return result;
    }
}
