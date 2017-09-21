package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;

import static seedu.addressbook.common.Messages.MESSAGE_EMPTY_ADDRESS_BOOK;


/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    private boolean isPartOfEditCommand;

    @Override
    public boolean isMutating() {
        return false;
    }

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Displays all persons in the address book as a list with index numbers.\n\t"
            + "Example: " + COMMAND_WORD;

    public ListCommand(){}

    public ListCommand(boolean isPartOfEditCommand){
        this.isPartOfEditCommand = isPartOfEditCommand;
    }

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        if (isPartOfEditCommand){
            return new CommandResult(getFeedbackForEditListingCommand(allPersons), allPersons);
        }
        return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
    }

    public String getFeedbackForEditListingCommand(List<ReadOnlyPerson> allPersons) {
        int numPersons = allPersons.size();
        if (numPersons == 0){
            return MESSAGE_EMPTY_ADDRESS_BOOK;
        }
        return "There are currently " + numPersons + ((numPersons>1) ? " persons" : " person")
                +" in the Address Book. Select an index to edit";
    }
}
