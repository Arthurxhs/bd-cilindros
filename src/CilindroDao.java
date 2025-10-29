//interfaz
import java.sql.Connection;
//
import java.sql.PreparedStatement;
//
import java.sql.SQLException;

public class CilindroDao {
    //DAO = data access object

    public boolean insertarCilindro(Cilindro cilindro){
        //aparte de que la variable tendra un string tambien tiene final o sea que nunca cambiara
        final String SQL_INSERT = "INSERT INTO cilindros (numero_serial, tipo_de_gas, donde_Esta, metros) VALUES (?, ?, ?, ?)";

        //aqui crea una conexion en la variable Connection, llamando al metodo Conectar de la clase Conexionbd
        //el tipo de variable conn es Connection por que el metodo Conectar usa metodos que son de interfaz
        try (Connection conn = Conexionbd.Conectar();
            PreparedStatement ps = conn.prepareStatement(SQL_INSERT)) {

            //el primer indice se mapea el numero serial, indices van en orden ?(1). ?(2), ?(3), ?(4)
            ps.setInt(1, cilindro.getNumeroSerial());
            ps.setString(2, cilindro.getTipoDeGas());
            ps.setString(3, cilindro.getDondeEsta());
            ps.setInt(4, cilindro.getMetros());
            //aqui se ejecuta la sentencia con las filas afectadas
            int resultado = ps.executeUpdate();
            //es un metodo boolean o sea que solo puede devolverse true o false
            //aqui lo que hacemos es que verifique si una sentencia es true o false y devuelva su resultado
            return resultado > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar el cilindro");
            e.printStackTrace();
            //
            return false;
        }
    }

}
