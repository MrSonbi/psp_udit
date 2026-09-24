package org.example;

import java.util.ArrayList;

public class Platform {
    ArrayList<Episode> catalog = new ArrayList<>();

    public Platform(ArrayList catalog) {
        this.catalog = new ArrayList<>();
    }

    public void agregarEpisode(Episode e) {
        catalog.add(new Episode("Diseño 3D - Intro", 45));
        catalog.add(new Episode("Animación", 60));
        catalog.add(new Episode("Texturas", 50));
        catalog.add(new Episode("Sonido", 55));
        catalog.add(new Episode("Subtítulos", 65));
    }

    long start = System.currentTimeMillis();

    public void procesarCatalogo() {
        for(Episode e : catalog) {
            e.procesar();
        }

        long fin = System.currentTimeMillis();

        long totalSegundos = (fin - start) / 1000;

        System.out.println("Tiempo total: " + totalSegundos + " segundos de bloqueo.");

    }
}
