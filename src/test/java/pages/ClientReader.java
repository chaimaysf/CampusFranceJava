package pages;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class ClientReader {

    public static Client readFromJson(String fileName) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(
                new File("src/test/resources/testData/" + fileName),
                Client.class
        );
    }
}