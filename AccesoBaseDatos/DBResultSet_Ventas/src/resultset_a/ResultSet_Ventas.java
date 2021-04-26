/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resultset_a;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author juan
 */
public class ResultSet_Ventas {

    // DDBC driver name and database URL
    static final String DDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/DEPEMP";
// Database credentials
    static final String USER = "pepe";
    static final String PASS = "pepa";
    static private ResultSetMetaData meta;
    static private ResultSet rs;

    public static void main(String[] args) {
        Connection conn = null;
        try {
//STEP 2: Register DDBC driver
            Class.forName("com.mysql.jdbc.Driver");
//STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//STEP 4: Execute a query to create statment with

            // required arguments for RS example.
            System.out.println("Creating statement...");
//            Statement stmt = conn.createStatement();
            Statement stmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//STEP 5: Execute a query
            String sql = "SELECT idEmp,v.idArt,nombre, ppu, unidades, unidades*ppu importe , fecha "
                    + "from ventas v inner join articulos a on a.idArt= v.idArt"
                    + " order by v.idart ";
            //ResultSet rs = stmt.executeQuery(sql);
            rs = stmt.executeQuery(sql);
            meta = rs.getMetaData();
            System.out.println("List result set for reference....");
            printRs(rs);
            totalizaIdArt();
            sql = "SELECT idEmp,v.idArt,nombre, ppu, unidades, unidades*ppu importe , fecha "
                    + "from ventas v inner join articulos a on a.idArt= v.idArt"
                    + " order by idemp ";
            //ResultSet rs = stmt.executeQuery(sql);
            rs = stmt.executeQuery(sql);
            meta = rs.getMetaData();
            printRs(rs);
            totalizaIdEmp();
            sql = "SELECT idEmp,v.idArt,nombre, ppu, unidades, unidades*ppu importe , fecha "
                    + "from ventas v inner join articulos a on a.idArt= v.idArt"
                    + " order by idemp, v.idArt";
            //ResultSet rs = stmt.executeQuery(sql);
            rs = stmt.executeQuery(sql);
            meta = rs.getMetaData();
            printRs(rs);
            totalizaIdEmpIdArt();

            rs.beforeFirst();
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
//Handle errors for DDBC 
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
//finally block used to close resources 
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try }//end try

            System.out.println("Goodbye!");
        }//end main
    }

    public static void printRs(ResultSet rs) throws SQLException {
//Ensure we start with first row 
        int nCol = meta.getColumnCount();
        String[] nombreColumnas = new String[nCol];
        String[] tipoColumnas = new String[nCol];
        Object[] valColumnas = new Object[nCol];
        for (int i = 0; i < nCol; i++) {
            nombreColumnas[i] = meta.getColumnName(i + 1);
            tipoColumnas[i] = meta.getColumnTypeName(i + 1);
        }
        for (int i = 0; i < nCol; i++) {
            System.out.print(nombreColumnas[i] + "[" + tipoColumnas[i] + "]\t");

        }
        System.out.println("");
        rs.beforeFirst();
        
        while (rs.next()) {
            for (int i = 0; i < nCol; i++) {
                valColumnas[i] = rs.getObject(i + 1);
            }
            for (int i = 0; i < nCol; i++) {
                System.out.print(valColumnas[i].toString());
                System.out.print("\t\t");
            }
            System.out.println("");
        }
        System.out.println();
    }//end printRs()

    public static void totalizaIdArt() throws SQLException {
        double total = 0;
        int idArtAux = 0;
        rs.beforeFirst();
        boolean bueno = rs.next();
        while (bueno) {
            total = 0;
            idArtAux = rs.getInt("idArt");
            while (bueno && idArtAux == rs.getInt("idArt")) {
                total += rs.getDouble("importe");
                bueno = rs.next();
            }
            System.out.println("idArt: " + idArtAux + " Total: " + total);
        }
        System.out.println("");
    }

    public static void totalizaIdEmp() throws SQLException {
        double total = 0;
        int idEmpAux = 0;
        rs.beforeFirst();
        boolean bueno = rs.next();
        while (bueno) {
            total = 0;
            idEmpAux = rs.getInt("idEmp");
            while (bueno && idEmpAux == rs.getInt("idEmp")) {
                total += rs.getDouble("importe");
                bueno = rs.next();
            }
            System.out.println("idEmp: " + idEmpAux + " Total: " + total);
        }
        System.out.println("");
    }

    public static void totalizaIdEmpIdArt() throws SQLException {
        double totalEmp;
        double totalArt;
        int idEmpAux = 0;
        int idArtAux = 0;
        rs.beforeFirst();
        boolean bueno = rs.next();
        while (bueno) {
            totalEmp = 0;
            idEmpAux = rs.getInt("idEmp");
            while (bueno && idEmpAux == rs.getInt("idEmp")) {        
                totalEmp += rs.getDouble("importe");
                totalArt = 0;
                idArtAux = rs.getInt("idart");
                while (bueno && idEmpAux == rs.getInt("idEmp")&& idArtAux == rs.getInt("idArt")) {
                    totalEmp += rs.getDouble("importe");
                    totalArt += rs.getDouble("importe"); 
                    bueno = rs.next();
                }
                System.out.println("idEmp: " + idEmpAux + "   idArt: " + idArtAux +" Total: " + totalArt);
            }
            System.out.println("idEmp: " + idEmpAux + " Total: " + totalEmp);
            System.out.println("");
        }
        System.out.println("");
    }
}
