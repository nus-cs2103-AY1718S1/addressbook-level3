package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortCommand extends Command{

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Address book has been sorted!";

    class nameComparator implements Comparator<ReadOnlyPerson> {
        @Override
        public int compare(ReadOnlyPerson o1, ReadOnlyPerson o2) {
            return o1.getName().fullName.toUpperCase().compareTo(o2.getName().fullName.toUpperCase());
        }
    };

    private List<ReadOnlyPerson> sortedList(UniquePersonList tempList) {
        nameComparator nameCom = new nameComparator();
        List<ReadOnlyPerson> sortingList = new ArrayList<>();
        for (ReadOnlyPerson person : tempList) {
            sortingList.add(person);
        }
        Collections.sort(sortingList, nameCom);
        return sortingList;
    }

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> sortedNames = sortedList(addressBook.getAllPersons());
        return new CommandResult(MESSAGE_SUCCESS);

    }

}

