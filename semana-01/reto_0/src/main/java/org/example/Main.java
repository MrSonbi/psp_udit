package org.example;
import java.util.ArrayList; //Herramientas nativas para listas

public class Main {
    public static void main(String[] args) {
        System.out.println("--- INICIANDO UDITVERSUM ---");

        Platform plataform = new Platform(new ArrayList<>());
        plataform.newEpisode(null);
        plataform.processCatalog();
        /*
        //1. MALA PRACTICA: Toda la logica de negocio tirada al Main, los diamantes <> obligan a que la lista SOLO acepte episodios
        ArrayList<Episode> catalog = new ArrayList<>();

        catalog.add(new Episode("Diseño 3D - Intro", 45));
        catalog.add(new Episode("Animación", 60));
        catalog.add(new Episode("Texturas", 50));

        //2. MEDICION DE TIEMPO
        //Usamos 'long' porque los milisegundos son desde 1970 forman un numero tan gigantesco que no cabe en la memoria int normal

        long start = System.currentTimeMillis();

        //3. BUCLE FOR - EACH Por cada episodio dentro del catalogo
        for(Episode ep : catalog) {
            ep.process();//Aqui ocurre el bloque secuencial de 2s
        }

        long fin = System.currentTimeMillis();

        //4. RESULTADO: Restamos fin menos inicio y dividimos entre mil (segundos)

        long totalSegundos = (fin - start) / 1000;

        System.out.println("Tiempo total: " + totalSegundos + " segundos de bloqueo.");
        */
    }
}