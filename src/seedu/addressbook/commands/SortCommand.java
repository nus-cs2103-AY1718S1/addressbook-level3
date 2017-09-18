package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;

import java.util.Collections;
import java.util.List;

/**
 * Sorts all persons in the address book to display to the user.
 */

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    public static final String PHONECASE = "phone";
    public static final String EMAILCASE = "email";

    private String sortingArg;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Displays a persons in the address book as a sorted list with index numbers.\n\t"
            + "Parameters: phone, email, name\n\t"
            + "Example: " + COMMAND_WORD + " email";

    public SortCommand(String argument){
        this.sortingArg = argument;
    }


    @Override
    public CommandResult execute() {
        List<Person> allPersons = addressBook.getAllPersons().changeableListView();
        switch (sortingArg.trim()) {
            case PHONECASE:
                Collections.sort(allPersons, Person.PHONESORT);
                break;
            case EMAILCASE:
                Collections.sort(allPersons, Person.EMAILSORT);
                break;
            default:
                Collections.sort(allPersons, Person.NAMESORT);
                break;
        }
        return new CommandResult(getMessageForPersonSortedShownSummary(allPersons), allPersons);
    }
}
