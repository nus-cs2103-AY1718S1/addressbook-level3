package seedu.addressbook.ui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import seedu.addressbook.commands.AddCommand;
import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.commands.ClearCommand;
import seedu.addressbook.commands.DeleteCommand;
import seedu.addressbook.commands.ExitCommand;
import seedu.addressbook.commands.FindCommand;
import seedu.addressbook.commands.HelpCommand;
import seedu.addressbook.commands.ListCommand;
import seedu.addressbook.commands.ViewCommand;
import seedu.addressbook.commands.ViewAllCommand;
import seedu.addressbook.logic.Logic;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;
import java.util.Optional;

import static seedu.addressbook.common.Messages.*;

/**
 * Main Window of the GUI.
 */
public class MainWindow {

    private Logic logic;
    private Stoppable mainApp;

    //Dimensions for Alert Information from Command Help
    private final double HELP_WIDTH_SIZE = 600;
    private final double HELP_HEIGHT_SIZE = 300;

    public MainWindow(){
    }

    public void setLogic(Logic logic){
        this.logic = logic;
    }

    public void setMainApp(Stoppable mainApp){
        this.mainApp = mainApp;
    }

    @FXML
    private TextArea outputConsole;

    @FXML
    private TextField commandInput;


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


    //exits program from menu bar
    @FXML
    private void handleExit() {
        System.exit(0);
    }

    //shows instructions for add command
    @FXML
    private void handleAddHelp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().setMinSize(HELP_WIDTH_SIZE, HELP_HEIGHT_SIZE);
        alert.setTitle("AddressBook Commands List");
        alert.setHeaderText("Add Command");
        alert.setContentText(AddCommand.MESSAGE_USAGE);


        alert.showAndWait();
    }

    //shows instructions for delete command
    @FXML
    private void handleDeleteHelp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().setMinSize(HELP_WIDTH_SIZE, HELP_HEIGHT_SIZE);
        alert.setTitle("AddressBook Commands List");
        alert.setHeaderText("Delete Command");
        alert.setContentText(DeleteCommand.MESSAGE_USAGE);

        alert.showAndWait();
    }

    //shows instructions for clear command
    @FXML
    private void handleClearHelp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().setMinSize(HELP_WIDTH_SIZE, HELP_HEIGHT_SIZE);
        alert.setTitle("AddressBook Commands List");
        alert.setHeaderText("Clear Command");
        alert.setContentText(ClearCommand.MESSAGE_USAGE);

        alert.showAndWait();
    }

    //shows instructions for find command
    @FXML
    private void handleFindHelp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().setMinSize(HELP_WIDTH_SIZE, HELP_HEIGHT_SIZE);
        alert.setTitle("AddressBook Commands List");
        alert.setHeaderText("Find Command");
        alert.setContentText(FindCommand.MESSAGE_USAGE);

        alert.showAndWait();
    }

    //shows instructions for list command
    @FXML
    private void handleListHelp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().setMinSize(HELP_WIDTH_SIZE, HELP_HEIGHT_SIZE);
        alert.setTitle("AddressBook Commands List");
        alert.setHeaderText("List Command");
        alert.setContentText(ListCommand.MESSAGE_USAGE);

        alert.showAndWait();
    }

    //shows instructions for view command
    @FXML
    private void handleViewHelp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().setMinSize(HELP_WIDTH_SIZE, HELP_HEIGHT_SIZE);
        alert.setTitle("AddressBook Commands List");
        alert.setHeaderText("View Command");
        alert.setContentText(ViewCommand.MESSAGE_USAGE);

        alert.showAndWait();
    }

    //shows instructions for View All command
    @FXML
    private void handleViewAllHelp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().setMinSize(HELP_WIDTH_SIZE, HELP_HEIGHT_SIZE);
        alert.setTitle("AddressBook Commands List");
        alert.setHeaderText("View All Command");
        alert.setContentText(ViewAllCommand.MESSAGE_USAGE);

        alert.showAndWait();
    }

    //shows instructions for help command
    @FXML
    private void handleHelpHelp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().setMinSize(HELP_WIDTH_SIZE, HELP_HEIGHT_SIZE);
        alert.setTitle("AddressBook Commands List");
        alert.setHeaderText("Help Command");
        alert.setContentText(HelpCommand.MESSAGE_USAGE);

        alert.showAndWait();
    }

    //shows instructions for exit command
    @FXML
    private void handleExitHelp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().setMinSize(HELP_WIDTH_SIZE, HELP_HEIGHT_SIZE);
        alert.setTitle("AddressBook Commands List");
        alert.setHeaderText("Exit Command");
        alert.setContentText(ExitCommand.MESSAGE_USAGE);

        alert.showAndWait();
    }

}
