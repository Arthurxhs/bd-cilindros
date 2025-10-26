import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Hola esta es la base de datos de Oxigas del tolima para guardar y verificar sus cilindros");

        boolean programaActivo = true;
        while (programaActivo) {
            System.out.println("1. Agregar cilindro");
            //System.out.println("Editar cilindro");
            //System.out.println("Eliminar cilindro");
            System.out.println("2. Ver todos los cilindros actuales");

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

                    Basededatos bd = new Basededatos();
                    bd.agregarRegistro(numeroSerial, tipoGas, donde, metros);

                    System.out.println("Este es el cilindro recien registrado: " + bd.getCilindro(1));

                    System.out.println("que quieres hacer ahora: ");
                    System.out.println("1. crear un numero cilindro ");
                    System.out.println("2. Actualizar un cilindro en particular");

                    int opcionElegida2 = sc.nextInt();

                    switch(opcionElegida2) {
                        case 1:
                            System.out.println("Ingrese el id del cilindro");
                            int numeroSerialxd = sc.nextInt();

                            sc.nextLine();

                            System.out.println("Ingrese el tipo de gas del cilindro");
                            String tipoGasxd = sc.nextLine();

                            System.out.println("Ingrese donde se encuentra el cilindro");
                            String dondexd = sc.nextLine();

                            System.out.println("Ingrese de cuantos metros es el cilindro");
                            int metrosxd = sc.nextInt();

                            bd.agregarRegistro(numeroSerialxd, tipoGasxd, dondexd, metrosxd);

                            System.out.println("Este es el cilindro recien registrado: " + bd.getCilindro(2));

                            System.out.println("que quieres hacer ahora: ");
                            System.out.println("1. Mostrar bd completa");

                            int opcionElegida3 = sc.nextInt();

                            switch(opcionElegida3) {
                                case 1:
                                    System.out.println("Estos son los Cilindros actuales en la bd");
                                    bd.listaDeCilindro();
                                    break;
                            }
                    }

                case 2:
                    //volverlo para que sea actualizado mediante id
                    System.out.println("Todos los cilindros actualmente en la base de datos son:");
                    //System.out.println(cilindroxd);

                    programaActivo = false;
                    break;
            }

        }

    }
}