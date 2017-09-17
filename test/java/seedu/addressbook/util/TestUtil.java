package seedu.addressbook.util;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TestUtil {
    /**
     * Asserts whether the text in the two given files are the same. Ignores any
     * differences in line endings
     */
    public static void assertTextFilesEqual(Path path1, Path path2) throws IOException {
        List<String> list1 = Files.readAllLines(path1, Charset.defaultCharset());
        List<String> list2 = Files.readAllLines(path2, Charset.defaultCharset());
        assertEquals(String.join("\n", list1), String.join("\n", list2));
    }

    /**
     * Creates an address book containing the given persons.
     */
    public static AddressBook createAddressBook(Person... persons) {
        AddressBook addressBook = new AddressBook();

        for (Person person : persons) {
            try {
                addressBook.addPerson(person);
            } catch (UniquePersonList.DuplicatePersonException e) {
                throw new AssertionError(e);
            }
        }
        return addressBook;
    }
    /**
     * Creates a list of persons.
     */
    public static List<ReadOnlyPerson> createList(Person...persons) {
        List<ReadOnlyPerson> list = new ArrayList<ReadOnlyPerson>();

        for (Person person : persons) {
            list.add(person);
        }

        return list;
    }

}
