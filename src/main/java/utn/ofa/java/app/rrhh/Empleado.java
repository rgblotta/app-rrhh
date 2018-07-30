/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.ofa.java.app.rrhh;

import java.util.Date;



/**
 *
 * @author User
 */
public abstract class Empleado {
    protected Integer id;
    protected String nombre;
    protected String correoElectronico;
    protected String cuil;
    protected Integer horasTrabajadas;
    protected Date fechaIngreso;

    public Empleado() {
        
    }
    
    public abstract Double salario();
    public abstract Boolean esEfectivo();
    public abstract Boolean esContratado();

      
    public Integer getId() {
         return id;
    }
    public void setId(Integer id) {
         this.id = id;
    }
     
    public String getNombre() {
         return nombre;
    }
    public void setNombre(String nombre) {
         this.nombre = nombre;
    }
    
    public String getCorreoElectronico() {
         return correoElectronico;
    }
    public void setCorreoElectronico(String correoElectronico) {
         this.correoElectronico = correoElectronico;
    }

    public String getCuil() {
        return cuil;
    }
    public void setCuil(String cuil) {
         this.cuil = cuil;
    }    
    
    public Date getFechaIngreso() {
        return fechaIngreso;
    }
    public void setFechaIngreso(Date fechaIngreso) {
         this.fechaIngreso = fechaIngreso;
    } 
    
    public Integer getHorasTrabajadas() {
        return horasTrabajadas;
    }
    public void setHorasTrabajadas(Integer horasTrabajadas){
        this.horasTrabajadas = horasTrabajadas;
    }

}
