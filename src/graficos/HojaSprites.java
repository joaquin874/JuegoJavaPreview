package graficos;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HojaSprites {
    private final int ancho;
    private final int alto;
    public final int[] pixeles;
    //RUTA DE LA IMAGEN
    public HojaSprites(final String ruta, final int ancho, final int alto){
        this.ancho = ancho;
        this.alto = alto;

        pixeles = new int[ancho*alto];

        //LEE LA IMAGEN
        BufferedImage imagen;
        try{
            imagen = ImageIO.read(HojaSprites.class.getResource(ruta));
            imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getAlto() {
        return alto;
    }

    public int getAncho() {
        return ancho;
    }
}
