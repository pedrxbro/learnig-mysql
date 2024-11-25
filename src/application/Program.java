package application;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

import java.sql.*;


public class Program {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;

        try{
            conn = DB.getConnection();
            st = conn.prepareStatement(
                    "DELETE FROM department " +
                        "WHERE " +
                        "Id = ?");
            st.setInt(1,2);

            int rowsAffected = st.executeUpdate();

            System.out.println("Feito! Linhas alteradas: " + rowsAffected);
        }
        catch (SQLException e){
            throw new DbIntegrityException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
