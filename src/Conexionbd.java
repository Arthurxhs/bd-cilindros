//conexion con el driver de mysql(interfaz)
import java.sql.Connection;
//traductor codigo java a sql y al reves(clase)
import java.sql.DriverManager;
//para manejo de errores, tiene un metodo para mostrar los errores de la conexion de la bd detalladamente(clase)
import java.sql.SQLException;

/*tener en cuenta que el constructor esta default, no tiene ningun parametro pero sirve para crear una variable tipo conexionbd y usar sus
metodos, pero aqui no lo usamos ya que hacemos variables tipo connection o sea de la interfaz con la que hicimos contrato y ella tiene su
su propio constructor el cual usamos para crear la variable conexion con "Connection Conexion", pero duda el construtor no tiene parametros
entonces por eso pone null? para que pueda sostenerse vacio y por que no usa el new*/
public class Conexionbd {
    //credenciales de la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/cilindros";
    private static final String USER = "root";
    private static final String PASSWORD = "Yosoymipropiodios1*";

    //aparece Connection por que ese es el tipo de variable que se va a devolver en el metodo, es como si fuera un getter
    public static Connection Conectar() {
        //la conexion se crea pero nula o sea vacia para que exista
        Connection conexion = null;

        //manejo de errores
        try {
            /*DriverManager es la Clase donde esta escrito el metodo getConnection, la razon por la que no usamos instancias es por que solo
            necesito la funcionalidad o sea el metodo getConnection, para no permitir que se creen instancias de la clase DriverManager se hace
            private DriverManager(){}
            pero el metodo get ya que no necesita instancia como tal entonces lo llamamos con la clase como tal ya que ese metodo es static
            el metodo get su logica interna es la que usa la interfaz Connection

            siempre que un metodo y variable este etiquetado como un tipo de interfaz entonces esta obligado a contrado
            * */
            //aqui se guarda la conexion a la base de datos aqui que usaremos esta variable para ejecutar todo lo relacionado  a la bd
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);

            if(conexion != null){
                System.out.println("Conexion a la base de datos establecida exitosamente :D");
            }
            //e almacena el error, e es tipo SQLException para que guarde los errores
        }catch(SQLException e) {
            System.err.println("Error al conectar la base de datos");
            //y printStackTrace() es un metodo de SQLExeption
            e.printStackTrace();
        }

        return conexion;
    }

    /*public static void main(String[] args) {
        /*tener en cuenta que Connection es digamos un metodo de la interfaz SQL conection, entonces primero para que nuestra clase pueda
        acceder a esos metodos de la interfaz necesita ser de ese tipo de interfaz, o sea connection
        Connection conexion = Conectar();

        //cerrar la conexion cuando la termine de usar
        if (conexion != null) {
            try {
                //close es un metodo de la interfaz connection
                conexion.close();
                System.out.println("Conexion cerrada");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la base de datos");
                e.printStackTrace();
            }
        }
    }*/
}
