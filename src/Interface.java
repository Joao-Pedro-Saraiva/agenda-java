import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
 
public class Interface { 
    private JFrame menuPrincipal;
    private JFrame janelaAddContato;
    private JFrame janelaListarContatos;
    private JFrame janelaRemoverContato;
    private Agenda novaAgenda = new Agenda();
    private GridBagConstraints gbc = new GridBagConstraints();

    public Interface(){
        criarMenuPrincipal();
        novaAgenda.carregarContatos();
    }

    public void criarMenuPrincipal(){
        menuPrincipal = new JFrame("Agenda");
        menuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuPrincipal.setSize(400, 500);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 5, 10, 5);

        JPanel painel = new JPanel(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;        
        gbc.weightx = 1;
        gbc.gridwidth = 1;
        painel.add(new JLabel("MENU AGENDA"), gbc);
        
        gbc.gridy = 1;
        JButton adicionarContato = new JButton("Adicionar Contato");
        adicionarContato.addActionListener(e ->{
            menuPrincipal.dispose();
            criarAddContato();
        });
        painel.add(adicionarContato, gbc);

        gbc.gridy = 2;
        JButton listarContato = new JButton("Listar Contatos");
        listarContato.addActionListener(e->{
            menuPrincipal.dispose();
            criarListarContatos();
        });
        painel.add(listarContato, gbc);

        gbc.gridy = 3;
        JButton removerContato = new JButton("Remover Contato");
        removerContato.addActionListener(e ->{
            menuPrincipal.dispose();
            criarRemoverContato();
        });
        painel.add(removerContato, gbc);

        menuPrincipal.add(painel);
        menuPrincipal.setVisible(true);
    }

    public void criarAddContato(){
    janelaAddContato = new JFrame("Adicionar Contatos");
    janelaAddContato.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    janelaAddContato.setSize(400, 500);

    gbc.anchor = GridBagConstraints.CENTER;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(10, 5, 10, 5);

    JPanel addContato = new JPanel(new GridBagLayout());

    gbc.gridwidth = 1;
    gbc.gridy = 0;
    addContato.add(new JLabel("Nome: "), gbc);

    gbc.gridx = 1;
    JTextField nome = new JTextField();
    addContato.add(nome, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    addContato.add(new JLabel("Numero: "), gbc);

    gbc.gridx = 1;
    JTextField numero = new JTextField();
    addContato.add(numero, gbc);

    gbc.gridy = 2;
    JButton adcionar = new JButton("Adicionar Contato");
    adcionar.addActionListener(e ->{
        novaAgenda.adicionarContato(nome.getText(), numero.getText());
        nome.setText("");
        numero.setText("");
        novaAgenda.gravarContatos();
    });
    addContato.add(adcionar, gbc);

    gbc.gridx = 0;
    JButton voltar = new JButton("Voltar Menu");
    voltar.addActionListener(e ->{
        janelaAddContato.dispose();;
        criarMenuPrincipal();
    });
    addContato.add(voltar,gbc);

    janelaAddContato.add(addContato);
    janelaAddContato.setVisible(true);
    }

    public void criarListarContatos(){
        janelaListarContatos = new JFrame("Lista de Contatos");
        janelaListarContatos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaListarContatos.setSize(400, 500);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 5, 10, 5);

        JPanel listarContatos = new JPanel(new GridBagLayout());

        ArrayList<String> contatos = new ArrayList<>();
        contatos = novaAgenda.getContatos();
        int contador = 0;
        for(String contato : contatos){
            gbc.gridy = contador;
            listarContatos.add(new JLabel(contato), gbc);
            contador += 1;
        }

        gbc.gridy = contador;
        gbc.gridwidth = 2;
        JButton voltar = new JButton("Voltar Menu");
        voltar.addActionListener(e ->{
            janelaListarContatos.dispose();
            criarMenuPrincipal();
        });
        listarContatos.add(voltar, gbc);

        janelaListarContatos.add(listarContatos);
        janelaListarContatos.setVisible(true);
    }

    public void criarRemoverContato(){
        janelaRemoverContato = new JFrame("Remover Contato");
        janelaRemoverContato.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaRemoverContato.setSize(400, 500);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 5, 10, 5);

        JPanel removerContato = new JPanel(new GridBagLayout());

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        removerContato.add(new JLabel("Nome: "), gbc);
        gbc.gridx = 1;
        JTextField nome = new JTextField();
        removerContato.add(nome, gbc);

        gbc.gridy = 1;
        JButton remover = new JButton("Remover");
        remover.addActionListener(e ->{
            if(! nome.getText().isEmpty()){
                novaAgenda.removerContato(nome.getText());
            }
            novaAgenda.gravarContatos();
            nome.setText("");
        });
        removerContato.add(remover, gbc);

        gbc.gridx = 0;
        JButton voltarMenu = new JButton("Voltar Menu");
        voltarMenu.addActionListener(e ->{
            janelaRemoverContato.dispose();
            criarMenuPrincipal();
        });
        removerContato.add(voltarMenu, gbc);


        janelaRemoverContato.add(removerContato);
        janelaRemoverContato.setVisible(true);
    }    

    public static void main(String[] args){
        SwingUtilities.invokeLater(Interface::new);
    }
}