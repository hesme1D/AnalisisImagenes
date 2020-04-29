/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisisfrecuencias;

/**
 *
 * @author esmec
 */
public class NumeroComplejo {
    private double real;
    private double imaginario;
    
    public NumeroComplejo(double real, double imaginario){
        this.real=real;
        this.imaginario=imaginario;
    }
    public NumeroComplejo(NumeroComplejo c){
        this(c.getParteReal(),c.getParteImaginaria());
    }
    public double getParteReal(){
        return real;
    }
    public double getParteImaginaria(){
        return imaginario;
    }
    
    public NumeroComplejo Suma(NumeroComplejo c2){
        NumeroComplejo c1 = this;
        double r = c1.real + c2.real;
        double i = c1.imaginario + c2.imaginario;
        return new NumeroComplejo(r,i);
    }
    public NumeroComplejo Mult(double m){
        return new NumeroComplejo(real*m, imaginario*m);
    }
}
