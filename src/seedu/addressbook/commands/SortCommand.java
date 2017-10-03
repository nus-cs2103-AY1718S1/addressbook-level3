package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Sort list alphabetically by person's name.\n\t"
            + "Example: " + COMMAND_WORD;

    public List<Person> sortByName(){

        return addressBook.sortList();
    }

    public CommandResult execute() {
        List<Person> sortedList=sortByName();
        return new CommandResult(getMessageForPersonSortShownSummary(sortedList),sortedList);
    }

}
