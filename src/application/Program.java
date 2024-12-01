package application;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

import java.sql.*;


public class Program {
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;

        try{
            conn = DB.getConnection();

            conn.setAutoCommit(false);

            st = conn.createStatement();

            int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");

            //int x = 1;
            //if (x != 2){
            //    throw new SQLException("Erro falso");
            //}
            int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

            conn.commit();

            System.out.println("Linha 1: " + rows1);
            System.out.println("Linha 2: " + rows2);
        }
        catch (SQLException e){
            try {
                conn.rollback();
                throw new DbException("Transação rolled back. Causado por " + e.getMessage());
            } catch (SQLException e1) {
                throw new DbException("Erro tentando dar rollback. Causado por " + e1.getMessage());
            }
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
