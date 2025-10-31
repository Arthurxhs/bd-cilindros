public class Cilindro {
    //static significa que es parametro de la clase no de cada objeto
    //static int contadorId = 0;
    //final una vez asignado no puede ser cambiado
    /*final*/ int iD;
    int numeroSerial;
    String tipoDeGas;
    String dondeEsta;
    int metros;

    public Cilindro(int numeroSerial,  String tipoDeGas, String dondeEsta, int metros) {

        //this.iD = ++contadorId;
        this.numeroSerial = numeroSerial;
        this.tipoDeGas = tipoDeGas;
        this.dondeEsta = dondeEsta;
        this.metros = metros;

    }

    //son metodos predefinidos que no usan variables no estaticas
    public static Cilindro crearCilindroDeOxigeno(){
        return new Cilindro(123123, "oxigeno", "bodega", 6);
    }

    public void setCilindro(int pNumeroSerial, String pTipoDeGas, String pDondeEsta, int pMetros){
        numeroSerial = pNumeroSerial;
        tipoDeGas = pTipoDeGas;
        dondeEsta = pDondeEsta;
        metros = pMetros;
    }

    @Override
    //defaultmente el toString devuelve el id del objeto no mas, pero id de java no el de la instancia
    //lo sobreescribo para que ahora muestre esto
    public String toString(){
        return "\n Cilindro: " +
                "\n id: " + iD +
                "\n Numero Serial: " + numeroSerial +
                "\n Tipo De Gas: " + tipoDeGas +
                "\n Donde Esta: " + dondeEsta +
                "\n Metros: " + metros;
    }

    /*public Cilindro getCilindro(){
        return this;
    }*/

    public int getNumeroSerial() {
        return numeroSerial;
    }

    public String getTipoDeGas() {
        return  tipoDeGas;
    }

    public void setTipoDeGas(String tipoDeGas) {
        this.tipoDeGas = tipoDeGas;
    }

    public String getDondeEsta() {
        return dondeEsta;
    }

    public void setId(int id){
        this.iD = id;
    }

    public int getId(){
        return iD;
    }

    public void  setDondeEsta(String dondeEsta) {this.dondeEsta = dondeEsta;}

    public int getMetros() {return metros;}

    public void setMetros(int metros) {this.metros = metros;}

    public void setNumeroSerial(int pNumeroSerial) {numeroSerial =  pNumeroSerial;}
}
