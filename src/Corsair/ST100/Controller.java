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

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        spiralSlider.setMajorTickUnit(1);
        spiralSlider.setShowTickMarks(true);
        spiralSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                DeviceHandler.setInterval(newValue.longValue());
                rate.setText(newValue.toString());
            }
        });

        RainbowWaveSelection.setItems(FXCollections.observableArrayList("Left", "Right"));
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

    @FXML
    private AnchorPane anchor;

    @FXML
    private TabPane settingsPane;

    @FXML
    private Tab spiralRainbowTab;

    @FXML
    private Slider spiralSlider = new Slider(0, 3, 1);

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

    @FXML
    void SetSetting(ActionEvent event) {
        DeviceHandler.Abort(true);
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
        DeviceHandler.Abort(false);
    }

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
