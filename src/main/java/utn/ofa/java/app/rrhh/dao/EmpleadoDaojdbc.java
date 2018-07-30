/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.ofa.java.app.rrhh.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utn.ofa.java.app.rrhh.Contratado;
import utn.ofa.java.app.rrhh.Efectivo;
import utn.ofa.java.app.rrhh.Empleado;

/**
 *
 * @author User
 */
public class EmpleadoDaojdbc implements EmpleadoDao {

    private final String INSERT_EMPLEADO = "INSERT INTO EMPLEADOS (NOMBRE, CORREO, CUIL, "
            + "FECHA_INGRESO, HS_TRABAJADAS, SUELDO_BASICO, "
            + "COMISIONES, HS_MINIMAS, COSTO_HORA, TIPO_EMPLEADO) "
            + "VALUES (?,?,?,?,?,?,?,?,?,?)";
 
    private final String UPDATE_EMPLEADO = "UPDATE EMPLEADOS SET NOMBRE=?, CORREO=?, CUIL=?, "
            + "FECHA_INGRESO=?, HS_TRABAJADAS=?, SUELDO_BASICO=?, "
            + "COMISIONES=?, HS_MINIMAS=?, COSTO_HORA=?, TIPO_EMPLEADO)=? "
            + "WHERE ID=?";

    private final String DELETE_EMPLEADO = "DELETE FROM EMPLEADOS WHERE ID=?";
     
    private final String BUSCAR_EMPLEADO = "SELECT NOMBRE, CORREO, CUIL, "
            + "FECHA_INGRESO, HS_TRABAJADAS, SUELDO_BASICO, "
            + "COMISIONES, HS_MINIMAS, COSTO_HORA, TIPO_EMPLEADO  "
            + "FROM EMPLEADOS "
            + "WHERE ID=?";
     
    private final String BUSCAR_TODOS_EMPLEADO = "SELECT NOMBRE, CORREO, CUIL, "
            + "FECHA_INGRESO, HS_TRABAJADAS, SUELDO_BASICO, "
            + "COMISIONES, HS_MINIMAS, COSTO_HORA, TIPO_EMPLEADO) "
            + "FROM EMPLEADOS";

    Empleado empBuscado;
   
    @Override
    public void crear(Empleado e) {
        Connection conn = null;
        try {
            conn = ConexionJDBC.getConexion();
       
              PreparedStatement ps = conn.prepareStatement(INSERT_EMPLEADO);
              ps.setString(1, e.getNombre());
              ps.setString(2, e.getCorreoElectronico());
              ps.setString(3, e.getCuil());
              if (e.getFechaIngreso() == null) {
                  e.setFechaIngreso(new java.util.Date());
              }  
            Date auxDate = null;
            auxDate = new Date(e.getFechaIngreso().getTime());
              ps.setDate(4,auxDate);
              ps.setInt(5, e.getHorasTrabajadas()); 
              if(e.esEfectivo()){
                    Efectivo empEf = (Efectivo) e;
                    ps.setDouble(6, empEf.getSueldoBasico());
                    ps.setDouble(7, empEf.getComisiones());
                    ps.setInt(8, empEf.getHorasMinimasObligatorias()); 
                    ps.setDouble(9, 0.0);
                    ps.setInt(10, 1); 
              }
              if(e.esContratado()){
                  Contratado c = (Contratado) e;
                  ps.setDouble(6, 0);
                  ps.setDouble(7, 0);
                  ps.setInt(8, 0); 
                  ps.setDouble(9, c.getCostoHora());
                  ps.setInt(10, 2); 
              }
              int filasInsertadas = ps.executeUpdate(); 

            ConexionJDBC.liberarConexion();
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoDaojdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actualizar(Empleado e) {
        Connection conn = null;
        try {
            conn = ConexionJDBC.getConexion();
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDaojdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
          try(PreparedStatement ps = conn.prepareStatement(UPDATE_EMPLEADO)){
              ps.setString(1, e.getNombre());
              ps.setString(2, e.getCorreoElectronico());
              ps.setString(3, e.getCuil());
              ps.setDate(4, new Date(e.getFechaIngreso().getTime()));
              ps.setInt(5, e.getHorasTrabajadas()); 
              if(e.esEfectivo()){
                    Efectivo empEf = (Efectivo) e;
                    ps.setDouble(6, empEf.getSueldoBasico());
                    ps.setDouble(7, empEf.getComisiones());
                    ps.setInt(8, empEf.getHorasMinimasObligatorias()); 
                    ps.setDouble(9, 0.0);
                    ps.setInt(10, 1); 
              }
               if(e.esContratado()){
                    Contratado c = (Contratado) e;
                    ps.setDouble(6, 0);
                    ps.setDouble(7, 0);
                    ps.setInt(8, 0); 
                    ps.setDouble(9, c.getCostoHora());
                    ps.setInt(10, 2); 
              }
              ps.setInt(11, e.getId());
              int filasActualizadas = ps.executeUpdate(); 
         }
          catch(SQLException ex){ 
         } 
        
        try {
            ConexionJDBC.liberarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDaojdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    @Override
    public void eliminar(Empleado e) {
        Connection conn = null;
        try {
            conn = ConexionJDBC.getConexion();
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDaojdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
          try(PreparedStatement ps = conn.prepareStatement(DELETE_EMPLEADO)){
              ps.setInt(1, e.getId());
              int filaDeleteada = ps.executeUpdate(); 
         }
          catch(SQLException ex){ 
         }
        
        try {
            ConexionJDBC.liberarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDaojdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
    @Override
    public Empleado buscarPorId(Integer id) {
        Empleado emp = null;
        Connection conn = null;
        try {
            conn = ConexionJDBC.getConexion();
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDaojdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
          try(PreparedStatement ps = conn.prepareStatement(BUSCAR_EMPLEADO)){
              ps.setInt(1, id);
              try(ResultSet rs = ps.executeQuery()){
                
                while(rs.next()){ 
                    if (rs.getInt("TIPO_EMPLEADO") == 1){
                        //---- Empleado Efectivo
                        Efectivo  empBus = new Efectivo();
                        empBus.setId(id);
                        empBus.setNombre(rs.getString("NOMBRE") );
                        empBus.setCorreoElectronico(rs.getString("CORREO"));
                        empBus.setCuil(rs.getString("CUIL"));
                        empBus.setHorasTrabajadas(rs.getInt("HS_TRABAJADAS"));
                        empBus.setFechaIngreso(rs.getDate("FECHA_INGRESO"));
                        empBus.setSueldoBasico(rs.getDouble("SUELDO_BASICO"));
                        empBus.setComisiones(rs.getDouble("COMISIONES"));
                        empBus.setHorasMinimasObligatorias(rs.getInt("HS_MINIMAS")); 
                        emp = empBus;
                    }
                    else if(rs.getInt("TIPO_EMPLEADO") == 2){
                        //---- Empleado Contratado
                        Contratado   empBus = new Contratado();
                        empBus.setId(id);
                        empBus.setNombre(rs.getString("NOMBRE") );
                        empBus.setCorreoElectronico(rs.getString("CORREO"));
                        empBus.setCuil(rs.getString("CUIL"));
                        empBus.setHorasTrabajadas(rs.getInt("HS_TRABAJADAS"));
                        empBus.setFechaIngreso(rs.getDate("FECHA_INGRESO"));                        
                        empBus.setCostoHora(rs.getDouble("COSTO_HORA"));
                        emp = empBus;
                    }
                }             
            }
            catch(SQLException ex){ 
         }
        
        try {
            ConexionJDBC.liberarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDaojdbc.class.getName()).log(Level.SEVERE, null, ex);
        }

        }   catch (SQLException ex) { 
            Logger.getLogger(EmpleadoDaojdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return emp;    
    }
    
    
    @Override
    public List<Empleado> buscarTodos() {
        Connection conn = null;
        List<Empleado> empBuscados = new ArrayList<>(1000);
        
        try {
            conn = ConexionJDBC.getConexion();
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDaojdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
          try(PreparedStatement ps = conn.prepareStatement(BUSCAR_EMPLEADO)){
              try(ResultSet rs = ps.executeQuery() ){
                  
                while(rs.next()){ 
                    if (rs.getInt("TIPO_EMPLEADO") == 1){
                        //---- Empleado Efectivo
                        Efectivo empBus = new Efectivo();
                        empBus.setId(rs.getInt("ID"));
                        empBus.setNombre(rs.getString("NOMBRE") );
                        empBus.setCorreoElectronico(rs.getString("CORREO"));
                        empBus.setCuil(rs.getString("CUIL"));
                        empBus.setHorasTrabajadas(rs.getInt("HS_TRABAJADAS"));
                        empBus.setFechaIngreso(rs.getDate("FECHA_INGRESO"));
                        empBus.setSueldoBasico(rs.getDouble("SUELDO_BASICO"));
                        empBus.setComisiones(rs.getDouble("COMISIONES"));
                        empBus.setHorasMinimasObligatorias(rs.getInt("HS_MINIMAS")); 
                        empBuscados.add(empBus);
                    }
                    else if(rs.getInt("TIPO_EMPLEADO") == 2){
                        //---- Empleado Contratado
                        Contratado empBus = new Contratado();
                        empBus.setId(rs.getInt("ID"));
                        empBus.setNombre(rs.getString("NOMBRE") );
                        empBus.setCorreoElectronico(rs.getString("CORREO"));
                        empBus.setCuil(rs.getString("CUIL"));
                        empBus.setHorasTrabajadas(rs.getInt("HS_TRABAJADAS"));
                        empBus.setFechaIngreso(rs.getDate("FECHA_INGRESO"));                        
                        empBus.setCostoHora(rs.getDouble("COSTO_HORA"));
                        empBuscados.add(empBus);
                    }
              }             
              int filasBuscadas = ps.executeUpdate(); 
         }
           catch(SQLException ex){ 
         }
        
        try {
            ConexionJDBC.liberarConexion();
         } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDaojdbc.class.getName()).log(Level.SEVERE, null, ex);
           }      
      }   
        catch (SQLException ex) {
            Logger.getLogger(EmpleadoDaojdbc.class.getName()).log(Level.SEVERE, null, ex);
        }  
          
       return empBuscados;
    }
}    