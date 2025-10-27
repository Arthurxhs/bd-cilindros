import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Basededatos bd = new Basededatos();

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

                    bd.agregarRegistro(numeroSerial, tipoGas, donde, metros);

                    System.out.println("Este es el cilindro recien registrado: " + bd.getCilindro(bd.size()));
                    break;

                case 2:
                    System.out.println("Todos los cilindros actualmente en la base de datos son:");
                    if(bd.size() == 0) {
                        System.out.println("No tienes ningun cilindro registrado actualmente");
                    } else {
                        bd.listaDeCilindro();
                    }
                    break;


                case 3:
                    System.out.println("Ingrese el id del cilindro al que quiere editar/modificar");

                    int searchId = sc.nextInt();

                    Cilindro cilindroEdicion = bd.getCilindro(searchId);

                    if (cilindroEdicion != null) {
                        subMenu2();

                        int opcionElegida2 = sc.nextInt();
                        sc.nextLine();

                        switch (opcionElegida2) {

                            case 1:
                                System.out.println("Numero serial actual: " +  cilindroEdicion.getNumeroSerial());
                                System.out.println("Ingresa el numero serial nuevo: ");

                                int numeroSerialNew = sc.nextInt();
                                sc.nextLine();

                                cilindroEdicion.setNumeroSerial(numeroSerialNew);

                                System.out.println("Numero Serial Actualizado a: " + cilindroEdicion.getNumeroSerial());
                                break;

                            case 2:
                                System.out.println("Tipo de Gas actual del cilindro a: " + cilindroEdicion.getTipoDeGas());
                                System.out.println("Ingresa el nuevo tipo de gas del cilindro: ");
                                String tipoGasNew = sc.nextLine();

                                cilindroEdicion.setTipoDeGas(tipoGasNew);

                                System.out.println("El tipo de gas del cilindro se ha actualizado a: " + cilindroEdicion.getTipoDeGas());
                                break;

                            case 3:
                                System.out.println("El cilindro se encuentra actualmente en: " + cilindroEdicion.getDondeEsta());
                                System.out.println("Ingresa el nuevo lugar donde se encuentra el cilindro: ");
                                String lugarNew = sc.nextLine();

                                cilindroEdicion.setDondeEsta(lugarNew);

                                System.out.println("Se ha actualizado el lugar donde se encuentra el cilindro: " + cilindroEdicion.getDondeEsta());
                                break;

                            case 4:
                                System.out.println("El cilindro es de " + cilindroEdicion.getMetros() + " metros");
                                System.out.println("Ingresa los metros del cilindro nuevamente: ");

                                int metrosNew = sc.nextInt();

                                cilindroEdicion.setMetros(metrosNew);

                                System.out.println("Los metros del cilindro se han actualizado correctamente a: " + cilindroEdicion.getMetros());
                                break;

                            default:
                                System.out.println("Ingresaste una opcion invalida");

                        }

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