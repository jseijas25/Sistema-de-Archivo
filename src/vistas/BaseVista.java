package vistas;

import javax.swing.*;

public abstract class BaseVista extends JFrame {

    private String error;
    private String message;

    public BaseVista() {
        this.error = "";
        this.message = "";
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void showError(){
        if(!error.isEmpty()){
            JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
        }
        error = "";
    }

    public void showMessage(){
        if(!message.isEmpty()){
            JOptionPane.showMessageDialog(this, message, "", JOptionPane.INFORMATION_MESSAGE);
        }
        message = "";
    }
}
