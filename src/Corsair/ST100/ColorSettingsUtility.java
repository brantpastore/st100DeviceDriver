package Corsair.ST100;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

/**
 * ColorSettingsUtility
 * Used to provide the predefined settings aswell as custom user settings
 */
public class ColorSettingsUtility {
    /**
     * ColorSettingsUtility Logger
     */
    Logger nLogger = Logger.getLogger("CSettingsUtilityLog");

    public static DeviceHandler dh = null;

    /**
     *
     * @param dHandler
     */
    public static void setHandler(DeviceHandler dHandler) {
        dh = dHandler;
    }

    /**
     * Stores Spiral Rainbow clockwise
     */
    public static ArrayList<Packet> SpiralRainbow = new ArrayList<>();

    /**
     * Stores Rainbow Wave left packets
     */
    public static ArrayList<Packet> RainbowWave_Left = new ArrayList<>();

    /**
     * Stores Rainbow Wave right packets
     */
    public static ArrayList<Packet> RainbowWave_Right = new ArrayList<>();

    /**
     * Stores Color Shift packets
     */
    public static ArrayList<Packet> ColorShift = new ArrayList<>();

    /**
     * Stores Color Wave packets
     */
    public static ArrayList<Packet> ColorWave = new ArrayList<>();

    /**
     * Stores Color Wave left packets
     */
    public static ArrayList<Packet> ColorWave_Left = new ArrayList<>();

    /**
     * Stores Color Wave right packets
     */
    public static ArrayList<Packet> ColorWave_Right = new ArrayList<>();

    /**
     * Stores Color Wave up packets
     */
    public static ArrayList<Packet> ColorWave_Up = new ArrayList<>();

    /**
     * Stores Color Wave down packets
     */
    public static ArrayList<Packet> ColorWave_Down = new ArrayList<>();

    /**
     * Stores Color Pulse packets
     */
    public static ArrayList<Packet> ColorPulse = new ArrayList<>();

    /**
     * Stores our Visor packets
     */
    public static ArrayList<Packet> visorPacketList = new ArrayList();

    /**
     *
     * @return
     */
    public static boolean isClockwise() {
        return Clockwise;
    }

    /**
     *
     * @param clockwise
     */
    public static void setClockwise(boolean clockwise) {
        Clockwise = clockwise;
    }

    /**
     * Stores the direction which colors are sent
     */
    public static boolean Clockwise = false;

    /**
     * SpiralRainbow
     * clockwise is used to determine the change in direction of the colors
     */
    public static void SpiralRainbow() {
        Controller.intervalSlider.setMin(SettingsIntervals.SPIRAL_RAINBOW_SLOW);
        Controller.intervalSlider.setMax(SettingsIntervals.SPIRAL_RAINBOW_FAST);

        if (isClockwise()) {
            dh.setCurrentPacketList(SpiralRainbow);
        } else { // Counterclockwise
            ArrayList<Packet> reversed = new ArrayList<>();
            reversed.addAll(SpiralRainbow);
            Collections.reverse(reversed);
            dh.setCurrentPacketList(reversed);
        }
    }

    /**
     * Direction is used to determin which way the pattern is sent
     * @param direction
     */
    public static void RainbowWave(int direction) {
        Controller.intervalSlider.setMin(SettingsIntervals.RAINBOW_WAVE_SLOW);
        Controller.intervalSlider.setMax(SettingsIntervals.RAINBOW_WAVE_FAST);
        Controller.intervalSlider.setValue(SettingsIntervals.RAINBOW_WAVE_MEDIUM);
        if (direction == 0) { // LEFT
            dh.setCurrentPacketList(RainbowWave_Left);
        } else if (direction == 1) { // RIGHT
            dh.setCurrentPacketList(RainbowWave_Right);
        }
    }

    /**
     * ColorShift
     */
    public static void ColorShift() {
        Controller.intervalSlider.setMin(SettingsIntervals.COLOR_SHIFT_SLOW);
        Controller.intervalSlider.setMax(SettingsIntervals.COLOR_SHIFT_FAST);
        Controller.intervalSlider.setValue(SettingsIntervals.COLOR_SHIFT_MEDIUM);
        if (isClockwise()) {
            dh.setCurrentPacketList(ColorShift);
        } else { // Counterclockwise
            ArrayList<Packet> reversed = new ArrayList<>();
            reversed.addAll(ColorShift);
            Collections.reverse(reversed);
            dh.setCurrentPacketList(reversed);
        }
    }

    /**
     * Direction determines which way the pattern is sent
     * @param direction
     */
    public static void ColorWave(int direction) {
        Controller.intervalSlider.setMin(SettingsIntervals.COLOR_WAVE_SLOW);
        Controller.intervalSlider.setMax(SettingsIntervals.COLOR_WAVE_FAST);
        Controller.intervalSlider.setValue(SettingsIntervals.COLOR_WAVE_MEDIUM);
        switch (direction) {
            case 0:
                if (isClockwise()) {
                    dh.setCurrentPacketList(ColorWave);
                } else {
                    ArrayList<Packet> reversed = new ArrayList<>();
                    reversed.addAll(ColorWave);
                    Collections.reverse(reversed);
                    dh.setCurrentPacketList(reversed);
                }
                break;

                case 1:
                    if (isClockwise()) {
                        dh.setCurrentPacketList(ColorWave_Left);
                    } else {
                        ArrayList<Packet> reversed = new ArrayList<>();
                        reversed.addAll(ColorWave_Left);
                        Collections.reverse(reversed);
                        dh.setCurrentPacketList(reversed);
                    }
                    break;

                case 2:
                    if (isClockwise()) {
                        dh.setCurrentPacketList(ColorWave_Right);
                    } else {
                        ArrayList<Packet> reversed = new ArrayList<>();
                        reversed.addAll(ColorWave_Right);
                        Collections.reverse(reversed);
                        dh.setCurrentPacketList(reversed);
                    }
                    break;


                case 3:
                    if (isClockwise()) {
                        dh.setCurrentPacketList(ColorWave_Up);
                    } else {
                        ArrayList<Packet> reversed = new ArrayList<>();
                        reversed.addAll(ColorWave_Up);
                        Collections.reverse(reversed);
                        dh.setCurrentPacketList(reversed);
                    }
                    break;

                case 4:
                    if (isClockwise()) {
                        dh.setCurrentPacketList(ColorWave_Down);
                    } else {
                        ArrayList<Packet> reversed = new ArrayList<>();
                        reversed.addAll(ColorWave_Down);
                        Collections.reverse(reversed);
                        dh.setCurrentPacketList(reversed);
                    }
                    break;

                default:
                    break;
        }
    }

    /**
     * ColorPulse
     */
    public static void ColorPulse() {
        Controller.intervalSlider.setMin(SettingsIntervals.COLOR_PULSE_SLOW);
        Controller.intervalSlider.setMax(SettingsIntervals.COLOR_PULSE_FAST);
        Controller.intervalSlider.setValue(SettingsIntervals.COLOR_PULSE_MEDIUM);
        if (isClockwise()) {
            dh.setCurrentPacketList(ColorPulse);
        } else { // Counterclockwise
            ArrayList<Packet> reversed = new ArrayList<>();
            reversed.addAll(ColorPulse);
            Collections.reverse(reversed);
            dh.setCurrentPacketList(reversed);
        }
    }

    /**
     * Visor()
     */
    public static void Visor() {
        Controller.intervalSlider.setMin(SettingsIntervals.VISOR_SLOW);
        Controller.intervalSlider.setMax(SettingsIntervals.VISOR_FAST);
        Controller.intervalSlider.setValue(SettingsIntervals.VISOR_MEDIUM);
        dh.setCurrentPacketList(visorPacketList);
    }
}
