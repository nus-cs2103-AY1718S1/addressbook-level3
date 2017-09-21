package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;

import java.util.Set;

/**
 * Edits a person identified using it's last displayed index from the address book.
 */
public class EditCommand extends Command{
    public static final String COMMAND_WORD = "edit";

    private static final String SELECTED_INDEX = "1";
    private static final String MESSAGE_USAGE_EXAMPLE = COMMAND_WORD + "\n\t\t"
            + "<List of all people in the address book will be populated>\n\t\t"
            + COMMAND_WORD + " " + SELECTED_INDEX
            + " John Doe [p]p/98765432 pe/johnd@gmail.com [p]a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\nEdits a person in the address book."
            + "Select the index of the person to be edited from the list provided.\n\t"
            + "The first command will just be " + COMMAND_WORD + " to list all the people in the database.\n\t"
            + "Contact details can be marked private by prepending 'p' to the prefix.\n\t"
            + "Parameters: n/NAME [p]p/PHONE [p]e/EMAIL [p]a/ADDRESS  [t/TAG]...\n\t"
            + "Example:\n\t\t" + MESSAGE_USAGE_EXAMPLE + "\n\t"
            + "To end the edit command prematurely:\n\t\t end edit";
    public static final String MESSAGE_SUCCESS = "Edit command selected";

    /**
     * Constructor to delete a person based on index and add a new person in replacement.
     * @param index index of the person in the previous listing after calling "edit"
     * @param name name of the person
     * @param phone phone number of the person
     * @param isPhonePrivate private status of phone number
     * @param email email of the person
     * @param isEmailPrivate private status of email
     * @param address address of the person
     * @param isAddressPrivate private status of address
     * @param tagArguments tags to the person
     */
    public EditCommand(String index, String name,
                       String phone, boolean isPhonePrivate,
                       String email, boolean isEmailPrivate,
                       String address, boolean isAddressPrivate,
                       Set<String> tagArguments){
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean isMutating() {
        return true;
    }
}
