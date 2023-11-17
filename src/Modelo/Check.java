package Modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;

public interface Check {
     Calendar fecha = new GregorianCalendar();
     //Obtenemos el valor del año, mes, día, hora, minuto y segundo del sistema.
     //Usando el método get y el parámetro correspondiente.
     int anio = fecha.get(Calendar.YEAR);
     int mes = fecha.get(Calendar.MONTH);
     int dia = fecha.get(Calendar.DAY_OF_MONTH);
     public boolean cumpleMes();
     public boolean cumpleAnio();
}
