package View;

import Controller.ControllerData;
import Model.gsModel;
import Model.queryModel;

public class Main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //Instancia
                Dashboard form = new Dashboard();
                retiro ret = new retiro();
                deposito dep = new deposito();
                queryModel qm = new queryModel();
                gsModel gs = new gsModel();
                Controller.ControllerData ctrl = new ControllerData(form, dep, ret, qm, gs);
                ctrl.iniciar();//LLama a los procesos que se requieren para correr la aplicacion
                form.setVisible(true);//Hace visible la vista
            }
        });
    }
}
