package Modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;


public class Empleado implements Check, Serializable {
    private static final long serialVersionUID = 1L;
    private int numero;
    private transient String nombre;
    private double sueldo;
    private double sueldoMax;
    private Date fAlta;
    private String copiaNombre;
    boolean calcula;
    public Empleado(int numero, String nombre, double sueldo, double sueldoMax, Date fAlta) {
        try {
            if (sueldo > sueldoMax) {
                throw new sueldoException();
            }
            this.numero = numero;
            this.nombre = nombre;
            this.sueldo = sueldo;
            this.sueldoMax = sueldoMax;
            this.fAlta = new Date();
            this.calcula = false;
        } catch (sueldoException e) {
            System.out.println("Excepción: " + e.getMessage());
        }
    }

    @Override
    public boolean cumpleMes() {
        if (this.fAlta.getDate() == dia && (this.fAlta.getMonth() + 1) != mes+1) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean cumpleAnio() {
        if(this.fAlta.getDate() == dia && this.fAlta.getMonth()+1 == mes+1 && this.fAlta.getYear()!= anio){
            return true;
        }
        return false;
    }


    public Date getfAlta() {
        return fAlta;
    }

    public int getNumero() {
        return numero;
    }

    public String getCopiaNombre() {
        return copiaNombre;
    }

    public void setCopiaNombre(String copiaNombre) {
        this.copiaNombre = copiaNombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public double getSueldoMax() {
        return sueldoMax;
    }

    public void setSueldoMax(double sueldoMax) {
        this.sueldoMax = sueldoMax;
    }

    public void setfAlta(Date fAlta) {
        this.fAlta = fAlta;
    }
    public void setCalcula(boolean calcula) {
        this.calcula = calcula;
    }
    public boolean getCalcula(){
        return this.calcula;
    }

}

