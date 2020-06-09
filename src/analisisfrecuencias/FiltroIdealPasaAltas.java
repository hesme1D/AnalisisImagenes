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
public class FiltroIdealPasaAltas extends FiltroFrecuencia{

    private int radio;
    private Dimension dim;
    private Image imagen;
    
    public FiltroIdealPasaAltas(int radio, Dimension dim){
        super((int)dim.getWidth(),(int)dim.getHeight());
        this.radio=radio;
        this.dim=dim;
        this.imagen=null;
    }
    
    @Override
    public void crearFiltro() {
        int tamImagen = (int)dim.getWidth();
        for(int i=0; i<tamImagen;i++){
            for(int j=0; j<tamImagen;j++){
                int u = -1*(tamImagen/2)+i;
                int v = (tamImagen/2)-j;
                
                double r = Math.sqrt(Math.pow(u, 2)+Math.pow(v, 2));
                Color color;
                //Verificamos si r es menor al radio
                if(r>this.radio){
                    //Pintamos en blanco
                    color = new Color(255,255,255);
                    getFiltroEspacial()[i][j] = new NumeroComplejo(1, 1);
                } else{
                    color = new Color(0,0,0);
                    getFiltroEspacial()[i][j] = new NumeroComplejo(0, 0);
                }
            }
        }
        this.imagen = FiltroFrecuencia.toImageDeComplejo(super.getFiltroEspacial());
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
