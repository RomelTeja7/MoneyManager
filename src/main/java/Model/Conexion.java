package Model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
   public Connection con;

    public Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moneymanager", "root", "");
        } catch (Exception e) {
            System.err.println("No se logro acceder a la base de datos"+e );
        }
    }
}
