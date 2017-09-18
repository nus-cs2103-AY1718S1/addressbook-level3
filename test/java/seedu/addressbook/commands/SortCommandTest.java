package seedu.addressbook.commands;

import org.junit.Before;
import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.UniqueTagList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SortCommandTest {
    private List<ReadOnlyPerson> initialList;
    private List<ReadOnlyPerson> sortedName;
    private List<ReadOnlyPerson> sortedPhone;
    private List<ReadOnlyPerson> sortedEmail;

    private AddressBook addressBook;

    @Before
    public void setup() throws Exception {
        initialList = new ArrayList<ReadOnlyPerson>();
        sortedName = new ArrayList<ReadOnlyPerson>();
        sortedPhone = new ArrayList<ReadOnlyPerson>();
        sortedEmail = new ArrayList<ReadOnlyPerson>();

        addressBook = new AddressBook();
        Person alice = new Person(new Name("Alice Berry"), new Phone("22222", false),
                new Email("berrylover@gmail.com", false), new Address("same", false), new UniqueTagList());
        Person charlie = new Person(new Name("Charlie Choco"), new Phone("7777777", false),
                new Email("acetone@gmail.com", false), new Address("same", false), new UniqueTagList());
        Person johan = new Person(new Name("Johan Liebert"), new Phone("1111111111", false),
                new Email("zzzzz@gmail.com", false), new Address("same", false), new UniqueTagList());
        Person dave = new Person(new Name("Dave Aaron"), new Phone("333333", false),
                new Email("aaaaaa@gmail.com", false), new Address("same", false), new UniqueTagList());


        initialList.add(alice);
        initialList.add(charlie);
        initialList.add(johan);
        initialList.add(dave);

        addressBook.addPerson(alice);
        addressBook.addPerson(charlie);
        addressBook.addPerson(johan);
        addressBook.addPerson(dave);

        sortedName.add(alice);
        sortedName.add(charlie);
        sortedName.add(dave);
        sortedName.add(johan);

        sortedPhone.add(johan);
        sortedPhone.add(alice);
        sortedPhone.add(dave);
        sortedPhone.add(charlie);

        sortedEmail.add(dave);
        sortedEmail.add(charlie);
        sortedEmail.add(alice);
        sortedEmail.add(johan);

    }

    //check if initial list matches list in addressbook.
    @Test
    public void checkInitialEquals() {
        assertEquals(initialList, addressBook.getAllPersons().changeableListView());
    }

    //Performs check for the 3 sorting function.
    @Test
    public void checkNameSort() {
        SortCommand byName = new SortCommand("name");
        byName.setData(addressBook, initialList);

        assertEquals(sortedName, byName.execute().getRelevantPersons().get());
    }

    @Test
    public void checkPhoneSort() {
        SortCommand byPhone = new SortCommand("phone");
        byPhone.setData(addressBook, initialList);

        assertEquals(sortedPhone, byPhone.execute().getRelevantPersons().get());
    }

    @Test
    public void checkEmailSort() {
        SortCommand byEmail =  new SortCommand("email");
        byEmail.setData(addressBook, initialList);

        assertEquals(sortedEmail, byEmail.execute().getRelevantPersons().get());
    }


}