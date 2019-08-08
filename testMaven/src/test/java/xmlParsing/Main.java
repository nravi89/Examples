package xmlParsing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.lang3.StringUtils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new XmlMapper();
        // Reads from XML and converts to POJO
        Employee[] employee = objectMapper.readValue(
                StringUtils.toEncodedString(Files.readAllBytes(Paths.get("D:\\backup\\git\\Examples\\testMaven\\src\\test\\resources\\emp.xml")), StandardCharsets.UTF_8),
                Employee[].class);
        System.out.println(employee[0]);
        System.out.println(employee[1]);
        // Reads from POJO and converts to XML
        //objectMapper.writeValue(new File("/tmp/fieldsets.xml"), fieldSet);
    }
}