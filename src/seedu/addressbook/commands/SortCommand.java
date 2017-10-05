package seedu.addressbook.commands;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

import java.util.*;

public class SortCommand extends Command{

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Sorts all contacts in the address book alphabetically. "
            + "Example: " + COMMAND_WORD;


    public static final String MESSAGE_SUCCESS = "AddressBook sorted in alphabetical order";

    @Override
    public CommandResult execute() {

        List<Person> sortedList = addressBook.getAllPersons().sort();
        return new CommandResult(getMessageForSortedListShownSummary(sortedList),sortedList);
    }

}
