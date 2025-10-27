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
        System.out.println("0. Salir del programa");
    }
}