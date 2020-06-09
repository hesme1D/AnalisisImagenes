/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisisfrecuencias;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
/**
 *
 * @author esmec
 */
public class FiltroButterworthPasaBajas extends FiltroFrecuencia{
    private double radio;
    private Dimension dim;
    private double n;
    private Image imagen;
    
    public FiltroButterworthPasaBajas(Dimension dim,double radio, double n){
        super((int)dim.getWidth(),(int)dim.getHeight());
        this.radio=radio;
        this.dim=dim;
        this.n=n;
        this.imagen=null;
    }
    @Override
    public void crearFiltro() {
        int tamImagen =(int)dim.getWidth();
        for(int i=0;i<tamImagen;i++){
            for(int j=0;j<tamImagen;j++){
                int u = -1*(tamImagen/2)+i;
                int v = (tamImagen/2)-j;
                
                double r= Math.sqrt(Math.pow(u, 2)+Math.pow(v, 2));
                double res = 1/(1+Math.pow(r/this.radio, 2*this.n));
                //int res2 = (int)(res*255);
                
                //Color color = new Color(res2,res2,res2);
                getFiltroEspacial()[i][j] = new NumeroComplejo(res,res);
            }
        }
        this.imagen=FiltroFrecuencia.toImageDeComplejo(super.getFiltroEspacial());
    }
    
     public void modificarFiltro(int radio){
      this.radio = radio;
      crearFiltro();
    }

    /**
     * @return the imagen
     */
    public Image getImagen() {
        return imagen;
    }
}
