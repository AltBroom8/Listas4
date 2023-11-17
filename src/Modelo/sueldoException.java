package Modelo;

import javax.swing.*;

public class sueldoException extends Exception {
    public sueldoException() {
        JOptionPane.showMessageDialog(null, "El salario no puede ser mayor al salario maximo", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
