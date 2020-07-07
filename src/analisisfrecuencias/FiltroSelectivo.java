/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisisfrecuencias;

import java.awt.Dimension;
import java.awt.Image;

/**
 *
 * @author esmec
 */
public class FiltroSelectivo extends FiltroFrecuencia{
    private Image imagen;
    private Dimension dim;
    private int radio;
    private int[][] puntos;
    
    public FiltroSelectivo(int radio, Dimension dim, int filtro[][]){
        super((int)dim.getWidth(),(int)dim.getHeight());
        this.puntos=filtro;
        this.radio=radio;
        this.dim=dim;
        this.imagen=null;
    }
    @Override
    public void crearFiltro() {
        int tamImagen = (int)dim.getWidth();
        for(int i=0; i<tamImagen;i++){
            for(int j=0; j<tamImagen;j++){
                getFiltroEspacial()[i][j] = new NumeroComplejo(1,1);
            }
        }
        
        for(int[] punto : this.puntos){
            int x = punto[0];
            int y = punto[1];
            int xx = tamImagen-x;
            int yy = tamImagen-y;
            
            for(int i=0;i<tamImagen;i++){
                for(int j=0;j<tamImagen;j++){
                    int u = -1*x+i;
                    int v = y-j;
                    
                    int uu = -1*xx+i;
                    int vv = yy-j;
                    
                    double r = Math.sqrt(Math.pow(u,2)+Math.pow(v,2));
                    double rr = Math.sqrt(Math.pow(uu,2)+Math.pow(vv,2));
                    
                    if(r<=this.radio){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(0,0);
                        
                    }
                    if(rr<=this.radio){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(0,0);
                    }
                }
            }
        }
        this.imagen = FiltroFrecuencia.toImageDeComplejo(super.getFiltroEspacial());
    }
    
    public Image getImage(){
        return imagen;
    }
}    

