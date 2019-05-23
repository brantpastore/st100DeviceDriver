package Corsair.ST100;

import javax.usb.UsbException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * ColorSettingsUtility
 * Used to provide the predefined settings aswell as custom user settings
 */
public class ColorSettingsUtility {
    public static DeviceHandler dh = null;

    public static void setHandler(DeviceHandler dHandler) {
        dh = dHandler;
    }

    public static ArrayList<Packet> SpiralRainbow_Clockwise = new ArrayList<>();
    public static ArrayList<Packet> SpiralRainbow_CounterClockwise = new ArrayList<>();

    public static ArrayList<Packet> RainbowWave_Left = new ArrayList<>();
    public static ArrayList<Packet> RainbowWave_Right = new ArrayList<>();

    public static ArrayList<Packet> ColorShift = new ArrayList<>();

    public static ArrayList<Packet> ColorWave = new ArrayList<>();
    public static ArrayList<Packet> ColorWave_Left = new ArrayList<>();
    public static ArrayList<Packet> ColorWave_Right = new ArrayList<>();
    public static ArrayList<Packet> ColorWave_Up = new ArrayList<>();
    public static ArrayList<Packet> ColorWave_Down = new ArrayList<>();

    public static ArrayList<Packet> ColorPulse = new ArrayList<>();

    public static ArrayList<Packet> visorPacketList = new ArrayList();

    public static boolean isClockwise() {
        return Clockwise;
    }

    public static void setClockwise(boolean clockwise) {
        Clockwise = clockwise;
    }

    public static boolean Clockwise = false;

    /**
     * SpiralRainbow
     * clockwise is used to determine the change in direction of the colors
     */
    public static void SpiralRainbow() {
        try {
            if (isClockwise()) {
                dh.runIt(SpiralRainbow_Clockwise);
            } else { // Counterclockwise
                dh.runIt(SpiralRainbow_CounterClockwise);
            }
        } catch (UsbException e) {
        }
    }

    /**
     * Direction is used to determin which way the pattern is sent
     * @param direction
     */
    public static void RainbowWave(int direction) {
        try {
            if (direction == 0) { // LEFT
                dh.runIt(RainbowWave_Left);
            } else if (direction == 1) { // RIGHT
                dh.runIt(RainbowWave_Right);
            }
        } catch (UsbException e) {
        }
    }

    /**
     * ColorShift
     */
    public static void ColorShift() {
        try {
            if (isClockwise()) {
                dh.runIt(ColorShift);
            } else { // Counterclockwise
                Collections.reverse(ColorShift);
                dh.runIt(ColorShift);
            }
        } catch (UsbException e) {
        }
    }

    /**
     * Direction determines which way the pattern is sent
     * @param direction
     */
    public static void ColorWave(int direction) {
        try {
            switch (direction) {
                case 0:
                    if (isClockwise()) {
                        dh.runIt(ColorWave);
                    } else {
                        Collections.reverse(ColorWave);
                        dh.runIt(ColorWave);
                    }
                    break;

                case 1:
                    if (isClockwise()) {
                        dh.runIt(ColorWave_Left);
                    } else {
                        Collections.reverse(ColorWave_Left);
                        dh.runIt(ColorWave_Left);
                    }
                    break;

                case 2:
                    if (isClockwise()) {
                        dh.runIt(ColorWave_Right);
                    } else {
                        Collections.reverse(ColorWave_Right);
                        dh.runIt(ColorWave_Right);
                    }
                    break;


                case 3:
                    if (isClockwise()) {
                        dh.runIt(ColorWave_Up);
                    } else {
                        Collections.reverse(ColorWave_Up);
                        dh.runIt(ColorWave_Up);
                    }
                    break;

                case 4:
                    if (isClockwise()) {
                        dh.runIt(ColorWave_Down);
                    } else {
                        Collections.reverse(ColorWave_Down);
                        dh.runIt(ColorWave_Down);
                    }
                    break;

                default:
                    break;
            }
        } catch (UsbException e) {
        }
    }

    /**
     * ColorPulse
     */
    public static void ColorPulse() {
        try {
            if (isClockwise()) {
                dh.runIt(ColorPulse);
            } else { // Counterclockwise
                Collections.reverse(ColorPulse);
                dh.runIt(ColorPulse);
            }
        } catch (UsbException e) {
        }
    }

    /**
     * Visor()
     */
    public static void Visor() {
        try {
            dh.runIt(visorPacketList);
        } catch (UsbException e) {
        }
    }
}
