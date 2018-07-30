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
public class Contratado extends Empleado{ 
    private Double costoHora;
    private Double salarioEmp;
    
    @Override
    public Double salario() {
         salarioEmp = costoHora * horasTrabajadas;
         
         return salarioEmp;
    }
    @Override
    public Boolean esEfectivo() {
        return false;
    }

    @Override
    public Boolean esContratado() {
        return true;
    }
    
    
    public double getCostoHora() {
        return costoHora;
    }

    public void setCostoHora(Double costoHora) {
        this.costoHora = costoHora;
    }
    
}
