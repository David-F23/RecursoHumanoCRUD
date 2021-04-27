package recursohumano;

import java.sql.*;

public class ConexionCRUD {
    
    private final String servidor = "jdbc:mysql://127.0.0.1:3306/bd_recurso_humano";
    
    private final String usuario = "root";
    
    private final String clave = "";
    
    private final String DriverConector = "com.mysql.jdbc.Driver";
    
    private static Connection conexion;
    
    
    public ConexionCRUD(){
        
        try{
            Class.forName(DriverConector);
            conexion = DriverManager.getConnection(servidor, usuario, clave);
            
        }catch(ClassNotFoundException | SQLException e){
            
            System.out.println("Coneción Fallida! Error!" + e.getMessage());
        }
    }
    
    public Connection getConnection(){
        return conexion;
    }
    
    public void guardarRegistros(String tabla, String camposTabla, String valoresCampos){
        
        ConexionCRUD conectar = new ConexionCRUD();
        Connection cone = conectar.getConnection();
        
        try{
            String sqlQueryStmt = "INSERT INTO " + tabla + " ("+ camposTabla +" ) VALUES (" + valoresCampos + ");";
            
            Statement stmt;
            stmt = cone.createStatement();
            stmt.executeUpdate(sqlQueryStmt);
            
            stmt.close();
            cone.close();
            System.out.println("Registro Guardado Correctamente!");
            
        }catch(Exception e){
            
            System.out.println(e.getMessage());
        }
    }
    
    
    public void actualizarEliminarRegistro(String tabla, String valoresCamposNuevos, String condicion){
        
        ConexionCRUD conectar = new ConexionCRUD();
        Connection cone = conectar.getConnection();
        
        try{
            
            Statement stmt;
            String sqlQueryStmt;
            
            if(valoresCamposNuevos.isEmpty()){
                
                sqlQueryStmt = "DELETE FROM " + tabla + " WHERE " + condicion + ";";
                
            }else{
                
                sqlQueryStmt = "UPDATE " + tabla + " SET " + valoresCamposNuevos + " WHERE " + condicion + ";";
                
            }
            
            stmt = cone.createStatement();
            stmt.executeUpdate(sqlQueryStmt);
            stmt.close();
            cone.close();
            
        }catch(SQLException ex){
            
            System.out.println("Ha ocurrido el siguiente error: " + ex.getMessage());
        }
    }
    
    
    public void desplegarRegistros(String tablaBuscar, String campoBuscar, String condicionBuscar) throws SQLException{
        
        ConexionCRUD conectar = new ConexionCRUD();
        Connection cone = conectar.getConnection();
        
        try{
            
            Statement stmt;
            String sqlQueryStmt;
            
            if(condicionBuscar.equals("")){
                
                sqlQueryStmt = "SELECT " + campoBuscar + " FROM " + tablaBuscar + ";";
                
            }else{
                
                sqlQueryStmt = "SELECT " + campoBuscar + " FROM " + tablaBuscar + " WHERE " + condicionBuscar;
                
            }
            
            stmt = cone.createStatement();
            stmt.executeUpdate(sqlQueryStmt);
            
        }finally{
            
            stmt.close();
            cone.close();
        }
    }
}


