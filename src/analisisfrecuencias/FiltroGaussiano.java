/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisisfrecuencias;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
/**
 *
 * @author esmec
 */
public class FiltroGaussiano extends FiltroFrecuencia{
    private int orden;
    private int radio;
    private Dimension dim;
    private Image imagen;
    private boolean pasaAltas;
    
    public FiltroGaussiano(Dimension dim, int radio, boolean pasaAltas){
        super((int)dim.getWidth(),(int)dim.getHeight());
        this.radio=radio;
        this.dim=dim;
        this.pasaAltas=pasaAltas;
        this.imagen=null;
    }
    @Override
    public void crearFiltro() {
        int tamImagen = (int)dim.getWidth();
        int centro = (tamImagen/2);
        double valor;
        for(int i=0; i<tamImagen;i++){
            for(int j=0; j<tamImagen;j++){
                int u = i- centro;
                int v = j- centro;
                
                double DistanceC = Math.pow(u, 2)+Math.pow(v, 2);
                
                if(!this.pasaAltas) valor = Math.exp(-DistanceC/(2*radio*radio));
                else valor = 1-Math.exp(-DistanceC/(2*radio*radio));
                
                getFiltroEspacial()[i][j] = new NumeroComplejo(valor,valor);
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
