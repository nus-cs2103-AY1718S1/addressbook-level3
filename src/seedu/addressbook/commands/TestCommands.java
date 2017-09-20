package seedu.addressbook.commands;

import seedu.addressbook.data.exception.IllegalValueException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;

public class TestCommands {

    public static void main(String args[]) throws IllegalValueException {

        ArrayList<Command> commands = new ArrayList<Command>();
        Set<String> aSet = null;

        Command deleteCommand = new DeleteCommand(1);
        Command clearCommand = new ClearCommand();
        Command listCommand = new ListCommand();

        commands.add(deleteCommand);
        commands.add(clearCommand);
        commands.add(listCommand);

        for (Command command : commands) {
            System.out.println("Is " + command.commandType() + " mutating? Answer is: " + command.isMutating());
        }
    }

}
