package Corsair.ST100;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

import javax.usb.UsbException;
import java.net.URL;
import java.util.ResourceBundle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controller class
 */
public class Controller implements Initializable {
    /**
     * Controller Logger
     */
    Logger nLogger = Logger.getLogger("ControllerLog");

    /**
     *
     */
    public static DeviceHandler dh = null;

    /**
     *
     * @param dhandle
     */
    public static void setDh(DeviceHandler dhandle) {
        dh = dhandle;
    }

    /**
     *
     */
    private static final int MAX_THREADS = 1;

    /**
     *
     */
    private ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);

    @FXML
    private AnchorPane anchor;

    @FXML
    private TabPane settingsPane;

    @FXML
    private Tab spiralRainbowTab;

    @FXML
    public static Slider intervalSlider = new Slider(SettingsIntervals.SPIRAL_RAINBOW_FAST, SettingsIntervals.SPIRAL_RAINBOW_SLOW, SettingsIntervals.SPIRAL_RAINBOW_MEDIUM);

    @FXML
    private Label rate;

    @FXML
    private Tab rainbowWaveTab;

    @FXML
    private ChoiceBox RainbowWaveSelection = new ChoiceBox();

    @FXML
    private Tab ColorShiftTab;

    @FXML
    private Tab ColorWaveTab;

    @FXML
    private ChoiceBox ColorWaveTypeSelection = new ChoiceBox();

    @FXML
    private Tab ColorPulseTab;

    @FXML
    private Tab VisorTab;

    @FXML
    private ProgressBar loadBar;

    @FXML
    private Button SetButton;

    @FXML
    private ToggleButton clockWise;

    /**
     *
     * @param url
     * @param rb
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        intervalSlider.setMax(2);
        intervalSlider.setMin(0);
        intervalSlider.setValue(1);
        intervalSlider.setMajorTickUnit(1);
        intervalSlider.setMinorTickCount(0);
        intervalSlider.setSnapToTicks(true);
        intervalSlider.setShowTickMarks(true);

        intervalSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //DeviceHandler.setInterval(newValue.longValue());
                //rate.setText(String.valueOf(newValue.intValue()));
                System.out.println(intervalSlider.getValue());
            }
        });


        RainbowWaveSelection.setItems(FXCollections.observableArrayList("Left", "Right"));
        RainbowWaveSelection.getSelectionModel().select(0);
        RainbowWaveSelection.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() == 0) {
                    ColorSettingsUtility.RainbowWave(0);
                } else if (newValue.intValue() == 1) {
                    ColorSettingsUtility.RainbowWave(1);
                }
            }
        });

        ColorWaveTypeSelection.setItems(FXCollections.observableArrayList("Default", "Left", "Right", "Up", "Down"));
        ColorWaveTypeSelection.getSelectionModel().select(0);
        ColorWaveTypeSelection.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() == 0) {
                    ColorSettingsUtility.ColorWave(0);
                } else if (newValue.intValue() == 1) {
                    ColorSettingsUtility.ColorWave(1);
                } else if (newValue.intValue() == 2) {
                    ColorSettingsUtility.ColorWave(2);
                } else if (newValue.intValue() == 3) {
                    ColorSettingsUtility.ColorWave(3);
                } else if (newValue.intValue() == 4) {
                    ColorSettingsUtility.ColorWave(4);
                }
            }
        });
    }

    /**
     * RGB gradient used for custom color settings (unimplemented)
     * @param scene
     */
    public static void addRectangle(final Scene scene) {
        Circle C = new Circle(200,150,100);
        RadialGradient gradient1 = new RadialGradient(0,
                .1,
                100,
                100,
                200,
                false,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.YELLOW),
                new Stop(1, Color.RED));
        C.setFill(gradient1);
    }

    /**
     * Used to change to the selected setting
     * @param event
     */
    @FXML
    void SetSetting(ActionEvent event) {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            nLogger.log(Level.SEVERE, e.getMessage());
            executorService.shutdownNow();
        }

        if(settingsPane.getSelectionModel().getSelectedItem().getText().equals(spiralRainbowTab.getText())) {
            ColorSettingsUtility.SpiralRainbow();
        } else if (settingsPane.getSelectionModel().getSelectedItem().getText().equals(rainbowWaveTab.getText())) {
            ColorSettingsUtility.RainbowWave(RainbowWaveSelection.getSelectionModel().getSelectedIndex());
        } else if (settingsPane.getSelectionModel().getSelectedItem().getText().equals(ColorShiftTab.getText())) {
            ColorSettingsUtility.ColorShift();
        } else if (settingsPane.getSelectionModel().getSelectedItem().getText().equals(ColorWaveTab.getText())) {
            ColorSettingsUtility.ColorWave(ColorWaveTypeSelection.getSelectionModel().getSelectedIndex());
        } else if (settingsPane.getSelectionModel().getSelectedItem().getText().equals(ColorPulseTab.getText())) {
            ColorSettingsUtility.ColorPulse();
        } else if (settingsPane.getSelectionModel().getSelectedItem().getText().equals(VisorTab.getText())) {
            ColorSettingsUtility.Visor();
        }
        try {
            DeviceHandler.Abort(false);
            executorService = Executors.newFixedThreadPool(MAX_THREADS);
            executorService.execute(new DeviceHandler());
        } catch (UsbException e) {
            nLogger.log(Level.SEVERE, e.getMessage());
        }
    }

    /**
     *
     * @param event
     */
    @FXML
    void setClockWise(ActionEvent event) {
        if (clockWise.isSelected()) {
            ColorSettingsUtility.setClockwise(true);
        } else {
            ColorSettingsUtility.setClockwise(false);
        }
    }

    @FXML
    void setColorWave(ActionEvent event) {
    }
}
