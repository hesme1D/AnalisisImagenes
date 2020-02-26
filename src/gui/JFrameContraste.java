/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import javax.swing.JSlider;

import gui.listeners.ContrasteListener;
import java.awt.BorderLayout;
import open.AbrirImagen;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.GridLayout;
import javax.swing.JPanel;
/**
 *
 * @author esmec
 */
public class JFrameContraste extends JFrame{
    private JSlider sliderU1, SliderU2;
    private JLabel labelImagen;
    private JButton btnAbrir;
    private Image imagenContrastada;
    
    public JFrameContraste(String title, Image io){
        this.setTitle(title);
        int ancho = io.getWidth(null)/2;
        int alto = io.getHeight(null)/2;
        this.setSize(ancho, alto);
        this.imagenContrastada = AbrirImagen.toBufferedImage(io);
        initcomponents();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void initcomponents(){
        this.setLayout(new BorderLayout());
        this.labelImagen = new JLabel(new ImageIcon(this.imagenContrastada));
        this.add(this.labelImagen,BorderLayout.CENTER);
        //Slider 1
        this.sliderU1 = new JSlider();
        this.sliderU1.setMinimum(0);
        this.sliderU1.setMaximum(255);
        this.sliderU1.setValue(0);
        this.sliderU1.setPaintLabels(true);
        this.sliderU1.setPaintTicks(true);
        this.sliderU1.setMinorTickSpacing(1);
        this.sliderU1.setMajorTickSpacing(25);
        //Slider 2
        this.SliderU2 = new JSlider();
        this.SliderU2.setMinimum(0);
        this.SliderU2.setMaximum(255);
        this.SliderU2.setValue(255);
        this.SliderU2.setPaintLabels(true);
        this.SliderU2.setPaintTicks(true);
        this.SliderU2.setMinorTickSpacing(1);
        this.SliderU2.setMajorTickSpacing(25);
        
        //Modificar el escuchador de los Slider's
        JPanel panel = new JPanel(new GridLayout(3,1));
        panel.add(this.sliderU1);
        panel.add(this.SliderU2);
        
        this.btnAbrir = new JButton("Contrastar");
        ContrasteListener clis = new ContrasteListener(this);
        this.btnAbrir.addActionListener(clis);
        panel.add(this.btnAbrir);
        this.add(panel,BorderLayout.SOUTH);
    }

    public JSlider getSliderU1() {
        return sliderU1;
    }

    public JSlider getSliderU2() {
        return SliderU2;
    }

    public JLabel getLabelImagen() {
        return labelImagen;
    }

    public Image getImagenContrastada() {
        return imagenContrastada;
    }
    
}
