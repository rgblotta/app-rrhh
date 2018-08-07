/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.ofa.java.app.rrhh.dao;

import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import utn.ofa.java.app.rrhh.Proyecto;

/**
 *
 * @author User
 */
public class ProyectoDaoJPATest {
  
    ProyectoDaoJPA proyectoDao;

    public ProyectoDaoJPATest() {
    }
    
    @Before
    public void setUp() {
        proyectoDao = new ProyectoDaoJPA();
    }
    
    @Test
    public void testCrearProyecto() {
        System.out.println("Crea un proyecto");

        List<Proyecto> lista = proyectoDao.buscarTodos();
        int proyectos = lista.size();
        
        Proyecto pry = new Proyecto();
        pry.setId(99);
        pry.setNombre("Proyecto 1");
        pry.setDescripcion("Proyecto Ventas 1");
        pry.setPresupuestoMaximo(999.0);
        proyectoDao.crear(pry);
        
        lista = proyectoDao.buscarTodos();
        int proyectosMas1 = lista.size();
        assertEquals(proyectos+1,proyectosMas1);
    }
    
    @Test
    public void testBuscarProyecto() {
        System.out.println("Buscar un Proyecto");
        
        Proyecto proy = proyectoDao.buscarPorId(99);  
        System.out.println("Busco Proyecto(id=99): " + proy.getNombre());
        
        assertEquals(proy.getNombre(), "Proyecto 1");           
     }
       
    @Test
    public void testActualizarProyecto() {
        System.out.println("Actualizar un Proyecto");
        
        Proyecto proy = proyectoDao.buscarPorId(99);  
        Double presupuesto1 = proy.getPresupuestoMaximo();
        
        proy.setPresupuestoMaximo(presupuesto1+50);
        proyectoDao.actualizar(proy);
        
        proy = proyectoDao.buscarPorId(99);
        Double presupuesto2 = proy.getPresupuestoMaximo();
        
        assertNotEquals(presupuesto1,presupuesto2);
    }
    @Test
    public void testEliminarProyecto() {
        System.out.println("Eliminar un Proyecto");
        
        Proyecto proy = new Proyecto();
        proy.setId(102);
        proy.setNombre("Proyecto 4");
        proy.setDescripcion("Proyecto Ventas 4");
        proy.setPresupuestoMaximo(500.0);
        proyectoDao.crear(proy);

        Integer id_elim = proy.getId();
 
        proyectoDao.eliminar(proy);
        
        assertNull(proyectoDao.buscarPorId(id_elim));
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
    }
    
}
