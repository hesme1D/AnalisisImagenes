/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author esmec
 */
public class JFrameIm extends JFrame{
    private Image imagen;
    private int x,y;
    private ArrayList<Integer[]> Lista;
    
    public JFrameIm(Image io){
        this.imagen=io;
        
        this.setSize(imagen.getWidth(null),imagen.getHeight(null));
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(io));
        this.add(label);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrameIm.EXIT_ON_CLOSE);
    }
    public JFrameIm(Image io, boolean list){
        this.imagen=io;
        this.Lista=new ArrayList<>();
        
        this.setSize(imagen.getWidth(null),imagen.getHeight(null));
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(io));
        this.add(label);
        if(list){
            this.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    x = me.getX();
                    y = me.getY();
                    Integer[] aux = {x,y};
                    Lista.add(aux);
                }

                @Override
                public void mousePressed(MouseEvent me) {}

                @Override
                public void mouseReleased(MouseEvent me) {}

                @Override
                public void mouseEntered(MouseEvent me) {}

                @Override
                public void mouseExited(MouseEvent me) {}
            });
        }
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrameIm.EXIT_ON_CLOSE);
    }
    public Image getImagen(){
        return imagen;
    }
    
    public void setImagen(Image imagen){
        this.imagen=imagen;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public ArrayList<Integer[]> getLista(){
       return Lista;
    }
}
