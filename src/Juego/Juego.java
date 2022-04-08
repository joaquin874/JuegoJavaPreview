package Juego;

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
    private static final String NOMBRE = "Juego";

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
        thread = new Thread(this, "Graficos");
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
    @Override
    public void run() {
        while(enFuncionamiento){

        }
//        System.out.printf("El thread 2 se esta ejecutando con exito");
    }
}

