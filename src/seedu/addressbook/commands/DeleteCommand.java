package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;


/**
 * Deletes a person identified using it's last displayed index from the address book.
 */
public class DeleteCommand extends Command {

    private boolean isPartOfEditCommand;

    @Override
    public boolean isMutating() {
        return true;
    }

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Deletes the person identified by the index number used in the last person listing.\n\t"
            + "Parameters: INDEX\n\t"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_DELETE_PERSON_SUCCESS_EDIT_COMMAND = "Part of Edit Command";


    public DeleteCommand(int targetVisibleIndex) {
        super(targetVisibleIndex);
    }
    public DeleteCommand(int targetVisibleIndex, boolean isPartOfEditCommand) {
        super(targetVisibleIndex);
        this.isPartOfEditCommand = isPartOfEditCommand;
    }


    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = getTargetPerson();
            addressBook.removePerson(target);
            if (isPartOfEditCommand){
                return new CommandResult(MESSAGE_DELETE_PERSON_SUCCESS_EDIT_COMMAND);
            }
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, target));

        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        }
    }

}
