package algoritmo;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
//import java.awt.GraphicsEnvironment;
//import java.awt.Transparency;


public class Simetrico implements Filtro{
	
    public BufferedImage filtrar(BufferedImage bi /*imagen a examinar*/){
        /***Crea una copia del mismo tamaño que la imagen
        BufferedImage biDestino = 
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration()
               .createCompatibleImage(bi.getWidth(), bi.getHeight(), Transparency.OPAQUE);*/
    	
    	/***Crea una imagen de 32x32 pixeles que sera la matriz de promedios*/
        BufferedImage biDestino = 
            GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getDefaultScreenDevice().getDefaultConfiguration()
               .createCompatibleImage(32, 32, Transparency.OPAQUE);
    	
        int pc=0; //promedio de color
        int i=0; //contador de pixeles
        int k; //color final
        /** Aqui TODO mi codigo para ver si es simetrico 
         * obtencion de la matriz de promedios
         * obtiene los promedios de los colores de la imagen para ponerlos en otra nueva imagen*/ 
        int p=32; //p^2=numero de cuadrantes
        double ix,iy,sx=0,sy=0,tx=(double)bi.getWidth()/p, ty=(double)bi.getHeight()/p; 
        
        for(int xh=0;xh<p;xh++){
        	for(int yh=0; yh<p;yh++){
        		pc=0;i=0;ix=tx*xh;sx=tx*(xh+1);iy=ty*yh;sy=ty*(yh+1);
        		for (int x=(int)ix; x<sx; x++){
                    for (int y=(int)iy; y<sy; y++){
                    	i++;
                        //Obtiene el color
                        Color c1=new Color(bi.getRGB(x, y));
                        //Calcula la media de tonalidades
                        int med=(c1.getRed()+c1.getGreen()+c1.getBlue())/3;
                        //segun la vision humana
                        //int med=(int)(0.3*c1.getRed()+0.59*c1.getGreen()+0.11*c1.getBlue());
                        pc=pc+med;
                    }
                }
        		k=pc/i;
        		/**Almacena el color en la imagen destino */
                biDestino.setRGB(xh, yh, new Color(k,k,k).getRGB());
        	}
        }
        //System.out.println("c."+sx+"."+sy+": "+tx);
        /** Aqui TODO mi codigo para ver si es simetrico 
         * Verficar si la imagen es simetrica respecto al eje vertical */
        i=0;//i=comparaciones entre 0.8 y 1.
        //Recorre la matriz de promedios para realizar las comparaciones
        for (int x=0;x < 16;x++){
            for (int y=0;y < 32;y++){
                //Obtiene las tonalidades a comparar
                int c1=new Color(biDestino.getRGB(x, y)).getRed();
                int c2=new Color(biDestino.getRGB(31-x, y)).getRed();
                //Compara los valores
                //System.out.println(x+","+y+","+" "+c1+","+c2+","+(double)(255-Math.abs(c1-c2))/255);
                if((double)(255-Math.abs(c1-c2))/255>0.8){
                	i++;
                }
            }
        }
        pc=100*i/512;
        System.out.println("simetria vertical: "+pc+"%");
        /** Aqui TODO mi codigo para ver si es simetrico 
         * Verficar si la imagen es simetrica respecto al eje horizontal */
        i=0;//i=comparaciones entre 0.8 y 1.
        //Recorre la matriz de promedios para realizar las comparaciones
        for (int x=0;x < 32;x++){
            for (int y=0;y < 16;y++){
                //Obtiene las tonalidades a comparar
                int c1=new Color(biDestino.getRGB(x, y)).getRed();
                int c2=new Color(biDestino.getRGB(x, 31-y)).getRed();
                //System.out.println(x+","+y+","+" "+c1+","+c2+","+(double)(255-Math.abs(c1-c2))/255);
                //Compara los valores
                if((double)(255-Math.abs(c1-c2))/255>0.8){
                	i++;
                }
            }
        }
        pc=100*i/512;
        System.out.println("simetria horizontal: "+pc+"%");
        return biDestino;
    }
}