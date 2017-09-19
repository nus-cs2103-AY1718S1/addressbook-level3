package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.ArrayList;
import java.util.List;

/**
 * Sorts all persons in the address book in alphabetic order to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Displays all persons in the address book in alphabetic order as a list with index numbers.\n\t"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {

        List<Person> SortAllPersons = addressBook.getAllPersons().SortAllPersons();

        return new CommandResult(getMessageForPersonSortShownSummary(SortAllPersons), SortAllPersons);

    }
}
