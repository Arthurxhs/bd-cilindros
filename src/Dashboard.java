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

    //para poder conectarme a los metodos DAO
    private CilindroDao dao = new CilindroDao();
    //la ventana donde se mostrara la tabla y lo demas
    private JTable dibujadorDeTabla;
    //la tabla de los cilindros(modelo)
    private DefaultTableModel tablaCilindros;
    //ARRAY UN ELEMENTO PARA CADA COLUMNA
    private final String[] columnas = {"ID", "NUMERO SERIAL", "TIPO DE GAS", "DONDE ESTA", "METROS"};

    //El JFrame es la vista
    public Dashboard() {
        //la clase padre JFRAME tiene un constructor que herede con super que es para colocarle un titulo a la ventana
        super("Dashboard Oxigas Del Tolima");
        //Para que cuando se cierre la ventana se deje de ejecutar la Dashboard
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //para que se abra con esos px
        setSize(800, 600);
        //ventana centrada
        setLocationRelativeTo(null);
        //para que sea responsive
        setResizable(true);

        //Crea la tabla y inserta las columnas pero con 0 filas
        tablaCilindros = new DefaultTableModel(columnas, 0);
        //hago esto para que se grafique la tabla, digamos es como si antes la tuviera en mi cabeza y ahora escrita
         dibujadorDeTabla= new JTable(tablaCilindros);

        //envuelvo el dibujadorDeTabla en un scroll y lo centro en el dibujadorDeTabla
        add(new JScrollPane(dibujadorDeTabla),  BorderLayout.CENTER);
        //creo un panel de botones que estara en el norte del dibujadorDeTabla
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
        btnActualizar.addActionListener(e -> accionEditar());
        btnEliminar.addActionListener(e -> accionEliminar());
        btnInsertar.addActionListener(e -> accionAgregar());
        /*btnCerrar.addActionListener(e -> {
            case 0:
                return;
        });*/

        panelBotones.add(btnEliminar);
        panelBotones.add(btnInsertar);
        panelBotones.add(btnActualizar);
        /*panelBotones.add(btnBuscar);
        panelBotones.add(btnActualizarTabla);
        panelBotones.add(btnCerrar);
        */
        return panelBotones;
    }

    //Aqui se crean las acciones que hara cada boton, sus eventos

    //boton eliminar registro
    private void accionEliminar() {
        int filaSeleccionada = dibujadorDeTabla.getSelectedRow();

        if(filaSeleccionada == -1){
            JOptionPane.showMessageDialog(this, "Seleccione un registro", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idAEliminar = (int) tablaCilindros.getValueAt(filaSeleccionada, 0);

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

        String numeroSerial = JOptionPane.showInputDialog(this, "ingresa el numero serial del cilindro:");
        int numeroSerialInt = 0;

        if(numeroSerial != null) {
            try {
                numeroSerialInt =  Integer.parseInt(numeroSerial);
                JOptionPane.showMessageDialog(this, "El numero serial es: " + numeroSerialInt);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error: Debe ingresar un valor numero valido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        String TipoDeGas = JOptionPane.showInputDialog(this, "ingresa el tipo de gas del cilindro:");
        if(TipoDeGas != null) {
            JOptionPane.showMessageDialog(this, "El tipo de gas es: " + TipoDeGas);
        } else {JOptionPane.showMessageDialog(this, "tienes que colocar caracteres validos", "Error",  JOptionPane.ERROR_MESSAGE);}

        String dondeEsta = JOptionPane.showInputDialog(this, "ingresa el donde se encuentra el cilindro:");
        if(dondeEsta != null) {
            JOptionPane.showMessageDialog(this, "El cilindro se encuentra actualmente en " + dondeEsta);
        } else {JOptionPane.showMessageDialog(this, "tienes que colocar lugares validos ", "Error", JOptionPane.ERROR_MESSAGE);}

        String metros =  JOptionPane.showInputDialog(this, "ingresa los metros del cilindro:");
        int metrosInt = 0;
        if(metros != null) {
            metrosInt = Integer.parseInt(metros);
            JOptionPane.showMessageDialog(this, "El metros es: " + metrosInt);
        } else {JOptionPane.showMessageDialog(this, "tienes que ingresar un valor numerico valido", "Error",  JOptionPane.ERROR_MESSAGE);}

        Cilindro cilindroNew = new Cilindro(numeroSerialInt, TipoDeGas, dondeEsta, metrosInt);

        int confirmacion = JOptionPane.showConfirmDialog(this, "quieres crearlo ?", "creacion registro",  JOptionPane.YES_NO_OPTION);

        if(confirmacion == JOptionPane.YES_OPTION) {
            boolean exito = dao.insertarCilindro(cilindroNew);
            if(exito){
                JOptionPane.showMessageDialog(this, "Registro Creado", "Exito", JOptionPane.INFORMATION_MESSAGE);
            } else {JOptionPane.showMessageDialog(this, "Registro no creado como lo esperado", "Error", JOptionPane.ERROR_MESSAGE);}
        }

        cargarDatosTabla();
    }

    private void accionEditar() {
        int filaSelecionada = dibujadorDeTabla.getSelectedRow();

        int idAEditar = (int) tablaCilindros.getValueAt(filaSelecionada, 0);

        Cilindro cilindroOld = dao.getCilindro(idAEditar);

        String[] opciones = {"numero Serial", "Tipo de Gas", "Donde se encuentra", "metros del cilindro"};

        // El último argumento indica el tipo de mensaje (puede ser QUESTION_MESSAGE, PLAIN_MESSAGE, etc.)
        int eleccion = JOptionPane.showOptionDialog(
                this,           // Ventana padre (null si no está en un JFrame)
                "que campo desea actualizar del cilindro",        // Mensaje del diálogo
                "Edicion Cilindro",         // Título de la ventana
                JOptionPane.YES_NO_CANCEL_OPTION, // Tipo de opciones (para mostrar los botones definidos)
                JOptionPane.QUESTION_MESSAGE,     // Tipo de icono
                null,           // Icono personalizado (null para el predeterminado)
                opciones,       // ¡Array de Strings con los nombres de tus botones!
                opciones[0]     // Opción por defecto (la que tiene el foco)
        );

        boolean actualizacionExitosa = false;

        switch (eleccion) {
            case 0:
                String newNumberSerial =  JOptionPane.showInputDialog(this, "ingresa el numero serial nuevo para este cilindro: ");
                int newNumberSerialInt = 0;

                if(newNumberSerial != null) {
                    try {
                        newNumberSerialInt = Integer.parseInt(newNumberSerial);

                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Error: Debe ingresar un numero valido", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

                cilindroOld.setNumeroSerial(newNumberSerialInt);

                actualizacionExitosa = dao.getCilindroUpdate(cilindroOld);

                if(actualizacionExitosa){
                    JOptionPane.showMessageDialog(this, "El cilindro se ha actualizado con el nuevo numero serial " + cilindroOld.getNumeroSerial(), "Actualizacion", JOptionPane.INFORMATION_MESSAGE);
                }
                cargarDatosTabla();
                break;

            case 1:
                String tipoDeGasNew = JOptionPane.showInputDialog(this, "ingresa el tipo de gas para este cilindro");

                if(tipoDeGasNew != null) {
                    try {
                        cilindroOld.setTipoDeGas(tipoDeGasNew);
                    } catch (IllegalArgumentException e) {
                        JOptionPane.showMessageDialog(this, "hubo un error al actualizar el tipo de gas de este cilindro",  "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

                actualizacionExitosa = dao.getCilindroUpdate(cilindroOld);
                if(actualizacionExitosa){
                    JOptionPane.showMessageDialog(this, "El tipo de gas de este cilindro fue actualizado a " + tipoDeGasNew, "Actualizacion", JOptionPane.INFORMATION_MESSAGE);
                }
                cargarDatosTabla();
                break;

            case 2:
                String dondeEstaNew = JOptionPane.showInputDialog(this, "ingresa la nueva ubicacion de donde se encuentra el cilindro");

                if(dondeEstaNew != null) {
                    try {
                        cilindroOld.setDondeEsta(dondeEstaNew);
                    } catch (IllegalArgumentException e) {
                        JOptionPane.showMessageDialog(this, "hubo un error actualizando el donde se encuentra el ciindro" + e);
                    }

                    actualizacionExitosa = dao.getCilindroUpdate(cilindroOld);

                    if(actualizacionExitosa){
                        JOptionPane.showMessageDialog(this, "Se ha actualizado el donde se encuentra este cilindro a" + dondeEstaNew, "Actualizacion", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                cargarDatosTabla();
                break;

            case 3:
                String metrosNew = JOptionPane.showInputDialog(this, "ingresa los nuevos metros del cilindro:");
                int metrosInt = 0;

                if(metrosNew != null) {
                    metrosInt = Integer.parseInt(metrosNew);
                    cilindroOld.setMetros(metrosInt);
                    try {
                        actualizacionExitosa = dao.insertarCilindro(cilindroOld);
                    }catch (IllegalArgumentException e) {
                        JOptionPane.showMessageDialog(this, "No fue posible actualizar los metros del cilindro " + e,  "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

                if(actualizacionExitosa){
                    JOptionPane.showMessageDialog(this, "Los metros se actualizaron a: " + metrosInt);
                }

                cargarDatosTabla();
                break;
        }
    }

    //esta es el metodo que recolecta todos los registros y los coloca en una tabla
    private void cargarDatosTabla(){
        //Limpiar datos de la vieja tabla
        tablaCilindros.setRowCount(0);

        //obtener todos los cilindros del DAO
        List<Cilindro> cilindros = dao.Registros();

        for(Cilindro c : cilindros) {
            Object[] fila = new Object[columnas.length];
            fila[0] = c.getId();
            fila[1] = c.getNumeroSerial();
            fila[2] = c.getTipoDeGas();
            fila[3] = c.getDondeEsta();
            fila[4] = c.getMetros();

            tablaCilindros.addRow(fila);
        }

        System.out.println("Tabla recargada. Total registros " + cilindros.size());
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Dashboard());
    }
}