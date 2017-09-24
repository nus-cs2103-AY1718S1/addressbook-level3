package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;

import java.util.Set;

/**
 * Edits a person identified using it's last displayed index from the address book.
 */
public class EditCommand extends Command{
    public static final String COMMAND_WORD = "edit";
    public static final String COMMAND_BREAK = "break";
    public static final String EDIT_LISTING_COMMAND = "editListing";
    public static final String EDIT_DATA_COMMAND = "editData";

    private static final String MESSAGE_USAGE_EXAMPLE = COMMAND_WORD + "\n\t\t"
            + "<List of all people in the address book will be populated>\n\t\t"
            + COMMAND_WORD + " 1 "
            + " n/John Doe [p]p/98765432 pe/johnd@gmail.com [p]a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\nEdits a person in the address book."
            + "Select the index of the person to be edited from the list provided.\n\t"
            + "The first command will just be " + COMMAND_WORD + " to list all the people in the database.\n\t"
            + "Contact details can be marked private by prepending 'p' to the prefix.\n\t"
            + "Parameters: n/NAME [p]p/PHONE [p]e/EMAIL [p]a/ADDRESS  [t/TAG]...\n\t"
            + "Example:\n\t\t" + MESSAGE_USAGE_EXAMPLE + "\n\t"
            + "To end the edit command prematurely:\n\t\t "+COMMAND_BREAK;
    public static final String MESSAGE_SUCCESS = "Edit command selected";
    public static final String MESSAGE_BREAK_EDIT_COMMAND = "Break Edit Command!";

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean isMutating() {
        return true;
    }
}
