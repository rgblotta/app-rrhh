/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.ofa.java.app.rrhh;

/**
 *
 * @author User
 */
public class Efectivo extends Empleado{
    private Double sueldoBasico;
    private Double comisiones;
    private Integer horasMinimasObligatorias;
    private Double salarioEmp;
   
    @Override
    public Double salario() {
        salarioEmp = sueldoBasico + comisiones;
        
        if(horasTrabajadas > horasMinimasObligatorias)
            salarioEmp += (horasTrabajadas-horasMinimasObligatorias)*(sueldoBasico/20);
        
        return salarioEmp;
    }
    
    //----Constructores
 
    public void setHorasMinimasObligatorias(Integer horasMinimasObligatorias){
        this.horasMinimasObligatorias = horasMinimasObligatorias;
    }
    
    public void setSueldoBasico(Double sueldoBasico){
        this.sueldoBasico = sueldoBasico;
    }
    
    public void setComisiones(Double comisiones){
        this.comisiones = comisiones;
    }

    public double getSueldoBasico() {
        return sueldoBasico;
    }

    public double getComisiones() {
        return comisiones;
    }

    public int getHorasMinimasObligatorias() {
        return horasMinimasObligatorias;
    }
    
    @Override
    public Boolean esEfectivo() {
        return true;
    }

    @Override
    public Boolean esContratado() {
        return false;
    }

}