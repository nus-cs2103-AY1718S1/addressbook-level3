package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;

import java.util.HashSet;
import java.util.Set;

public class UpdateCommand extends Command {
    public static final String COMMAND_WORD = "update";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates a person information in the address book. "
            + "Indicate which field to update by add 'N'ame 'P'hone 'E'mail 'A'ddress 'T'ag after the INDEX"
            + "Contact details can be marked private by prepending 'p' to the prefix.\n"
            + "Parameters: INDEX FIELD NAME [p]p/PHONE [p]e/EMAIL [p]a/ADDRESS  [t/TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 NPEAT"
            + " John Doe p/98765432 e/johnd@gmail.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney";

    public static final String MESSAGE_SUCCESS = "Selected person updated: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    public static final char NAME = 'N';
    public static final char PHONE = 'P';
    public static final char EMAIL = 'E';
    public static final char ADDRESS = 'A';
    public static final char TAG = 'T';

    private final Person toUpdate;
    private String fieldNotUpdated;

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public UpdateCommand(int targetVisibleIndex, String fieldNotUpdated, String name,
                         String phone, boolean isPhonePrivate,
                         String email, boolean isEmailPrivate,
                         String address, boolean isAddressPrivate,
                         Set<String> tags) throws IllegalValueException {
        super(targetVisibleIndex);
        final Set<Tag> tagSet = new HashSet<>();

        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.fieldNotUpdated = fieldNotUpdated;
        this.toUpdate = new Person(
                new Name(name),
                new Phone(phone, isPhonePrivate),
                new Email(email, isEmailPrivate),
                new Address(address, isAddressPrivate),
                new UniqueTagList(tagSet)
        );
    }



    public UpdateCommand(Person toUpdate) {
        this.toUpdate = toUpdate;
    }

    public ReadOnlyPerson getPerson() {
        return toUpdate;
    }

    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = getTargetPerson();
            addressBook.removePerson(target);
            Person person = retainNonUpdateData(fieldNotUpdated, toUpdate, target);
            addressBook.updatePerson(person);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toUpdate));

        } catch (UniquePersonList.DuplicatePersonException dpe) {
            return new CommandResult(MESSAGE_DUPLICATE_PERSON);
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (UniquePersonList.PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        }
    }

    private Person retainNonUpdateData(String args ,Person newData, ReadOnlyPerson oldData){
        for (int i=0; i<args.length(); i++){
            switch (args.charAt(i)) {

                case UpdateCommand.NAME:
                    newData.setName(oldData.getName());
                    continue;

                case UpdateCommand.PHONE:
                    newData.setPhone(oldData.getPhone());
                    continue;

                case UpdateCommand.EMAIL:
                    newData.setEmail(oldData.getEmail());
                    continue;

                case UpdateCommand.ADDRESS:
                    newData.setAddress(oldData.getAddress());
                    continue;

                case UpdateCommand.TAG:
                    newData.setTags(oldData.getTags());
                    continue;
                default:
                    break;
            }
        }

        return newData;
    }
}
