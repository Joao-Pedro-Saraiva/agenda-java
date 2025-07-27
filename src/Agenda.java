import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Agenda {
    private ArrayList<Contato> contatos = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    public void adicionarContato(String nome, String numero){
        Contato novoContato = new Contato(nome, numero);
        contatos.add(novoContato);
    }
    public boolean removerContato(String nome){
        int contador = 0;
        for(Contato contato : contatos){
            if(contato.getNome().contains(nome)){
                System.out.println("Contato Removido");
                contatos.remove(contador);
                return true;
            }
            contador += 1;
        }
        return false;
        }

    public String buscarContato(){
        String nome = input.nextLine();
        String buscado = "Contato não encontrado";
        for(Contato contato : contatos){
            if(contato.getNome().contains(nome)){
                buscado = contato.toString();
            }
        }
        return buscado;
    }

    public void listarContatos(){
        contatos.sort(null);
        for(Contato contato : contatos){
            System.out.println(contato.getNome());
        }
    }
    public void gravarContatos(){
        try{
            FileWriter dados = new FileWriter("Dados.txt");
            for(Contato contato : contatos){
                dados.write(contato.getNome()+";"+contato.getNumero() + "\n");
            }
            dados.close();
        } catch (IOException e){
            System.out.println("Erro na gravação dos contatos");
            e.printStackTrace();
        }
    }
    public void carregarContatos(){
        try{
            File dados = new File("Dados.txt");
            Scanner leitor = new Scanner(dados);
            while(leitor.hasNextLine()){
                String[] contato = leitor.nextLine().split(";");
                Contato novoContato = new Contato(contato[0], contato[1]);
                contatos.add(novoContato);
            }
            leitor.close();
        } catch(IOException e){
            System.out.println("Erro ao carregar contatos");
            e.printStackTrace();
        }
    }
    public ArrayList<String> getContatos(){
        contatos.sort(null);
        ArrayList<String> dados= new ArrayList<>();
        for(Contato contato : contatos){
            dados.add(contato.toString());
        }
        return dados;
    }
}  
