package seedu.addressbook.commands;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Clears address book permanently.\n\t"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";
    private static final String MESSAGE_ABORTED = "Clear operation has been aborted!";

    @Override
    public CommandResult execute() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning!");
        alert.setHeaderText("Confirm Clear");
        alert.setContentText("Are you sure you want to clear the address book?");
        Optional<ButtonType> confirmation = alert.showAndWait();
        if (confirmation.get() == ButtonType.OK) {
            addressBook.clear();
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_ABORTED);
        }
    }
}
