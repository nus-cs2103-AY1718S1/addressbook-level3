package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class SortCommand extends Command{
    
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Sort and list all persons in the address book as a list with index numbers.\n\t"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> sortedList = sortByName(addressBook.getAllPersons().immutableListView());
        return new CommandResult(getMessageForPersonListShownSummary(sortedList), sortedList);
    }

    private List<ReadOnlyPerson> sortByName(List<ReadOnlyPerson> dummyList) {
        NameComparator nameComparator = new NameComparator();
        List<ReadOnlyPerson> sortedContacts = new ArrayList<>();
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            sortedContacts.add(person);
        }


        Collections.sort(sortedContacts, nameComparator);
        
        return sortedContacts;
    }
    
    class NameComparator implements Comparator<ReadOnlyPerson> {
        public int compare(ReadOnlyPerson p1, ReadOnlyPerson p2) {
            return p1.getName().fullName.toUpperCase().compareTo(p2.getName().fullName.toUpperCase());
        }
    }
}
