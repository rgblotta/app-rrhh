/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.ofa.java.app.rrhh;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author User
 */
@Entity
public class Proyecto {
    @Id
    private Integer id;
    private String nombre;
    private String descripcion;
    private Double presupuestoMaximo;
    
    
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
       public String getDescripcion() {
         return descripcion;
    }
    public void setDescripcion(String descripcion) {
         this.descripcion = descripcion;
    }
    
     public double getPresupuestoMaximo() {
        return presupuestoMaximo;
    }
    public void setPresupuestoMaximo(Double presupuestoMaximo) {
        this.presupuestoMaximo = presupuestoMaximo;
    }
    
    public String toString(){
        String texto_proy = "Proyecto - "+ "Id; "+ id + ", Nombre; "+ nombre
                                         + ", Desc.: "+ descripcion +", Presup.Max: "+ presupuestoMaximo;
        return texto_proy;
    }
    
    public boolean equals(Object obj) {
        Proyecto p = (Proyecto)obj;
 
        return p.getNombre().equals(this.getNombre());
    }
}
    