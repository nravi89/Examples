package xmlParsing;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@Data
public final class Employee {
    @JacksonXmlProperty(localName = "id", isAttribute = true)
    private String id;
    @JacksonXmlProperty(localName = "first_name")
    private String firstName;
    @JacksonXmlProperty(localName = "last_name")
    private String lastName;
    @JacksonXmlProperty(localName = "age")
    private int age;
}
