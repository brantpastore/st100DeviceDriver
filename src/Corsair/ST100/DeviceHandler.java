package Corsair.ST100;

import org.usb4java.BufferUtils;
import org.usb4java.DeviceHandle;
import org.usb4java.LibUsb;
import org.usb4java.LibUsbException;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.usb.*;

import static Corsair.ST100.Packets.initPacket;

/**
 * DeviceHandler
 * Used to communicate between our program and our Corsair ST100 (model RDA0014) headset stand
 */
public class DeviceHandler implements Runnable {

    /**
     * DeviceHandler Logger
     */
    private static Logger nLogger = Logger.getLogger("DHandlerLog");

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
     *
     * @return
     */
    public static long getInterval() {
        return interval;
    }

    /**
     *
     * @param interval
     */
    public static void setInterval(long interval) {
        DeviceHandler.interval = interval;
    }

    /**
     * The interval between each packet sent
     */
    public static long interval = 0l;

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
     * runThread
     */
    private Thread runThread;

    /**
     * used to end the current thread
     * @param abort
     */
    public static void Abort(boolean abort) {
        Abort = abort;
    }

    /**
     * Used to end the current thread
     */
    public static boolean Abort = false;

    /**
     * The current list of packets being sent to the device
     */
    public static ArrayList<Packet> currentPacketList = new ArrayList<>();

    /**
     * Used to set the current list of packets being sent to the device
     * @param list
     */
    public void setCurrentPacketList(ArrayList<Packet> list) {
        this.currentPacketList.clear();
        this.currentPacketList.addAll(list);
    }

    /**
     * DeviceHandler()
     * @throws SecurityException
     * @throws UsbException
     */
    public DeviceHandler() throws SecurityException, UsbException {
        super();
        device = findCorsairStand(UsbHostManager.getUsbServices().getRootUsbHub());
        if (device == null) {
            System.err.println("not found.");
            System.exit(1);
            return;
        }

        Claim();
        usbEndpoint = usbInterface.getUsbEndpoint(OUT_ENDPOINT);
    }

    /**
     * Claims the interface of the selected device
     */
    public static void Claim() {
        try {
            // Claim the interface
            UsbConfiguration configuration = device.getActiveUsbConfiguration();
            usbInterface = configuration.getUsbInterface((byte) 0);
            usbInterface.claim(new UsbInterfacePolicy() {
                @Override
                public boolean forceClaim(UsbInterface usbInterface) {
                    nLogger.log(Level.INFO, "Device claimed");
                    return true;
                }
            });
        } catch (UsbClaimException e) {
            nLogger.log(Level.SEVERE, e.getMessage());
        } catch (UsbException e) {
            nLogger.log(Level.SEVERE, e.getMessage());
        }
    }

    /**
     * terminates our interface
     */
    public void close() {
        try {
            nLogger.log(Level.INFO, "Terminating interface");
            usbInterface.release();
        } catch (UsbNotActiveException | UsbDisconnectedException | UsbException e) {
            nLogger.log(Level.SEVERE, e.getMessage());
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
                nLogger.log(Level.INFO, "Found Corsair 1400 " + desc.idVendor() + " " + desc.idProduct());
                return device;
            }

            if (device.isUsbHub()) {
                device = findCorsairStand((UsbHub) device);

                if (device != null) {
                    return device;
                }
            }
        }
        return null;
    }

    /**
     * Our thread method
     */
    @Override
    public void run() {
        Abort(false);
        runThread = Thread.currentThread();
        nLogger.log(Level.INFO, "Thread starting");
        try {
            runIt(currentPacketList);
        } catch(UsbException e) {
            nLogger.log(Level.SEVERE, e.getMessage());
        }
    }

    /**
     * Interrupts the current thread
     */
    public void stop() {
        nLogger.log(Level.WARNING, "Aborting current Thread");
        Abort(true);
        runThread.interrupt();
    }

    /**
     *
     * @param type
     * @throws SecurityException
     * @throws UsbException
     * The Type is the arrayList packets given by the type of setting selected by the user
     */
    public void runIt(ArrayList<Packet> type) throws SecurityException, UsbException {
        nLogger.log(Level.INFO, "Thread starting...");
        // System.out.println("device: " + device);

        // System.err.println(iface.getUsbInterfaceDescriptor());
        // List usbEndpoints = usbInterface.getUsbEndpoints();

        //for (Object p : usbEndpoints) {
            //System.err.println(((UsbEndpoint) p).getUsbEndpointDescriptor());
            //System.err.println(((UsbEndpoint) p).getUsbEndpointDescriptor().bEndpointAddress());
        //}

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
            nLogger.log(Level.INFO, "Sent IRP");
        } catch (IllegalArgumentException | UsbDisconnectedException | UsbException e) {
            nLogger.log(Level.INFO, e.getMessage());
        }

        nLogger.log(Level.INFO, "current configuration number "+irp.getData()[0]);
        nLogger.log(Level.INFO, "getUsbEndpoints: " + usbInterface.getUsbEndpoints().size());

        usbEndpoint = usbInterface.getUsbEndpoint(OUT_ENDPOINT);
        UsbPipe pipe = usbEndpoint.getUsbPipe();

        try {
            while (Boolean.valueOf(Abort).equals(false)) {
                try {
                    if (!usbInterface.isClaimed()) {
                        Claim();
                    }
                    if (pipe.isOpen() == false) {
                        pipe.open();
                        nLogger.log(Level.INFO, "Pipe opened successfully");
                    }

                    int sendIt = 0;
                    sendIt = pipe.syncSubmit(initPacket);
                    nLogger.log(Level.INFO, "[InitPacket] " + sendIt + " bytes sent");

                    for (Packet p : type) {
                        pipe.syncSubmit(PacketManager.FormPacket(p.getBody()));
                        runThread.sleep(interval);
                    }
                } catch (ConcurrentModificationException e) {
                    nLogger.log(Level.SEVERE, e.getMessage());
                } catch (UsbException e) {
                    nLogger.log(Level.SEVERE, e.getMessage());
                } catch (InterruptedException e) {
                    nLogger.log(Level.SEVERE, e.getMessage());
                    return;
                }
            }
        } finally {
            try {
                pipe.close();
                usbInterface.release();
            } catch (UsbException e) {
                nLogger.log(Level.SEVERE, e.getMessage());
            }
        }
    }


    /**
     * sendWithPipe(byte[])
     * @param data
     */
    public static void sendWithPipe(byte[] data) {
        UsbPipe pipe = usbEndpoint.getUsbPipe();
        try {
            pipe.open();
            int sent = pipe.syncSubmit(data);
        } catch (UsbNotActiveException | UsbNotClaimedException
                | UsbDisconnectedException | UsbException e) {
            nLogger.log(Level.SEVERE, e.getMessage());
        } finally {
            try {
                pipe.close();
            } catch (UsbNotActiveException | UsbNotOpenException
                    | UsbDisconnectedException | UsbException e) {
                nLogger.log(Level.INFO, e.getMessage());
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
        nLogger.log(Level.INFO, transferred.get() + " bytes read from device");
        return buffer;
    }
}
