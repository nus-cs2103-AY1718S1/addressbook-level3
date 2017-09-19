package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.awt.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortCommand extends Command{

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Address book has been sorted!";

    public static Comparator<ReadOnlyPerson> nameComparator = new Comparator<ReadOnlyPerson>() {
        @Override
        public int compare(ReadOnlyPerson o1, ReadOnlyPerson o2) {
            return o1.getName().fullName.compareTo(o2.getName().fullName);
        }
    };

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        Collections.sort(allPersons, nameComparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

