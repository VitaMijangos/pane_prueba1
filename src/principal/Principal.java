package principal;

import controlador.LoginController;
import vista.LoginView;

public class Principal {

    public static void main(String[] args) {
        LoginView vistaLogin = new LoginView();
        new LoginController(vistaLogin);
        vistaLogin.setVisible(true);
    }
}