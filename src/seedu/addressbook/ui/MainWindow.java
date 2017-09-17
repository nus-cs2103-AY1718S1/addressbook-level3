package seedu.addressbook.ui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import seedu.addressbook.Main;
import seedu.addressbook.commands.ExitCommand;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.logic.Logic;
import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.commands.ListCommand;
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
    private Gui mainGui;

    public MainWindow() {
    }

    public void setLogic(Logic logic) {
        this.logic = logic;
    }

    public void setMainApp(Stoppable mainApp) {
        this.mainApp = mainApp;
    }

    public void setMainGui(Gui mainGui) {
        this.mainGui = mainGui;
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
            if (isExitCommand(result)) {
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

    @FXML
    private void handleAdd() {
        String addCommand = mainGui.showPersonAddDialog();
        if (!addCommand.equals("cancelled")) {
            try {
                CommandResult result = logic.execute(addCommand);
                displayResult(result);
                clearCommandInput();
            } catch (Exception e) {
                display(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    private void handleHelp() {
        try {
            CommandResult result = logic.execute("help");
            displayResult(result);
            clearCommandInput();
        } catch (Exception e) {
            display(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void handleList() {
        try {
            CommandResult result = logic.execute("list");
            displayResult(result);
            clearCommandInput();
        } catch (Exception e) {
            display(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void handleClear() {
        try {
            CommandResult result = logic.execute("clear");
            displayResult(result);
            clearCommandInput();
        } catch (Exception e) {
            display(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void handleExit() {
        try {
            displayResult(new CommandResult(ExitCommand.MESSAGE_EXIT_ACKNOWEDGEMENT));
            exitApp();
        } catch (Exception e) {
            display(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    private void exitApp() throws Exception {
        mainApp.stop();
    }

    /**
     * Returns true of the result given is the result of an exit command
     */
    private boolean isExitCommand(CommandResult result) {
        return result.feedbackToUser.equals(ExitCommand.MESSAGE_EXIT_ACKNOWEDGEMENT);
    }

    /**
     * Clears the command input box
     */
    private void clearCommandInput() {
        commandInput.setText("");
    }

    /**
     * Clears the output display area
     */
    public void clearOutputConsole() {
        outputConsole.clear();
    }

    /**
     * Displays the result of a command execution to the user.
     */
    public void displayResult(CommandResult result) {
        clearOutputConsole();
        final Optional<List<? extends ReadOnlyPerson>> resultPersons = result.getRelevantPersons();
        if (resultPersons.isPresent()) {
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
