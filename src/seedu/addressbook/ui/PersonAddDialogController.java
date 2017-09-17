package seedu.addressbook.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.tag.UniqueTagList;

public class PersonAddDialogController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField addressField;

    private Stage dialogStage;
    private boolean okClicked = false;
    private Person personToAdd;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() throws IllegalValueException {
        Name newName = new Name(nameField.getText());
        Phone newPhone = new Phone(phoneField.getText(), false);
        Email newEmail = new Email(emailField.getText(), false);
        Address newAddress = new Address(addressField.getText(), false);
        UniqueTagList newTags = new UniqueTagList();
        this.personToAdd = new Person(newName, newPhone, newEmail, newAddress, newTags);
        okClicked = true;
        dialogStage.close();
    }

    public String toAdd() {
        return "add " + nameField.getText() + " p/" + phoneField.getText() + " e/" + emailField.getText()
                + " a/" + addressField.getText();
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
