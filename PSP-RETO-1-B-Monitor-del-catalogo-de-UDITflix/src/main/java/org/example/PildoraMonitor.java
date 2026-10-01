package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;

public class PildoraMonitor {
    public static void main(String[] args) {
        System.out.println("========================================\n" +
                "           UDITFLIX - CATÁLOGO\n" +
                "========================================");
        String[][] contenidos = {
                {"Series", "127.0.0.x"},
                {"Películas", "127.0.0.1"},
                {"Documentales", "127.0.0.1"},
                {"Anime", "127.0.0.x"},
                {"Infantil", "127.0.0.1"}
        };
        for (int i = 0 ; i < contenidos.length ; i++) {
            String categoria = contenidos[i][0];
            String ip = contenidos[i][1];
            System.out.println("[CONTENIDO] " + categoria);
            try {
                ProcessBuilder pb = new ProcessBuilder(
                        "ping", "-n", "1", ip
                );
                pb.redirectErrorStream(true);
                Process proceso = pb.start();
                System.out.println("PID: " + proceso.pid());
                int codigo = proceso.waitFor();
                if (codigo == 0) {
                    System.out.println("ESTADO : SERVICIO ACTIVO");
                } else {
                    System.out.println("ESTADO : SERVICIO CON ERROR");
                }
            } catch (IOException e) {
                System.out.println("No se pudo lanzar el proceso");
            } catch (InterruptedException e) {
                System.out.println("La ejecución fue interrumpida");
            }
        }
        System.out.println("========================================\n" +
                "       COMPROBACIÓN FINALIZADA\n" +
                "========================================");
    }
}
