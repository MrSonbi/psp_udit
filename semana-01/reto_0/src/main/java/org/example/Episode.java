package org.example;

public class Episode {
    //ATRIBUTOS: Variables privadas por seguridad (encapsulamiento)
    //Solo esta clase puede modificar directamente
    private String title;
    private int duration;

    //CONSTRUCTORES: El molde que ejecuta al hacer un nuevo Episodio
    public Episode(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    //METODO: La acción simula la carga lenta del servidor

    public void procesar() {
        System.out.println("Iniciando procesamiento de: " + this.title + "...");
        //El bloque try-catch es OBLIGATORIO en java al pausar el hilo (Thread)
        //Evita que el programa explote (crash) si el SO interrumpe la pausa
        try {
            //Pausamos la ejecucion en 2000 milisegundos (2s)
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Error al procesar el episodio");
        }
        System.out.println("Completadp: " + this.title);
    }
}
