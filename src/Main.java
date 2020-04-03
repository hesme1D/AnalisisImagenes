import analisisespacial.Convolucion;
import open.AbrirImagen;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import analisisespacial.FiltrosEspaciales;
import analisisespacial.Histogramas;
import analisisespacial.Suavizado;
import gui.JFrameContraste;
import gui.JFrameIluminacion;
import gui.JFrameSegmentacion;
import gui.JframeImagen;

public class Main {

    public static void main(String[] args) {

        Image imagen = AbrirImagen.openImage();
        JframeImagen io = new JframeImagen(imagen);
        Histogramas h1 = new Histogramas(imagen);
        h1.graficarHistogramas();
        
        //Image ruidoA = Suavizado.agregarRuidoAditivo(imagen, 30);
        //JframeImagen ira = new JframeImagen(ruidoA);
        //Histogramas hra = new Histogramas(ruidoA);
        //hra.graficarHistogramas();
        
        //Image ruidoS = Suavizado.agregarRuidoSustractivo(imagen, 5);
        //JframeImagen irs = new JframeImagen(ruidoS);
        //Histogramas hrs = new Histogramas(ruidoS);
        //hrs.graficarHistogramas();
        
        //Mezclado
        //Image ruidoAS = Suavizado.agregarRuidosMezclados(ruidoS, 5);
        //JframeImagen iras = new JframeImagen(ruidoAS);
        //Histogramas hras = new Histogramas(ruidoAS);
        //hras.graficarHistogramas();
        
        //int[][] mascara = new int[][]{{1,1,1},{1,0,1},{1,1,1}};
        //Image Suav = Suavizado.Suavizar(ruidoS, mascara);
        //JframeImagen is = new JframeImagen(Suav);
        //Histogramas his = new Histogramas(Suav);
        //his.graficarHistogramas();
        
        //Máscara para el enfoque
        int[][] mascaraEnfoque = new int[][]{{0,0,0,0,0},{0,0,-1,0,0},{0,-1,5,-1,0},{0,0,-1,0,0},{0,0,0,0,0}};
        Image ConvEnfoque = Convolucion.AplicarConvolucion(imagen, mascaraEnfoque, 1, 0);
        JframeImagen icE = new JframeImagen(ConvEnfoque);
        Histogramas hce = new Histogramas(ConvEnfoque);
        hce.graficarHistogramas();
        
        //Mascara de desenfoque
        int[][] mascaraDes = new int[][]{{1,1,1},{1,1,1},{1,1,1}};
        //int[][] mascaraDes = new int[][]{{0,0,0,0,0},{0,1,1,1,0},{0,1,1,1,0},{0,1,1,1,0},{0,0,0,0,0}};
        Image ConvDes = Convolucion.AplicarConvolucion(imagen, mascaraDes, 9, 0);
        JframeImagen icD = new JframeImagen(ConvDes);
        Histogramas hcd = new Histogramas(ConvDes);
        hcd.graficarHistogramas();
        
        //Mascara realzar bordes
        int[][] mascaraRealce = new int[][]{{0,0,0},{-1,1,0},{0,0,0}};
        Image ConvRes = Convolucion.AplicarConvolucion(imagen, mascaraRealce, 1, 0);
        JframeImagen icR = new JframeImagen(ConvRes);
        Histogramas hcr = new Histogramas(ConvRes);
        hcr.graficarHistogramas();
        
        //Mascara detectar bordes
        int[][] mascaraBordes = new int[][]{{0,1,0},{1,-4,1},{0,1,0}};
        Image ConvBorde = Convolucion.AplicarConvolucion(imagen, mascaraBordes, 1, 0);
        JframeImagen icB = new JframeImagen(ConvBorde);
        Histogramas hcb = new Histogramas(ConvBorde);
        hcb.graficarHistogramas();
        
        //Mascara Repujado
        int[][] mascaraRep = new int[][]{{-2,-1,0},{-1,1,1},{0,1,2}};
        Image ConvRep = Convolucion.AplicarConvolucion(imagen, mascaraRep, 1, 0);
        JframeImagen icRep = new JframeImagen(ConvRep);
        Histogramas hcre = new Histogramas(ConvRep);
        hcre.graficarHistogramas();
 
        
        //Generar imagen en grises
        //Image gris = FiltrosEspaciales.generarImagenGirs(imagen);
        //JframeImagen igris = new JframeImagen(gris);
        //Histogramas h2 = new Histogramas(gris);
        //h2.graficarHistogramaGrises();
        //Image e = FiltrosEspaciales.Ecualizar(gris);
        //JframeImagen frame2 = new JframeImagen(e);
        //Histogramas he = new Histogramas(e);
        //he.graficarHistogramaGrises();
        
        //Implementar un método interativo
        //int u = FiltrosEspaciales.metodoIterativo(h2.getHBlue());
        //Image bin = FiltrosEspaciales.Binarizar(gris, u);
        //JframeImagen bin1 = new JframeImagen(bin);
        //Histogramas h3 = new Histogramas(bin);
        //h3.graficarHistogramas();
        
        //Expansión lineal
        //Histogramas h = new Histogramas(imagen); 
        //h.graficarHistogramas();
        //JframeImagen fi = new JframeImagen(imagen);
        //Image contraste = FiltrosEspaciales.contraste(h, imagen);
        //JframeImagen fic = new JframeImagen(contraste);
        //Histogramas h1 = new Histogramas(contraste);
        //h1.graficarHistogramas();
        //Expansión Exponencial
        //Image exp = FiltrosEspaciales.Exponencial(imagen, 100);
        //JframeImagen frame = new JframeImagen(exp);
        //Histogramas h2 = new Histogramas(exp);
        //h2.graficarHistogramas();
        //Expansión Logaritmica
        //Image log = FiltrosEspaciales.Logaritmica(imagen);
        //JframeImagen frame2 = new JframeImagen(log);
        //Histogramas h3 = new Histogramas(log);
        //h3.graficarHistogramas();
        
        //Formula Propia
        //Image p = FiltrosEspaciales.Propia(imagen);
        //JframeImagen frame4 = new JframeImagen(p);
        //Histogramas h4 = new Histogramas(p);
        //h4.graficarHistogramas();
        
        //Binarización
        //Histogramas h = new Histogramas(imagen);
        //h.graficarHistogramas();
        //JframeImagen frame = new JframeImagen(imagen);
        //Image Bin = FiltrosEspaciales.Binarizar(imagen, 50);
        //JFrame frameB = new JframeImagen(Bin);
        //Histogramas h2 = new Histogramas(Bin);
        //h2.graficarHistogramas();
        
    


        //JFrameContraste frame = new JFrameContraste("Contraste", imagen);
        
        //JFrameIluminacion frame = new JFrameIluminacion("Iluminacion", imagen);
        //JFrameSegmentacion frame = new JFrameSegmentacion("Segmentacion",imagen);
        //Grises
        //Image imagenGrises = FiltrosEspaciales.generarImagenGirs(imagen);
        //JframeImagen frame2 = new JframeImagen(imagenGrises);
        //Negativa
        //Image imagenN = FiltrosEspaciales.generarImagenNegativa(imagen);
        //JframeImagen frame3 = new JframeImagen(imagenN);
        //Iluminada 
        //Image imagenIl = FiltrosEspaciales.iluminacion(imagen,60);
        //JframeImagen frame4 = new JframeImagen(imagenIl);
        //oscura
        //Image imagenS = FiltrosEspaciales.segmentarImagen(imagen, 100);
        //JframeImagen frame7 = new JframeImagen(imagenS);
        //Temperatura Calida
        //Image imagenTem = FiltrosEspaciales.temperatura(imagen,80);
        //JframeImagen frame5 = new JframeImagen(imagenTem);
        //Temperatura fría
        //Image imagenTem2 = FiltrosEspaciales.temperatura(imagen, -80);
        //JframeImagen frame6 = new JframeImagen(imagenTem2);
        //Contraste
        //Image imagenC = FiltrosEspaciales.contraste(imagen, 14);
        //JframeImagen frame4 = new JframeImagen(imagenC);
        //Histograma, imagen original
        //Histogramas hi = new Histogramas(imagen);
        //hi.graficarHistogramaGrises();
        //hi.graficarHistogramaAzul();
        //hi.graficarHistogramaRojo();
        //hi.graficarHistogramaVerde();
        //hi.graficarHistogramas();
        
        //Histogramas hi2 = new Histogramas(imagenIl);
        //hi2.graficarHistogramas();
        //Histogramas hi5 = new Histogramas(imagen);
        //hi5.graficarHistogramas();
        //Histogramas hi3 = new Histogramas(imagenTem);
        //hi3.graficarHistogramas();
        //Histogramas hi4 = new Histogramas(imagenTem2);
        //hi4.graficarHistogramas();
       
        System.out.println();

    }
}