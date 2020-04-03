package analisisespacial;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;
import org.jfree.chart.plot.XYPlot;

import open.AbrirImagen;

public class Histogramas{

    private double hRojo[];
    private double hVerde[];
    private double hAzul[];
    private double hGrises[];
    private Integer minR,maxR;
    private Integer minG,maxG;
    private Integer minB,maxB;

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
        calcularMinimosYMaximos();
    } 
   
    public void graficarHistogramas(){
        Grafica aux = new Grafica("Tono","Intesidad","Frecuencias");
        aux.agregarSerie("Rojos",this.hRojo);
        aux.agregarSerie("Azules",this.hAzul);
        aux.agregarSerie("Verdes",this.hVerde);
        
        aux.crearGrafica();
        
        XYPlot plot = aux.getGrafica().getXYPlot();
        plot.getRenderer().setSeriesPaint(0, new Color(Color.RED.getRGB()));
        plot.getRenderer().setSeriesPaint(1, new Color(Color.BLUE.getRGB()));
        plot.getRenderer().setSeriesPaint(2, new Color(Color.GREEN.getRGB()));
        
        aux.MostrarGrafica();
    } 
    public void graficarHistogramaRojo(){
        Grafica aux = new Grafica("Tono","Intesidad","Frecuencias");
        aux.agregarSerie("Rojos",this.hRojo);
        aux.crearGrafica();
        XYPlot plot = aux.getGrafica().getXYPlot();
        plot.getRenderer().setSeriesPaint(0, new Color(Color.RED.getRGB()));
        aux.MostrarGrafica();
    } 
    public void graficarHistogramaVerde(){
        Grafica aux = new Grafica("Tono","Intesidad","Frecuencias");
        aux.agregarSerie("Verdes",this.hVerde);
        aux.crearGrafica();
        XYPlot plot = aux.getGrafica().getXYPlot();
        plot.getRenderer().setSeriesPaint(0, new Color(Color.GREEN.getRGB()));
        aux.MostrarGrafica();
    } 
    public void graficarHistogramaAzul(){
        Grafica aux = new Grafica("Tono","Intesidad","Frecuencias");
        aux.agregarSerie("Azules",this.hAzul);
        aux.crearGrafica();
        XYPlot plot = aux.getGrafica().getXYPlot();
        plot.getRenderer().setSeriesPaint(0, new Color(Color.BLUE.getRGB()));
        aux.MostrarGrafica();
    } 

    public void graficarHistogramaGrises(){
        Grafica aux = new Grafica("Tono","Intesidad","Frecuencias");
        aux.agregarSerie("Grises",this.hGrises);
        aux.crearGrafica();
        XYPlot plot = aux.getGrafica().getXYPlot();
        plot.getRenderer().setSeriesPaint(0, new Color(Color.DARK_GRAY.getRGB()));
        aux.MostrarGrafica();
    }
    
    private void calcularMinimosYMaximos(){
    this.minR = -1;
    this.minG = -1;
    this.minB = -1;
    this.maxR = 256;
    this.maxG = 256;
    this.maxB = 256;

    for(int t1 = 0, t2= hRojo.length-1; minR==-1 || maxR==256 ;t1++,t2--){
        if(hRojo[t1]!=0 && minR ==-1){
            minR = t1;
        }
        if(hRojo[t2]!=0 && maxR==256){
            maxR = t2;
        }
       
    }

    for(int t1 = 0, t2= hVerde.length-1; minG==-1 || maxG==256 ;t1++,t2--){
        if(hVerde[t1]!=0 && minG ==-1){
            minG = t1;
        }
        if(hVerde[t2]!=0 && maxG==256){
            maxG = t2;
        }
       
    }

    for(int t1 = 0, t2= hAzul.length-1; minB==-1 || maxB==256 ;t1++,t2--){
        if(hAzul[t1]!=0 && minB ==-1){
            minB = t1;
        }
        if(hAzul[t2]!=0 && maxB==256){
            maxB = t2;
        }
       
    }
    
   System.out.println();

 }
  
    public double[] getHRed(){
        return this.hRojo;
    }
    public double[] getHBlue(){
        return this.hAzul;
    }
    public double[] getGreen(){
        return this.hVerde;
    }
    public int getMinR(){
        return this.minR;
    }
    public int getMaxR(){
        return this.maxR;
    }

    public int getMinG(){
        return this.minG;
    }
    public int getMaxG(){
        return this.maxG;
    }

    public int getMinB(){
        return this.minB;
    }
    public int getMaxB(){
        return this.maxB;
    }
}