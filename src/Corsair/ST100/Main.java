package Corsair.ST100;

/**
 * Main class
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {
    /**
     * Main logger
     */
    private static Logger nLogger = Logger.getLogger("MainLogger");

    /**
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
        primaryStage.setTitle("ST100 RGB Controller");
        Scene myScene = new Scene(root, 775, 520);
        primaryStage.setScene(myScene);
        Controller.addRectangle(myScene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                nLogger.log(Level.INFO, "Exiting application");
                Platform.setImplicitExit(true);
                Platform.exit();
                System.exit(0);
            }
        });
    }

    /**
     * Method used to setup our Device class, and SQL driver
     */
    public static void BackgroundTasks() {
        try {
            ColorSettingsUtility cs = new ColorSettingsUtility();

            DeviceHandler dHandler = new DeviceHandler();
            cs.setHandler(dHandler);
            Controller.setDh(dHandler);

            if (dHandler.getDevice() == null) {
                nLogger.log(Level.SEVERE, "ST100 [RDA0014] not found");
                throw new Exception("ST100 [RDA0014] not found");
            } else {
                nLogger.log(Level.INFO, "ST100 [RDA0014]  Found!");
            }

            SQLiteDriverConnection sqlDriver = new SQLiteDriverConnection();
            sqlDriver.CreateDatabase("/home/linux/", "packets.db");
            sqlDriver.CreatePacketTable();

            cs.SpiralRainbow = sqlDriver.SelectAllLabel("SPIRALRAINBOW_CLOCKWISE");

            cs.RainbowWave_Left = sqlDriver.SelectAllLabel("RAINBOWWAVE_LEFT");
            cs.RainbowWave_Right = sqlDriver.SelectAllLabel("RAINBOWWAVE_RIGHT");

            cs.ColorShift = sqlDriver.SelectAllLabel("COLORSHIFT");

            cs.ColorWave = sqlDriver.SelectAllLabel("COLORWAVE");
            cs.ColorWave_Left = sqlDriver.SelectAllLabel("COLORWAVE_LEFT");
            cs.ColorWave_Right = sqlDriver.SelectAllLabel("COLORWAVE_RIGHT");
            cs.ColorWave_Up = sqlDriver.SelectAllLabel("COLORWAVE_UP");
            cs.ColorWave_Down = sqlDriver.SelectAllLabel("COLORWAVE_DOWN");

            cs.ColorPulse = sqlDriver.SelectAllLabel("COLORPULSE");
            cs.visorPacketList = sqlDriver.SelectAllLabel("VISOR");
        } catch (Exception e) {
            nLogger.log(Level.SEVERE, e.getMessage());
        }
    }

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        nLogger.log(Level.INFO, "Starting application...");
        BackgroundTasks();
        launch(args);
    }
}

