/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisisfrecuencias;
import analisisespacial.Convolucion;
import analisisespacial.FiltrosEspaciales;
import analisisfrecuencias.HerramientasColor.CanalColor;
import analisisfrecuencias.FFT;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author esmec
 */
public class Gestor {
    private BufferedImage io;
    public Map<HerramientasColor.CanalColor, NumeroComplejo[][]> representacionEspacial;
    public Map<HerramientasColor.CanalColor, NumeroComplejo[][]> representacionFrecuencias;
    
    public Gestor(BufferedImage io){
        this.io=io;
        this.representacionEspacial= new HashMap<HerramientasColor.CanalColor, NumeroComplejo[][]>();
        this.representacionFrecuencias= new HashMap<HerramientasColor.CanalColor, NumeroComplejo[][]>();
        
        for(CanalColor color : HerramientasColor.CanalColor.values()){
            representacionEspacial.put(color, obtenerDatosPorCanal(io,color));
        }
    }
    private NumeroComplejo[][] obtenerDatosPorCanal(BufferedImage io, CanalColor color){
        NumeroComplejo[][] aux = new NumeroComplejo[io.getWidth()][io.getHeight()];
        //Obtenemos los datos por canal
        for(int y=0; y<io.getHeight();y++){
            for(int x=0; x<io.getWidth();x++){
                aux[x][y]= new NumeroComplejo(HerramientasColor.obtenerValorPorCanal(io.getRGB(x, y), color),0);
            }
        }
        return aux;
    }
//    public void aplicarFiltro(NumeroComplejo[][] filtro){
//        //Recorrer el filtro
//        for(int x=0; x < this.io.getWidth(); x++){
//            for(int y=0; y<this.io.getHeight();y++){
//                //Obtener el color RGB de la parte de frecuencias
//                if(filtro[x][y].getParteReal()<1){
//                    int rgb = obtenerPixelDominioFrecuencias(x,y,true);
//                    Color aux = new Color(rgb);
//                    int r = (int)(aux.getRed()*filtro[x][y].getParteReal());
//                    int g = (int)(aux.getGreen()*filtro[x][y].getParteReal());
//                    int b = (int)(aux.getBlue()*filtro[x][y].getParteReal());
//                    aux = new Color(r,g,b);
//                    setPixelDominioFrecuencias(x,y,true,aux.getRGB());
//                }
//            }
//        }
//    }
    
    public BufferedImage obtenerImagenFrecuencias(boolean reAjustarCuadrante){
     //Obtenemos las dimensiones
     int anchoImagen = this.io.getWidth();
     int altoImagen = this.io.getHeight();
     BufferedImage aux = new BufferedImage(anchoImagen, altoImagen, BufferedImage.TYPE_INT_ARGB);
     
     FFT fft = new FFT();
     
     //Construir el mapeo de representacion en frecuencia utilizando FFT
     for(CanalColor canal : HerramientasColor.CanalColor.values()){
         NumeroComplejo[][] datos = this.representacionEspacial.get(canal);
         NumeroComplejo[][] transformada = fft.calcularFT(datos, false);
         representacionFrecuencias.put(canal, transformada);
         //Crear la imagen del espectro
         for(int y=0; y<aux.getHeight();y++){
             for(int x=0; x<aux.getWidth();x++){
                 //Modificamos la posicion de los cuadrantes
                 int ejeX = reAjustarCuadrante ? (x+(anchoImagen / 2)) % anchoImagen : x;
                 int ejeY = reAjustarCuadrante ? (y+(altoImagen /2)) % altoImagen : y;
                 //Setear la info de la imagen
                 //El que se encuentre en la imagen
                 int color1 = aux.getRGB(x, y);
                 int color2 = obtenerColorRealDeFrecuencia(ejeX, ejeY, transformada, canal);
                 int rgb = HerramientasColor.acumularColor(color1, color2);
                 aux.setRGB(x, y, rgb);
                }
            }
        }
     return aux;
    }
//    public BufferedImage obtenerImagenEspacial(){
//        //Obtenemos las dimensiones 
//        int anchoImagen = this.io.getWidth();
//        int altoImagen = this.io.getHeight();
//        BufferedImage aux = new BufferedImage(anchoImagen, altoImagen, BufferedImage.TYPE_INT_ARGB);
//        
//        FFT fft = new FFT();
//        
//        //Construir el mapeo de representación en frecuencias utilizando fft
//        for(CanalColor canal : HerramientasColor.CanalColor.values()){
//            NumeroComplejo[][] datos = this.representacionFrecuencias.get(canal);
//            NumeroComplejo[][] transformadaInv = fft.calcularFT(datos, true);
//            representacionEspacial.put(canal, transformadaInv);
//            
//            //Crear la imagen del espectro
//            for(int y=0; y<aux.getHeight(); y++){
//                for(int x=0; x<aux.getWidth();x++){
//                    int color = (int) Math.abs(transformadaInv[x][y].getParteReal());
//                    color = FiltrosEspaciales.validarLimites(color);
//                    color = HerramientasColor.obtenerRGBPorCanal(color, canal);
//                    
//                    int rgb = HerramientasColor.acumularColor(aux.getRGB(x, y), color);
//                    aux.setRGB(x, y, rgb);
//                }
//            }
//        }
//        return aux;
//    }
    
    private int obtenerColorRealDeFrecuencia(int ejeX, int ejeY, NumeroComplejo[][] transformada, CanalColor canal){
        int color = (int) Math.abs(transformada[ejeX][ejeY].getParteReal());
        color= Convolucion.validarValor(color);
        color = HerramientasColor.obtenerRGBPorCanal(color, canal);
        return color;
    }
    
//    private int obtenerPixelDominioFrecuencias(int x, int y, boolean encuadre){
//        //Obtenemos las dimensiones
//        int anchoImagen = this.io.getWidth();
//        int altoImagen = this.io.getHeight();
//        //Modificamos la posicion de los cuadrantes
//        int ejeX = encuadre ? (x+(anchoImagen /2)) % anchoImagen : x;
//        int ejeY = encuadre ? (y+(altoImagen /2)) % altoImagen : y;
//        
//        //Acumulamos
//        int valorColor =0;
//        for(CanalColor canal : CanalColor.values()){
//            NumeroComplejo[][] aux = representacionFrecuencias.get(canal);
//            valorColor += obtenerColorRealDeFrecuencia(ejeX, ejeY, aux, canal);
//        }
//        return valorColor;
//    }
    
//    private void setPixelDominioFrecuencias(int x, int y, boolean encuadre, int color){
//        //Obtenemos las dimensiones
//        int anchoImagen = this.io.getWidth();
//        int altoImagen = this.io.getHeight();
//        //Modificamos la posicion de los cuadrantes
//        int ejeX = encuadre ? (x+(anchoImagen /2)) % anchoImagen : x;
//        int ejeY = encuadre ? (y+(altoImagen /2)) % altoImagen : y;
//        //Recores por canal de color
//        for(CanalColor canal: CanalColor.values()){
//            NumeroComplejo[][] datos = representacionFrecuencias.get(canal);
//            int nuevo = HerramientasColor.obtenerValorPorCanal(color, canal);
//            
//            datos[ejeX][ejeY] = new NumeroComplejo(nuevo,nuevo);
//        }
//    }
}
