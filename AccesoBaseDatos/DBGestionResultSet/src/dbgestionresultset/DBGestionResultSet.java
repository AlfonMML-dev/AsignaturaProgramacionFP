/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbgestionresultset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author juan
 */
public class DBGestionResultSet {

    // DDBC driver name and database URL
    static final String DDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/pruebauno";
// Database credentials
    static final String USER = "pepe";
    static final String PASS = "pepa";
    static private ResultSetMetaData meta;
    static private ResultSet rs;
    static Scanner in;

    public static void main(String[] args) {
        in = new Scanner(System.in);
        Connection conn = null;
        try {
//STEP 2: Register DDBC driver
            Class.forName("com.mysql.jdbc.Driver");
//STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
//            Statement stmt = conn.createStatement();
            Statement stmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//
            String sql = "select * from empleado;";

            rs = stmt.executeQuery(sql);
            meta = rs.getMetaData();
            System.out.println("Listado...");
            printRs(rs);
//            System.out.println("teclea");
//            in.nextLine();



//            Para modificar
//            para modificar la primera fila .first() que devuelve true si la fila existe
            if (rs.first()) {
                rs.updateDouble(3, rs.getDouble(3)+10); //modificamos el valor de la 3ª columna
                rs.updateRow();
            }
            System.out.println("Modificar. Teclea intro.");
            in.nextLine();
            printRs(rs);
            // para borrar la última fila
            if (rs.last()) {
                rs.deleteRow();

            }
            System.out.println("Borrar. Teclea intro.");
            in.nextLine();
            printRs(rs);
      

            // para insertar una fila la última fila
            rs.moveToInsertRow();
            rs.updateInt(1, 50);
            rs.updateString(2, "javi");
            rs.updateDouble(3, 555);

            rs.insertRow();

            System.out.println("Insertar. Teclea intro.");
            in.nextLine();
            printRs(rs);

           

           

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
        ResultSetMetaData meta;
        meta = rs.getMetaData();
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
}
