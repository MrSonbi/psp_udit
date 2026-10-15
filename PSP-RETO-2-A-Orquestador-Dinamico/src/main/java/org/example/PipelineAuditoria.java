package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PipelineAuditoria {
    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("            PIPELINE DE AUDITORÍA");
        System.out.println("==============================================\n");

        try {
            List<String> lineasIps = Files.readAllLines(Paths.get("C:\\Users\\Sonbi\\Documents\\UDIT_2026-2027\\PSP\\psp_udit\\PSP-RETO-2-Pipeline-de-Auditoria\\src\\main\\resources\\servidores.txt"));

            ArrayList<Process> listaProcesos = new ArrayList<>();

            long inicio = System.currentTimeMillis();

            System.out.println("INICIANDO EJECUCIÓN PARALELA (desde archivo)... ");

            for (String ip : lineasIps) {
                if (ip.trim().isEmpty()) continue;

                System.out.println("     -> Lanzando ping a la IP: " + ip);
                Process proceso = new ProcessBuilder("ping", "-n", "1", "-w", "3000", ip.trim()).start();
                listaProcesos.add(proceso);
            }

            int exitos = 0;
            int fallos = 0;

            for (Process proceso : listaProcesos) {
                int resultado = proceso.waitFor();
                if (resultado == 0) {
                    exitos++;
                } else {
                    fallos++;
                }
            }

            System.out.println("\nResultados -> Éxitos: " + exitos + " | Fallos: " + fallos);

            if (fallos == 0) {
                System.out.println("Situación: 0 fallos. Abriendo Notepad...");
                new ProcessBuilder("notepad.exe").start();
            } else if (exitos > 0 && fallos > 0) {
                System.out.println("Situación: Hay éxitos y fallos. Abriendo Calculadora...");
                new ProcessBuilder("calc.exe").start();
            } else {
                System.out.println("Situación: Todo falla. Abriendo Explorer...");
                new ProcessBuilder("explorer.exe").start();
            }

            System.out.println("Tiempo total: " + (System.currentTimeMillis() - inicio) + " ms");

        } catch (IOException e) {
            System.out.println("Error: no se pudo leer el archivo servidores.txt o lanzar el proceso");
        } catch (InterruptedException e) {
            System.out.println("Error: La espera fue interrumpida de forma inesperada");
        }
    }
}
