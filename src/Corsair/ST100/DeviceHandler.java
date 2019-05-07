package ST100;

import org.usb4java.BufferUtils;
import org.usb4java.DeviceHandle;
import org.usb4java.LibUsb;
import org.usb4java.LibUsbException;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.List;

import javax.usb.UsbConfiguration;
import javax.usb.UsbConst;
import javax.usb.UsbControlIrp;
import javax.usb.UsbDevice;
import javax.usb.UsbDeviceDescriptor;
import javax.usb.UsbDisconnectedException;
import javax.usb.UsbEndpoint;
import javax.usb.UsbException;
import javax.usb.UsbHostManager;
import javax.usb.UsbHub;
import javax.usb.UsbInterface;
import javax.usb.UsbInterfacePolicy;
import javax.usb.UsbNotActiveException;
import javax.usb.UsbNotClaimedException;
import javax.usb.UsbNotOpenException;
import javax.usb.UsbPipe;

import static ST100.Packets.*;

/**
 * DeviceHandler
 * Used to communicate between our program and our Corsair ST100 (model RDA0014) headset stand
 */
public class DeviceHandler {
    /**
     * 1b1c - corsair (vendor)
     */
    private static final short VENDOR_ID = 0x1b1c;

    /**
     * 0a34 - product (LED Controller)
     */
    private static final short PRODUCT_ID = 0x0a34;
    // 1b1c:0a32 (AUDIO DEVICE)
    // 1b1c:0a33( HUB)
    // 1b1c:0a36 (HUB)

    /**
     * TIMEOUT default is 1 second
     */
    private static final int TIMEOUT = 1000;

    /**
     * bEndpointAddress     0x01  EP 1 OUT
     */
    private static final byte OUT_ENDPOINT = (byte)0x01;

    /** bEndpointAddress     0x81  EP 1 IN */
    private static final byte IN_ENDPOINT = (byte)0x81;


    /**
     * getDevice()
     * @return
     */
    public static UsbDevice getDevice() {
        return device;
    }

    /**
     * UsbDevice device
     */
    private static UsbDevice device;

    /**
     * UsbEndpoint usbEndpoint
     */
    private static UsbEndpoint usbEndpoint;

    /**
     * UsbInterface usbInterface
     */
    private static UsbInterface usbInterface;


    /**
     * DeviceHandler()
     * @throws SecurityException
     * @throws UsbException
     */
    public DeviceHandler() throws SecurityException, UsbException {
        device = findCorsairStand(UsbHostManager.getUsbServices().getRootUsbHub());
        if (device == null) {
            System.err.println("not found.");
            System.exit(1);
            return;
        }

        // Claim the interface
        UsbConfiguration configuration = device.getActiveUsbConfiguration();
        usbInterface = configuration.getUsbInterface((byte) 0);
        usbInterface.claim(new UsbInterfacePolicy() {
            @Override
            public boolean forceClaim(UsbInterface usbInterface) {
                return true;
            }
        });

        usbEndpoint = usbInterface.getUsbEndpoint(OUT_ENDPOINT);

    }

    /**
     * terminates our interface
     */
    public void close() {
        try {
            usbInterface.release();
        } catch (UsbNotActiveException	| UsbDisconnectedException | UsbException e) {
            e.printStackTrace();
        }
    }

    /**
     * findCorsairStand
     * @param hub
     * @return
     * Iterate through the device list
     * Find our headset stand
     * set it as the device
     */
    public static UsbDevice findCorsairStand(UsbHub hub) {
        for (UsbDevice device : (List<UsbDevice>) hub.getAttachedUsbDevices()) {
            UsbDeviceDescriptor desc = device.getUsbDeviceDescriptor();

            if (desc.idVendor() == VENDOR_ID && desc.idProduct() == PRODUCT_ID) {

                System.err.println("Found Corsair 1400 " + desc.idVendor() + " " + desc.idProduct());
                return device;
            }

            if (device.isUsbHub()) {
                //System.err.println("device.isUsbHub()" + " " + desc.idVendor() + " " + desc.idProduct());
                device = findCorsairStand((UsbHub) device);

                if (device != null) {

                    return device;
                }
            }
        }

        return null;
    }


    /**
     * runIt()
     * @throws SecurityException
     * @throws UsbException
     * Sends the information needed to change the headset stand colors (Theres a lot more to do but heres the POC)
     */
    public static void runIt() throws SecurityException, UsbException {
        device = findCorsairStand(UsbHostManager.getUsbServices().getRootUsbHub());
        if (device == null) {
            System.err.println("not found.");
            System.exit(1);
            return;
        }
        System.out.println("device: " + device);

        // System.err.println(iface.getUsbInterfaceDescriptor());
        List usbEndpoints = usbInterface.getUsbEndpoints();

        for (Object p : usbEndpoints) {
            System.err.println(((UsbEndpoint) p).getUsbEndpointDescriptor());
            //System.err.println(((UsbEndpoint) p).getUsbEndpointDescriptor().bEndpointAddress());
        }

        UsbControlIrp irp = device.createUsbControlIrp(
                (byte) (UsbConst.REQUESTTYPE_DIRECTION_IN
                        | UsbConst.REQUESTTYPE_TYPE_STANDARD
                        | UsbConst.REQUESTTYPE_RECIPIENT_DEVICE),
                UsbConst.REQUEST_GET_CONFIGURATION,
                (short) 0,
                (short) 0);

        irp.setData(new byte[1]);

        try {
            device.syncSubmit(irp);
        } catch (IllegalArgumentException | UsbDisconnectedException | UsbException e) {
            e.printStackTrace();
        }
        //System.out.println("current configuration number "+irp.getData()[0]);
        System.out.println("getUsbEndpoints: " + usbInterface.getUsbEndpoints().size());


        usbEndpoint = usbInterface.getUsbEndpoint(OUT_ENDPOINT);
        UsbPipe pipe = usbEndpoint.getUsbPipe();
        pipe.open();
        try {
            int sendIt = 0;
            sendIt = pipe.syncSubmit(initPacket);
            System.out.println("[InitPacket] " + sendIt + " bytes sent");

            sendIt = pipe.syncSubmit(PacketManager.SetColor(Packets.PADDING, Packets.SIDE_ONE_POS_BLUE, (byte)0xFF));
            Thread.sleep(2000);
            System.out.println(sendIt + " bytes sent");

            sendIt = pipe.syncSubmit(PacketManager.SetColor(Packets.PADDING, Packets.SIDE_TWO_POS_BLUE, (byte)0xFF));
            System.out.println(sendIt + " bytes sent");


            sendIt = pipe.syncSubmit(PacketManager.SetColor(Packets.PADDING, Packets.LOGO_POS_BLUE, (byte)0xFF));
            Thread.sleep(2000);
            System.out.println(sendIt + " bytes sent");

            sendIt = pipe.syncSubmit(PacketManager.SetColor(Packets.PADDING, Packets.SIDE_THREE_POS_BLUE, (byte)0xFF));
            Thread.sleep(2000);
            System.out.println(sendIt + " bytes sent");

            sendIt = pipe.syncSubmit(PacketManager.SetColor(Packets.PADDING, Packets.SIDE_FOUR_POS_BLUE, (byte)0xFF));
            Thread.sleep(2000);
            System.out.println(sendIt + " bytes sent");

            sendIt = pipe.syncSubmit(PacketManager.SetColor(Packets.PADDING, Packets.CORNER_ONE_POS_RED, (byte)0xFF));
            Thread.sleep(2000);
            System.out.println(sendIt + " bytes sent");

            sendIt = pipe.syncSubmit(PacketManager.SetColor(Packets.PADDING, Packets.CORNER_TWO_POS_RED, (byte)0xFF));
            Thread.sleep(2000);
            System.out.println(sendIt + " bytes sent");

            sendIt = pipe.syncSubmit(PacketManager.SetColor(Packets.PADDING, Packets.CORNER_THREE_POS_RED, (byte)0xFF));
            Thread.sleep(2000);
            System.out.println(sendIt + " bytes sent");

            sendIt = pipe.syncSubmit(PacketManager.SetColor(Packets.PADDING, Packets.CORNER_FOUR_POS_RED, (byte)0xFF));
            Thread.sleep(2000);
            System.out.println(sendIt + " bytes sent");
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            pipe.close();
        }
        usbInterface.release();
    }


//	public void sendMessage(UsbDevice device, byte[] message) throws UsbException {
//		UsbControlIrp irp = device.createUsbControlIrp(
//				(byte) (UsbConst.REQUESTTYPE_TYPE_CLASS |
//						UsbConst.REQUESTTYPE_RECIPIENT_INTERFACE), (byte) 0x09,
//						(short) 2, (short) 1);
//		irp.setData(message);
//		device.syncSubmit(irp);
//	}
    /**
     *     /* "7","0.000060","host","1.1.0","USB","36","CLEAR FEATURE Request"
     * bmRequestType = 0x02;
     * bRequest = 0xa3;
     * wValue = 0x0000;
     * wIndex = 0x0000;
     * transfer bytes = 8;
     * new byte[] data = {0x00, 0x00, 0x00, 0x00} */
//	public void sendIRP(byte[] data) {
//		UsbPipe pipe = usbEndpoint.getUsbPipe();
//		try {
//			pipe.open();
//			UsbIrp irp = pipe.createUsbIrp();
//			irp.setData(data);
//			pipe.syncSubmit(irp);
//		} catch (UsbNotActiveException | UsbNotClaimedException
//				| UsbDisconnectedException | UsbException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				pipe.close();
//			} catch (UsbNotActiveException | UsbNotOpenException
//					| UsbDisconnectedException | UsbException e) {
//				e.printStackTrace();
//			}
//		}
//	}

    /**
     * sendWithPipe(byte[])
     * @param data
     */
    public static void sendWithPipe(byte[] data) {
        UsbPipe pipe = usbEndpoint.getUsbPipe();
        try {
            pipe.open();
            int sent = pipe.syncSubmit(data);
            System.out.println(sent + " bytes sent");
        } catch (UsbNotActiveException | UsbNotClaimedException
                | UsbDisconnectedException | UsbException e) {
            e.printStackTrace();
        } finally {
            try {
                pipe.close();
            } catch (UsbNotActiveException | UsbNotOpenException
                    | UsbDisconnectedException | UsbException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * read
     * @param handle
     * @param size
     * @return
     * reads and displays information from the device
     */
    public static ByteBuffer read(DeviceHandle handle, int size) {
        ByteBuffer buffer = BufferUtils.allocateByteBuffer(size).order(ByteOrder.LITTLE_ENDIAN);
        IntBuffer transferred = BufferUtils.allocateIntBuffer();
        int result = LibUsb.bulkTransfer(handle, IN_ENDPOINT, buffer, transferred, TIMEOUT);
        if (result != LibUsb.SUCCESS) {
            throw new LibUsbException("Unable to read data", result);
        }
        System.out.println(transferred.get() + " bytes read from device");
        return buffer;
    }
}
