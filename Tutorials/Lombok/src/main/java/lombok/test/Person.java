package lombok.test;

import lombok.Data;

@Data
public class Person {
    enum Gender { Male, Female }

    /*@NonNull*/ private String name;
    /*@NonNull*/ private Gender gender;
    
    private String ssn;
    private String address;
    private String city;
    private String state;
    private String zip;
}