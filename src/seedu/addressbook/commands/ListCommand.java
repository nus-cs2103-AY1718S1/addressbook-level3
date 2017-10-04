package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;

import static seedu.addressbook.common.Messages.MESSAGE_EMPTY_ADDRESS_BOOK;


/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    @Override
    public boolean isMutating() {
        return false;
    }

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Displays all persons in the address book as a list with index numbers.\n\t"
            + "Example: " + COMMAND_WORD;

    public ListCommand(){}

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
    }
}
