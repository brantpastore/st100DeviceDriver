package Corsair.ST100;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class that connects to our SQLite database
 */
public class SQLiteDriverConnection {
    private static Connection conn;
    private static String url = "jdbc:sqlite:./packets.db";

    public static Connection Connect() {
        conn = null;

        try {
            conn = DriverManager.getConnection(url);
            System.out.println("[SQLite]: Connecting to db..");
            if (conn != null) {
                System.out.println(" Success!");
            } else {
                System.out.println(" Error establising connection.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void CreateDatabase(String path, String name) {
        url = "jdbc:sqlite:"+ path + name;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("New database " + name + " created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void CreatePacketTable() {
        String packetDataTable = "CREATE TABLE IF NOT EXISTS packets (\n"
                + " packetID integer UNIQUE PRIMARY KEY,\n"
                + " label String NOT NULL,\n"
                + " SideOneByteOne String,\n"
                + " SideOneByteTwo String,\n"
                + " SideOneByteThree String,\n"
                + " CornerFourByteOne String,\n"
                + " CornerFourByteTwo String,\n"
                + " CornerFourByteThree String,\n"
                + " SideFourByteOne String,\n"
                + " SideFourByteTwo String,\n"
                + " SideFourByteThree String,\n"
                + " CornerThreeByteOne String,\n"
                + " CornerThreeByteTwo String,\n"
                + " CornerThreeByteThree String,\n"
                + " LogoByteOne String,\n"
                + " LogoByteTwo String,\n"
                + " LogoByteThree String,\n"
                + " SideThreeByteOne String,\n"
                + " SideThreeByteTwo String,\n"
                + " SideThreeByteThree String,\n"
                + " CornerTwoByteOne String,\n"
                + " CornerTwoByteTwo String,\n"
                + " CornerTwoByteThree String,\n"
                + " SideTwoByteOne String,\n"
                + " SideTwoByteTwo String,\n"
                + " SideTwoByteThree String,\n"
                + " CornerOneByteOne String,\n"
                + " CornerOneByteTwo String,\n"
                + " CornerOneByteThree String,\n"
                + " UNKNOWN String\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {
            boolean pdtRes = stmt.execute(packetDataTable);
            if (pdtRes) {
                System.out.println("[SQLite]: packet data table created successfully!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void CreateCustomSettingsTable() {
        String customFilter = "CREATE TABLE IF NOT EXISTS customSetting (\n"
                + " label String NOT NULL,\n"
                + " interval INTEGER\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            boolean cstRes = stmt.execute(customFilter);
            if (cstRes) {
                System.out.println("[SQLite]: Custom filter table created successfully!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void InsertPacketDataTable(String label, ArrayList<Byte> packetData) {
        this.InsertPacketData(label, packetData.get(0),  packetData.get(1), packetData.get(2),
                packetData.get(3), packetData.get(4),  packetData.get(5),
                packetData.get(6), packetData.get(7),  packetData.get(8),
                packetData.get(9), packetData.get(10),  packetData.get(11),
                packetData.get(12), packetData.get(13),  packetData.get(14),
                packetData.get(15),  packetData.get(16),  packetData.get(17),
                packetData.get(18),  packetData.get(19),  packetData.get(20),
                packetData.get(21),  packetData.get(22),  packetData.get(23),
                packetData.get(24),  packetData.get(25),  packetData.get(26), packetData.get(27));
    }

    public void InsertPacketData(String label, byte SideOneByteOne,  byte SideOneByteTwo, byte SideOneByteThree,
                                 byte CornerFourByteOne, byte CornerFourByteTwo, byte CornerFourByteThree,
                                 byte SideFourByteOne, byte SideFourByteTwo, byte SideFourByteThree,
                                 byte  CornerThreeByteOne, byte CornerThreeByteTwo, byte CornerThreeByteThree,
                                 byte LogoByteOne, byte LogoByteTwo, byte LogoByteThree,
                                 byte SideThreeByteOne, byte SideThreeByteTwo, byte SideThreeByteThree,
                                 byte CornerTwoByteOne, byte CornerTwoByteTwo, byte CornerTwoByteThree,
                                 byte SideTwoByteOne, byte SideTwoByteTwo, byte SideTwoByteThree,
                                 byte CornerOneByteOne, byte CornerOneByteTwo, byte CornerOneByteThree, byte UNKNOWN) {

        String sql = "INSERT INTO packets(packetID, label, SideOneByteOne,  SideOneByteTwo, SideOneByteThree,\n" +
                "CornerFourByteOne, CornerFourByteTwo, CornerFourByteThree,\n" +
                "SideFourByteOne, SideFourByteTwo, SideFourByteThree,\n" +
                "CornerThreeByteOne, CornerThreeByteTwo, CornerThreeByteThree,\n" +
                "LogoByteOne, LogoByteTwo, LogoByteThree,\n" +
                "SideThreeByteOne, SideThreeByteTwo, SideThreeByteThree,\n" +
                "CornerTwoByteOne, CornerTwoByteTwo, CornerTwoByteThree,\n" +
                "SideTwoByteOne, SideTwoByteTwo, SideTwoByteThree,\n" +
                "CornerOneByteOne, CornerOneByteTwo, CornerOneByteThree, UNKNOWN"
                + "VALUES(NULL, ?, ?, ?, ?," +
                "?, ?, ?," +
                "?, ?, ?," +
                "?, ?, ?," +
                "?, ?, ?," +
                "?, ?, ?," +
                "?, ?, ?," +
                "?, ?, ?," +
                "?, ?, ?, ?)";

        try (Connection conn = this.Connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, label);
                pstmt.setString(2, new String(new byte[]{ (byte)SideOneByteOne }, "US-ASCII"));
                pstmt.setString(3, new String(new byte[]{ (byte)SideOneByteTwo }, "US-ASCII"));
                pstmt.setString(4, new String(new byte[]{ (byte)SideOneByteThree }, "US-ASCII"));
                pstmt.setString(5, new String(new byte[]{ (byte)CornerFourByteOne }, "US-ASCII"));
                pstmt.setString(6, new String(new byte[]{ (byte)CornerFourByteTwo }, "US-ASCII"));
                pstmt.setString(7, new String(new byte[]{ (byte)CornerFourByteThree }, "US-ASCII"));
                pstmt.setString(8, new String(new byte[]{ (byte)SideFourByteOne }, "US-ASCII"));
                pstmt.setString(9, new String(new byte[]{ (byte)SideFourByteTwo }, "US-ASCII"));
                pstmt.setString(10, new String(new byte[]{ (byte)SideFourByteThree }, "US-ASCII"));
                pstmt.setString(11, new String(new byte[]{ (byte)CornerThreeByteOne }, "US-ASCII"));
                pstmt.setString(12, new String(new byte[]{ (byte)CornerThreeByteTwo }, "US-ASCII"));
                pstmt.setString(13, new String(new byte[]{ (byte)CornerThreeByteThree }, "US-ASCII"));
                pstmt.setString(14, new String(new byte[]{ (byte)LogoByteOne }, "US-ASCII"));
                pstmt.setString(15, new String(new byte[]{ (byte)LogoByteTwo }, "US-ASCII"));
                pstmt.setString(16, new String(new byte[]{ (byte)LogoByteThree }, "US-ASCII"));
                pstmt.setString(17, new String(new byte[]{ (byte)SideThreeByteOne }, "US-ASCII"));
                pstmt.setString(18, new String(new byte[]{ (byte)SideThreeByteTwo }, "US-ASCII"));
                pstmt.setString(19, new String(new byte[]{ (byte)SideThreeByteThree }, "US-ASCII"));
                pstmt.setString(20, new String(new byte[]{ (byte)CornerTwoByteOne }, "US-ASCII"));
                pstmt.setString(21, new String(new byte[]{ (byte)CornerTwoByteTwo }, "US-ASCII"));
                pstmt.setString(22, new String(new byte[]{ (byte)CornerTwoByteThree }, "US-ASCII"));
                pstmt.setString(23, new String(new byte[]{ (byte)SideTwoByteOne }, "US-ASCII"));
                pstmt.setString(24, new String(new byte[]{ (byte)SideTwoByteTwo }, "US-ASCII"));
                pstmt.setString(25, new String(new byte[]{ (byte)SideTwoByteThree }, "US-ASCII"));
                pstmt.setString(26, new String(new byte[]{ (byte)CornerOneByteOne }, "US-ASCII"));
                pstmt.setString(27, new String(new byte[]{ (byte)CornerOneByteTwo }, "US-ASCII"));
                pstmt.setString(28, new String(new byte[]{ (byte)CornerOneByteThree }, "US-ASCII"));
                pstmt.setString(29, new String(new byte[]{ (byte)UNKNOWN }, "US-ASCII"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        }
    }

    public void InsertCustomSetting(String label, int interval) {
        String sql = "INSERT INTO customSetting(label, interval) VALUES(?,?)";

        try (Connection conn = this.Connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList SelectAllLabel(String label) {
        ArrayList resultList = new ArrayList();
        String sql = "SELECT * from packets WHERE label=?";

        try (Connection conn = this.Connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, label);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Packet nPacket = new Packet(rs.getInt("packetID"));
                nPacket.setSIDE_ONE_POS_RED_VALUE((byte)(int)Long.parseLong(rs.getString("SideOneByteOne")));
                nPacket.setSIDE_ONE_POS_GREEN_VALUE((byte)(int)Long.parseLong(rs.getString("SideOneByteTwo")));
                nPacket.setSIDE_ONE_POS_BLUE_VALUE((byte)(int)Long.parseLong(rs.getString("SideOneByteThree")));
                nPacket.setCORNER_FOUR_POS_RED_VALUE((byte)(int)Long.parseLong(rs.getString("CornerFourByteOne")));
                nPacket.setCORNER_FOUR_POS_GREEN_VALUE((byte)(int)Long.parseLong(rs.getString("CornerFourByteTwo")));
                nPacket.setCORNER_FOUR_POS_BLUE_VALUE((byte)(int)Long.parseLong(rs.getString("CornerFourByteThree")));
                nPacket.setSIDE_FOUR_POS_RED_VALUE((byte)(int)Long.parseLong(rs.getString("SideFourByteOne")));
                nPacket.setSIDE_FOUR_POS_GREEN_VALUE((byte)(int)Long.parseLong(rs.getString("SideFourByteTwo")));
                nPacket.setSIDE_FOUR_POS_BLUE_VALUE((byte)(int)Long.parseLong(rs.getString("SideFourByteThree")));
                nPacket.setCORNER_THREE_POS_RED_VALUE((byte)(int)Long.parseLong(rs.getString("CornerThreeByteOne")));
                nPacket.setCORNER_THREE_POS_GREEN_VALUE((byte)(int)Long.parseLong(rs.getString("CornerThreeByteTwo")));
                nPacket.setCORNER_THREE_POS_BLUE_VALUE((byte)(int)Long.parseLong(rs.getString("CornerThreeByteThree")));
                nPacket.setLOGO_POS_RED_VALUE((byte)((int)Long.parseLong(rs.getString("LogoByteOne"))));
                nPacket.setLOGO_POS_GREEN_VALUE((byte)(int)Long.parseLong(rs.getString("LogoByteTwo")));
                nPacket.setLOGO_POS_BLUE_VALUE((byte)(int)Long.parseLong(rs.getString("LogoByteThree")));
                nPacket.setSIDE_THREE_POS_RED_VALUE((byte)(int)Long.parseLong(rs.getString("SideThreeByteOne")));
                nPacket.setSIDE_THREE_POS_GREEN_VALUE((byte)(int)Long.parseLong(rs.getString("SideThreeByteTwo")));
                nPacket.setSIDE_THREE_POS_BLUE_VALUE((byte)(int)Long.parseLong(rs.getString("SideThreeByteThree")));
                nPacket.setCORNER_TWO_POS_RED_VALUE((byte)(int)Long.parseLong(rs.getString("CornerTwoByteOne")));
                nPacket.setCORNER_TWO_POS_GREEN_VALUE((byte)(int)Long.parseLong(rs.getString("CornerTwoByteTwo")));
                nPacket.setCORNER_TWO_POS_BLUE_VALUE((byte)(int)Long.parseLong(rs.getString("CornerTwoByteThree")));
                nPacket.setSIDE_TWO_POS_RED_VALUE((byte)(int)Long.parseLong(rs.getString("SideTwoByteOne")));
                nPacket.setSIDE_TWO_POS_GREEN_VALUE((byte)(int)Long.parseLong(rs.getString("SideTwoByteTwo")));
                nPacket.setSIDE_TWO_POS_BLUE_VALUE((byte)(int)Long.parseLong(rs.getString("SideTwoByteThree")));
                nPacket.setCORNER_ONE_POS_RED_VALUE((byte)(int)Long.parseLong(rs.getString("CornerOneByteOne")));
                nPacket.setCORNER_ONE_POS_GREEN_VALUE((byte)(int)Long.parseLong(rs.getString("CornerOneByteTwo")));
                nPacket.setCORNER_ONE_POS_BLUE_VALUE((byte)(int)Long.parseLong(rs.getString("CornerOneByteThree")));
                nPacket.setUNKNOWN_VALUE((byte)(int)Long.parseLong(rs.getString("UNKNOWN")));
                nPacket.setBody();
                resultList.add(nPacket);
            }
            return resultList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
