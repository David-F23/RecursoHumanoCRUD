package recursohumano;

import java.sql.SQLException;
import java.util.Scanner;

public class Delete {
    
    Delete() throws SQLException{
        
        Scanner leer = new Scanner(System.in);
        ConexionCRUD utilerias = new ConexionCRUD();
        System.out.println("<<  ELIMINAR REGISTROS  >>");
        
        System.out.println("Ingresar el id del registro a eliminar");
        String idContactoEliminar = leer.next();
        
        String tabla = "tb_contacto";
        String campos = "*";
        String condicion = "id_contacto = " + idContactoEliminar;
        utilerias.desplegarRegistros(tabla, campos, condicion);
        
        System.out.println("presione << Y >> para confirmar");
        String confirmarBorrar = leer.next();
        
        if("Y".equals(confirmarBorrar) || "y".equals(confirmarBorrar)){
            
            String valoresCamposNuevos = "";
            utilerias.actualizarEliminarRegistro(tabla, valoresCamposNuevos, condicion);
            
            System.out.println("Registro Eliminado Correctamente!!");
        }
        
        MenuPrincipal.desplegarMenu();
    }
}
