package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class Teclado implements KeyListener {

    private final static int numeroTeclas = 120;
    private final boolean[] teclas = new boolean[numeroTeclas];

    //UTILIZA PUBLIC PARA AGILIZAR LA RESPUESTA AL PULSAR LA TECLA DE LO CONTRARIA PODRIA SER MAS LENTO AL USAR PRIVATE CON GETERS Y SETERS
    public boolean arriba;
    public boolean abajo;
    public boolean izquierda;
    public boolean derecha;

    public void actualizar(){
        arriba = teclas[KeyEvent.VK_W];
        abajo = teclas[KeyEvent.VK_S];
        izquierda = teclas[KeyEvent.VK_A];
        derecha = teclas[KeyEvent.VK_D];
    }

    //RECONOCE EL PULSADO DE UNA TECLA
    @Override
    public void keyPressed(KeyEvent e){
        teclas[e.getKeyCode()] = true;
    }

    //RECONOCE QUE SE DEJO DE PULSAR UNA TECLA
    @Override
    public void keyReleased(KeyEvent e) {
        teclas[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
