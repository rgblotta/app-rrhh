/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.ofa.java.app.rrhh.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import utn.ofa.java.app.rrhh.Contratado;
import utn.ofa.java.app.rrhh.Efectivo;

/**
 *
 * @author User
 */
public class EmpleadoDaoTest {
    
    public EmpleadoDaoTest() {
    }
    
    EmpleadoDaojdbc empleadoDao;
    
    @Before
      public void init(){
        empleadoDao = new EmpleadoDaojdbc();
      }
      
    //public void setUp() {
    //}
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCrearEmpleadoEfe() {
        System.out.println("Crea empleado Efectivo");
        
        Efectivo empleado_1 = new Efectivo();
        empleado_1.setNombre("MOLINAS");
        empleado_1.setCorreoElectronico("molinasjuan@gmail.com");
        empleado_1.setCuil("1780732134");
        empleado_1.setHorasTrabajadas(40);
        empleado_1.setHorasMinimasObligatorias(40);
        empleado_1.setComisiones(2000.0);
        empleado_1.setSueldoBasico(30000.0);   
        empleadoDao.crear(empleado_1);
        
        Efectivo empleado_2 = (Efectivo) empleadoDao.buscarPorId(empleado_1.getId());
        
        assertEquals(empleado_1.getNombre(), empleado_2.getNombre());
    }       
    
    @Test
    public void testCrearEmpleadoCon() {
        System.out.println("Crea empleado Contratado");
        
        Contratado empleado_3 = new Contratado();
        empleado_3.setNombre("ABAD");
        empleado_3.setCorreoElectronico("abadcecilia@justiciasantfe.gov.ar");
        empleado_3.setCuil("3380735134");
        empleado_3.setHorasTrabajadas(45); 
        empleado_3.setCostoHora(150.0);
        empleadoDao.crear(empleado_3);
        
        Contratado empleado_4 = (Contratado) empleadoDao.buscarPorId(empleado_3.getId());
        
        assertEquals(empleado_3.getCuil(), empleado_4.getCuil());
    }
    
    @Test
    public void testActulizaEmpleado() {
        System.out.println("Actualiza empleado");
        
        Efectivo empleado_1 = (Efectivo) empleadoDao.buscarPorId(2);
        Efectivo empleado_2 = (Efectivo) empleadoDao.buscarPorId(2);
        
        empleado_1.setCorreoElectronico("molinasjuan@hotmail.com");

        empleadoDao.actualizar(empleado_1);
        assertNotEquals(empleado_1.getCorreoElectronico(), empleado_2.getCorreoElectronico()) ;
    }
    
   @Test
    public void testEliminarEmpleado() {
        System.out.println("Elimina empleado");
               
        Efectivo empleado_1 = new Efectivo();
        empleado_1.setNombre("ROJAS HECTOR");
        empleado_1.setCorreoElectronico("rojashector@gmail.com");
        empleado_1.setCuil("2123732134");
        empleado_1.setHorasTrabajadas(40);
        empleado_1.setHorasMinimasObligatorias(40);
        empleado_1.setComisiones(2000.0);
        empleado_1.setSueldoBasico(30000.0);   
        empleadoDao.crear(empleado_1);
        
        Integer id_elim = empleado_1.getId();
 
        empleadoDao.eliminar(empleado_1);
        
        assertNull(empleadoDao.buscarPorId(id_elim));
    }
}   
