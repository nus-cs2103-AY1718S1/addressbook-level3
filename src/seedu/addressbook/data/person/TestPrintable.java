package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.tag.UniqueTagList;

public class TestPrintable {

    public static void main(String[] args) throws IllegalValueException{

        Name name = new Name("Sherlock Holmes");
        Phone phone = new Phone("123456", true);
        Email email = new Email("abc@123.com", true);
        Address address = new Address("221B Baker Street", true);
        UniqueTagList tags = new UniqueTagList();
        Person aGuy = new Person(name, phone, email, address, tags);

        System.out.println(getPrintableString(aGuy.getName(), aGuy.getPhone(), aGuy.getEmail(), aGuy.getAddress()));

    }

    public static String getPrintableString(Printable printName, Printable printPhone, Printable printEmail, Printable printAddress) {
        return printName.getPrintableString() + " " + printPhone.getPrintableString() + " " + printEmail.getPrintableString() + " " + printAddress.getPrintableString();
    }

}
