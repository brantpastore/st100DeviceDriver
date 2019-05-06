package ST100;

/**
 * Main class
 */
public class Main {
    public static void main(String[] args) throws Exception {
        DeviceHandler dMan = new DeviceHandler();

        if (dMan.getDevice() == null) {
            throw new Exception("ST100 [RDA0014] not found");
        } else {
            System.out.println("ST100 [RDA0014]  Found!");
        }
        dMan.runIt();
    }
}
