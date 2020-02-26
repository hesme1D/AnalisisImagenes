/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import javax.swing.JSlider;

import gui.listeners.IluminacionListener;
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
public class JFrameIluminacion extends JFrame{
    
    private JSlider sliderU1, SliderU2;
    private JLabel labelImagen;
    private JButton btnAbrir;
    private Image imagenIluminada;
    
    public JFrameIluminacion(String title, Image io){
        this.setTitle(title);
        int ancho = io.getWidth(null)/2;
        int alto = io.getHeight(null)/2;
        this.setSize(ancho,alto);
        this.imagenIluminada = AbrirImagen.toBufferedImage(io).getScaledInstance(ancho,alto, BufferedImage.TYPE_INT_RGB);
        initcomponents();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void initcomponents(){
        this.setLayout(new BorderLayout());
        this.labelImagen = new JLabel(new ImageIcon(this.imagenIluminada));
        add(this.labelImagen,BorderLayout.CENTER);
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
        
        this.btnAbrir = new JButton("Iluminar / Oscurecer");
        IluminacionListener lis = new IluminacionListener(this);
        this.btnAbrir.addActionListener(lis);
        panel.add(this.btnAbrir);
        add(panel, BorderLayout.SOUTH);
    }
    
    public JSlider getJSliderU1(){
        return this.sliderU1;
    }
    public JSlider getJSliderU2(){
        return this.SliderU2;
    }
    public JLabel getLabelImagen(){
        return this.labelImagen;
    }
    public Image getImagenIluminada(){
        return this.imagenIluminada;
    }
}
    