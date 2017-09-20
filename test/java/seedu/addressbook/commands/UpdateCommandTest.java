package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;
import seedu.addressbook.util.TestUtil;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Address;


public class UpdateCommandTest {
    private AddressBook emptyAddressBook;
    private AddressBook addressBook;

    private List<ReadOnlyPerson> emptyDisplayList;
    private List<ReadOnlyPerson> listWithEveryone;
    private List<ReadOnlyPerson> listWithSurnameDoe;

    private static final Set<String> EMPTY_STRING_LIST = Collections.emptySet();
    private static final int INDEX = 1;
    private static final String UPDATEFORMAT = "NPEAT";
    private static final int DISPLAYED_INDEX_OFFSET = 1;

    @Before
    public void setUp() throws Exception {
        Person johnDoe = new Person(new Name("John Doe"), new Phone("61234567", false),
                new Email("john@doe.com", false), new Address("395C Ben Road", false), new UniqueTagList());
        Person janeDoe = new Person(new Name("Jane Doe"), new Phone("91234567", false),
                new Email("jane@doe.com", false), new Address("33G Ohm Road", false), new UniqueTagList());
        Person samDoe = new Person(new Name("Sam Doe"), new Phone("63345566", false),
                new Email("sam@doe.com", false), new Address("55G Abc Road", false), new UniqueTagList());
        Person davidGrant = new Person(new Name("David Grant"), new Phone("61121122", false),
                new Email("david@grant.com", false), new Address("44H Define Road", false),
                new UniqueTagList());

        emptyAddressBook = TestUtil.createAddressBook();
        addressBook = TestUtil.createAddressBook(johnDoe, janeDoe, davidGrant, samDoe);

        emptyDisplayList = TestUtil.createList();

        listWithEveryone = TestUtil.createList(johnDoe, janeDoe, davidGrant, samDoe);
        listWithSurnameDoe = TestUtil.createList(johnDoe, janeDoe, samDoe);
    }

    @Test
    public void updateCommand_invalidName_throwsException() {
        final String[] invalidNames = { "", " ", "[]\\[;]" };
        for (String name : invalidNames) {
            assertConstructingInvalidUpdateCmdThrowsException(name, Phone.EXAMPLE, true, Email.EXAMPLE, false,
                    Address.EXAMPLE, true, EMPTY_STRING_LIST);
        }
    }

    @Test
    public void execute_emptyAddressBook_returnsPersonNotFoundMessage() {
        assertUpdateFailsDueToNoSuchPerson(1, emptyAddressBook, listWithEveryone);
    }

    @Test
    public void execute_noPersonDisplayed_returnsInvalidIndexMessage() {
        assertUpdateFailsDueToInvalidIndex(1, addressBook, emptyDisplayList);
    }

    @Test
    public void execute_targetPersonNotInAddressBook_returnsPersonNotFoundMessage()
            throws IllegalValueException {
        Person notInAddressBookPerson = new Person(new Name("Not In Book"), new Phone("63331444", false),
                new Email("notin@book.com", false), new Address("156D Grant Road", false), new UniqueTagList());
        List<ReadOnlyPerson> listWithPersonNotInAddressBook = TestUtil.createList(notInAddressBookPerson);

        assertUpdateFailsDueToNoSuchPerson(1, addressBook, listWithPersonNotInAddressBook);
    }

    @Test
    public void execute_invalidIndex_returnsInvalidIndexMessage() {
        assertUpdateFailsDueToInvalidIndex(0, addressBook, listWithEveryone);
        assertUpdateFailsDueToInvalidIndex(-1, addressBook, listWithEveryone);
        assertUpdateFailsDueToInvalidIndex(listWithEveryone.size() + 1, addressBook, listWithEveryone);
    }

    @Test
    public void execute_invalidFieldName_returnInvalidCommandFormat()throws PersonNotFoundException {
        try {
            assertUpdateSuccessful(1, UPDATEFORMAT, addressBook, listWithSurnameDoe);
        }catch(UniquePersonList.PersonNotFoundException e){
            return;
        }
    }

    /**
     * Asserts that attempting to construct an add command with the supplied
     * invalid data throws an IllegalValueException
     */
    private void assertConstructingInvalidUpdateCmdThrowsException(String name, String phone,
                                                                boolean isPhonePrivate, String email, boolean isEmailPrivate, String address,
                                                                boolean isAddressPrivate, Set<String> tags) {
        try {
            new UpdateCommand(INDEX, UPDATEFORMAT, name, phone, isPhonePrivate, email, isEmailPrivate, address, isAddressPrivate,
                    tags);
        } catch (IllegalValueException e) {
            return;
        }
        String error = String.format(
                "An update command was successfully constructed with invalid input: %s %s %s %s %s %s %s %s",
                name, phone, isPhonePrivate, email, isEmailPrivate, address, isAddressPrivate, tags);
        fail(error);
    }

    @Test
    public void addCommand_invalidPhone_throwsException() {
        final String[] invalidNumbers = { "", " ", "1234-5678", "[]\\[;]", "abc", "a123", "+651234" };
        for (String number : invalidNumbers) {
            assertConstructingInvalidUpdateCmdThrowsException(Name.EXAMPLE, number, false, Email.EXAMPLE, true,
                    Address.EXAMPLE, false, EMPTY_STRING_LIST);
        }
    }

    @Test
    public void updateCommand_invalidEmail_throwsException() {
        final String[] invalidEmails = { "", " ", "def.com", "@", "@def", "@def.com", "abc@",
                "@invalid@email", "invalid@email!", "!invalid@email" };
        for (String email : invalidEmails) {
            assertConstructingInvalidUpdateCmdThrowsException(Name.EXAMPLE, Phone.EXAMPLE, false, email, false,
                    Address.EXAMPLE, false, EMPTY_STRING_LIST);
        }
    }

    @Test
    public void updateCommand_invalidTags_throwsException() {
        final String[][] invalidTags = { { "" }, { " " }, { "'" }, { "[]\\[;]" }, { "validTag", "" },
                { "", " " } };
        for (String[] tags : invalidTags) {
            Set<String> tagsToAdd = new HashSet<>(Arrays.asList(tags));
            assertConstructingInvalidUpdateCmdThrowsException(Name.EXAMPLE, Phone.EXAMPLE, true, Email.EXAMPLE,
                    true, Address.EXAMPLE, false, tagsToAdd);
        }
    }


    /**
     * Executes the command, and checks that the execution was what we had expected.
     */
    private void assertCommandBehaviour(UpdateCommand updateCommand, String expectedMessage,
                                        AddressBook expectedAddressBook, AddressBook actualAddressBook) {

        CommandResult result = updateCommand.execute();
        System.out.println(result.feedbackToUser);
        assertEquals(expectedMessage, result.feedbackToUser);
    }

    /**
     * Asserts that the index is not valid for the given display list.
     */
    private void assertUpdateFailsDueToInvalidIndex(int invalidVisibleIndex, AddressBook addressBook,
                                                    List<ReadOnlyPerson> displayList) {

        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

        UpdateCommand command = createUpdateCommand(invalidVisibleIndex, UPDATEFORMAT, Name.EXAMPLE, Phone.EXAMPLE, true,
                Email.EXAMPLE, true, Address.EXAMPLE, false, EMPTY_STRING_LIST,addressBook,displayList);
        assertCommandBehaviour(command, expectedMessage, addressBook, addressBook);
    }

    /**
     * Asserts that the person at the specified index cannot be deleted, because that person
     * is not in the address book.
     */
    private void assertUpdateFailsDueToNoSuchPerson(int visibleIndex, AddressBook addressBook,
                                                    List<ReadOnlyPerson> displayList) {

        String expectedMessage = Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK;

        UpdateCommand command = createUpdateCommand(visibleIndex, UPDATEFORMAT, Name.EXAMPLE, Phone.EXAMPLE, true,
                Email.EXAMPLE, true, Address.EXAMPLE, false, EMPTY_STRING_LIST,addressBook,displayList);
        assertCommandBehaviour(command, expectedMessage, addressBook, addressBook);
    }

    /**
     * Creates a new delete command.
     *
     * @param targetVisibleIndex of the person that we want to delete
     */
    private UpdateCommand createUpdateCommand(int targetVisibleIndex, String fieldNotUpdate,
                                              String name, String phone, boolean isPhonePrivate,
                                              String email, boolean isEmailPrivate, String address,
                                              boolean isAddressPrivate, Set<String> tags, AddressBook addressBook,
                                              List<ReadOnlyPerson> displayList) {
        UpdateCommand command = null;
        try {
            command = new UpdateCommand(targetVisibleIndex, fieldNotUpdate, name, phone, isPhonePrivate, email, isEmailPrivate, address, isAddressPrivate,
                    tags);
            ;
            command.setData(addressBook, displayList);
        }catch (IllegalValueException e) {
            return command;
        }

        return command;
    }

    /**
     * Asserts that the person at the specified index can be successfully deleted.
     *
     * The addressBook passed in will not be modified (no side effects).
     *
     */
    private void assertUpdateSuccessful(int targetVisibleIndex, String UpdateField, AddressBook addressBook,
                                          List<ReadOnlyPerson> displayList) throws UniquePersonList.PersonNotFoundException{

        ReadOnlyPerson targetPerson = displayList.get(targetVisibleIndex - DISPLAYED_INDEX_OFFSET);

        AddressBook expectedAddressBook = TestUtil.clone(addressBook);
        expectedAddressBook.removePerson(targetPerson);

        try {
            final Set<Tag> tagSet = new HashSet<>();

            for (String tagName : EMPTY_STRING_LIST) {
                tagSet.add(new Tag(tagName));
            }
            expectedAddressBook.addPerson(new Person(
                    new Name(Name.EXAMPLE),
                    new Phone(Phone.EXAMPLE, true),
                    new Email(Email.EXAMPLE, true),
                    new Address(Address.EXAMPLE, false),
                    new UniqueTagList(tagSet)));
        }catch(IllegalValueException ev){
            return;
        }
        String expectedMessage = String.format(UpdateCommand.MESSAGE_SUCCESS, targetPerson);

        AddressBook actualAddressBook = TestUtil.clone(addressBook);

        UpdateCommand command = createUpdateCommand(targetVisibleIndex, UpdateField, Name.EXAMPLE, Phone.EXAMPLE, true,
                Email.EXAMPLE, true, Address.EXAMPLE, false, EMPTY_STRING_LIST,actualAddressBook,displayList);
        assertCommandBehaviour(command, expectedMessage, expectedAddressBook, actualAddressBook);
    }
}
