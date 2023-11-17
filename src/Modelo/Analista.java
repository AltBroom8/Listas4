package Modelo;

import java.util.Date;

public class Analista extends Empleado {
    private double plus;
    private boolean jefe;

    public double getPlus() {
        return plus;
    }

    public void setPlus(double plus) {
        this.plus = plus;
    }

    public boolean isJefe() {
        return jefe;
    }

    public void setJefe(boolean jefe) {
        this.jefe = jefe;
    }

    public Analista(int numero, String nombre, double sueldo, double sueldoMax, Date fAlta, double plus, boolean jefe) {
        super(numero, nombre, sueldo, sueldoMax, fAlta);
        try {
            if (sueldo > sueldoMax) {
                throw new sueldoException();
            }
            this.plus = plus;
            this.jefe = jefe;
        } catch (sueldoException e) {
            System.out.println("Excepción en el constructor de Analista: " + e.getMessage());
        }finally{
            this.plus = plus;
            this.jefe = jefe;
        }
    }
}
