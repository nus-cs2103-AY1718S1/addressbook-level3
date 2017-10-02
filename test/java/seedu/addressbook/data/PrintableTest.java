package seedu.addressbook.data;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.Email;

import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

import static org.junit.Assert.assertEquals;

public class PrintableTest {
    private Tag tagPrizeWinner;
    private Person davidElliot;

    @Before
    public void setUp() throws Exception {
        tagPrizeWinner   = new Tag("prizewinner");

        davidElliot    = new Person(new Name("David Elliot"),
                new Phone("84512575", false),
                new Email("douglas@nuscomputing.com", false),
                new Address("11 Arts Link", false),
                new UniqueTagList( tagPrizeWinner));
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getPrintableStringTest() throws Exception {

        assertEquals("Phone: 84512575 Email: douglas@nuscomputing.com Address: 11 Arts Link ",
                davidElliot.getPrintableString(davidElliot.getPhone(), davidElliot.getEmail(), davidElliot.getAddress()));
    }

}
