package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Displays all persons in the address book as a list with index numbers.\n\t"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = getAddressList(addressBook.getAllPersons().iterator());
        sortListByName(allPersons);
        return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
    }

    private void sortListByName(List<ReadOnlyPerson> allPersons) {
        Collections.sort(allPersons, (ReadOnlyPerson person1, ReadOnlyPerson person2) ->
                (person1.getName().toString()).compareTo(person2.getName().toString()));
    }

    private List<ReadOnlyPerson> getAddressList(Iterator<Person> iterator) {
        List<ReadOnlyPerson> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }
}
