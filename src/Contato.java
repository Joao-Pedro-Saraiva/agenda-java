public class Contato implements Comparable<Contato>{
    private String nome;
    private String numero;

    public Contato(String nome, String numero){
        this.setNome(nome);
        this.setNumero(numero);
    } 

    @Override
    public String toString(){
        return "Contato: " + this.getNome() + " Numero: " + this.getNumero();
    }
    public int compareTo(Contato outro){
        return this.getNome().compareTo(outro.getNome());
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }

    
}
