package Model;

public class gsModel {

    private int id;
    private String fecha;
    private String tipo_operacion;
    private double monto;
    private double saldo_anterior;
    private double saldo_actual;

    public double getSaldo_actual() {
        return saldo_actual;
    }

    public void setSaldo_actual(double saldo_actual) {
        this.saldo_actual = saldo_actual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo_operacion() {
        return tipo_operacion;
    }

    public void setTipo_operacion(String tipo_operacion) {
        this.tipo_operacion = tipo_operacion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getSaldo_anterior() {
        return saldo_anterior;
    }

    public void setSaldo_anterior(double saldo_anterior) {
        this.saldo_anterior = saldo_anterior;
    }

}
