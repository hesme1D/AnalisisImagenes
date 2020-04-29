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
public class Mascaras {
    //Diferencias de pixeles
    private double[][] diferenciaPixelesGx = {{0.0, 0.0, 0.0}, {0.0, 1.0, -1.0}, {0.0, 0.0, 0.0}};
    private double[][] diferenciaPixelesGy = {{0.0, -1.0, 0.0}, {0.0, 1.0, 0.0}, {0.0, 0.0, 0.0}};
    private double[][][] diferenciaPixeles = {this.diferenciaPixelesGx, this.diferenciaPixelesGy};
    // mascara de diferencia de pixeless separados
    private double[][] diferenciaPixelesSeparadosGx = {{0.0, 0.0, 0.0}, {1.0, 0.0, -1.0}, {0.0, 0.0, 0.0}};
    private double[][] diferenciaPixelesSeparadosGy = {{0.0, -1.0, 0.0}, {0.0, 0.0, 0.0}, {0.0, 1.0, 0.0}};
    private double[][][] diferenciaPixelesSeparados = {this.diferenciaPixelesSeparadosGx, this.diferenciaPixelesSeparadosGy};
    // mascara de operador prewitt
    private double[][] prewittGx = {{1.0, 0.0, -1.0}, {1.0, 0.0, -1.0}, {1.0, 0.0, -1.0}};
    private double[][] prewittGy = {{-1.0, -1.0, -1.0}, {0.0, 0.0, 0.0}, {1.0, 1.0, 1.0}};
    private double[][][] prewitt= {this.prewittGx, this.prewittGy};
    // mascara de operador Sobel
    private double[][] SobelGx = {{1.0, 0.0, -1.0}, {2.0, 0.0, -2.0}, {1.0, 0.0, -1.0}};
    private double[][] SobelGy = {{-1.0, -2.0, -1.0}, {0.0, 0.0, 0.0}, {1.0, 2.0, 1.0}};
    private double[][][] Sobel= {this.SobelGx, this.SobelGy};
    // mascara dde operador Roberts
    private double[][] robertsGx = {{0.0, 0.0, -1.0}, {0.0, 1.0, 0.0}, {0.0, 0.0, 0.0}};
    private double[][] robertsGy = {{-1.0, 0.0, 0.0}, {0.0, 1.0, 0.0}, {0.0, 0.0, 0.0}};
     private double[][][] roberts= {this.robertsGx, this.robertsGy};
      // creamos mascara de Laplace
    private double[][] laplace = {{0.0, 1.0, 0.0}, {1.0, -4.0, 1.0}, {0.0, 1.0, 0.0}};
    private double[][][] laplace1= {this.laplace};
      //mascaras de Kirch
    private double mascara1[][] = new double[][]{{-3,-3,5},{-3,0,5},{-3,-3,5}};
    private double mascara2[][] = new double[][]{{-3,5,5},{-3,0,5},{-3,-3,-3}};
    private double mascara3[][] = new double[][]{{5,5,5},{-3,0,-3},{-3,-3,-3}};
    private double mascara4[][] = new double[][]{{5,5,-3},{5,0,-3},{-3,-3,-3}}; 
    private double mascara5[][] = new double[][]{{5,-3,-3},{5,0,-3},{5,-3,-3}};
    private double mascara6[][] = new double[][]{{-3,-3,-3},{5,0,-3},{5,5,-3}};
    private double mascara7[][] = new double[][]{{-3,-3,-3},{-3,0,-3},{5,5,5}};
    private double mascara8[][] = new double[][]{{-3,-3,-3},{-3,0,5},{-3,5,5}};
    private double arregloKirch[][][] = new double[][][]{mascara1, mascara2, mascara3, mascara4, mascara5, mascara6, mascara7, mascara8};
    
    ///Arreglo de todas las mascaras
    private double[][][][] mascaras= {arregloKirch,diferenciaPixeles,diferenciaPixelesSeparados,prewitt,Sobel,roberts,laplace1};
  
   
    //private Image imagen;
    
    //public Mascaras(Image io){
   //     this.imagen=io;
   // }
    
    public static Image aplicarMascaras(Image io, int div, int cM){
        double[][] k = new double[3][3];
        BufferedImage bi = AbrirImagen.toBufferedImage(io);
        BufferedImage bnuevo = new BufferedImage(bi.getWidth(),bi.getHeight(),BufferedImage.TYPE_INT_RGB);
        
        //Recorrer buffer
        for(int x=0;x<bi.getWidth();x++){
            for(int y=0;y<bi.getHeight();y++){
                double muestra[][] = extraerMuestra(x,y,bi);
                if(muestra!=null){
                    //Color color = colorMascaras(muestra,div, cM);
                    //bnuevo.setRGB(x, y, color.getRGB());
                }else{
                //int rgb = calcularNuevoTono(x,y,bi,mascara);
                    bnuevo.setRGB(x, y, new Color(255,255,255).getRGB());
                }
            }
        }
        return AbrirImagen.toImage(bnuevo);
    }
    
    private Color colorMascaras(double[][] muestra, int divisor, int casoMascara) {
        
        int mR = 0, mG = 0, mB = 0;
        int r,g,b;
                 for(int i=0;i<this.mascaras[casoMascara].length;i++){
                    //Color color = Convolucion.Convolucion(this.mascaras[casoMascara][i], muestra, divisor);
                    //r = color.getRed();
                    //g = color.getGreen();
                    //b = color.getBlue();
                    //if(r>mR)mR=r;
                    //if(g>mG)mG=g;
                    //if(b>mB)mB=b;
                    
             }
        //return new Color(validarLimites(r),validarLimites(g),validarLimites(b));     
        return null;
    }
    
     
      private static double[][] extraerMuestra(int x, int y, BufferedImage bi) {
        double matriz[][] = new double[3][3];
       int xx=0;
        int yy=0;
        try {
         for(int i=x-(matriz[0].length-1)/2;i<=x+(matriz[0].length-1)/2;i++){
            for(int j=y-(matriz[0].length-1)/2;j<=y+(matriz[0].length-1)/2;j++){
            matriz[xx][yy] = bi.getRGB(i,j);
            yy++;
            }
            yy=0;
            xx++;
         }
          return matriz;
        } catch (Exception e) {
            return null;
        } 
    }
}
