import open.AbrirImagen;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import analisisespacial.FiltrosEspaciales;
import analisisespacial.Histogramas;
import gui.JFrameContraste;
import gui.JFrameIluminacion;
import gui.JFrameSegmentacion;
import gui.JframeImagen;

public class Main {

    public static void main(String[] args) {

        Image imagen = AbrirImagen.openImage();
        
        //JFrameContraste frame = new JFrameContraste("Contraste", imagen);
        
        JFrameIluminacion frame = new JFrameIluminacion("Iluminacion", imagen);
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
        Histogramas hi = new Histogramas(imagen);
        //hi.graficarHistogramaGrises();
        //hi.graficarHistogramaAzul();
        //hi.graficarHistogramaRojo();
        //hi.graficarHistogramaVerde();
        hi.graficarHistogramas();
        
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