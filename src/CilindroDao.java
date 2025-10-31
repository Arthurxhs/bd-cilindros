//interfaz
import java.sql.*;
//
//


public class CilindroDao {
    //DAO = data access object

    public boolean insertarCilindro(Cilindro cilindro){
        //aparte de que la variable tendra un string tambien tiene final o sea que nunca cambiara
        final String SQL_INSERT = "INSERT INTO registro_cilindros (numero_serial, tipo_de_gas, donde_esta, metros) VALUES (?, ?, ?, ?)";
        //para indicar que es un error o no se encontro
        int idDelUltimoCilindro = -1;

        //aqui crea una conexion en la variable Connection, llamando al metodo Conectar de la clase Conexionbd
        //el tipo de variable conn es Connection por que el metodo Conectar usa metodos que son de interfaz
        try (Connection conn = Conexionbd.Conectar();
             //esta ejecutando dos cosas en mysql una insercion de un objeto y pidiendo las llaves generadas automaticamente o sea el id
            PreparedStatement ps = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {

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

    public Cilindro getCilindro(int id){
        final String SQL_GET = "SELECT * FROM registro_cilindros WHERE numero_serial = ?";
        Cilindro cilindro = null;

        try (Connection conn = Conexionbd.Conectar();
            PreparedStatement ps = conn.prepareStatement(SQL_GET)){

            ps.setInt(1, id);

            //ejecuta la consulta ps y el resultado se guarda en rs
            try (ResultSet rs = ps.executeQuery()){
                //el .next convierte la respuesta a un objeto java
                if (rs.next()) {
                    cilindro = new Cilindro(
                            //rs.getInt("id"), no se puede tener aqui ya que no tengo donde guardarlo
                            rs.getInt("numero_serial"),
                            rs.getString("tipo_de_gas"),
                            rs.getString("donde_esta"),
                            rs.getInt("metros")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar el cilindro");
            e.printStackTrace();
        }

        return cilindro;
    }

    public static void main(String[] args) {
        Cilindro cilindro = new Cilindro(123123, "argon", "bogota", 8);
        CilindroDao dao = new CilindroDao();
        dao.insertarCilindro(cilindro);
    }
}