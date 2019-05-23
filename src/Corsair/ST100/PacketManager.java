package Corsair.ST100;

import org.usb4java.*;

import javax.usb.*;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class PacketManager extends Thread {
    /** Packets enum structure */
    public static Packets packets;


    /** The communication timeout in milliseconds. */
    private static final int TIMEOUT = 5000;


    /**
     * FormPacket
     * @param bodyData
     * @return
     * Combines Header with the supplied bodydata and adds trailing padding
     */
    public static byte[] FormPacket(byte[] bodyData) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            out.write(Packets.HEADER);
            out.write(bodyData);
            out.write(Packets.PADDING);

            byte[] formedPacket = out.toByteArray();

            return formedPacket;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /*
    * S1cR| S1cG| S1cB| C4cR
    *   0 |    1|    2|    3|
    * C4cG| C4cB| S4cR| S4cG| S4cB| C3cR| C3cG| C3cB
    *    4|    5|    6|    7|    8|    9|   10| 11
    * LGR |  LGG| LGB | S3cR| S3cG| S3cB| C2cR| C2cG
    *   12|   13|   14|   15|   16|   17|   18| 19
    * C2cB| S2cR| S2cG| S2cB| C1cR| C1cG| C1cB| ??
    *   20|   21|   22|   23|   24|   25|   26| 27,
     */
    public static byte[] SetColor(byte[] packet, int ledLocation, byte colorData) {
        packet[ledLocation] = colorData;
        byte[] nPacket = packet;
        byte[] resPacket = FormPacket(packet);
        return resPacket;
    }

    public static UsbControlIrp SendIRP(byte bmRequestType, byte bRequest, short wValue, short wIndex, byte[] data) {
        UsbControlIrp getUsageIrp = DeviceHandler.getDevice().createUsbControlIrp(bmRequestType, bRequest, wValue, wIndex);
        return getUsageIrp;
    }

    public byte[] CreateDataPacket(byte[] data) throws IOException  {
        ByteArrayOutputStream byteArrPacket = new ByteArrayOutputStream();
        DataOutputStream dataOutPacket = new DataOutputStream(byteArrPacket);
        dataOutPacket.write(data);
        dataOutPacket.writeBytes("\r\n");
        dataOutPacket.flush();
        byte[] res = byteArrPacket.toByteArray();
        return res;
    }
}
