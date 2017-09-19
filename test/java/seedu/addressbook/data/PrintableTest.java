package seedu.addressbook.data;

import org.junit.Before;
import org.junit.Test;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

import static junit.framework.TestCase.assertEquals;

public class PrintableTest {
    private Person danielChew;

    @Before
    public void setUp() throws Exception {
        danielChew = new Person(new Name("Daniel Chew"),
                new Phone("91234567", false),
                new Email("ggg@gg.com", false),
                new Address("100 Jurong Avenue 1", false),
                new UniqueTagList(new Tag("champion")));
    }

    @Test
    public void getPrintableStringTest() throws Exception {
        assertEquals("Phone: 91234567 Email: ggg@gg.com Address: 100 Jurong Avenue 1 ",
                danielChew.getPrintableString(danielChew.getPhone(), danielChew.getEmail(), danielChew.getAddress()));
    }
}
