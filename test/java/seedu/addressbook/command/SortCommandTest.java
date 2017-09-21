package seedu.addressbook.commands;

import org.junit.Before;
import org.junit.Test;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.tag.UniqueTagList;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SortCommandTest {
    private AddressBook emptyAddressBook;
    private AddressBook addressBook;

    private List<ReadOnlyPerson> emptyDisplayList;
    private List<ReadOnlyPerson> sortDisplayList;
    private List<ReadOnlyPerson> DisplayList;

    @Before
    public void setUp() throws Exception {
        Person johnDoe = new Person(new Name("John Doe"), 
                new Phone("61234567", false), 
                new Email("john@doe.com", false), 
                new Address("395C Ben Road", false), 
                new UniqueTagList());

        Person janeDoe = new Person(new Name("Jane Doe"), 
                new Phone("91234567", false), 
                new Email("jane@doe.com", false), 
                new Address("33G Ohm Road", false), 
                new UniqueTagList());

        Person samDoe = new Person(new Name("Sam Doe"), 
                new Phone("63345566", false), 
                new Email("sam@doe.com", false), 
                new Address("55G Abc Road", false), 
                new UniqueTagList());

        Person davidGrant = new Person(new Name("David Grant"), 
                new Phone("61121122", false), 
                new Email("david@grant.com", false), 
                new Address("44H Define Road", false), 
                new UniqueTagList());

        emptyAddressBook = createAddressBook();
        addressBook = createAddressBook(johnDoe, janeDoe, davidGrant, samDoe);

        emptyDisplayList = createList();
        DisplayList = createList(johnDoe, janeDoe, davidGrant, samDoe);
        sortDisplayList = createList(davidGrant, janeDoe, johnDoe, samDoe);

    }

    @Test
    public void execute_sortWithEmptyAddressBook() {
        assertSort(new SortCommand(), emptyAddressBook, emptyDisplayList, emptyDisplayList);
    }

    @Test
    public void execute_sortWithAddressBook() {
        assertSort(new SortCommand(), addressBook, DisplayList, sortDisplayList);
    }
    
    private void assertSort(Command SortCommand, 
                            AddressBook addressBook, 
                            List<ReadOnlyPerson> relatedList, 
                            List<ReadOnlyPerson> expectedList) {
        
        SortCommand.setData(addressBook, relatedList);
        CommandResult result = SortCommand.execute();

        assertEquals(expectedList, result.getRelevantPersons().get());
    }

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

    public static List<ReadOnlyPerson> createList(Person... persons) {
        List<ReadOnlyPerson> list = new ArrayList<>();

        for (Person person : persons) {
            list.add(person);
        }

        return list;
    }

}
 