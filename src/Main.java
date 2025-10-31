import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        //ArrayList arrayList = new ArrayList();

        System.out.println("Hola esta es la base de datos de Oxigas del tolima para guardar y verificar sus cilindros");

        while (true) {

            menu();

            int opcionElegida = sc.nextInt();

            switch (opcionElegida) {

                case 1:
                    System.out.println("Ingrese el id del cilindro");
                    int numeroSerial = sc.nextInt();

                    sc.nextLine();

                    System.out.println("Ingrese el tipo de gas del cilindro");
                    String tipoGas = sc.nextLine();

                    System.out.println("Ingrese donde se encuentra el cilindro");
                    String donde = sc.nextLine();

                    System.out.println("Ingrese de cuantos metros es el cilindro");
                    int metros = sc.nextInt();

                    //el arrayList crea la instancia de cilindro dentro de su metodo
                    //arrayList.agregarRegistro(numeroSerial, tipoGas, donde, metros);

                    //crea el cilindro donde se guardaran los datos del cilindro
                    Cilindro cilindro = new Cilindro(numeroSerial, tipoGas, donde, metros);
                    //crea la instancia de la clase donde se encuentra el metodo que necesitamos
                    CilindroDao cilindroDao = new CilindroDao();
                    /*aqui guardamos el resultado que nos devuelve el metodo que es de tipo boolean
                    y necesita a la instancia cilindro como parametro, la clase cilindro dao solo
                    existe para acceder al metodo que establecimos, y el metodo accede a la interfaz Connection*/
                    boolean operacion = cilindroDao.insertarCilindro(cilindro);

                    if(operacion) {
                        System.out.println("Cilindro insertado exitosamente");
                    } else {
                        System.out.println("No se pudo insertar el cilindro");
                    }

                    //System.out.println("Este es el cilindro recien registrado: " + arrayList.getCilindro(arrayList.size()));
                    System.out.println("Este es el cilindro recien registrado: " + cilindroDao.getCilindro(numeroSerial));
                    break;

                case 2:
                    System.out.println("Todos los cilindros actualmente en la base de datos son:");
                    /*if(arrayList.size() == 0) {
                        System.out.println("No tienes ningun cilindro registrado actualmente");
                    } else {
                        arrayList.listaDeCilindro();
                    }*/

                    CilindroDao cilindroDao2 = new CilindroDao();
                    int total = cilindroDao2.getTotalRegistros();
                    List<Cilindro> listaTotal = cilindroDao2.Registros();

                    if( total > 0) {
                        for (Cilindro objectCilindro : listaTotal) {
                            System.out.println(objectCilindro.toString());
                        }
                    } else {
                        System.out.println("No hay ningun cilindro registrado en la  base de datos");
                    }
                    break;


                case 3:
                    System.out.println("Ingrese el id del cilindro al que quiere editar/modificar");
                    int searchId = sc.nextInt();

                    CilindroDao cilindroDao3 = new CilindroDao();
                    Cilindro cilindroBuscado = cilindroDao3.getCilindro(searchId);

                    if (cilindroBuscado != null) {
                        subMenu2();

                        int opcionElegida2 = sc.nextInt();
                        sc.nextLine();

                        switch (opcionElegida2) {

                            case 1:
                                System.out.println("Numero serial actual: " + cilindroBuscado.getNumeroSerial());
                                System.out.println("Ingresa el numero serial nuevo: ");

                                int numeroSerialNew = sc.nextInt();
                                sc.nextLine();

                                cilindroBuscado.setNumeroSerial(numeroSerialNew);

                                System.out.println("Numero Serial Actualizado a: " + cilindroBuscado.getNumeroSerial());
                                break;

                            case 2:
                                System.out.println("Tipo de Gas actual del cilindro a: " + cilindroBuscado.getTipoDeGas());
                                System.out.println("Ingresa el nuevo tipo de gas del cilindro: ");
                                String tipoGasNew = sc.nextLine();

                                cilindroBuscado.setTipoDeGas(tipoGasNew);

                                System.out.println("El tipo de gas del cilindro se ha actualizado a: " + cilindroBuscado.getTipoDeGas());
                                break;

                            case 3:
                                System.out.println("El cilindro se encuentra actualmente en: " + cilindroBuscado.getDondeEsta());
                                System.out.println("Ingresa el nuevo lugar donde se encuentra el cilindro: ");
                                String lugarNew = sc.nextLine();

                                cilindroBuscado.setDondeEsta(lugarNew);

                                System.out.println("Se ha actualizado el lugar donde se encuentra el cilindro: " + cilindroBuscado.getDondeEsta());
                                break;

                            case 4:
                                System.out.println("El cilindro es de " + cilindroBuscado.getMetros() + " metros");
                                System.out.println("Ingresa los metros del cilindro nuevamente: ");

                                int metrosNew = sc.nextInt();

                                cilindroBuscado.setMetros(metrosNew);

                                System.out.println("Los metros del cilindro se han actualizado correctamente a: " + cilindroBuscado.getMetros());
                                break;

                            default:
                                System.out.println("Ingresaste una opcion invalida");

                        }

                        //actualizacion del objeto
                        boolean exito = cilindroDao3.getCilindroUpdate(cilindroBuscado);
                        if(exito) {
                            System.out.println("Se pudo actualizar el cilindro en la bd");
                        }

                        //Mostrar el cilindro actualizado
                        Cilindro cilindroActualizado = cilindroDao3.getCilindro(searchId);
                        System.out.println(cilindroActualizado.toString());

                    } else {
                        System.out.println("No hay un cilindro registrado con ese id");
                    };


                break;

                case 0:
                    return;//devuelve un valor null

                default:
                    System.out.println("No has ingresado una opcion valida, por favor");
                    break;
            }

        }

    }

    public static void menu() {
        System.out.println("1. Agregar cilindro");
        System.out.println("2. Ver todos los cilindros actuales");
        System.out.println("3. Editar Cilindro");
        System.out.println("0. Salir del programa");
    }

    public static void subMenu2() {
        System.out.println("Que quieres modificar del cilindro: ");

        System.out.println("1. Numero Serial");
        System.out.println("2. Tipo de gas");
        System.out.println("3. Donde se encuentra");
        System.out.println("4. De cuantos metros es");
        System.out.println("0. Retroceder");
    }
}