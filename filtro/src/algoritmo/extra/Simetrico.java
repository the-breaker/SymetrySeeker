package algoritmo.extra;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
//import java.awt.GraphicsEnvironment;
//import java.awt.Transparency;
import java.awt.image.BufferedImage;

public class Simetrico implements Filtro{
	
    public BufferedImage filtrar(BufferedImage bi){
        /***Crea una copia del mismo tamaño que la imagen*/
        BufferedImage biDestino = 
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration()
               .createCompatibleImage(bi.getWidth(), bi.getHeight(), Transparency.OPAQUE);
    	
    	/***Crea una imagen de 16x16 pixeles 
        BufferedImage biDestino = 
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration()
               .createCompatibleImage(32, 32, Transparency.OPAQUE);*/
    	
        /** Aqui TODO mi codigo para ver si es simetrico 
         * obtiene los promedios de los colores de la imagen para ponerlos en otra nueva imagen 
        int p=32,ix, iy,sx,sy, tx=bi.getWidth()/p, ty=bi.getHeight()/p; //p°2=numero de cuadrantes
        //parte 1 
        int pc=0; //promedio de color
        int i=0; //contador de pixeles
        int k; //color final
        for(int xh=0;xh<p;xh++){
        	for(int yh=0; yh<p;yh++){
        		pc=0;i=0;ix=tx*xh;sx=tx*(xh+1);iy=ty*yh;sy=ty*(yh+1);
        		for (int x=ix; x<sx; x++){
                    for (int y=iy; y<sy; y++){
                    	i++;
                        //Obtiene el color
                        Color c1=new Color(bi.getRGB(x, y));
                        //Calcula la media de tonalidades
                        int med=(c1.getRed()+c1.getGreen()+c1.getBlue())/3;
                        pc=pc+med;
                    }
                }
        		//System.out.println("c."+xh+"."+yh+": "+pc/i);
        		k=pc/i;
        		/**Almacena el color en la imagen destino *
                biDestino.setRGB(xh, yh, new Color(k,k,k).getRGB());
        	}
        }*/
        //pc=0;i=0;
        //Recorre las imagenes y obtiene el color de la imagen original y la almacena en el destino
        for (int x=0;x < bi.getWidth();x++){
            for (int y=0;y < bi.getHeight();y++){
                //Obtiene el color
                Color c1=new Color(bi.getRGB(x, y));
                //Calcula la media de tonalidades
                /**int med=(c1.getRed()+c1.getGreen()+c1.getBlue());
                if(c1.getGreen()>0){
                	if(med<255){
                		biDestino.setRGB(x, y, new Color(0,med,0).getRGB());
                	}else{
                		biDestino.setRGB(x, y, new Color((med-255)/2,255,(med-255)/2).getRGB());
                	}
                }else{
                	//Almacena el color en la imagen destino
                    biDestino.setRGB(x, y, new Color(0,0,0).getRGB());
                }*/
                int med=c1.getGreen();
              //Almacena el color en la imagen destino
                biDestino.setRGB(x, y, new Color(255-med,255,255-med).getRGB());
            }
        }
        return biDestino;
    }
}