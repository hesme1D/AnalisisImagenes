/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.listeners;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JSlider;

import analisisespacial.FiltrosEspaciales;
import gui.JFrameSegmentacion;

import java.awt.Image;
/**
 *
 * @author esmec
 */
public class SegmentacionListener implements ActionListener{

  
    private JFrameSegmentacion frame;

    public SegmentacionListener (JFrameSegmentacion frame){
        this.frame = frame;

    }

    public void actionPerformed(ActionEvent e){
        int u1 = this.frame.getJSliderU1().getValue();
        int u2 = this.frame.getJSliderU2().getValue();
        Image res = FiltrosEspaciales.
            segmentarImagen(this.frame.getImagenEscalada(), u1, u2);
        this.frame. getLabelImagen().setIcon(new ImageIcon(res));
    }



}
