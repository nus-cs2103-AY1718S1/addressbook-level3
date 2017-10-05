package seedu.addressbook.commands;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;
import java.util.Objects;


/**
 * Lists all persons in the address book to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Displays all persons in the address book, sorted by names in alphabetical order.\n\t"
            + "Example: " + COMMAND_WORD + ", " + COMMAND_WORD + " asc[/desc]";
    
    public static final String MESSAGE_INVALID_ARGUMENT = "Sort order should be 'asc' or 'desc'";

    private enum SortOrder {
        ASC, DESC
    }
    
    // Sort in ascending order by default
    private SortOrder order = SortOrder.ASC;

    public SortCommand() {
        super(0);
    }
    
    public SortCommand(String sortOrderInput) throws IllegalValueException {
        if (Objects.equals(sortOrderInput, "asc")) {
            this.order = SortOrder.ASC;
        } else if (Objects.equals(sortOrderInput, "desc")) {
            this.order = SortOrder.DESC;
        } else {
            throw new IllegalValueException(MESSAGE_INVALID_ARGUMENT);
        }
    }
    
    @Override
    public CommandResult execute() {
        if (order == SortOrder.ASC) {
            addressBook.sortAllPersons();
        } else {
            addressBook.sortAllPersonsDesc();
        }
        
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
    }

    @Override
    public boolean isMutating() {
        return true;
    }
}
