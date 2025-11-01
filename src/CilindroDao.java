//interfaz
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
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
            PreparedStatement ps = conn.prepareStatement(SQL_INSERT /*,Statement.RETURN_GENERATED_KEYS*/)) {

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
        final String SQL_GET = "SELECT * FROM registro_cilindros WHERE id = ?";
        Cilindro cilindro = null;
//se guarda la conexion hecha en conn donde ejecutaremos los query
        try (Connection conn = Conexionbd.Conectar();
             //ahora esta variable guardara la sentencia que esta como string a query y despues
            PreparedStatement ps = conn.prepareStatement(SQL_GET/*, Statement.RETURN_GENERATED_KEYS*/)) {
//se usa el metodo en la variable ps para insertar los parametros del query
            ps.setInt(1, id);


            //Despues de tener el string a query e insertar los parametros, se envia para que se ejecute
            //y este se guarda en una variable Resulset para usar los parametros de este sobre el resultado obtenido sobre
            //esa misma variable
            try (ResultSet rs = ps.executeQuery()){
                //el .next convierte la respuesta a un objeto java
                if (rs.next()) {
                    //capturo el id en una variable aparte y la inserto en la variable de la clase
                    int idCilindro = rs.getInt("id");

                    cilindro = new Cilindro(
                            rs.getInt("numero_serial"),
                            rs.getString("tipo_de_gas"),
                            rs.getString("donde_esta"),
                            rs.getInt("metros")
                    );

                    //la inserto en la variable de la clase
                    cilindro.setId(idCilindro);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar el cilindro");
            e.printStackTrace();
        }

        return cilindro;
    }

    public int getTotalRegistros(){
        final String SQL_GET = "SELECT COUNT(*) FROM registro_cilindros";
        int totalRegistros = 0;

        try (Connection conn = Conexionbd.Conectar();
            PreparedStatement ps = conn.prepareStatement(SQL_GET);
            ResultSet rs =  ps.executeQuery()) {

            if (rs.next()) {
                totalRegistros = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar el cilindro");
            e.printStackTrace();
        }
        return totalRegistros;
    }

    //no entiendo por que una lista y no un arrayLista
    public List<Cilindro> Registros(){
        final String SQL_GET = "SELECT * FROM registro_cilindros";
        List<Cilindro> registros = new ArrayList<>();

        try(
                Connection conn = Conexionbd.Conectar();
                PreparedStatement ps = conn.prepareStatement(SQL_GET);
                ResultSet rs =  ps.executeQuery()
                ){

            //por que da error el if y toca usar el while
                while(rs.next()) {
                    Cilindro cilindro = new Cilindro(
                            rs.getInt("numero_serial"),
                            rs.getString("tipo_de_gas"),
                            rs.getString("donde_esta"),
                            rs.getInt("metros")
                    );

                    cilindro.setId(rs.getInt("id"));

                    registros.add(cilindro);
                }

        }catch (SQLException e) {
            System.out.println("Error al consultar el cilindro");
            e.printStackTrace();
        }

        return registros;
    }

    public boolean getCilindroUpdate(Cilindro cilindroUpdate){
        final String SQL_UPDATE = "UPDATE registro_cilindros SET numero_serial = ?, tipo_de_gas = ?, donde_esta = ?, metros = ? WHERE id = ?";

        try(
                Connection conn = Conexionbd.Conectar();
                PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)
        ) {

            ps.setInt(1, cilindroUpdate.getNumeroSerial());
            ps.setString(2, cilindroUpdate.getTipoDeGas());
            ps.setString(3, cilindroUpdate.getDondeEsta());
            ps.setInt(4, cilindroUpdate.getMetros());

            ps.setInt(5, cilindroUpdate.getId());

            int filasAfectadas =  ps.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al consultar el cilindro");
            e.printStackTrace();
            return false;
        }
    }

    public boolean Eliminar(int Id){
        final String SQL_DELETE = "DELETE FROM registro_cilindros WHERE id = ?";

        try(
                Connection conn = Conexionbd.Conectar();
                PreparedStatement ps = conn.prepareStatement(SQL_DELETE);
                ) {

            ps.setInt(1, Id);

            int rs = ps.executeUpdate();
            return rs > 0;


        } catch (SQLException e) {
            System.out.println("Error al eliminar el cilindro");
            e.printStackTrace();
            return false;
        }
    }

    /*public static void main(String[] args) {
        Cilindro cilindro = new Cilindro(123123, "argon", "bogota", 8);
        CilindroDao dao = new CilindroDao();
        dao.insertarCilindro(cilindro);
    }*/
}