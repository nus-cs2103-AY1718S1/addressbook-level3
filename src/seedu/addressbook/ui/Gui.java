package seedu.addressbook.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import seedu.addressbook.logic.Logic;
import seedu.addressbook.Main;

import java.io.File;
import java.io.IOException;

/**
 * The GUI of the App
 */
public class Gui {

    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    public static final int INITIAL_WINDOW_WIDTH = 800;
    public static final int INITIAL_WINDOW_HEIGHT = 600;
    private final Logic logic;

    private MainWindow mainWindow;
    private String version;
    private BorderPane rootLayout;

    public Gui(Logic logic, String version) {
        this.logic = logic;
        this.version = version;
    }

    public void start(Stage stage, Stoppable mainApp) throws IOException {
        stage = initRootLayout(stage);
        mainWindow = createMainWindow(stage, mainApp);
        mainWindow.displayWelcomeMessage(version, logic.getStorageFilePath());
    }

    private Stage initRootLayout(Stage stage) {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("ui/RootLayout.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.setTitle(version);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stage;
    }

    private MainWindow createMainWindow(Stage stage, Stoppable mainApp) throws IOException{
        FXMLLoader loader = new FXMLLoader();

        /* Note: When calling getResource(), use '/', instead of File.separator or '\\'
         * More info: http://docs.oracle.com/javase/8/docs/technotes/guides/lang/resources.html#res_name_context
         */
        loader.setLocation(Main.class.getResource("ui/mainwindow.fxml"));
        AnchorPane mainwindow = loader.load();

        rootLayout.setCenter(mainwindow);

        MainWindow mainWindow = loader.getController();
        mainWindow.setLogic(logic);
        mainWindow.setMainApp(mainApp);
        return mainWindow;
    }

}
