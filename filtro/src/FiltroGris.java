import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

public class FiltroGris implements Filtro{
	
    public BufferedImage filtrar(BufferedImage bi){
        //Crea una copia del mismo tamaño que la imagen
        BufferedImage biDestino = 
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration()
               .createCompatibleImage(bi.getWidth(), bi.getHeight(), Transparency.OPAQUE);
        //Recorre las imagenes y obtiene el color de la imagen original y la almacena en el destino
        for (int x=0;x < bi.getWidth();x++){
            for (int y=0;y < bi.getHeight();y++){
                //Obtiene el color
                Color c1=new Color(bi.getRGB(x, y));
                //Calcula la media de tonalidades
                int med=(c1.getRed()+c1.getGreen()+c1.getBlue())/3;
                //Almacena el color en la imagen destino
                biDestino.setRGB(x, y, new Color(med,med,med).getRGB());
            }
        }
        return biDestino;
    }
}