/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisisespacial;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import open.AbrirImagen;
/**
 *
 * @author esmec
 */
public class FiltrosEspaciales {
    public static Image generarImagenGirs(Image io){
        BufferedImage bi = AbrirImagen.toBufferedImage(io);
        Color color;
        for(int x=0; x<bi.getWidth();x++)
            for(int y=0; y<bi.getHeight();y++){
                color = new Color(bi.getRGB(x, y));
                int prom =(color.getRed()+color.getGreen()+color.getBlue())/3;
                color = new Color(prom, prom, prom);
                bi.setRGB(x, y, color.getRGB());
            }
        return AbrirImagen.toImage(bi);
    }
    
    public static Image generarImagenNegativa(Image io){
        BufferedImage bi = AbrirImagen.toBufferedImage(io);
        Color color;
        for(int x=0; x<bi.getWidth();x++)
            for(int y=0; y<bi.getHeight();y++){
                color = new Color(bi.getRGB(x, y));
                //int prom =(color.getRed()+color.getGreen()+color.getBlue())/3;
                color = new Color(255-color.getRed(), 255-color.getGreen(), 255-color.getBlue());
                bi.setRGB(x, y, color.getRGB());
            }
        return AbrirImagen.toImage(bi);
    }
    //Crear método para controlar la iluminación
    public static Image iluminacion(Image io, int alpha){
        BufferedImage bi = AbrirImagen.toBufferedImage(io);
        Color color;
        for(int x=0; x<bi.getWidth();x++)
            for(int y=0; y<bi.getHeight();y++){
                color = new Color(bi.getRGB(x, y));
                //Se debe condiderar que el grado de iluminación no exceda o se quede
                //por debajo de los límites establecidos para el color
                int r = color.getRed() + alpha;
                int g = color.getGreen() + alpha;
                int b = color.getBlue() + alpha;
                color = new Color(validarLimites(r),validarLimites(g),validarLimites(b));
                bi.setRGB(x, y, color.getRGB());
            }
        return AbrirImagen.toImage(bi);
    }
    
    public static Image iluminacion(Image io, int u1, int u2){
        BufferedImage bi = AbrirImagen.toBufferedImage(io);
        Color color, colorFondo;
        colorFondo = new Color(255,255,255);
        for(int x=0; x<bi.getWidth();x++)
            for(int y=0; y<bi.getHeight();y++){
                color = new Color(bi.getRGB(x, y));
                //Se debe condiderar que el grado de iluminación no exceda o se quede
                //por debajo de los límites establecidos para el color
                int r = color.getRed() + u1-u2;
                int g = color.getGreen() + u1-u2;
                int b = color.getBlue() + u1-u2;
                color = new Color(validarLimites(r),validarLimites(g),validarLimites(b));
                bi.setRGB(x, y, color.getRGB());
            }
        return AbrirImagen.toImage(bi);
    }
    
    public static int validarLimites(int aux){
        if(aux<0) return 0;
        if(aux>255) return 255;
        return aux;
    }
    public static int obtenerMin(double[] h){

        for(int x=0; x<h.length;x++){
            if(h[x]!=0) return x;
        }
     return -1;
    }

    public static int obtenerMax(double[] h){

        for(int x=h.length-1; x>=0;x--){
            if(h[x]!=0) return x;
        }
     return -1;
    }
    //Crear un metodo para controlar la temperatura
    public static Image temperatura(Image io, int alpha){
        BufferedImage bi = AbrirImagen.toBufferedImage(io);
        Color color;
        for(int x=0; x<bi.getWidth();x++)
            for(int y=0; y<bi.getHeight();y++){
                color = new Color(bi.getRGB(x, y));
                //Para hacer una imagen más calida es necesario aumentar el color rojo
                //para hacerla más fría, se debe agregar más azul.
                int r = color.getRed() + alpha;
                int g = color.getGreen() + alpha;
                int b = color.getBlue() - alpha;
                color = new Color(validarLimites(r),validarLimites(g),validarLimites(b));
                bi.setRGB(x, y, color.getRGB());
            }
        return AbrirImagen.toImage(bi);
    }
    
    public static Image segmentarImagen(Image imagen, int umbral){

        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        Color color, colorFondo;
        colorFondo = new Color(255,255,255);
        for(int x=0; x<bi.getWidth();x++)
            for(int y=0; y<bi.getHeight();y++){
            color = new Color(bi.getRGB(x, y));
            int prom = (color.getRed()+ color.getGreen()+color.getBlue())/3;
            if (prom>umbral){
                bi.setRGB(x,y,colorFondo.getRGB());
            }
                       
        }
        return AbrirImagen.toImage(bi);
    }

    public static Image segmentarImagen(Image imagen, int u1, int u2){
        // TODO: garantizar  que el u2>u1
        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        Color color, colorFondo;
        colorFondo = new Color(255,255,255);
        for(int x=0; x<bi.getWidth();x++)
            for(int y=0; y<bi.getHeight();y++){
            color = new Color(bi.getRGB(x, y));
            int prom = (color.getRed()+ color.getGreen()+color.getBlue())/3;
            if (!(prom>= u1 && prom<=u2)){
                bi.setRGB(x,y,colorFondo.getRGB());
            }
                       
        }
        return AbrirImagen.toImage(bi);
    }
    
    public static Image Binarizar(Image imagen, int u1){
        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        Color color, colorFondo, colorObj;
        colorFondo = new Color(255,255,255);
        colorObj = new Color(0,0,0);
        for(int x=0;x<bi.getWidth();x++){
            for(int y=0;y<bi.getHeight();y++){
                //Se genera la imagen en tonos grises
                color = new Color(bi.getRGB(x, y));
                int prom =(color.getRed()+color.getGreen()+color.getBlue())/3;
                color = new Color(prom, prom, prom);
                if(prom>u1){
                    bi.setRGB(x, y, colorFondo.getRGB());
                }else if(prom<u1){
                    bi.setRGB(x, y, colorObj.getRGB());
                }
            }
        }
        return AbrirImagen.toImage(bi);
    }
    //Contraste Expansión LINEAL
//    public static Image contraste(Image io, int u1, int u2){
//        BufferedImage bi = AbrirImagen.toBufferedImage(io);
//        Color color;
//        for(int x=0; x<bi.getWidth();x++)
//            for(int y=0; y<bi.getHeight();y++){
//                color = new Color(bi.getRGB(x, y));
//                //Se debe condiderar que el grado de iluminación no exceda o se quede
//                //por debajo de los límites establecidos para el color
//                int r = color.getRed() + u1-u2;
//                int g = color.getGreen() + u1-u2;
//                int b = color.getBlue() + u1- u2;
//                int valorFinalPixel =0;
//                int prom = (r + g + b)/3;
//                //No se como acomodar la formula para que funcione
//                valorFinalPixel = (int)((255/(u1/u2))*(prom - u2));
//                //color = new Color(validarLimites(r),validarLimites(g),validarLimites(b));
//                bi.setRGB(x, y, color.getRGB());
//            }
//        return AbrirImagen.toImage(bi);
//    }
    //Lineal
    public static Image contraste(Histogramas h, Image imagen){
        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        Color color;
        for(int x=0; x<bi.getWidth();x++){
            for(int y=0; y<bi.getHeight();y++){
                color = new Color(bi.getRGB(x, y));
                int r = (color.getRed()-h.getMinR())*(255/h.getMaxR()-h.getMinR());
                int g = (color.getGreen()-h.getMinG())*(255/h.getMaxG()-h.getMinG());
                int b = (color.getBlue()-h.getMinB())*(255/h.getMaxB()-h.getMinB());
                color = new Color(validarLimites(r),
                validarLimites(g),
                validarLimites(b));
                bi.setRGB(x,y,color.getRGB());
            }
        }
        return AbrirImagen.toImage(bi);
    }
    
    public static Image Exponencial(Image imagen, int z){
        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        Color color;
        for(int x=0;x<bi.getWidth();x++){
            for(int y=0;y<bi.getHeight();y++){
                color = new Color(bi.getRGB(x, y));
                int r = (int)(Math.pow(1+z, color.getRed()/z));
                int g = (int)(Math.pow(1+z, color.getGreen()/z));
                int b = (int)(Math.pow(1+z, color.getBlue()/z));
                
                color = new Color(validarLimites(r),
                validarLimites(g),
                validarLimites(b));
                bi.setRGB(x, y, color.getRGB());
            }
        }
        return AbrirImagen.toImage(bi);
    }
    
    public static Image Logaritmica(Image imagen){
        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        Color color;
        for(int x=0;x<bi.getWidth();x++){
            for(int y=0;y<bi.getHeight();y++){
                color = new Color(bi.getRGB(x, y));
                int r = (int)((255*Math.log(1+color.getRed()))/(Math.log(1+255)));
                int g = (int)((255*Math.log(1+color.getGreen()))/(Math.log(1+255)));
                int b = (int)((255*Math.log(1+color.getBlue()))/(Math.log(1+255)));
                
                color = new Color(r,g,b);
                bi.setRGB(x, y, color.getRGB());
            }
        }
        return AbrirImagen.toBufferedImage(bi);
    }
    
    public static Image Propia(Image imagen){
        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        Color color;
        for(int x=0; x<bi.getWidth();x++){
            for(int y=0; y<bi.getHeight();y++){
                color = new Color(bi.getRGB(x, y));
                int r = (int)(color.getRed()*25/100);
                int g = (int)(color.getGreen()*25/100);
                int b = (int)(color.getBlue()*25/100);
                color = new Color(r,g,b);
                bi.setRGB(x, y, color.getRGB());
            }
        }
        return AbrirImagen.toBufferedImage(bi);
    }
    
    //Ecualizar
    public static Image Ecualizar(Image imagen){
        
        int nxm = imagen.getWidth(null)*imagen.getHeight(null);
        Histogramas h = new Histogramas(imagen);
        double[] ho = h.getHRed();
        double[] daf = new double[256];
        int[] nt = new int[256];
        daf[0] = (int)ho[0];
        nt[0] = (int)Math.round((daf[0]/nxm)*255);
        //Recorremos el histograma para acumular
        for(int x=1; x<ho.length;x++){
            daf[x] = (int)(ho[x]+daf[x-1]);
            double aux = daf[x]/nxm;
            int tmp = (int) Math.round(aux * 255);
            nt[x] = tmp;
        }
        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        Color color;
        for(int x=0; x<bi.getWidth();x++){
            for(int y=0; y<bi.getHeight();y++){
                color = new Color(bi.getRGB(x, y));
                int t = color.getRed();
                int t2 = nt[t];
                color = new Color(t2,t2,t2);
                bi.setRGB(x, y, color.getRGB());
            }
        }
        return AbrirImagen.toImage(bi);
    }
    
    //Iterativo.
    public static int metodoIterativo(double[] histograma){
        int ui = calcularUmbralInicial(histograma);
        int uNuevo=0;
        System.out.println(ui);
        do{
        uNuevo = ui;
        ui = reajustarUmbral(ui,histograma);
        System.out.println(ui);
        }while(uNuevo!=ui);
        
        return ui;
    }
    
    private static int calcularUmbralInicial(double[] histograma) {
        int numPixels = 0;
        int suma = 0;
        for(int x=0;x<histograma.length;x++){
        numPixels+=histograma[x];
        suma+=histograma[x]*x;
        }
        return (int)(suma/numPixels);
    }

    private static int reajustarUmbral(int ui, double[] histograma) {
       int u1,u2;
       int a1=0,a2=0,n1=0,n2=0;
       a1+=histograma[0];
       for(int x=1;x<ui;x++){
        a1+=histograma[x]*x;
        n1+=histograma[x];
       }
       
        for(int y=ui;y<=255;y++){
        a2+=histograma[y]*y;
        n2+=histograma[y];
       }
        if (n1==0 || n2==0) return 0;
        u1 = a1/n1;
        u2 = a2/n2;
       return (int)((u1+u2)/2);
    }
}
