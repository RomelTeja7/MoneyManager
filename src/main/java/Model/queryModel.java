package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class queryModel extends Conexion {

    public boolean Depositar(gsModel data) {
        PreparedStatement ps = null;
        Connection con = this.con;
        String sql = "INSERT INTO cuenta (fecha, tipo_operacion, monto, saldo_anterior, saldo_actual) VALUES (?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, data.getFecha());
            ps.setString(2, data.getTipo_operacion());
            ps.setDouble(3, data.getMonto());
            ps.setDouble(4, data.getSaldo_anterior());
            ps.setDouble(5, data.getSaldo_actual());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean Retirar(gsModel data) {
        PreparedStatement ps = null;
        Connection con = this.con;
        String sql = "INSERT INTO cuenta (fecha, tipo_operacion, monto, saldo_anterior, saldo_actual) VALUES (?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, data.getFecha());
            ps.setString(2, data.getTipo_operacion());
            ps.setDouble(3, data.getMonto());
            ps.setDouble(4, data.getSaldo_anterior());
            ps.setDouble(5, data.getSaldo_actual());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }


}
