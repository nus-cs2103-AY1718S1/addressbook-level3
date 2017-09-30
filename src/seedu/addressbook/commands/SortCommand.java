package seedu.addressbook.commands;

/**
 * Sorts the address book in ascending order.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Sorts the address book in ascending order.\n\t"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    @Override
    public CommandResult execute() {

    }
}
