package Controlador;

import Modelo.Empleado;

import javax.swing.*;
import java.io.*;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Lista <T extends Empleado> implements Serializable  {
    private static final long serialVersionUID = 1L;
    private int contador; // Contador para rastrear el número de nodos en la lista
    private Nodo<T> inicial; // Nodo inicial de la lista
    private Nodo<T> ultimo; // Nodo final de la lista
    private Nodo<T> actual; // Nodo actual de la lista

    // Constructor de la clase Lista
    public Lista() {
        contador = 0;
        inicial = null;
        ultimo = null;
        actual = null;
    }
    public void insertar(T e) {
        contador++;
        Nodo<T> nuevo = new Nodo<T>(e,contador-1);
        if (inicial == null && ultimo == null) {
            inicial = nuevo;
            ultimo = nuevo;
            inicial.setIndice(0);
            nuevo.sig = null;
            nuevo.ant = null;
            this.setActual(inicial);
        } else if (ultimo == inicial) {
            ultimo = nuevo;
            ultimo.setIndice(inicial.getIndice()+1);
            inicial.sig = ultimo;
            inicial.ant = null;
            ultimo.ant = inicial;
            ultimo.sig = null;
        } else {
            Nodo<T> aux = inicial;
            while (aux.sig != null) {
                aux = aux.sig;
            }
            aux.sig = nuevo;
            nuevo.ant = aux;
            nuevo.sig = null;
            ultimo = nuevo;
            nuevo.setIndice(nuevo.getAnt().getIndice()+1);
        }
    }
    // Método para verificar si la lista está vacía//
    public boolean esVacia() {
        return inicial == null;
    }
    // Método para modificar un objeto en la lista
    public boolean modifica(T b) {
        boolean exito = false;
        if(esVacia()){
            return false;
        }
        actual.info = b;
        exito = true;
        return exito;
    }
    public boolean elimina() {
        boolean exito = false;
        if (actual != null && actual.tieneSig()) {
            Nodo sig = actual.getSig();
            actual.setSig(null);

            if (actual.getAnt() != null) {
                Nodo anterior = actual.getAnt();
                anterior.setSig(sig);
            } else {
                // Si no hay nodo anterior, significa que actual era el primer nodo
                inicial = sig;
            }

            sig.setAnt(actual.getAnt());
            actual = sig;
            contador--;
            System.out.println("PRIMERO");
            return true;
        } else if (actual.tieneAnt()) {
            actual.getAnt().setSig(actual.getSig());
            actual=actual.getAnt();
            contador--;
            System.out.println("SEGUNDO");
            return true;
        }else if(actual!=null){
            actual = null;
            inicial = null;
            ultimo = null;
            contador--;
            System.out.println("TERCERO");
            return true;
        }else{
            System.out.println("CUARTO");
            return false;
        }
    }
    public boolean numRepe(int num) {
        Nodo<T> aux = this.inicial;
        while (aux != null) {
            Empleado e1 = (Empleado) aux.info;
            if (e1.getNumero() == num) {
                return true;
            }
            aux = aux.getSig();
        }
        return false;
    }
    public boolean existe (int numEmp){
        Nodo<T> aux = inicial;
        while (aux!= null) {
            Empleado e1 = (Empleado) aux.info;
            if (e1.getNumero() == numEmp) {
                return true;
            }
            aux = aux.getSig();
        }
        return false;
    }
    //METODO SORT
    public void imprimeLista(){
        for (Nodo<T> i = inicial; i != null; i = i.getSig()) {
            Empleado temp = (Empleado) i.getInfo();
            System.out.println("Codigo "+i.getIndice()+":"+temp.getNumero()+" "+temp.getNombre());
        }
    }
    public void sort(int inicio,int fin){
        System.out.println("inicio es "+inicio);
        System.out.println("fin es "+fin);
        try {
            Nodo nodoInicio = this.getInicial();
            while (nodoInicio != null && nodoInicio.getIndice() != inicio) {
                nodoInicio = nodoInicio.getSig();
            }
            Nodo nodoFin = this.getInicial();
            while (nodoFin != null && nodoFin.getIndice() != fin) {
                nodoFin = nodoFin.getSig();
            }


            if (inicio == fin) {
                return;
            } else if (inicio == (fin - 1)) {
                T primero = (T) nodoInicio.getInfo();
                T ultimo = (T) nodoFin.getInfo();
                System.out.println("PRIMERO ES " + primero.getNumero() + " Y ULTIMO ES " + ultimo.getNumero());
                if (primero.getNumero() > ultimo.getNumero()) {
                    T temp = (T) nodoInicio.getInfo();
                    nodoInicio.setInfo(nodoFin.getInfo());
                    nodoFin.setInfo(temp);
                }

                return;
            }
            int j = inicio;
            int i = inicio;
            int pivot = fin;
            Nodo nodoj = nodoInicio;
            Nodo nodoi = nodoj;

            Nodo nodoPivot = nodoFin;
            boolean avanzai = false;
            while (j < pivot) {
                T eJ = (T) nodoj.getInfo();
                T ePivot = (T) nodoPivot.getInfo();
                T eI = eJ;
                if (eJ.getNumero() < ePivot.getNumero()) {
                    if (avanzai == true) {
                        i++;
                        nodoi = nodoi.getSig();

                    }
                    T temp = (T) nodoi.getInfo();
                    nodoi.setInfo(nodoj.getInfo());
                    nodoj.setInfo(temp);
                    avanzai = true;
                }
                j++;
                nodoj = nodoj.getSig();
                if (j == pivot) {
                    if (avanzai == true) {
                        i = i + 1;
                        nodoi = nodoi.getSig();
                    }
                    T temp = (T) nodoPivot.getInfo();
                    nodoPivot.setInfo(nodoi.getInfo());
                    nodoi.setInfo(temp);

                    pivot = i;
                    break;
                }
            }
            if (pivot - 1 >= inicio && pivot - 1 <= fin) {
                sort(inicio, (pivot - 1));
            }
            if (pivot + 1 >= inicio && pivot + 1 <= fin) {
                sort((pivot + 1), fin);
            }
        }catch (Exception e){

            e.printStackTrace();

        }
    }

    public boolean leeArchivo(File archivo){
        if (archivo.exists()) {
            this.vaciar();
            // Realiza alguna operación si el archivo existe
            System.out.println("El archivo existe en la ruta: " + archivo.getAbsolutePath());
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                Object objeto = ois.readObject();
                if (objeto instanceof Empleado) {
                    Empleado empleado = (Empleado) objeto;
                    String asterisco = "";
                    for (int i = 1; i <=empleado.getCopiaNombre().length() ; i++){
                        asterisco += "*";
                    }
                    empleado.setNombre(asterisco);
                    insertar((T)empleado);

                } else if (objeto instanceof Lista) {
                    System.out.println("Es una lista");
                    Lista lista = (Lista) objeto;
                    Nodo<T> nuevo = lista.inicial;
                    while (nuevo!= null) {
                        T e = (T) nuevo.info;
                        String asterisco = "";
                        for (int i = 1; i <=e.getCopiaNombre().length() ; i++){
                            asterisco += "*";
                        }
                        e.setNombre(asterisco);
                        if(!existe(e.getNumero())){
                            insertar((T)e);
                        }
                        nuevo = nuevo.getSig();
                    }
                    this.imprimeLista();
                }else{
                    System.out.println("No es lista ni empleado");
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return true;
        } else {
            // Realiza alguna operación si el archivo no existe
            System.out.println("El archivo no existe en la ruta especificada.");
            return false;
        }

    }
    public static String convierteFecha(Date calendar){
        SimpleDateFormat formato = new SimpleDateFormat("d/M/yyyy");
        return formato.format(calendar);
    }
    public void guardarLista(File archivo){
        Nodo <T> aux = inicial;
        while(aux!=null) {
            T e1 = (T) aux.info;
            e1.setCopiaNombre(e1.getNombre());
            aux = aux.getSig();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean importar(){
        File archivo = new File("lista.bin");
        if(leeArchivo(archivo)){
            System.out.println("if importar "+this.getInicial());
            return true;
        }else{
            System.out.println("else importar " +this.getInicial());
            return false;
        }

    }
    public void exportar(){
        File archivo = new File("lista.bin");
        guardarLista(archivo);
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public Nodo<T> getInicial() {
        return inicial;
    }

    public void setInicial(Nodo<T> inicial) {
        this.inicial = inicial;
    }

    public Nodo<T> getUltimo() {
        return ultimo;
    }

    public void setUltimo(Nodo<T> ultimo) {
        this.ultimo = ultimo;
    }
    public void vaciar(){
        actual = null;
        inicial = null;
        ultimo = null;
    }

    public Nodo<T> getActual() {
        return actual;
    }

    public void setActual(Nodo<T> actual) {
        this.actual = actual;
    }
    public void cambiaPos(int pos){
        actual = inicial;
        int posactual = 0;
        while(posactual < pos && actual!= null){
            posactual++;
            actual = actual.getSig();
        }
    }
    public int extraeNum1(int num1,int num2){
        Random rand = new Random();
        int num = rand.nextInt(num2-num1)+num1;
        return num;
    }
    public String extraeNombre(){
        String[] listita = {"Aarón", "Abdón", "Abel", "Abelardo", "Abrahán", "Absalón", "Acacio", "Adalberto",
                "Adán", "Adela", "Adelaida", "Adolfo", "Adón", "Adrián", "Agustín", "Aitor", "Alba",
                "Albert", "Alberto", "Albina", "Alejandra", "Alejandro", "Alejo", "Alfonso",
                "Alfredo", "Alicia", "Alipio", "Almudena", "Alonso", "Álvaro", "Amadeo", "Amaro",
                "Ambrosio", "Amelia", "Amparo", "Ana", "Ananías", "Anastasia", "Anatolio", "Andrea",
                "Andrés", "Ángel", "Ángela", "Ángeles", "Aniano", "Anna", "Anselmo", "Antero",
                "Antonia", "Antonio", "Aquiles", "Araceli", "Aránzazu", "Arcadio", "Aresio", "Ariadna",
                "Aristides", "Arnaldo", "Artemio", "Arturo", "Ascensión", "Asunción", "Atanasio",
                "Augusto", "Áurea", "Aurelia", "Aureliano", "Aurelio", "Aurora", "Baldomero",
                "Balduino", "Baltasar", "Bárbara", "Bartolomé", "Basileo", "Beatriz", "Begoña",
                "Belén", "Beltrán", "Benedicto", "Benigno", "Benito", "Benjamín", "Bernabé",
                "Bernarda", "Bernardo", "Blanca", "Blas", "Bonifacio", "Borja", "Bruno", "Calixto",
                "Camilo", "Cándida", "Carina", "Carlos", "Carmelo", "Carmen", "Carolina", "Casiano",
                "Casimiro", "Casio", "Catalina", "Cayetano", "Cayo", "Cecilia", "Ceferino", "Celia",
                "Celina", "Celso", "César", "Cesáreo", "Cipriano", "Cirilo", "Cirino", "Ciro", "Clara",
                "Claudia", "Claudio", "Cleofás", "Clotilde", "Colombo", "Columba", "Columbano",
                "Concepción", "Conrado", "Constancio", "Constantino", "Consuelo", "Cosme",
                "Cristian", "Cristina", "Cristóbal", "Daciano", "Dacio", "Dámaso", "Damián", "Daniel",
                "Dario", "David", "Demócrito", "Diego", "Dimas", "Dolores", "Domingo", "Donato",
                "Dorotea", "Edgar", "Edmundo", "Eduardo", "Eduvigis", "Efrén", "Elena", "Elías", "Elisa",
                "Eliseo", "Elvira", "Emilia", "Emiliano", "Emilio", "Encarnación", "Enrique", "Epifanía",
                "Erico", "Ernesto", "Esdras", "Esiquio", "Esperanza", "Esteban", "Ester", "Esther",
                "Eugenia", "Eugenio", "Eulalia", "Eusebio", "Eva", "Evaristo", "Ezequiel", "Fabián",
                "Fabio", "Fabiola", "Facundo", "Fátima", "Faustino", "Fausto", "Federico", "Feliciano",
                "Felipe", "Félix", "Fermín", "Fernando", "Fidel", "Fortunato", "Francesc", "Francisca",
                "Francisco", "Fulgencio", "Gabriel", "Gema", "Genoveva", "Gerardo", "Germán",
                "Gertrudis", "Gisela", "Gloria", "Godofredo", "Gonzalo", "Gregorio", "Guadalupe",
                "Guido", "Guillermo", "Gustavo", "Guzmán", "Héctor", "Heliodoro", "Heraclio",
                "Heriberto", "Hilarión", "Hildegarda", "Homero", "Honorato", "Honorio", "Hugo",
                "Humberto", "Ifigenia", "Ignacio", "Ildefonso", "Inés", "Inmaculada", "Inocencio",
                "Irene", "Ireneo", "Isaac", "Isabel", "Isaías", "Isidro", "Ismael", "Iván", "Jacinto",
                "Jacob", "Jacobo", "Jaime", "Jaume", "Javier", "Jeremías", "Jerónimo", "Jesús", "Joan",
                "Joaquím", "Joaquín", "Joel", "Jonás", "Jonathan", "Jordi", "Jorge", "Josafat", "José",
                "Josefa", "Josefina", "Josep", "Josué", "Juan", "Juana", "Julia", "Julián", "Julio",
                "Justino", "Juvenal", "Ladislao", "Laura", "Laureano", "Lázaro", "Leandro", "Leocadia",
                "León", "Leonardo", "Leoncio", "Leonor", "Leopoldo", "Lidia", "Liduvina", "Lino",
                "Lorena", "Lorenzo", "Lourdes", "Lucano", "Lucas", "Lucía", "Luciano", "Lucrecia",
                "Luis", "Luisa", "Luz", "Macario", "Magdalena", "Manuel", "Manuela", "Mar", "Marc",
                "Marcelino", "Marcelo", "Marcial", "Marciano", "Marcos", "Margarita", "María",
                "Mariano", "Marina", "Mario", "Marta", "Martín", "Mateo", "Matías", "Matilde",
                "Mauricio", "Maximiliano", "Melchor", "Mercedes", "Miguel", "Milagros", "Miqueas",
                "Míriam", "Mohamed", "Moisés", "Mónica", "Montserrat", "Narciso", "Natalia",
                "Natividad", "Nazario", "Nemesio", "Nicanor", "Nicodemo", "Nicolás", "Nicomedes",
                "Nieves", "Noé", "Noelia", "Norberto", "Nuria", "Octavio", "Odón", "Olga", "Onésimo",
                "Orestes", "Oriol", "Oscar", "Óscar", "Oseas", "Oswaldo", "Otilia", "Oto", "Pablo",
                "Pancracio", "Pascual", "Patricia", "Patricio", "Paula", "Pedro", "Petronila",
                "Pilar", "Pío", "Poncio", "Porfirio", "Primo", "Priscila", "Probo", "Purificación",
                "Rafael", "Raimundo", "Ramiro", "Ramón", "Raquel", "Raúl", "Rebeca", "Reinaldo",
                "Remedios", "Renato", "Ricardo", "Rigoberto", "Rita", "Roberto", "Rocío", "Rodrigo",
                "Rogelio", "Román", "Romualdo", "Roque", "Rosa", "Rosalia", "Rosario", "Rosendo",
                "Rubén", "Rufo", "Ruperto", "Salomé", "Salomón", "Salvador", "Salvio", "Samuel",
                "Sandra", "Sansón", "Santiago", "Sara", "Sebastián", "Segismundo", "Sergio",
                "Severino", "Silvia", "Simeón", "Simón", "Siro", "Sixto", "Sofía", "Soledad", "Sonia",
                "Susana", "Tadeo", "Tarsicio", "Teodora", "Teodosia", "Teófanes", "Teófila", "Teresa",
                "Timoteo", "Tito", "Tobías", "Tomas", "Tomás", "Toribio", "Trinidad", "Ubaldo",
                "Urbano", "Úrsula", "Valentín", "Valeriano", "Vanesa", "Velerio", "Venancio",
                "Verónica", "Vicenta", "Vicente", "Víctor", "Victoria", "Victorino", "Victorio",
                "Vidal", "Virgilio", "Virginia", "Vladimiro", "Wilfredo", "Xavier", "Yolanda",
                "Zacarías", "Zaqueo"};
        Random r = new Random();
        int num  = r.nextInt(455);
        return listita[num];
    }
    public double extraeSueldo(){
        Random r = new Random();
        double num  = r.nextInt(10001-1000)+1000;
        return num;
    }
    public Date extraeFecha(){
        long startMillis = 0L; // 1970-01-01
        long endMillis = System.currentTimeMillis(); // Fecha actual

        long randomMillis = ThreadLocalRandom.current().nextLong(startMillis, endMillis + 1);

        return new Date(randomMillis);
    }

    public double extraePlus(double sueldo) {
        Random r = new Random();
        double num  = r.nextInt((int) (sueldo+1));
        return num;
    }

    public class Nodo<T> implements Serializable {
        private T info;
        private Nodo<T> sig;
        private Nodo<T> ant;
        private int indice;

        public int getIndice() {
            return indice;
        }

        public void setIndice(int indice) {
            this.indice = indice;
        }

        public T getInfo() {
            return info;
        }
        public void setInfo(T info) {
            this.info = info;
        }
        public Nodo(T info) {
            this.info = info;
        }
        public Nodo(T info, int indice) {
            this.info = info;
            this.indice = indice;
        }
        public boolean tieneSig() {
            return this.sig!= null;
        }
        public boolean tieneAnt() {
            return this.ant != null;
        }

        public Nodo<T> getSig() {
            return sig;
        }

        public void setSig(Nodo<T> sig) {
            this.sig = sig;
        }

        public Nodo<T> getAnt() {
            return ant;
        }

        public void setAnt(Nodo<T> ant) {
            this.ant = ant;
        }
    }



}





