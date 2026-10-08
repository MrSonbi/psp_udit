package org.example;

import java.io.IOException;

public class PipelineAuditoria {
    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("            PIPELINE DE AUDITORÍA");
        System.out.println("==============================================\n");

        try {
            System.out.println("INICIANDO EJECUCIÓN PARALELA... ");

            System.out.println("     -> Lanzando proceso 1");
            Process proceso1 = new ProcessBuilder("ping", "-n", "2", "127.0.0.1").start();
            System.out.println("     -> Lanzando proceso 2");
            Process proceso2 = new ProcessBuilder("ping", "-n", "2", "x").start();

            proceso1.waitFor();
            proceso2.waitFor();
            int proceso1resultado = proceso1.waitFor();
            int proceso2resultado = proceso2.waitFor();
            System.out.println("El resultado del proceso 1 es: " + proceso1resultado);
            System.out.println("El resultado del proceso 2 es: " + proceso2resultado);

            if (proceso1resultado == 0 && proceso2resultado == 0) {
                new ProcessBuilder("notepad.exe").start();
            } else {
                new ProcessBuilder("calc.exe").start();
            }
        } catch (IOException e) {
            System.out.println("Error: no se pudo lanzar el proceso");
        } catch (InterruptedException e) {
            System.out.println("Error: La espera fue interrumpida de forma inesperada");
        }
    }
}
