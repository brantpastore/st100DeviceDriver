package ST100;

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

    /** If thread should abort. */
    private static volatile boolean abort;

    /** The communication timeout in milliseconds. */
    private static final int TIMEOUT = 5000;

    /**  */
    public void InitIRQConnection() {

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

    /**
     * Aborts the event handling thread.
     */
    public void abort()
    {
        this.abort = true;
    }

    static TransferCallback callback = new TransferCallback()
    {
        @Override
        public void processTransfer(Transfer transfer)
        {
            System.out.println(transfer.actualLength() + " bytes sent");
            LibUsb.freeTransfer(transfer);
        }
    };

//    protected final void processControlIrp(final UsbControlIrp irp) throws UsbException {
//        final ByteBuffer buffer = ByteBuffer.allocateDirect(irp.getLength());
//        buffer.put(irp.getData(), irp.getOffset(), irp.getLength());
//        buffer.rewind();
//        final DeviceHandle handle = DeviceManager.open();
//        final int result = LibUsb.controlTransfer(handle, irp.bmRequestType(), irp.bRequest(), irp.wValue(), irp.wIndex(), buffer, TIMEOUT);
//        if (result < 0) {
//            System.out.println("Unable to submit control message" + result);
//        }
//        buffer.rewind();
//        buffer.get(irp.getData(), irp.getOffset(), result);
//        irp.setActualLength(result);
//        if (irp.getActualLength() != irp.getLength() && !irp.getAcceptShortPacket()) {
//            throw new UsbShortPacketException();
//        }
//    }


    public static int bulkTransfer(DeviceHandle handle, byte address, byte[] buff) throws UsbException
    {
//        ByteBuffer buffer = BufferUtils.allocateByteBuffer(buff.length);
//        buffer.put(buff);
//        Transfer transfer = LibUsb.allocTransfer();
//        LibUsb.fillBulkTransfer(transfer, handle, Connector.getOutEndpoint(), buffer, callback, null, TIMEOUT);
//        int result = LibUsb.submitTransfer(transfer);
//        if (result != LibUsb.SUCCESS) throw new LibUsbException("Unable to submit transfer", result);
//
//        if (result == LibUsb.SUCCESS) {
//            System.out.println("Data Transferred:");
//            System.out.println(buff.length);
//            System.out.println(buff);
//            for (int i = 0; i < buffer.capacity(); i++) {
//                System.out.println(buffer.get(i));
//            }
//        }
        //return result;

        IntBuffer transferred = IntBuffer.allocate(1);
        int res = 0;
        ByteBuffer buffer = BufferUtils.allocateByteBuffer(buff.length);
        buffer.put(buff);
        while (res == LibUsb.ERROR_TIMEOUT) {
            res = LibUsb.bulkTransfer(handle, address, buffer, transferred, TIMEOUT);

            if (res == LibUsb.ERROR_TIMEOUT) {
                throw new UsbAbortException();
            }
        }

        if (res < 0) {
            //throw
        } else {
            System.out.println("Data Transferred:");
            System.out.println("Length: " + buff.length);
            System.out.println(buff);
            for (int i = 0; i < buffer.capacity(); i++) {
                System.out.println(buffer.get(i));
            }
        }
        return transferred.get(0);
    }


    /* "7","0.000060","host","1.1.0","USB","36","CLEAR FEATURE Request"
bmRequestType = 0x02;
bRequest = 0xa3;
wValue = 0x0000;
wIndex = 0x0000;
transfer bytes = 8;
new byte[] data = {0x00, 0x00, 0x00, 0x00} */
    public static int transferInterrupt(final DeviceHandle handle, final byte address, byte bmRequestType, byte bRequest, byte wFeatureSelector, short wIndex, byte[] buff) throws UsbException
    {
        final ByteBuffer buffer = BufferUtils.allocateByteBuffer(buff.length);
        buffer.put(buff);
        final IntBuffer transferred = IntBuffer.allocate(1);
        int result;
        do
        {
            result = LibUsb.interruptTransfer(handle, address, buffer, transferred, TIMEOUT);
            if (result == LibUsb.ERROR_TIMEOUT)
                throw new UsbAbortException();
        }
        while (result == LibUsb.ERROR_TIMEOUT);
        if (result < 0)
        {
            System.out.println("Transfer error on interrupt endpoint");
        }
        return transferred.get(0);
    }

    /**
     *
     * @param handle
     * @param data
     */
//    public static void write(DeviceHandle handle, byte[] data)
//    {
//        ByteBuffer buffer = BufferUtils.allocateByteBuffer(data.length);
//        buffer.put(data);
//        IntBuffer transferred = BufferUtils.allocateIntBuffer();
//        int result = LibUsb.bulkTransfer(handle, DeviceManager.getOutEndpoint(), buffer,
//                transferred, TIMEOUT);
//        if (result != LibUsb.SUCCESS)
//        {
//            throw new LibUsbException("Unable to send data", result);
//        }
//        System.out.println(transferred.get() + " bytes sent to device");
//    }



    /**
     * run()
     */
    @Override
    public void run()
    {
        while (!abort)
        {
            int result = LibUsb.handleEventsTimeout(null, 250000);
            if (result != LibUsb.SUCCESS)
                throw new LibUsbException("Unable to handle events", result);
        }
    }
}
