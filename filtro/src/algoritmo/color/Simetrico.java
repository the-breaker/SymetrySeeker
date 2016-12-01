package algoritmo.color;

//import java.awt.GraphicsEnvironment;
//import java.awt.Transparency;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

public class Simetrico implements Filtro{
	
    public BufferedImage filtrar(BufferedImage bi){
        /***Crea una copia del mismo tamaño que la imagen
        BufferedImage biDestino = 
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration()
               .createCompatibleImage(bi.getWidth(), bi.getHeight(), Transparency.OPAQUE);*/
    	
    	/***Crea una imagen de 16x16 pixeles */
        BufferedImage biDestino = 
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration()
               .createCompatibleImage(32, 32, Transparency.OPAQUE);
    	
        int i=0; //contador de pixeles
        /** Aqui TODO mi codigo para ver si es simetrico 
         * obtiene los promedios de los colores de la imagen para ponerlos en otra nueva imagen*/ 
        int pc1,pc2,pc3,p=32; //p^2=numero de cuadrantes
        double ix,iy,sx=0,sy=0,tx=(double)bi.getWidth()/p, ty=(double)bi.getHeight()/p; 
        
        for(int xh=0;xh<p;xh++){
        	for(int yh=0; yh<p;yh++){
        		ix=tx*xh;sx=tx*(xh+1);iy=ty*yh;sy=ty*(yh+1);
        		i=0;pc1=0 ;pc2=0;pc3=0; //promedios de color
        		for (int x=(int)ix; x<sx; x++){
                    for (int y=(int)iy; y<sy; y++){
                    	i++;
                        //Obtiene el color
                        Color c1=new Color(bi.getRGB(x, y));
                        //Calcula la media de tonalidades
                        pc1=c1.getRed()+pc1;
                        pc2=c1.getGreen()+pc2;
                        pc3=c1.getBlue()+pc3;
                    }
                }
        		//System.out.println(">: "+pc1/i+","+pc2/i+","+pc3/i);
        		/**Almacena el color en la imagen destino */
                biDestino.setRGB(xh, yh, new Color(pc1/i,pc2/i,pc3/i).getRGB());
        	}
        }
        /** Aqui TODO mi codigo para ver si es simetrico 
         * Verficar si la imagen es simetrica respecto al eje vertical */
        int i1=0,i2=0,i3=0;//i=comparaciones entre 0.8 y 1.
        //Recorre la matriz de promedios para realizar las comparaciones
        for (int x=0;x < 16;x++){
            for (int y=0;y < 32;y++){
                //Obtiene los colores de la imagen
                Color c1=new Color(biDestino.getRGB(x, y));
                Color c2=new Color(biDestino.getRGB(31-x, y));
                //Compara los valores en rojo
                if((double)(255-Math.abs(c1.getRed()-c2.getRed()))/255>0.8){
                	i1++;
                }
                //Compara los valores en verde
                if((double)(255-Math.abs(c1.getGreen()-c2.getGreen()))/255>0.8){
                	i2++;
                }
                //Compara los valores en azul
                if((double)(255-Math.abs(c1.getBlue()-c2.getBlue()))/255>0.8){
                	i3++;
                }
            }
        }
        pc1=100*i1/512;
        System.out.println("simetria vertical en rojo: "+pc1+"%");
        pc2=100*i2/512;
        System.out.println("simetria vertical en verde: "+pc2+"%");
        pc3=100*i3/512;
        System.out.println("simetria vertical en azul: "+pc3+"%");
        /** Aqui TODO mi codigo para ver si es simetrico 
         * Verficar si la imagen es simetrica respecto al eje horizontal */
        i1=0;i2=0;i3=0;//i=comparaciones entre 0.8 y 1.
        //Recorre la matriz de promedios para realizar las comparaciones
        for (int x=0;x < 32;x++){
            for (int y=0;y < 16;y++){
                //Obtiene los colores de la imagen
                Color c1=new Color(biDestino.getRGB(x, y));
                Color c2=new Color(biDestino.getRGB(x, 31-y));
                //Compara los valores en rojo
                if((double)(255-Math.abs(c1.getRed()-c2.getRed()))/255>0.8){
                	i1++;
                }
                //Compara los valores en verde
                if((double)(255-Math.abs(c1.getGreen()-c2.getGreen()))/255>0.8){
                	i2++;
                }
                //Compara los valores en azul
                if((double)(255-Math.abs(c1.getBlue()-c2.getBlue()))/255>0.8){
                	i3++;
                }
            }
        }
        pc1=100*i1/512;
        System.out.println("simetria horizontal en rojo: "+pc1+"%");
        pc2=100*i2/512;
        System.out.println("simetria horizontal en verde: "+pc2+"%");
        pc3=100*i3/512;
        System.out.println("simetria horizontal en azul: "+pc3+"%");
 
        return biDestino;
    }
}