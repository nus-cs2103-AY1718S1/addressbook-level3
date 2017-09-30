package seedu.addressbook.commands;

/**
 * Sorts the address book in ascending order.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Sorts the address book in ascending order.\n\t"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SORT_PERSON_SUCCESS = "Address book has been sorted!";

    @Override
    public CommandResult execute() {
        addressBook.sort();
        return new CommandResult(String.format(MESSAGE_SORT_PERSON_SUCCESS));
    }
}
