package juego;

import javax.swing.JFrame;

import java.awt.Canvas;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class Juego extends Canvas implements Runnable{

    //sirve para identificar la clase en caso de reutilizarla en otras circunstancias
    //private static final long serialVersionUID = 1L;

    private static final int ANCHO = 800;
    private static final int ALTO = 600;

    //VOLATILE HACE QUE NO SE CUELGUE EL PROGRAMA DE LA NADA AL USAR enFuncionamiento EN MAS DE 1 HILO
    //EN RESUMEN HACE QUE NO PUEDA UTILIZAR DE FORMA SIMULTANEA POR LOS 2 THREADS
    private static volatile boolean  enFuncionamiento = false;

    private static final String NOMBRE = "juego";

    private static int aps = 0;
    private static int fps = 0;
    private static JFrame ventana;
    private static Thread thread;

    public Juego() {
        setPreferredSize(new Dimension(ANCHO, ALTO));

        ventana = new JFrame(NOMBRE);
        //PARA QUE SE CIERRE LA VENTANA CUANDO HACEMOS CLICK EN LA CRUZ
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //PARA QUE NO SE CAMBIE EL TAMANIO DE LA VENTANA A DISPOSICION DEL USUARIO
        ventana.setResizable(false);
        ventana.setLayout(new BorderLayout());
        //PONE LA VENTANA EN EL CENTRO DE LA PANTALLA
        ventana.add(this, BorderLayout.CENTER);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.iniciar();
    }

    private synchronized void iniciar(){
        enFuncionamiento = true;
        thread = new Thread(this, "graficos");
        thread.start();
    }

    private synchronized void detener(){
        enFuncionamiento = false;

        try {
            thread.join();    //PARAR EL THREAD
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private void actualizar(){
        aps++;
    }

    private void mostrar(){
        fps++;
    }
    @Override
    public void run() {
        //TEMPORIZADOR PARA LOS FPS
        final int NS_POR_SEGUNDO = 1000000000;
        final byte APS_OBJETIVO = 60;
        final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO/APS_OBJETIVO;

        long referenciaActualizacion = System.nanoTime();
        long referenciaContador = System.nanoTime();

        double tiempoTranscurrido;
        double delta = 0;

        while(enFuncionamiento){
            final long incioBucle = System.nanoTime();

            tiempoTranscurrido = incioBucle - referenciaActualizacion;
            referenciaActualizacion = incioBucle;

            delta += tiempoTranscurrido/NS_POR_ACTUALIZACION;

            while (delta >= 1){
                actualizar();
                delta--;
            }
            mostrar();

            if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO){
                ventana.setTitle(NOMBRE + " || APS: " + aps + " || FPS: " + fps);
                aps = 0;
                fps = 0;
                referenciaContador = System.nanoTime();
            }
        }
//        System.out.printf("El thread 2 se esta ejecutando con exito");
    }
}

