/*public class ArrayList {
    java.util.ArrayList<Cilindro> cilindros =  new java.util.ArrayList<>();

    //el metodo constructor es el default, siempre hay uno default para cada clase

    //metodos void no devuelven valores si no que hacen logica
    public void agregarRegistro(int numeroSerial, String tipoDeGas, String dondeEsta, int metros){
        Cilindro Cilindro = new Cilindro(numeroSerial, tipoDeGas, dondeEsta, metros);

        cilindros.add(Cilindro);

        System.out.println("Cilindro agregado");
    }

    public Cilindro getCilindro(int iD){
        for(Cilindro cilindro : cilindros){
            if(cilindro.getiD() == iD){
                return cilindro;
            }
        }
        return null;
    }

    public void listaDeCilindro(){
        for(Cilindro cilindro : cilindros){
            System.out.println(cilindro);
        }
    }


    public int size(){
        return cilindros.size();
    }

    /*
    // Cambia 'void' por 'String'
public String listaDeCilindro(){
    StringBuilder sb = new StringBuilder(); // Usamos StringBuilder para eficiencia
    sb.append("\n--- Lista de Cilindros ---\n");

    for(Cilindro cilindro : cilindros){
        sb.append(cilindro.toString()).append("\n"); // Agrega cada cilindro
    }

    return sb.toString(); // Devuelve la cadena completa
}
     */