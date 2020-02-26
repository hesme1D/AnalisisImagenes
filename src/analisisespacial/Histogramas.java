package analisisespacial;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;

import open.AbrirImagen;

public class Histogramas{

    private double hRojo[];
    private double hVerde[];
    private double hAzul[];
    private double hGrises[];


    public Histogramas(Image imagen){
        this.hRojo = new double[256];
        this.hVerde = new double[256];
        this.hAzul = new double[256];
        this.hGrises = new double[256];
        calcularHistogramas(imagen);
    }

    private void calcularHistogramas(Image imagen){
        // en solo corrimiento del buffer se calcula el histograma de frecuencias de color
        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        Color aux = null;
        for(int x=0; x<bi.getWidth();x++)
            for(int y=0; y<bi.getHeight();y++){
                aux = new Color(bi.getRGB(x, y));
                this.hRojo[aux.getRed()]++;
                this.hVerde[aux.getGreen()]++;
                this.hAzul[aux.getBlue()]++;
                int prom = (aux.getRed()+aux.getGreen()+aux.getBlue())/3;
                this.hGrises[prom]++;
            }
    } 
   
    public void graficarHistogramas(){
        Grafica aux = new Grafica("Tono","Intesidad","Frecuencias");
        aux.agregarSerie("Rojos",this.hRojo);
        aux.agregarSerie("Azules",this.hAzul);
        aux.agregarSerie("Verdes",this.hVerde);
        aux.crearYmostrarGrafica();
               
    } 
    public void graficarHistogramaRojo(){
        Grafica aux = new Grafica("Tono","Intesidad","Frecuencias");
        aux.agregarSerie("Rojos",this.hRojo);
        aux.crearYmostrarGrafica();
        
    } 
    public void graficarHistogramaVerde(){
        Grafica aux = new Grafica("Tono","Intesidad","Frecuencias");
        aux.agregarSerie("Verdes",this.hVerde);
        aux.crearYmostrarGrafica();
        
    } 
    public void graficarHistogramaAzul(){
        Grafica aux = new Grafica("Tono","Intesidad","Frecuencias");
        aux.agregarSerie("Azules",this.hAzul);
        aux.crearYmostrarGrafica();
        
    } 

    public void graficarHistogramaGrises(){
        Grafica aux = new Grafica("Tono","Intesidad","Frecuencias");
        aux.agregarSerie("Grises",this.hGrises);
        aux.crearYmostrarGrafica();
        
    } 


}