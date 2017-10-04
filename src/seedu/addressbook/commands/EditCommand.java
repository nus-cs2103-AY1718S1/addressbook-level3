package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

import java.util.HashSet;
import java.util.Set;

import static seedu.addressbook.ui.Gui.DISPLAYED_INDEX_OFFSET;

/**
 * Edits a person identified using it's last displayed index from the address book.
 */
public class EditCommand extends Command{
    public static final String COMMAND_WORD = "edit";
    private static int personIndex;
    private final Person toEdit;

    private static final String MESSAGE_USAGE_EXAMPLE = COMMAND_WORD + " 1"
            + " n/John Doe pp/98765432 pe/johnd@gmail.com pa/311, Clementi Ave 2, #02-25 t/friends t/owesMoney";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\nEdits a person in the address book."
            + "Select the index of the person to be edited from the list provided.\n\t"
            + "Parameters: n/NAME [p]p/PHONE [p]e/EMAIL [p]a/ADDRESS  [t/TAG]...\n\t"
            + "Example:\n\t\t" + MESSAGE_USAGE_EXAMPLE + "\n\t";
    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";

    public EditCommand(int index, String name,
                       String phone, boolean isPhonePrivate,
                       String email, boolean isEmailPrivate,
                       String address, boolean isAddressPrivate,
                       Set<String> tags) throws IllegalValueException {
        super(index);
        personIndex = index - DISPLAYED_INDEX_OFFSET;
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.toEdit = new Person(
                new Name(name),
                new Phone(phone, isPhonePrivate),
                new Email(email, isEmailPrivate),
                new Address(address, isAddressPrivate),
                new UniqueTagList(tagSet)
        );
    }

    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = getTargetPerson();
            addressBook.editPerson(personIndex,toEdit);
            return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, toEdit));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (UniquePersonList.NoChangeException nce){
            return new CommandResult(String.format(nce.getLocalizedMessage(), toEdit));
        }
    }

    @Override
    public boolean isMutating() {
        return true;
    }
}
