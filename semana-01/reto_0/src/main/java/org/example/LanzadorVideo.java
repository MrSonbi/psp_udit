package org.example;

import java.io.IOException;

public class LanzadorVideo {
    public static void main(String[] args) {
        ProcessBuilder ph = new ProcessBuilder("cmd", "/c", "start", "https://www.youtube.com/watch?v=-EbpzuHuNR4&list=RD-EbpzuHuNR4&start_radio=1");
        try {
            Process process = ph.start();
            System.out.println("¡Vídeo lanzado con éxito!");
            int exit = process.exitValue();
            System.out.println("El proceso externo finalizo con código: " + exit);
        } catch (IOException e) {
            System.out.println("Error: el sistema operativo no encuentra el programa.");
        }
    }
}
