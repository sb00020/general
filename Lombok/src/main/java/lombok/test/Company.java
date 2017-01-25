package lombok.test;


import java.util.List;

import lombok.Data;

@Data(staticConstructor="of")
public class Company {
    private Person founder;
    private String name;
    private List<Person> employees;
}
