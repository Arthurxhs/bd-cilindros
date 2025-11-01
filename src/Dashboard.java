//para botones y demas
import javax.swing.JPanel;
//mensajes emergentes
import javax.swing.JOptionPane;
//lista de cilindros
import javax.swing.table.DefaultTableModel;
//ventana principal
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.util.List;

//se vuelve una subclase de JFRAME para poder usar sus metodos
public class Dashboard extends JFrame {

    private CilindroDao dao = new CilindroDao();
    //creo la pre tabla o sea la variable
    private JTable tableCilindros;
    //la tabla modelo
    private DefaultTableModel modeloCilindros;
    //ARRAY UN ELEMENTO PARA CADA COLUMNA
    private final String[] columnas = {"ID", "NUMERO SERIAL", "TIPO DE GAS", "DONDE ESTA", "METROS"};

    public Dashboard() {
        super("Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        //ventana centrada
        setLocationRelativeTo(null);
        //para que sea
        setResizable(true);

        //configuracion de la tabla
        modeloCilindros = new DefaultTableModel(columnas, 0);
        tableCilindros = new JTable(modeloCilindros);

        add(new JScrollPane(tableCilindros),  BorderLayout.CENTER);
        add(crearPanelBotones(), BorderLayout.NORTH);

        cargarDatosTabla();
        setVisible(true);
    }

    //aqui se crean los botones que podremos usar
    private JPanel crearPanelBotones() {
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());

        JButton btnInsertar = new JButton("Crear Cilindro");
        JButton btnEliminar = new JButton("Eliminar Cilindro");
        JButton btnActualizar = new JButton("Actualizar Cilindro");
        JButton btnBuscar = new JButton("Buscar Cilindro");
        JButton btnActualizarTabla = new JButton("Actualizar tabla");
        JButton btnCerrar = new JButton("Cerrar");

        //btnActualizar.addActionListener(e -> dao.insertarCilindro());
        //btnBuscar.addActionListener(e -> dao.getCilindro());
        //btnActualizarTabla.addActionListener(e -> dao.Registros());
        //btnActualizar.addActionListener(e -> dao.);
        btnEliminar.addActionListener(e -> accionEliminar());
        //btnInsertar.addActionListener(e -> dao.insertarCilindro());
        /*btnCerrar.addActionListener(e -> {
            case 0:
                return;
        });*/

        panelBotones.add(btnEliminar);
        /*panelBotones.add(btnInsertar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnActualizarTabla);
        panelBotones.add(btnCerrar);
        */
        return panelBotones;
    }

    //Aqui se crean las acciones que hara cada boton, sus eventos

    //boton eliminar registro
    private void accionEliminar() {
        int filaSeleccionada = tableCilindros.getSelectedRow();

        if(filaSeleccionada == -1){
            JOptionPane.showMessageDialog(this, "Seleccione un registro", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idAEliminar = (int) modeloCilindros.getValueAt(filaSeleccionada, 0);

        int confirmacion = JOptionPane.showConfirmDialog(this,
                "Esta seguro de que desea eliminar este registro " + idAEliminar + "?",
                "Confirmar Elimacion",  JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            boolean exito = dao.Eliminar(idAEliminar);

            if(exito){
                JOptionPane.showMessageDialog(this, "Registro Eliminado", "Exito", JOptionPane.INFORMATION_MESSAGE);
                cargarDatosTabla();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminarlo de la db", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //boton agregar registro
    private void accionAgregar() {
        int filaSeleccionada = tableCilindros.getSelectedRow();

        //para asegurarse de que se seleccione algun resgistro, van de [0 > infi)
        if(filaSeleccionada == -1){
            JOptionPane.showMessageDialog(this, "Seleccione un registro", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //agarramos el id de la fila que selecionamos que contiene el registro(instancia cilindro)
        int idAEliminar = (int) modeloCilindros.getValueAt(filaSeleccionada, 0);


    }

    //esta es el metodo que recolecta todos los registros y los coloca en una tabla
    private void cargarDatosTabla(){
        //Limpiar datos de la vieja tabla
        modeloCilindros.setRowCount(0);

        //obtener todos los cilindros del DAO
        List<Cilindro> cilindros = dao.Registros();

        for(Cilindro c : cilindros) {
            Object[] fila = new Object[columnas.length];
            fila[0] = c.getId();
            fila[1] = c.getNumeroSerial();
            fila[2] = c.getTipoDeGas();
            fila[3] = c.getDondeEsta();
            fila[4] = c.getMetros();

            modeloCilindros.addRow(fila);
        }

        System.out.println("Tabla recargada. Total registros " + cilindros.size());
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Dashboard());
    }
}