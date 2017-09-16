package seedu.addressbook.ui;

import org.junit.Before;
import org.junit.Test;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

public class FormatterTest {
    private Formatter formatter = null;

    @Before
    public void setUp() {
        formatter = new Formatter();
    }

    @Test
    public void formatter_getPrintableString_printPersonDetails() throws Exception {
        Person testPerson = adam();
        System.out.println(formatter.getPrintableString(testPerson.getAddress(), testPerson.getEmail()));
    }

    private Person adam() throws Exception {
        Name name = new Name("Kik Smith");
        Phone privatePhone = new Phone("111111", true);
        Email email = new Email("adam@gmail.com", false);
        Address privateAddress = new Address("111, alpha street", true);

        return new Person(name, privatePhone, email, privateAddress, new UniqueTagList());
    }
}