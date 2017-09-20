package seedu.addressbook.ui;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import seedu.addressbook.commands.Command;
import seedu.addressbook.commands.ExitCommand;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.logic.Logic;
import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Optional;

import static seedu.addressbook.common.Messages.*;

/**
 * Main Window of the GUI.
 */
public class MainWindow {
    @FXML
    private TableView<ReadOnlyPerson> personTable;
    @FXML
    private TableColumn<ReadOnlyPerson, String> firstNameColumn;

    @FXML
    private Label nameLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label tagLabel;

    @FXML
    private TextArea outputConsole;
    @FXML
    private TextField commandInput;


    private Logic logic;
    private Stoppable mainApp;

    public MainWindow(){
    }

    public void setLogic(Logic logic){
        this.logic = logic;
    }

    public void setMainApp(Stoppable mainApp) {
        this.mainApp = mainApp;
        getAllPersonObservableList();
    }

    /**
     * Get an observable list of everyone in the
     * database to initialise personTable
     */
    private void getAllPersonObservableList() {
        try {
            CommandResult result = logic.execute("list");

            List<? extends ReadOnlyPerson> resultPersons = result.getRelevantPersons().get();
            ObservableList<ReadOnlyPerson> personsData = FXCollections.observableArrayList();
            personsData.addAll(resultPersons);

            personTable.setItems(personsData);
        }
        catch (Exception e) {
            return;
        }
    }

    @FXML
    void onCommand(ActionEvent event) {
        try {
            String userCommandText = commandInput.getText();
            CommandResult result = logic.execute(userCommandText);
            if(isExitCommand(result)){
                exitApp();
                return;
            }
            displayResult(result);
            clearCommandInput();
        } catch (Exception e) {
            display(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Initializes the controller for displaying person details. This
     * method is automatically called after the fxml file has been loaded
     */
    @FXML
    private void initialize() {
        // Initialize the columns with the person table
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName().getStringProperty());

        // Clear person details
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    /**
     * Shows the person details depending on the selected item on the personTable
     * @param person
     */
    private void showPersonDetails(ReadOnlyPerson person) {
        if (person != null) {
            // Fill the labels with info from the person object
            nameLabel.setText(person.getName().fullName);
            phoneLabel.setText(person.getPhone().value);
            addressLabel.setText(person.getAddress().value);
            String tags = new String();

            for(Tag tag : person.getTags()) {
                tags += tag + " ";
            }

            tagLabel.setText(tags);
           } else {
            // Person is null, remove all the text
            nameLabel.setText("");
            phoneLabel.setText("");
            addressLabel.setText("");
            tagLabel.setText("");
        }
    }

    /**
     * Called when the user clicks on the delete button
     */
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Deletion?", ButtonType.YES, ButtonType.NO);
            confirmAlert.setTitle("Confirmation");
            confirmAlert.showAndWait();

            if(confirmAlert.getResult() == ButtonType.YES) {
                personTable.getItems().remove(selectedIndex);
                try {
                    CommandResult result = logic.execute("delete " + (++selectedIndex));
                    displayResult(result);
                    clearCommandInput();
                } catch (Exception e) {
                    return;
                }
            }
        } else {
            // Nothing selected
            Alert noSelectAlert = new Alert(Alert.AlertType.WARNING, "Please select a person in the contact list.");
            noSelectAlert.setTitle("No Selection");
            noSelectAlert.setHeaderText("No Person Selected");
            noSelectAlert.showAndWait();
        }
    }
    private void exitApp() throws Exception {
        mainApp.stop();
    }

    /** Returns true of the result given is the result of an exit command */
    private boolean isExitCommand(CommandResult result) {
        return result.feedbackToUser.equals(ExitCommand.MESSAGE_EXIT_ACKNOWEDGEMENT);
    }

    /** Clears the command input box */
    private void clearCommandInput() {
        commandInput.setText("");
    }

    /** Clears the output display area */
    public void clearOutputConsole(){
        outputConsole.clear();
    }

    /** Displays the result of a command execution to the user. */
    public void displayResult(CommandResult result) {
        clearOutputConsole();
        final Optional<List<? extends ReadOnlyPerson>> resultPersons = result.getRelevantPersons();
        if(resultPersons.isPresent()) {
            display(resultPersons.get());
        }
        display(result.feedbackToUser);
    }

    public void displayWelcomeMessage(String version, String storageFilePath) {
        String storageFileInfo = String.format(MESSAGE_USING_STORAGE_FILE, storageFilePath);
        display(MESSAGE_WELCOME, version, MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE, storageFileInfo);
    }

    /**
     * Displays the list of persons in the output display area, formatted as an indexed list.
     * Private contact details are hidden.
     */
    private void display(List<? extends ReadOnlyPerson> persons) {
        display(new Formatter().format(persons));
    }

    /**
     * Displays the given messages on the output display area, after formatting appropriately.
     */
    private void display(String... messages) {
        outputConsole.setText(outputConsole.getText() + new Formatter().format(messages));
    }
}
