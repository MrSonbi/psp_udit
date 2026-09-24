package org.example;

import java.io.IOException;

public class LanzadorProcesos {
    public static void main(String[] args) {
        System.out.println("Solicitando al SO la creación de un proceso nativo ...");

        //1. ProcessBuilder es el puente entre Java y e Sistema Operativo
        //Preparamos el comando (en Windows, notepad.exe es el block de notas)

        ProcessBuilder ph = new ProcessBuilder("notepad.exe");
        try {
            //2. start {} es el gatillo. Pide el planificador del SO que cree el proceso
            Process process = ph.start();
            //3. waitFor() pausa nuestro hilo en Java hasta que el usuario cierre el block de notas
            int exit = process.waitFor();
            //0 Significa cierre limpio. Cualquier otro numero indica error o cierre forzoso
            System.out.println("El proceso externo finalizo con código: " + exit);
        } catch (IOException e) {
            System.out.println("Error: el sistema operativo no encuentra el programa.");
        } catch (InterruptedException e) {
            System.out.println("Error: el proceso fue interrumpido bruscamente");
        }
    }
}
