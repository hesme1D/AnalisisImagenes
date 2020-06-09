/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisisfrecuencias;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import open.AbrirImagen;
/**
 *
 * @author esmec
 */
public abstract class FiltroFrecuencia {
    private NumeroComplejo[][] filtroEspacial;
    
    public FiltroFrecuencia(int ancho, int alto){
        this.filtroEspacial= new NumeroComplejo[ancho][alto];
    }
    
    public abstract void crearFiltro();
    
    /**
     * @return the filtroEspacial
     */
    public NumeroComplejo[][] getFiltroEspacial(){
        return filtroEspacial;
    }
    
    /**
     * @param filtroEspacial the filtroEspacial to set
     */
    public void setFiltroEspacial(NumeroComplejo[][] filtroEspacial){
        this.filtroEspacial=filtroEspacial;
    }
    
    public static Image toImageDeComplejo(NumeroComplejo[][] filtro){
        //Inicializar el buffer
        BufferedImage bi = new BufferedImage((int)filtro.length,(int)filtro.length, BufferedImage.TYPE_INT_ARGB);
        
        //Recorrer el filtro
        for(int x=0; x<filtro.length; x++){
            for(int y=0; y<filtro.length; y++){
                double valor = filtro[x][y].getParteReal()*255;
                bi.setRGB(x, y, new Color((int)valor,(int)valor,(int)valor).getRGB());
            }
        }
        return AbrirImagen.toImage(bi);
    }
}
