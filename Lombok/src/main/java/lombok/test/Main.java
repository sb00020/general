package lombok.test;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import lombok.test.Person.Gender;

public class Main {

	public static void main(String[] args) {
		
		Person p = new Person();
	
		p.setGender(Gender.Female);
		
		IntegerProperty i = new SimpleIntegerProperty();
		
		i.set(1);
		i.get();
		
	
	}

}
