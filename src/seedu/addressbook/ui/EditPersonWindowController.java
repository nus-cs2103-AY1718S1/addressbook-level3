package seedu.addressbook.ui;

import javafx.stage.Stage;

public class EditPersonWindowController {

    private Stage dialogStage;

    private boolean okClicked = false;

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
}
