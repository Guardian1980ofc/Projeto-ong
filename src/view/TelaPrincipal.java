package view;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    private JPanel painelPrincipal;
    private JLabel labelTitulo;
    private JButton btnCadastrarDoador;
    private JButton btnCadastrarIdoso;
    private JButton btnCadastrarFuncionario;
    private JButton btnRegistrarDoacao;
    private JButton btnListarDoadores;
    private JButton btnListarIdosos;
    private JButton btnListarFuncionarios;
    private JButton btnListarDoacoes;
    private JButton btnRelatorios;
    private JButton btnSair;
    private JButton btnSalvarDados; // NOVO BOTÃO

    public TelaPrincipal() {
        configurarJanela();
        inicializarComponentes();
        configurarLayout();
        configurarEventos();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Sistema ONG - Gestão de Idosos");
        setSize(500, 700); // Aumentei para 700 para caber o novo botão
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void inicializarComponentes() {
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(null);
        painelPrincipal.setBackground(new Color(240, 240, 240));

        // Título
        labelTitulo = new JLabel("SISTEMA ONG - GESTÃO DE IDOSOS");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        labelTitulo.setForeground(new Color(41, 128, 185));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        // Botões de Cadastro
        btnCadastrarDoador = criarBotao("Cadastrar Doador", new Color(46, 204, 113));
        btnCadastrarIdoso = criarBotao("Cadastrar Idoso", new Color(46, 204, 113));
        btnCadastrarFuncionario = criarBotao("Cadastrar Funcionário", new Color(46, 204, 113));
        btnRegistrarDoacao = criarBotao("Registrar Doação", new Color(52, 152, 219));

        // Botões de Listagem
        btnListarDoadores = criarBotao("Listar Doadores", new Color(155, 89, 182));
        btnListarIdosos = criarBotao("Listar Idosos", new Color(155, 89, 182));
        btnListarFuncionarios = criarBotao("Listar Funcionários", new Color(155, 89, 182));
        btnListarDoacoes = criarBotao("Listar Doações", new Color(155, 89, 182));

        // Botões Especiais
        btnRelatorios = criarBotao("Relatórios", new Color(230, 126, 34));
        btnSair = criarBotao("Sair", new Color(231, 76, 60));
        btnSalvarDados = criarBotao("Salvar Dados em TXT", new Color(52, 152, 219)); // NOVO
    }

    private JButton criarBotao(String texto, Color cor) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(cor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(cor);
            }
        });

        return botao;
    }

    private void configurarLayout() {
        // Título
        labelTitulo.setBounds(50, 20, 400, 30);
        painelPrincipal.add(labelTitulo);

        // Separador visual
        JSeparator separador1 = new JSeparator();
        separador1.setBounds(50, 60, 400, 2);
        painelPrincipal.add(separador1);

        // Label "Cadastros"
        JLabel lblCadastros = new JLabel("CADASTROS");
        lblCadastros.setFont(new Font("Arial", Font.BOLD, 12));
        lblCadastros.setBounds(50, 75, 400, 20);
        painelPrincipal.add(lblCadastros);

        // Botões de cadastro
        btnCadastrarDoador.setBounds(50, 100, 400, 45);
        btnCadastrarIdoso.setBounds(50, 155, 400, 45);
        btnCadastrarFuncionario.setBounds(50, 210, 400, 45);
        btnRegistrarDoacao.setBounds(50, 265, 400, 45);

        painelPrincipal.add(btnCadastrarDoador);
        painelPrincipal.add(btnCadastrarIdoso);
        painelPrincipal.add(btnCadastrarFuncionario);
        painelPrincipal.add(btnRegistrarDoacao);

        // Separador
        JSeparator separador2 = new JSeparator();
        separador2.setBounds(50, 325, 400, 2);
        painelPrincipal.add(separador2);

        // Label "Listagens"
        JLabel lblListagens = new JLabel("LISTAGENS");
        lblListagens.setFont(new Font("Arial", Font.BOLD, 12));
        lblListagens.setBounds(50, 335, 400, 20);
        painelPrincipal.add(lblListagens);

        // Botões de listagem
        btnListarDoadores.setBounds(50, 360, 400, 45);
        btnListarIdosos.setBounds(50, 415, 400, 45);
        btnListarFuncionarios.setBounds(50, 470, 400, 45);
        btnListarDoacoes.setBounds(50, 525, 400, 45);

        painelPrincipal.add(btnListarDoadores);
        painelPrincipal.add(btnListarIdosos);
        painelPrincipal.add(btnListarFuncionarios);
        painelPrincipal.add(btnListarDoacoes);

        // Separador
        JSeparator separador3 = new JSeparator();
        separador3.setBounds(50, 580, 400, 2);
        painelPrincipal.add(separador3);

        // Botões da parte inferior
        btnRelatorios.setBounds(50, 590, 195, 35);
        painelPrincipal.add(btnRelatorios);

        btnSair.setBounds(255, 590, 195, 35);
        painelPrincipal.add(btnSair);

        // NOVO BOTÃO - Salvar Dados em TXT
        btnSalvarDados.setBounds(50, 635, 400, 35);
        painelPrincipal.add(btnSalvarDados);

        add(painelPrincipal);
    }

    private void configurarEventos() {
        // Cadastros
        btnCadastrarDoador.addActionListener(e -> abrirCadastroDoador());
        btnCadastrarIdoso.addActionListener(e -> abrirCadastroIdoso());
        btnCadastrarFuncionario.addActionListener(e -> abrirCadastroFuncionario());
        btnRegistrarDoacao.addActionListener(e -> abrirRegistroDoacao());

        // Listagens
        btnListarDoadores.addActionListener(e -> abrirListagemDoadores());
        btnListarIdosos.addActionListener(e -> abrirListagemIdosos());
        btnListarFuncionarios.addActionListener(e -> abrirListagemFuncionarios());
        btnListarDoacoes.addActionListener(e -> abrirListagemDoacoes());

        // Especiais
        btnRelatorios.addActionListener(e -> abrirRelatorios());
        btnSair.addActionListener(e -> sair());
        btnSalvarDados.addActionListener(e -> salvarDadosTXT()); // NOVO EVENTO
    }

    // MÉTODOS DE NAVEGAÇÃO

    private void abrirCadastroDoador() {
        new TelaCadastroDoador();
    }

    private void abrirCadastroIdoso() {
        new TelaCadastroIdoso();
    }

    private void abrirCadastroFuncionario() {
        new TelaCadastroFuncionario();
    }

    private void abrirRegistroDoacao() {
        new TelaCadastroDoacao();
    }

    private void abrirListagemDoadores() {
        new TelaListagemDoadores();
    }

    private void abrirListagemIdosos() {
        new TelaListagemIdosos();
    }

    private void abrirListagemFuncionarios() {
        new TelaListagemFuncionarios();
    }

    private void abrirListagemDoacoes() {
        new TelaListagemDoacoes();
    }

    private void abrirRelatorios() {
        new TelaRelatorios();
    }

    //  salvar dados em arquivos TXT
    private void salvarDadosTXT() {
        util.SalvarDados.salvarTudo();
        util.SalvarDados.gerarRelatorioDiario();
        util.SalvarDados.registrarLog("Usuário salvou dados manualmente");

        JOptionPane.showMessageDialog(this,
                "✅ Dados salvos em arquivos TXT!\n\n" +
                        "📄 doadores.txt\n" +
                        "📄 doacoes.txt\n" +
                        "📄 relatorio_" + java.time.LocalDate.now() + ".txt\n" +
                        "📄 log_sistema.txt\n\n" +
                        "Os arquivos foram gerados na pasta do projeto.",
                "Salvamento Concluído",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void sair() {
        int opcao = JOptionPane.showConfirmDialog(this,
                "Deseja salvar os dados antes de sair?",
                "Confirmar Saída",
                JOptionPane.YES_NO_CANCEL_OPTION);

        if (opcao == JOptionPane.YES_OPTION) {
            // Salva e depois sai
            salvarDadosTXT();
            util.SalvarDados.registrarLog("Sistema encerrado com salvamento");
            System.exit(0);

        } else if (opcao == JOptionPane.NO_OPTION) {
            // Sai sem salvar
            util.SalvarDados.registrarLog("Sistema encerrado sem salvamento");
            System.exit(0);
        }
        // Se CANCELAR, não faz nada (fica na tela)
    }
}