package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class PildoraMonitor {
    public static void main(String[] args) {
        System.out.println("---MONITOR UDITFLIX---");
        System.out.println("Comprobando servicio...");

        //TRY / CATCH
        //Lanzar un programa externo o esperar a que termine PUEDE FALLAR
        //try -> "intenta hacer esto"
        //catch -> "Si algo sale mal, haz esto otro en vez de romper el programa"

        try {
            //PASO 1: PREPARAR el proceso (todavía no se ejecuta)
            //ProcessBuilder es el "encargado" que prepara la orden que le daremos al sistema operativo. Es como rellenar un formulario
            //"ping" -> el programa que queremos ejecutar
            //"-n" -> opción de windows: número de intentos
            //"1" -> haz solo 1 intento (así termina más rápido)
            //"127.0.0.1 -> a quién hacemos ping : nuestro propio ordenador
            //(siempre responde, simula un servicio ACTIVO)
            //OJO "-n" slo vale en Window. En Linux y Mac sería "-c"
            ProcessBuilder pb = new ProcessBuilder(
                    "ping", "-n", "1", "127.0.0.x"
            );

            //PASO 2: UNIR los dos canales de salida
            //Tod0 programa tiene dos canales por los que "habla"
            // - salida normal (lo que funciona bien)
            // - salida error (los mensajes de fallo)
            //Con redirectErrorStream(true) lo juntamos en UNO SOLO, así leyendo un único canal, vemos tod0 lo que el proceso diga, sea un resultado normal o un error
            pb.redirectErrorStream(true);

            //PASO 3: LANZAR el proceso
            //start() es el botón de "enviar". Ahora si el sistema operativo crea un programa nuevo (ping) que corre por su cuenta con su propia memoria, de nuestro programa Java. Process es el objeto con el que controlamos ese programa
            Process proceso = pb.start();

            //PASO 4: MOSTRAR EL PID
            //PID = Process IDentifier, es el "DNI" del proceso

            System.out.println("PID: " + proceso.pid());

            //PASO 5: PREPARAR la lectura de lo que dice el proceso
            //Elproceso ping escribe su propia consola, que Java no ve, para escucharlo nos "conectamos" a su salida con una cadena: proceso.getInputStream() -> la "tubería" por la que sale el texto del proceso (en bytes). new InputStreamReader(...) -> traduce esos bytes a letras. new BufferReader(...) -> nos deja leer línea a línea

            BufferedReader lector = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream())
            );

            //Variable donde guardaremos cada línea que vayamos leyendo, todavía está vacía
            String linea;

            //PASO 6: LEER tod0 lo que el proceso va escrbiendo
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }

            //PASO 7: ESPERAR a que el proceso termine
            //waitFor() es lo que nos garantiza el código de salida
            int codigo = proceso.waitFor();

            //PASO 8: INTERPRETAR el resultado
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
}
