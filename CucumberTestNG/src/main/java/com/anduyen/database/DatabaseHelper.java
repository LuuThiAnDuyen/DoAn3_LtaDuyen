package com.anduyen.database;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class DatabaseHelper {
//    public static void main(String [] args){
//        SQLServerDataSource ds = new SQLServerDataSource();
//
//        ds.setUser("sa");
//        ds.setPassword("247204");
//        ds.setServerName("LTADUYEN");
//        ds.setPortNumber(1433);
//        ds.setDatabaseName("DOAN3");
//        ds.setEncrypt(false);
//        ds.setTrustServerCertificate(true);
//
//
//        try(Connection conn = ds.getConnection()){
//            System.out.println("Connection success!");
//            System.out.println(conn.getMetaData());
//        } catch (SQLServerException e) {
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
    private static final String URL = "jdbc:sqlserver://LTADUYEN:1433;databaseName=DOAN3;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "sa";
    private static final String PASSWORD = "247204";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public static int getSearchResultCount(String searchTerm) {
        int count = 0;
        String query = "SELECT COUNT(*) FROM SanPhams WHERE TenSanPham LIKE N'%" + searchTerm + "%'";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
