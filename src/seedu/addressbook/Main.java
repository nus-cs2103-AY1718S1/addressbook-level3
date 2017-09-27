package seedu.addressbook;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.scene.text.Font;
import javafx.stage.Stage;
import seedu.addressbook.logic.Logic;
import seedu.addressbook.ui.Gui;
import seedu.addressbook.ui.Stoppable;

/**
 * Main entry point to the application.
 */
public class Main extends Application implements Stoppable{

    /** Version info of the program. */
    public static final String VERSION = "AddessBook Level 3 - Version 1.0";

    private Gui gui;

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Load custom fonts
        Font.loadFont(getClass().getResourceAsStream("/seedu/addressbook/ui/fonts/NovaSquare.ttf"), 10);
        Font.loadFont(getClass().getResourceAsStream("/seedu/addressbook/ui/fonts/Electrolize-Regular.ttf"), 10);

        gui = new Gui(new Logic(), VERSION);
        gui.start(primaryStage, this);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Platform.exit();
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}


