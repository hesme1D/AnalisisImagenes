/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisisespacial;

import static analisisespacial.FiltrosEspaciales.validarLimites;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import open.AbrirImagen;

/**
 *
 * @author esmec
 */
public class Convolucion {
    
    //Le agregamos al método el divisor y el offset
    public static Image AplicarConvolucion(Image io, int[][] mascara, int div, int offset){
        
        BufferedImage bi = AbrirImagen.toBufferedImage(io);
        BufferedImage bnuevo = new BufferedImage(bi.getWidth(),bi.getHeight(),BufferedImage.TYPE_INT_RGB);
        //Recorrer buffer
        for(int x=0;x<bi.getWidth();x++){
            for(int y=0;y<bi.getHeight();y++){
                //Como para calcular el nuevo tono se necesitan tanto el divisor como el offset 
                //se agregan
                int rgb = calcularNuevoTono(x,y,bi,mascara,div,offset);
                bnuevo.setRGB(x, y, rgb);
            }
        }
        return AbrirImagen.toImage(bnuevo);
    }

    private static int calcularNuevoTono(int x, int y,BufferedImage bi, int[][] mascara,int div, int offset) {
        //Recorrer la máscara
        //int r=x-1;
        //int c=y-1;
        int auxR=0; //Auxiliares para acumular
        int auxG=0;
        int auxB=0;
        Color color = null;
        //int k=0;
        for(int i=0,r=x-1; i<mascara.length;i++,r++){
            for(int j=0, c=y-1; j<mascara[0].length;j++,c++){
                //Se debe quitar el if
                //if(mascara[i][j]!=0){
                    try{
                        int rgb = bi.getRGB(r,c);
                        //k++;
                        color = new Color(rgb);
                        //Los auxiliares de los colores se multiplican por la máscara
                        auxR += color.getRed()*mascara[i][j];
                        auxG += color.getGreen()*mascara[i][j];
                        auxB += color.getBlue()*mascara[i][j];
                    }catch(Exception e){
                        //e.printStackTrace();
                    }
                //}
            }
        }
        //quitamos k y lo sustituimos por el divisor
        //if(div!=0){
            auxR/=div;
            auxG/=div;
            auxB/=div;
        //}
        //Sumamos el valor de los auxiliares del clor más el offset
        color = new Color(validarLimites(auxR+offset),validarLimites(auxG+offset),validarLimites(auxB+offset));
        //color = new Color(auxR+offset,auxG+offset,auxB+offset);
        
        return color.getRGB();
    }
    public static Image Convolucion(Image io, double[][]kernel, double fD){
        BufferedImage bi = AbrirImagen.toBufferedImage(io);
        BufferedImage nueva = new BufferedImage(bi.getWidth(),bi.getHeight(),BufferedImage.TYPE_INT_RGB);
        int n = kernel.length;
        int t = (int) Math.floor(n / 2);
        Color color;
        // recorres la imagen para generar la nueva 
        for(int i = 0; i<bi.getWidth();i++){
            for(int j =0; j<bi.getHeight(); j++){
                double auxR=0, auxG=0, auxB=0 ;
                for(int x=0; x<n; x++){
                    for(int y =0; y<n; y++){
                    try{
                        color = new Color(bi.getRGB(x - t +i, y -t +j));
                        auxR += kernel[x][y] * color.getRed();
                        auxG += kernel[x][y] * color.getGreen();
                        auxB += kernel[x][y] * color.getBlue();
                                
                    }catch(Exception e){
                        
                    }
                }
            }
            auxR /= fD;
            auxG /= fD;
            auxB /= fD;
            
             color = new Color(validarLimites((int) auxR),validarLimites((int) auxG),validarLimites((int) auxB));
             nueva.setRGB(i, j, color.getRGB());
            }
        }
        return AbrirImagen.toImage(nueva);
    }
    public static int validarValor(int valor){
      if(valor<0) return 0;
      if(valor>255) return 255;
      return valor;
    }
}
