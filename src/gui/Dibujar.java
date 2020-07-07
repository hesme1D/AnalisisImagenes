/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import open.AbrirImagen;

/**
 *
 * @author esmec
 */
public class Dibujar {
    public static void Circulo(Image imagen, int radioPix){
        BufferedImage bi = AbrirImagen.toBufferedImage(imagen);
        int centroImagen[] = {imagen.getWidth(null)/2,imagen.getHeight(null)/2};
        
        for(int i=0;i<360;i++){
            int x = (int)(radioPix*Math.cos((i*2*Math.PI)/360)+centroImagen[0]);
            int y = (int)(radioPix*Math.sin((i*2*Math.PI)/360)-centroImagen[1]);
            
            bi.setRGB(x, y, Color.CYAN.getRGB());
            bi.setRGB(x-1, y, Color.CYAN.getRGB());
            bi.setRGB(x-1, y-1, Color.CYAN.getRGB());
            bi.setRGB(x, y-1, Color.CYAN.getRGB());
            bi.setRGB(x+1, y-1, Color.CYAN.getRGB());
            bi.setRGB(x+1, y, Color.CYAN.getRGB());
            bi.setRGB(x+1, y+1, Color.CYAN.getRGB());
            bi.setRGB(x, y+1, Color.CYAN.getRGB());
            bi.setRGB(x-1, y+1, Color.CYAN.getRGB());
        }
        JFrameIm f = new JFrameIm(AbrirImagen.toBufferedImage(bi));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
