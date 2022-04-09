package graficos;

public final class Sprite {
    private final int lado;

    private int x;
    private int y;

    public int[] pixeles;
    private final HojaSprites hojaSprites;

    public Sprite(final int lado, final int columna, final int fila, final HojaSprites hojaSprites){
        this.hojaSprites = hojaSprites;
        this.lado = lado;

        //ES LADO POR LADO PORQUE CARGA SPRITES CUADRADOS
        pixeles = new int[this.lado * this.lado];

        this.x = columna * lado;
        this.y = fila * lado;


        //NOTA: EN JAVA LAS OPERACIONES SE EJECUTAN SECUENCIALMENTE EN JAVA, POR ESO EL x + y * lado
        //Y NO (x * y) + lado
        for (int y = 0; y < lado; y++){
            for (int x = 0; x < lado; x++){
                pixeles[x + y * lado] = hojaSprites.pixeles[(x + this.x) + (y + this.y) * hojaSprites.getAncho()];
            }
        }
    }
}
