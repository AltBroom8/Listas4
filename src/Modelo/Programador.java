package Modelo;

import java.util.Date;

public class Programador extends Empleado  {
    private double extra;
    private boolean coche;

    public boolean getCoche() {
        return coche;
    }

    public double getExtra() {
        return extra;
    }

    public void setExtra(double extra) {
        this.extra = extra;
    }

    public void setCoche(boolean coche) {
        this.coche = coche;
    }

    public Programador(int numero, String nombre, double sueldo, double sueldoMax, Date fAlta, double extra, boolean coche) {
        super(numero, nombre, sueldo, sueldoMax, fAlta);
        this.extra = extra;
        this.coche = coche;
    }

}
