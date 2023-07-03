package Controller;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import Model.Conexion;
import Model.gsModel;
import Model.queryModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JOptionPane;

public class ControllerData implements ActionListener {

    private View.Dashboard form;
    private View.deposito dep;
    private View.retiro ret;
    private Model.queryModel queryModel;
    private Model.gsModel data;

    //Contructor
    public ControllerData(View.Dashboard form, View.deposito dep, View.retiro ret, Model.queryModel queryModel, Model.gsModel data) {
        this.queryModel = queryModel;
        this.data = data;
        this.form = form;
        this.dep = dep;
        this.ret = ret;
        this.dep.jButton4.addActionListener(this);
        this.ret.jButton4.addActionListener(this);
    }

    //Proceso con atributos para iniciar la App
    public void iniciar() {
        ImageIcon img = new ImageIcon("D:\\Documents\\NetBeans\\MoneyManager\\src\\main\\java\\View\\mm.png");
        form.setIconImage(img.getImage());
        form.setTitle("MoneyManager");
        form.setVisible(false);
        dep.setVisible(false);
        ret.setVisible(false);
        ViewTable();
        Total();
        DepositoMensual();
        RetiroMensual();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dep.jButton4) {
            Model.gsModel data = new gsModel();  //Instancia get and set
            Model.queryModel queryModel = new queryModel(); //Instancia transferencia de datos
            LocalDate fechaActual = LocalDate.now();

            data.setFecha(String.valueOf(fechaActual));
            data.setTipo_operacion("DEPOSITO");
            data.setMonto(Double.parseDouble(dep.jTextField1.getText()));
            data.setSaldo_anterior(Double.parseDouble(dep.jLabel4.getText()));
            data.setSaldo_actual(Double.parseDouble(dep.jLabel4.getText()) + Double.parseDouble(dep.jTextField1.getText()));

            if (queryModel.Depositar(data)) {
                JOptionPane.showMessageDialog(null, "Deposito exitoso");
                ViewTable();
                Total();
                DepositoMensual();
                RetiroMensual();
                form.setVisible(true);//Reinicia las vistas
                dep.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo depositar");
            }

        } else if (e.getSource() == ret.jButton4) {
            Model.gsModel data = new gsModel();
            Model.queryModel queryModel = new queryModel();
            LocalDate fechaActual = LocalDate.now();

            data.setFecha(String.valueOf(fechaActual));
            data.setTipo_operacion("RETIRO");
            data.setMonto(Double.parseDouble(ret.jTextField1.getText()));
            data.setSaldo_anterior(Double.parseDouble(ret.jLabel4.getText()));
            data.setSaldo_actual(Double.parseDouble(ret.jLabel4.getText()) - Double.parseDouble(ret.jTextField1.getText()));
            if (queryModel.Retirar(data)) {
                JOptionPane.showMessageDialog(null, "Retiro exitoso");
                ViewTable();
                Total();
                DepositoMensual();
                RetiroMensual();
                form.setVisible(true);//Reinicia las vistas
                ret.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo retirar");
            }
        }
    }

    //Muestra los datos en la tabla
    public void ViewTable() {
        String[] dato = new String[5];
        Statement st;
        ResultSet rs;
        DefaultTableModel tab = (DefaultTableModel) form.jTable1.getModel();
        Conexion con = new Conexion();
        cleanRow();
        try {
            st = con.con.createStatement();
            rs = st.executeQuery("SELECT * FROM cuenta ORDER BY id DESC;");
            while (rs.next()) {
                dato[0] = rs.getString(2);
                dato[1] = rs.getString(3);
                dato[2] = String.valueOf("$" + rs.getBigDecimal(4));
                dato[3] = String.valueOf("$" + rs.getBigDecimal(5));
                dato[4] = String.valueOf("$" + rs.getBigDecimal(6));
                tab.addRow(dato);
            }
        } catch (Exception ex) {
            System.out.println("No se pudo establecer la conexicon con la tabla " + ex);
        }
    }

    //Muestra el total de la cuenta
    public void Total() {
        Conexion cone = new Conexion();
        ResultSet rs;
        Statement st;
        try {
            st = cone.con.createStatement();
            rs = st.executeQuery("SELECT saldo_actual FROM Cuenta ORDER BY id DESC LIMIT 1;");
            while (rs.next()) {
                Double total = rs.getDouble("saldo_actual");
                form.jLabel6.setText("$" + Double.toString(total));
                dep.jLabel4.setText("Total Actual $" + Double.toString(total));
                ret.jLabel4.setText("Total Actual $" + Double.toString(total));
            }
        } catch (Exception e) {
            System.err.println("No se pudo acceder a la tabla" + e);
        }
    }

    //Muestra el deposito total filtrado por mes actual
    public void DepositoMensual() {
        Conexion cone = new Conexion();
        ResultSet rs;
        Statement st;
        LocalDate fechaActual = LocalDate.now();
        int mesActual = fechaActual.getMonthValue();
        int añoActual = fechaActual.getYear();
        try {
            st = cone.con.createStatement();
            rs = st.executeQuery("SELECT SUM(monto) AS totalIngresos FROM cuenta WHERE MONTH(fecha) =" + mesActual + " AND YEAR(fecha) = " + añoActual + " And tipo_operacion = 'DEPOSITO'");
            while (rs.next()) {
                Double total = rs.getDouble("totalIngresos");
                form.jLabel8.setText("$" + Double.toString(total));
            }
        } catch (Exception e) {
            System.err.println("No se pudo acceder a la tabla" + e);
        }
    }

    //Muestra el retiro total filtrado por mes actual
    public void RetiroMensual() {
        Conexion cone = new Conexion();
        ResultSet rs;
        Statement st;
        LocalDate fechaActual = LocalDate.now();
        int mesActual = fechaActual.getMonthValue();
        int añoActual = fechaActual.getYear();
        try {
            st = cone.con.createStatement();
            rs = st.executeQuery("SELECT SUM(monto) AS totalGasto FROM cuenta WHERE MONTH(fecha) =" + mesActual + " AND YEAR(fecha) = " + añoActual + " And tipo_operacion = 'RETIRO'");
            while (rs.next()) {
                Double total = rs.getDouble("totalGasto");
                form.jLabel7.setText("$" + Double.toString(total));
            }
        } catch (Exception e) {
            System.err.println("No se pudo acceder a la tabla" + e);
        }
    }

    public void cleanRow() {
        DefaultTableModel tab = (DefaultTableModel) form.jTable1.getModel();
        tab.getDataVector().clear();
    }
}
