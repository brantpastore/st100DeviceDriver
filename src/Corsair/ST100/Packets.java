package ST100;

/**
 * Packets
 * This enum is used to hold packets needed to communicate to our device
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


    /** ASCII REFERENCES
     *
     *     %%%%%%
     *     %%%%%%
     *     %&%%%%
     *     %&%&%%
     *     %&&&&&
     *     &&&&&&  *&&&/
     *     %&&%(/(&& C4 &&&&
     *     %(( L1 &&&&&&&&&&&&&
     *   &&&@&@@@&&@&&&&& L4 &&&&%
     * &&& C1 @@@&&&&&&&&&&&&&&&&&&&
     * %%&&&&&@&&&&%&&&&&&&&&&& C3 &&&,#
     *  (%%%& L2 &&&&&&&&&&&&&&&&%%%.
     *     #%%*&&&&&&&&&&&&& L3 %%.
     *       .%%%&&&&&&&&&%%%.
     *          .%%%@ C2 %%%(
     *             ,&&&(
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

    /**
     *             .&&&&&&&%
     *              /& &&&@%
     *                 %%&&&
     *                 %%%&&
     *                 %LGO%
     *                 %%%&%
     *                 %%%%%
     *                 %%%&%
     *                 %%&&%
     *                 %%&&%
     *                 %&&&%
     *                 &&&&%
     *                 &&&&%
     *                 &&&&%
     *                 &&&&%
     *             ,   %%&&%
     *        &&&&&&&%&@@@@&.
     *   ,&&&&&&&&&&%%&@@@@@&&
     *   %&&&&%&&&&&&&&@@@@%%%%*
     *   .,,(/%&&&&&&&@&%%#%/..
     *         .,,*((%&%/,.
     *
     *  LGO - Logo LED
     */

    /**
     * Structure of the packet is explained here
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
     */
    public static final byte[] Structure = new byte[]
            { //??  | ?? | ??  | ??  |L1cR, L1cG, L1cB| C4cR
                    0x07, 0x22, 0x14, 0x00, 0x00, 0x00, 0x00, 0x00,
                    //C4cG, C4cB |L4cR, L4cG, L4cB |C3cR, C3cG, C3cB
                    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                    //LGR,  LGG,  LGB | L3cR, l3cG, L3cB | C2cR, C2cG
                    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                    //C2cB, C3cR, C3cG, C3cB,|C1cR, C1cG, C1cB | ??
                    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,

                    // The rest of the packet seems to be padding.
                    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};


//    public static final byte[] setupPacket = new byte[] {0xff, 0x98, 0x00, 0xff,
//            0x40, 0x00, 0xe0, 0x00, 0x24, 0x7c, 0x00, 0x97,
//            0xff, 0x98, 0x00, 0x52, 0x00, 0x7d, 0x18, 0x00,
//            0x8e, 0x00, 0x5d, 0x4c, 0x6d, 0xbb, 0x00, 0x00};//

    /**
     * TODO:
     * Dig and dive into these packets to understand what they are/do/are necessary
     */

    public static final byte[] setupOne = {0x21, 0x0A, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
    public static final byte[] setupTwo = {(byte) 0x80, 0x06, 0x04, 0x03, 0x09, 0x04, (byte) 0x82, 0x00};

    public static final byte[] initDevMsg = {
            0x0E, 0x01, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00,
            0x02, 0x00, 0x01, 0x00, 0x1C, 0x1B, 0x34, 0x0A,
            0x08, 0x00, 0x00, 0x00, (byte) 0xC2, (byte) 0xFF, 0x40, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    public static final byte[] ourReply = {
            0x0E, 0x05, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    public static final byte[] initPacket = {
            0x07, 0x04, 0x02, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    public static final byte[] initPacketTwo = {
            0x07, 0x22, 0x14, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    public static final byte[] initPacketThree = {
            0x07, 0x04, 0x02, 0x00, 0x03, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    public static final byte[] FirstPacket = {0x07, 0x22, 0x14, 0x00, (byte) 0xFF, (byte) 0xA3, 0x00, (byte) 0xFF,
            0x4B, 0x00, (byte) 0xEF, 0x00, 0x12, (byte) 0x88, 0x00, (byte) 0x8E,
            (byte) 0xFF, (byte) 0xA3, 0x00, 0x58, 0x00, (byte) 0x81, 0x20, 0x00,
            (byte) 0x8B, 0x00, 0x46, 0x5F, 0x4F, (byte) 0xBB, 0x00, 0x00,
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