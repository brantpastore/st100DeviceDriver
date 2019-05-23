package Corsair.ST100;

/**
 * A class object containing the appropriate LED color code values used for predefined/user settings
 */
public class Packet {
    private static int GUID = 0;

    public static final int LOGO_POS_RED = 12;
    public static final int LOGO_POS_GREEN = 13;
    public static final int LOGO_POS_BLUE = 14;

    public static final int SIDE_ONE_POS_RED = 0;
    public static final int SIDE_ONE_POS_GREEN = 1;
    public static final int SIDE_ONE_POS_BLUE = 2;

    public static final int CORNER_ONE_POS_RED = 3;
    public static final int CORNER_ONE_POS_GREEN = 4;
    public static final int CORNER_ONE_POS_BLUE = 5;

    public static final int SIDE_TWO_POS_RED = 6;
    public static final int SIDE_TWO_POS_GREEN = 7;
    public static final int SIDE_TWO_POS_BLUE =  8;

    public static final int CORNER_TWO_POS_RED = 9;
    public static final int CORNER_TWO_POS_GREEN = 10;
    public static final int CORNER_TWO_POS_BLUE = 11;

    public static final int SIDE_THREE_POS_RED = 15;
    public static final int SIDE_THREE_POS_GREEN = 16;
    public static final int SIDE_THREE_POS_BLUE = 17;

    public static final int CORNER_THREE_POS_RED = 18;
    public static final int CORNER_THREE_POS_GREEN = 19;
    public static final int CORNER_THREE_POS_BLUE = 20;

    public static final int SIDE_FOUR_POS_RED = 21;
    public static final int SIDE_FOUR_POS_GREEN = 22;
    public static final int SIDE_FOUR_POS_BLUE =  23;

    public static final int CORNER_FOUR_POS_RED = 24;
    public static final int CORNER_FOUR_POS_GREEN = 25;
    public static final int CORNER_FOUR_POS_BLUE = 26;

    public static final int UNKNOWN = 27;

    private static byte LOGO_POS_RED_VALUE = 0x00;
    private static byte LOGO_POS_GREEN_VALUE = 0x00;
    private static byte LOGO_POS_BLUE_VALUE = 0x00;

    private static byte SIDE_ONE_POS_RED_VALUE = 0x00;
    private static byte SIDE_ONE_POS_GREEN_VALUE = 0x00;
    private static byte SIDE_ONE_POS_BLUE_VALUE = 0x00;

    private static byte CORNER_ONE_POS_RED_VALUE = 0x00;
    private static byte CORNER_ONE_POS_GREEN_VALUE = 0x00;
    private static byte CORNER_ONE_POS_BLUE_VALUE = 0x00;

    private static byte SIDE_TWO_POS_RED_VALUE = 0x00;
    private static byte SIDE_TWO_POS_GREEN_VALUE = 0x00;
    private static byte SIDE_TWO_POS_BLUE_VALUE = 0x00;

    private static byte CORNER_TWO_POS_RED_VALUE = 0x00;
    private static byte CORNER_TWO_POS_GREEN_VALUE = 0x00;
    private static byte CORNER_TWO_POS_BLUE_VALUE = 0x00;

    private static byte SIDE_THREE_POS_RED_VALUE = 0x00;
    private static byte SIDE_THREE_POS_GREEN_VALUE = 0x00;
    private static byte SIDE_THREE_POS_BLUE_VALUE = 0x00;

    private static byte CORNER_THREE_POS_RED_VALUE = 0x00;
    private static byte CORNER_THREE_POS_GREEN_VALUE = 0x00;
    private static byte CORNER_THREE_POS_BLUE_VALUE = 0x00;

    private static byte SIDE_FOUR_POS_RED_VALUE = 0x00;
    private static byte SIDE_FOUR_POS_GREEN_VALUE = 0x00;
    private static byte SIDE_FOUR_POS_BLUE_VALUE = 0x00;

    private static byte CORNER_FOUR_POS_RED_VALUE = 0x00;
    private static byte CORNER_FOUR_POS_GREEN_VALUE = 0x00;
    private static byte CORNER_FOUR_POS_BLUE_VALUE = 0x00;

    // Probably just padding
    private static byte UNKNOWN_VALUE = 0x00;


    /**
     * Initially we set our packet body to null
     */
    public byte[] body = new byte[] {
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
    };

    public Packet(int guid) {
        setGUID(guid);
    }

    public static int getGUID() {
        return GUID;
    }

    public static void setGUID(int guid) {
        GUID = guid;
    }

    public static byte getLOGO_POS_RED_VALUE() {
        return LOGO_POS_RED_VALUE;
    }

    public void setLOGO_POS_RED_VALUE(byte LOGO_POS_RED_VALUE) {
        this.LOGO_POS_RED_VALUE = LOGO_POS_RED_VALUE;
    }

    public static byte getLOGO_POS_GREEN_VALUE() {
        return LOGO_POS_GREEN_VALUE;
    }

    public void setLOGO_POS_GREEN_VALUE(byte LOGO_POS_GREEN_VALUE) {
        this.LOGO_POS_GREEN_VALUE = LOGO_POS_GREEN_VALUE;
    }

    public static byte getLOGO_POS_BLUE_VALUE() {
        return LOGO_POS_BLUE_VALUE;
    }

    public void setLOGO_POS_BLUE_VALUE(byte LOGO_POS_BLUE_VALUE) {
        this.LOGO_POS_BLUE_VALUE = LOGO_POS_BLUE_VALUE;
    }

    public static byte getSIDE_ONE_POS_RED_VALUE() {
        return SIDE_ONE_POS_RED_VALUE;
    }

    public void setSIDE_ONE_POS_RED_VALUE(byte SIDE_ONE_POS_RED_VALUE) {
        this.SIDE_ONE_POS_RED_VALUE = SIDE_ONE_POS_RED_VALUE;
    }

    public static byte getSIDE_ONE_POS_GREEN_VALUE() {
        return SIDE_ONE_POS_GREEN_VALUE;
    }

    public void setSIDE_ONE_POS_GREEN_VALUE(byte SIDE_ONE_POS_GREEN_VALUE) {
        this.SIDE_ONE_POS_GREEN_VALUE = SIDE_ONE_POS_GREEN_VALUE;
    }

    public static byte getSIDE_ONE_POS_BLUE_VALUE() {
        return SIDE_ONE_POS_BLUE_VALUE;
    }

    public void setSIDE_ONE_POS_BLUE_VALUE(byte SIDE_ONE_POS_BLUE_VALUE) {
        this.SIDE_ONE_POS_BLUE_VALUE = SIDE_ONE_POS_BLUE_VALUE;
    }

    public static byte getCORNER_ONE_POS_RED_VALUE() {
        return CORNER_ONE_POS_RED_VALUE;
    }

    public void setCORNER_ONE_POS_RED_VALUE(byte CORNER_ONE_POS_RED_VALUE) {
        this.CORNER_ONE_POS_RED_VALUE = CORNER_ONE_POS_RED_VALUE;
    }

    public static byte getCORNER_ONE_POS_GREEN_VALUE() {
        return CORNER_ONE_POS_GREEN_VALUE;
    }

    public void setCORNER_ONE_POS_GREEN_VALUE(byte CORNER_ONE_POS_GREEN_VALUE) {
        this.CORNER_ONE_POS_GREEN_VALUE = CORNER_ONE_POS_GREEN_VALUE;
    }

    public static byte getCORNER_ONE_POS_BLUE_VALUE() {
        return CORNER_ONE_POS_BLUE_VALUE;
    }

    public void setCORNER_ONE_POS_BLUE_VALUE(byte CORNER_ONE_POS_BLUE_VALUE) {
        this.CORNER_ONE_POS_BLUE_VALUE = CORNER_ONE_POS_BLUE_VALUE;
    }

    public static byte getSIDE_TWO_POS_RED_VALUE() {
        return SIDE_TWO_POS_RED_VALUE;
    }

    public void setSIDE_TWO_POS_RED_VALUE(byte SIDE_TWO_POS_RED_VALUE) {
        this.SIDE_TWO_POS_RED_VALUE = SIDE_TWO_POS_RED_VALUE;
    }

    public static byte getSIDE_TWO_POS_GREEN_VALUE() {
        return SIDE_TWO_POS_GREEN_VALUE;
    }

    public void setSIDE_TWO_POS_GREEN_VALUE(byte SIDE_TWO_POS_GREEN_VALUE) {
        this.SIDE_TWO_POS_GREEN_VALUE = SIDE_TWO_POS_GREEN_VALUE;
    }

    public static byte getSIDE_TWO_POS_BLUE_VALUE() {
        return SIDE_TWO_POS_BLUE_VALUE;
    }

    public void setSIDE_TWO_POS_BLUE_VALUE(byte SIDE_TWO_POS_BLUE_VALUE) {
        this.SIDE_TWO_POS_BLUE_VALUE = SIDE_TWO_POS_BLUE_VALUE;
    }

    public static byte getCORNER_TWO_POS_RED_VALUE() {
        return CORNER_TWO_POS_RED_VALUE;
    }

    public void setCORNER_TWO_POS_RED_VALUE(byte CORNER_TWO_POS_RED_VALUE) {
        this.CORNER_TWO_POS_RED_VALUE = CORNER_TWO_POS_RED_VALUE;
    }

    public static byte getCORNER_TWO_POS_GREEN_VALUE() {
        return CORNER_TWO_POS_GREEN_VALUE;
    }

    public void setCORNER_TWO_POS_GREEN_VALUE(byte CORNER_TWO_POS_GREEN_VALUE) {
        this.CORNER_TWO_POS_GREEN_VALUE = CORNER_TWO_POS_GREEN_VALUE;
    }

    public static byte getCORNER_TWO_POS_BLUE_VALUE() {
        return CORNER_TWO_POS_BLUE_VALUE;
    }

    public void setCORNER_TWO_POS_BLUE_VALUE(byte CORNER_TWO_POS_BLUE_VALUE) {
        this.CORNER_TWO_POS_BLUE_VALUE = CORNER_TWO_POS_BLUE_VALUE;
    }

    public static byte getSIDE_THREE_POS_RED_VALUE() {
        return SIDE_THREE_POS_RED_VALUE;
    }

    public void setSIDE_THREE_POS_RED_VALUE(byte SIDE_THREE_POS_RED_VALUE) {
        this.SIDE_THREE_POS_RED_VALUE = SIDE_THREE_POS_RED_VALUE;
    }

    public static byte getSIDE_THREE_POS_GREEN_VALUE() {
        return SIDE_THREE_POS_GREEN_VALUE;
    }

    public void setSIDE_THREE_POS_GREEN_VALUE(byte SIDE_THREE_POS_GREEN_VALUE) {
        this.SIDE_THREE_POS_GREEN_VALUE = SIDE_THREE_POS_GREEN_VALUE;
    }

    public static byte getSIDE_THREE_POS_BLUE_VALUE() {
        return SIDE_THREE_POS_BLUE_VALUE;
    }

    public void setSIDE_THREE_POS_BLUE_VALUE(byte SIDE_THREE_POS_BLUE_VALUE) {
        this.SIDE_THREE_POS_BLUE_VALUE = SIDE_THREE_POS_BLUE_VALUE;
    }

    public static byte getCORNER_THREE_POS_RED_VALUE() {
        return CORNER_THREE_POS_RED_VALUE;
    }

    public void setCORNER_THREE_POS_RED_VALUE(byte CORNER_THREE_POS_RED_VALUE) {
        this.CORNER_THREE_POS_RED_VALUE = CORNER_THREE_POS_RED_VALUE;
    }

    public static byte getCORNER_THREE_POS_GREEN_VALUE() {
        return CORNER_THREE_POS_GREEN_VALUE;
    }

    public void setCORNER_THREE_POS_GREEN_VALUE(byte CORNER_THREE_POS_GREEN_VALUE) {
        this.CORNER_THREE_POS_GREEN_VALUE = CORNER_THREE_POS_GREEN_VALUE;
    }

    public static byte  getCORNER_THREE_POS_BLUE_VALUE() {
        return CORNER_THREE_POS_BLUE_VALUE;
    }

    public void setCORNER_THREE_POS_BLUE_VALUE(byte CORNER_THREE_POS_BLUE_VALUE) {
        this.CORNER_THREE_POS_BLUE_VALUE = CORNER_THREE_POS_BLUE_VALUE;
    }

    public static byte getSIDE_FOUR_POS_RED_VALUE() {
        return SIDE_FOUR_POS_RED_VALUE;
    }

    public void setSIDE_FOUR_POS_RED_VALUE(byte SIDE_FOUR_POS_RED_VALUE) {
        this.SIDE_FOUR_POS_RED_VALUE = SIDE_FOUR_POS_RED_VALUE;
    }

    public static byte getSIDE_FOUR_POS_GREEN_VALUE() {
        return SIDE_FOUR_POS_GREEN_VALUE;
    }

    public void setSIDE_FOUR_POS_GREEN_VALUE(byte SIDE_FOUR_POS_GREEN_VALUE) {
        this.SIDE_FOUR_POS_GREEN_VALUE = SIDE_FOUR_POS_GREEN_VALUE;
    }

    public static  byte getSIDE_FOUR_POS_BLUE_VALUE() {
        return SIDE_FOUR_POS_BLUE_VALUE;
    }

    public void setSIDE_FOUR_POS_BLUE_VALUE(byte SIDE_FOUR_POS_BLUE_VALUE) {
        this.SIDE_FOUR_POS_BLUE_VALUE = SIDE_FOUR_POS_BLUE_VALUE;
    }

    public static byte getCORNER_FOUR_POS_RED_VALUE() {
        return CORNER_FOUR_POS_RED_VALUE;
    }

    public void setCORNER_FOUR_POS_RED_VALUE(byte CORNER_FOUR_POS_RED_VALUE) {
        this.CORNER_FOUR_POS_RED_VALUE = CORNER_FOUR_POS_RED_VALUE;
    }

    public static byte getCORNER_FOUR_POS_GREEN_VALUE() {
        return CORNER_FOUR_POS_GREEN_VALUE;
    }

    public void setCORNER_FOUR_POS_GREEN_VALUE(byte CORNER_FOUR_POS_GREEN_VALUE) {
        this.CORNER_FOUR_POS_GREEN_VALUE = CORNER_FOUR_POS_GREEN_VALUE;
    }

    public static byte getCORNER_FOUR_POS_BLUE_VALUE() {
        return CORNER_FOUR_POS_BLUE_VALUE;
    }

    public void setCORNER_FOUR_POS_BLUE_VALUE(byte CORNER_FOUR_POS_BLUE_VALUE) {
        this.CORNER_FOUR_POS_BLUE_VALUE = CORNER_FOUR_POS_BLUE_VALUE;
    }

    public static byte getUNKNOWN_VALUE() {
        return UNKNOWN_VALUE;
    }

    public void setUNKNOWN_VALUE(byte UNKNOWN_VALUE) {
        this.UNKNOWN_VALUE = UNKNOWN_VALUE;
    }

    /**
     *
     * @return
     * returns our byte array
     */
    public byte[] getBody() {
        return body;
    }


    /**
     * sets the appropriate location color code
     */
    public void setBody() {
        body[SIDE_ONE_POS_RED] = getSIDE_ONE_POS_RED_VALUE();
        body[SIDE_ONE_POS_GREEN] = getSIDE_ONE_POS_GREEN_VALUE();
        body[SIDE_ONE_POS_BLUE] = getSIDE_ONE_POS_BLUE_VALUE();
        body[CORNER_ONE_POS_RED] = getCORNER_ONE_POS_RED_VALUE();
        body[CORNER_ONE_POS_GREEN] = getCORNER_ONE_POS_GREEN_VALUE();
        body[CORNER_ONE_POS_BLUE] = getCORNER_ONE_POS_BLUE_VALUE();
        body[SIDE_TWO_POS_RED] = getSIDE_TWO_POS_RED_VALUE();
        body[SIDE_TWO_POS_GREEN] = getSIDE_TWO_POS_GREEN_VALUE();
        body[SIDE_TWO_POS_BLUE] = getSIDE_TWO_POS_BLUE_VALUE();
        body[CORNER_TWO_POS_RED] = getCORNER_TWO_POS_RED_VALUE();
        body[CORNER_TWO_POS_GREEN] = getCORNER_TWO_POS_GREEN_VALUE();
        body[CORNER_TWO_POS_BLUE] = getCORNER_TWO_POS_BLUE_VALUE();
        body[LOGO_POS_RED] = getLOGO_POS_RED_VALUE();
        body[LOGO_POS_GREEN] = getLOGO_POS_GREEN_VALUE();
        body[LOGO_POS_BLUE] = getLOGO_POS_BLUE_VALUE();
        body[SIDE_THREE_POS_RED] = getSIDE_THREE_POS_RED_VALUE();
        body[SIDE_THREE_POS_GREEN] = getSIDE_THREE_POS_GREEN_VALUE();
        body[SIDE_THREE_POS_BLUE] = getSIDE_THREE_POS_BLUE_VALUE();
        body[CORNER_THREE_POS_RED] = getCORNER_THREE_POS_RED_VALUE();
        body[CORNER_THREE_POS_GREEN] = getCORNER_THREE_POS_GREEN_VALUE();
        body[CORNER_THREE_POS_BLUE] = getCORNER_THREE_POS_BLUE_VALUE();
        body[SIDE_FOUR_POS_RED] = getSIDE_FOUR_POS_RED_VALUE();
        body[SIDE_FOUR_POS_GREEN] = getSIDE_FOUR_POS_GREEN_VALUE();
        body[SIDE_FOUR_POS_BLUE] = getSIDE_FOUR_POS_BLUE_VALUE();
        body[CORNER_FOUR_POS_RED] = getCORNER_FOUR_POS_RED_VALUE();
        body[CORNER_FOUR_POS_GREEN] = getCORNER_FOUR_POS_GREEN_VALUE();
        body[CORNER_FOUR_POS_BLUE] = getCORNER_FOUR_POS_BLUE_VALUE();
        body[UNKNOWN] = getUNKNOWN_VALUE();
    }
}
