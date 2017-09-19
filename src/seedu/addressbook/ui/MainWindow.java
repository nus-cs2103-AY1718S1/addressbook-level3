package seedu.addressbook.ui;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import seedu.addressbook.commands.ExitCommand;
import seedu.addressbook.logic.Logic;
import seedu.addressbook.commands.CommandResult;
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
    private TableView<LastViewedPerson> lastViewedTable;

    @FXML
    private TableColumn<LastViewedPerson, Integer> personIndex;
    
    @FXML
    private TableColumn<LastViewedPerson, String> personName;

    @FXML
    private TableColumn<LastViewedPerson, String> personPhone;
   
    @FXML
    private TableColumn<LastViewedPerson, String> personEmail;

    @FXML
    private TableColumn<LastViewedPerson, String> personAddress;

    @FXML
    private TableColumn<LastViewedPerson, String> personTags;

    @FXML
    private void initialize() {
        personIndex.setCellValueFactory(cellData -> cellData.getValue().index);
        personName.setCellValueFactory(cellData -> cellData.getValue().name);
        personPhone.setCellValueFactory(cellData -> cellData.getValue().phone);
        personEmail.setCellValueFactory(cellData -> cellData.getValue().email);
        personAddress.setCellValueFactory(cellData -> cellData.getValue().address);
        personTags.setCellValueFactory(cellData -> cellData.getValue().tags);
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
            displayLastViewed();
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
    
    private void displayLastViewed() {
        List<ReadOnlyPerson> lastShownList = logic.getLastShownList();
        ObservableList<LastViewedPerson> toSet = FXCollections.observableArrayList();
        
        for (int i = 0; i < lastShownList.size(); i++) {
            ReadOnlyPerson personInList = lastShownList.get(i);
            toSet.add(new LastViewedPerson(personInList, i+1));
        }
        
        lastViewedTable.setItems(toSet);
    }

}

