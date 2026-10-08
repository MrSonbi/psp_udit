package org.example;

import java.io.IOException;

public class PildoraParalelismo {
    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("🚀 PÍLDORA TÉCNICA: SECUENCIAL VS PARALELO 🚀");
        System.out.println("==============================================");

        try {
            System.out.println(" INICIANDO EJECUCIÓN SECUENCIAL... ");
            long inicioSecuencial = System.currentTimeMillis();

            System.out.println("     -> Lanzando proceso 1 ( y esperando que muera...");
            Process p1 = new ProcessBuilder("ping", "-n", "2", "127.0.0.1").start();
            p1.waitFor();//CUIDADO: Java se congela aquí. El proceso 2 aún no existe
            //Una vez que el proceso 1 termina, por fin lanzamos el SEGUNDO proceso
            System.out.println("     -> Lanzando proceso 2 ( y esperando que muera...");
            Process p2 = new ProcessBuilder("ping", "-n", "2", "8.8.8.8").start();
            p2.waitFor();//Java se vuelve a congelar

            long finSecuencial = System.currentTimeMillis();
            System.out.println("⏱️ TIEMPO TOTAL SECUENCIAL: " + (finSecuencial - inicioSecuencial) + "ms ⏱️");
            System.out.println("==============================================");

        //2. EL CAMINO PARALELO(Ejecución solapada)
            System.out.println("INICIANDO EJECUCIÓN PARALELA... ");
            //Reseteo el cronómetro
            long inicioParalelo = System.currentTimeMillis();

            //PASO A: Apretamos todos los gatillos primero
            System.out.println("     -> Lanzando proceso 3 ( ¡No esperamos!");
            Process p3 = new ProcessBuilder("ping", "-n", "2", "127.0.0.1").start();
            System.out.println("     -> Lanzando proceso 4 ( ¡No esperamos!");
            Process p4 = new ProcessBuilder("ping", "-n", "2", "8.8.8.8").start();

            //PASO B: Ahora si, le decimos a java que recoja los resultados
            //Como ya están recorriendo simultáneamente en el Sistema Operativo, el tiempo de espera se solapa
            System.out.println("     -> Bloqueando Java para recoger los resultados");
            p3.waitFor();
            p4.waitFor();

            long finParalelo = System.currentTimeMillis();
            System.out.println("⏱️ TIEMPO TOTAL PARALELO: " + (finParalelo - inicioParalelo) + "ms ⏱️");
        } catch (IOException e) {
            System.out.println("Error: no se pudo lanzar el proceso");
        } catch (InterruptedException e) {
            System.out.println("Error: La espera fue interrumpida de forma inesperada");
        }
    }
}
