/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import javax.swing.JSlider;

import gui.listeners.SegmentacionListener;
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
public class JFrameSegmentacion extends JFrame {

    private JSlider sliderU1, sliderU2;
    private JLabel labelImagen;
    private JButton btnAbrir;
    private Image imagenEscalada;

    public JFrameSegmentacion (String title, Image io){
        setTitle(title);
        int ancho = io.getWidth(null)/2;
        int alto = io.getHeight(null)/2;
        setSize(ancho,alto);
        this.imagenEscalada = AbrirImagen.toBufferedImage(io).getScaledInstance(ancho,alto, BufferedImage.TYPE_INT_RGB);
        initcomponents();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void initcomponents(){
        // layout
        setLayout(new BorderLayout());
        this.labelImagen = new JLabel(new ImageIcon(this.imagenEscalada));
        add(this.labelImagen, BorderLayout.CENTER);
        this.sliderU1 = new JSlider();
        this.sliderU1.setMinimum(0);
        this.sliderU1.setMaximum(255);
        this.sliderU1.setValue(0);
        this.sliderU1.setPaintLabels(true);
        this.sliderU1.setPaintTicks(true);
        this.sliderU1.setMinorTickSpacing(1);
        this.sliderU1.setMajorTickSpacing(25);
        this.sliderU2 = new JSlider();
        this.sliderU2.setMinimum(0);
        this.sliderU2.setMaximum(255);
        this.sliderU2.setValue(255);
        this.sliderU2.setPaintLabels(true);
        this.sliderU2.setPaintTicks(true);
        this.sliderU2.setMinorTickSpacing(1);
        this.sliderU2.setMajorTickSpacing(25);
        // modificar el escuchador de los slider
        JPanel panel = new JPanel(new GridLayout(3,1)); 
        panel.add(this.sliderU1);
        panel.add(this.sliderU2);
        
        this.btnAbrir = new JButton("Segmentar");
        SegmentacionListener lis = new SegmentacionListener(this);
        this.btnAbrir.addActionListener(lis);
        panel.add(this.btnAbrir);
        add(panel, BorderLayout.SOUTH);
    }

    public JSlider getJSliderU1(){
        return this.sliderU1;
    }
    public JSlider getJSliderU2(){
        return this.sliderU2;
    }
    public JLabel getLabelImagen(){

       return  this.labelImagen;
    }

    public Image getImagenEscalada(){

        return  this.imagenEscalada;
     }


}
