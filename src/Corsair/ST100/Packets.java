package Corsair.ST100;

/**
 * Packets
 * This enum is used to hold packets data needed for predefined settings
 */
public enum Packets {
    Packets() {};

    /**
     * Header
     * First four bytes seem to be in all packets that contain color hex bodies
     */
    public static final byte[] HEADER = new byte[]{
            0x07, 0x22, 0x14, 0x00
    };

    public static final byte[] PADDING = new byte[] {
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    /* ASCII REFERENCES
     *
     *
     *     %%LGO%
     *     %%%%%%
     *     %%%%%%
     *     %%%%%%
     *     %%%%%%
     *     %&%%%%
     *     %&%&%%
     *     %&&&&&
     *     &&&&&&  *&&&/
     *     %&&%(/(&& C1 &&&&
     *     %(( S1 &&&&&&&&&&&&&
     *   &&&@&@@@&&@&&&&& S2 &&&&%
     * &&& C4 @@@&&&&&&&&&&&&&&&&&&&
     * %%&&&&&@&&&&%&&&&&&&&&&& C2 &&&,#
     *  (%%%& S4 &&&&&&&&&&&&&&&&%%%.
     *     #%%*&&&&&&&&&&&&& S3 %%.
     *       .%%%&&&&&&&&&%%%.
     *          .%%%@ C3 %%%(
     *             ,&&&(
     *
     *   LOG - Logo LED
     *
     *   C1 - Corner One
     *   C2 - Corner TWO
     *   C3 - Corner Three
     *   C4 - Corner Four
     *
     *   S1 - Side 1
     *   S2 - Side 2
     *   S3 - Side 3
     *   S4 - Side 4
     */

    /*
     * Structure of the packet is explained here
     *
     * /**
     * * order seems to be every 3 bytes is a different LED on the device
     * * the colors are represented as Hexidecimal colors
     * * first byte in order is RED, second is GREEN, third is BLUE
     * *
     * * ?? - means the value is unknown
     * * first two characters in the label are associated to what part of the device theyre on the ascii chart above
     * * for instance C1 is corner 1, L1 is line 1
     * * the second two characters in the label are their colors if set to 0xFF
     * * cR - color Red, cG - color Green, cB - color Blue
     * *
     * * so if you set C1cR to (byte)0xFF it will change corner 1 color to red
     *
     *
     * Packet Header (First four bytes) mean change LED values?
     *
     * ?? - UNKNOWN
     *
     * ??  |  ?? |  ?? |   ?? |S1cR| S1cG| S1cB| C4cR
     * 0x07| 0x22| 0x14| 0x00 |0x00| 0x00| 0x00| 0x00
     *
     * C4cG | C4cB |S4cR | S4cG | S4cB |C3cR | C3cG | C3c
     * 0x00 | 0x00| 0x00 | 0x00| 0x00 | 0x00 | 0x00 | 0x00
     *
     * LGR  | LGG | LGB | S3cR | S3cG | S3cB | C2cR | C2cG
     * 0x00 |0x00| 0x00 | 0x00 | 0x00 | 0x00 | 0x00 | 0x00
     *
     * C2cB |S2cR | S2cG | S2cB |C1cR |C1cG | C1cB | ??
     * 0x00| 0x00 | 0x00 | 0x00 |0x00 |0x00 | 0x00 |0x00
     *
     * The remaining bytes are padding as far as i can tell
     * 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
     * 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
     * 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
     * 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
     *
     */

    public static final byte[] initPacket = {
            0x07, 0x04, 0x02, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};


    // Sets all values to null shuts the lighting off
    public static final byte[] turnOffLEDs = new byte[]{
            0x07, 0x22, 0x14, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    /**
     * Sets all the LEDs to RED (0xFF 0x00 0x00)
     */
    public static final byte[] SET_TO_RED = new byte[]{
            //??  | ?? | ??  | ??  |L1cR, L1cG, L1cB| C4cR
            0x07, 0x22, 0x14, 0x00, (byte) 0xFF, 0x00, 0x00, (byte) 0xFF,
            //C4cG, C4cB |L4cR, L4cG, L4cB |C3cR, C3cG, C3cB
            0x00, 0x00, 0x00, 0x00, 0x00, (byte) 0xFF, 0x00, 0x00,
            //LGR,  LGG,  LGB | L3cR, l3cG, L3cB | C2cR, C2cG
            (byte) 0xFF, 0x00, 0x00, 0x00, 0x00, 0x00, (byte)0xFF, 0x00,
            //C2cB, C3cR, C3cG, C3cB,|C1cR, C1cG, C1cB | ??
            0x00, 0x00, 0x00, 0x00, (byte) 0xFF, 0x00, 0x00, 0x00,
            // The rest of the packet seems to be padding.
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    /**
     * Sets all the LEDs to GREEN (0x00 0xFF 0x00)
     */
    public static final byte[] SET_TO_GREEN = new byte[]{
            //??  | ?? | ??  | ??  |L1cR, L1cG, L1cB| C4cR
            0x07, 0x22, 0x14, 0x00, 0x00, (byte)0xFF, 0x00, 0x00,
            //C4cG, C4cB |L4cR, L4cG, L4cB |C3cR, C3cG, C3cB
            (byte)0xFF, 0x00, 0x00, (byte)0xFF, 0x00, 0x00, (byte)0xFF, 0x00,
            //LGR,  LGG,  LGB | L3cR, l3cG, L3cB | C2cR, C2cG
            0x00, (byte)0xFF, 0x00, 0x00, (byte)0xFF, 0x00, 0x00, (byte)0xFF,
            //C2cB, C3cR, C3cG, C3cB,|C1cR, C1cG, C1cB | ??
            0x00, 0x00, (byte)0xFF, 0x00, 0x00, (byte)0xFF, 0x00, 0x00,

            // The rest of the packet seems to be padding.
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    /**
     * Sets all the LEDs to BLUE (0x00 0x00 0xFF)
     */
    public static final byte[] SET_TO_BLUE = new byte[] {
            //??  | ?? | ??  | ??  |L1cR, L1cG, L1cB| C4cR
            0x07, 0x22, 0x14, 0x00, 0x00, (byte)0xFF, 0x00, 0x00,
            //C4cG, C4cB |L4cR, L4cG, L4cB |C3cR, C3cG, C3cB
            0x00, (byte)0xFF, 0x00, 0x00, (byte)0xFF, 0x00, 0x00, (byte)0xFF,
            //LGR,  LGG,  LGB | L3cR, l3cG, L3cB | C2cR, C2cG
            0x00, 0x00, (byte)0xFF, 0x00, 0x00, (byte)0xFF, 0x00, 0x00,
            //C2cB, C3cR, C3cG, C3cB,|C1cR, C1cG, C1cB | ??
            (byte)0xFF, 0x00, 0x00, (byte)0xFF, 0x00, 0x00, (byte)0xFF, 0x00,

            // The rest of the packet seems to be padding.
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
}