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
import gui.JFrameContraste;
import java.awt.Image;
/**
 *
 * @author esmec
 */
public class ContrasteListener implements ActionListener{
    
    private JFrameContraste frame;
    
    public ContrasteListener(JFrameContraste frame){
        this.frame=frame;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        int u1 = this.frame.getSliderU1().getValue();
        int u2 = this.frame.getSliderU2().getValue();
        //Image res = FiltrosEspaciales.contraste(this.frame.getImagenContrastada(),u1,u2);
        //this.frame.getLabelImagen().setIcon(new ImageIcon(res));
    }
    
}
