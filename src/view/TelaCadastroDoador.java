package view;

import gerenciador.GerenciadorDoadores;
import model.Doador;

import javax.swing.*;
import java.awt.*;

/**
 * Tela de Cadastro de Doador
 */
public class TelaCadastroDoador extends JFrame {

    // Componentes
    private JPanel painelPrincipal;
    private JLabel labelTitulo;

    private JLabel labelNome;
    private JTextField campoNome;

    private JLabel labelCpf;
    private JTextField campoCpf;

    private JLabel labelTelefone;
    private JTextField campoTelefone;

    private JLabel labelEmail;
    private JTextField campoEmail;

    private JLabel labelEndereco;
    private JTextField campoEndereco;

    private JLabel labelTipoPessoa;
    private JRadioButton radioFisica;
    private JRadioButton radioJuridica;
    private ButtonGroup grupoTipoPessoa;

    private JButton btnSalvar;
    private JButton btnLimpar;
    private JButton btnVoltar;

    public TelaCadastroDoador() {
        configurarJanela();
        inicializarComponentes();
        configurarLayout();
        configurarEventos();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Cadastrar Doador");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha só esta janela
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void inicializarComponentes() {
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(null);
        painelPrincipal.setBackground(new Color(240, 240, 240));

        // Título
        labelTitulo = new JLabel("CADASTRAR DOADOR");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitulo.setForeground(new Color(46, 204, 113));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        // Labels e Campos
        labelNome = criarLabel("Nome Completo:*");
        campoNome = criarCampoTexto();

        labelCpf = criarLabel("CPF:*");
        campoCpf = criarCampoTexto();

        labelTelefone = criarLabel("Telefone:");
        campoTelefone = criarCampoTexto();

        labelEmail = criarLabel("Email:");
        campoEmail = criarCampoTexto();

        labelEndereco = criarLabel("Endereço:");
        campoEndereco = criarCampoTexto();

        labelTipoPessoa = criarLabel("Tipo de Pessoa:*");

        // Radio Buttons
        radioFisica = new JRadioButton("Pessoa Física");
        radioFisica.setFont(new Font("Arial", Font.PLAIN, 13));
        radioFisica.setBackground(new Color(240, 240, 240));
        radioFisica.setSelected(true); // Padrão selecionado

        radioJuridica = new JRadioButton("Pessoa Jurídica");
        radioJuridica.setFont(new Font("Arial", Font.PLAIN, 13));
        radioJuridica.setBackground(new Color(240, 240, 240));

        // Agrupar radio buttons (apenas um pode ser selecionado)
        grupoTipoPessoa = new ButtonGroup();
        grupoTipoPessoa.add(radioFisica);
        grupoTipoPessoa.add(radioJuridica);

        // Botões
        btnSalvar = criarBotao("Salvar", new Color(46, 204, 113));
        btnLimpar = criarBotao("Limpar", new Color(52, 152, 219));
        btnVoltar = criarBotao("Voltar", new Color(149, 165, 166));
    }

    private JLabel criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.BOLD, 13));
        label.setForeground(new Color(52, 73, 94));
        return label;
    }

    private JTextField criarCampoTexto() {
        JTextField campo = new JTextField();
        campo.setFont(new Font("Arial", Font.PLAIN, 13));
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return campo;
    }

    private JButton criarBotao(String texto, Color cor) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 13));
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efeito hover
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

        // Separador
        JSeparator separador = new JSeparator();
        separador.setBounds(50, 60, 400, 2);
        painelPrincipal.add(separador);

        int y = 80; // Posição Y inicial
        int espacamento = 60; // Espaço entre campos

        // Nome
        labelNome.setBounds(50, y, 400, 20);
        campoNome.setBounds(50, y + 25, 400, 35);
        painelPrincipal.add(labelNome);
        painelPrincipal.add(campoNome);
        y += espacamento;

        // CPF
        labelCpf.setBounds(50, y, 400, 20);
        campoCpf.setBounds(50, y + 25, 400, 35);
        painelPrincipal.add(labelCpf);
        painelPrincipal.add(campoCpf);
        y += espacamento;

        // Telefone
        labelTelefone.setBounds(50, y, 400, 20);
        campoTelefone.setBounds(50, y + 25, 400, 35);
        painelPrincipal.add(labelTelefone);
        painelPrincipal.add(campoTelefone);
        y += espacamento;

        // Email
        labelEmail.setBounds(50, y, 400, 20);
        campoEmail.setBounds(50, y + 25, 400, 35);
        painelPrincipal.add(labelEmail);
        painelPrincipal.add(campoEmail);
        y += espacamento;

        // Endereço
        labelEndereco.setBounds(50, y, 400, 20);
        campoEndereco.setBounds(50, y + 25, 400, 35);
        painelPrincipal.add(labelEndereco);
        painelPrincipal.add(campoEndereco);
        y += espacamento;

        // Tipo de Pessoa
        labelTipoPessoa.setBounds(50, y, 400, 20);
        radioFisica.setBounds(50, y + 25, 150, 25);
        radioJuridica.setBounds(210, y + 25, 150, 25);
        painelPrincipal.add(labelTipoPessoa);
        painelPrincipal.add(radioFisica);
        painelPrincipal.add(radioJuridica);
        y += 60;

        // Nota de campos obrigatórios
        JLabel labelObrigatorio = new JLabel("* Campos obrigatórios");
        labelObrigatorio.setFont(new Font("Arial", Font.ITALIC, 11));
        labelObrigatorio.setForeground(Color.GRAY);
        labelObrigatorio.setBounds(50, y, 200, 20);
        painelPrincipal.add(labelObrigatorio);
        y += 30;

        // Botões
        btnSalvar.setBounds(50, y, 130, 40);
        btnLimpar.setBounds(190, y, 130, 40);
        btnVoltar.setBounds(330, y, 120, 40);
        painelPrincipal.add(btnSalvar);
        painelPrincipal.add(btnLimpar);
        painelPrincipal.add(btnVoltar);

        add(painelPrincipal);
    }

    private void configurarEventos() {
        btnSalvar.addActionListener(e -> salvarDoador());
        btnLimpar.addActionListener(e -> limparCampos());
        btnVoltar.addActionListener(e -> voltar());
    }

    private void salvarDoador() {
        // 1. Validar campos obrigatórios
        if (!validarCampos()) {
            return;
        }

        // 2. Pegar valores dos campos
        String nome = campoNome.getText().trim();
        String cpf = campoCpf.getText().trim();
        String telefone = campoTelefone.getText().trim();
        String email = campoEmail.getText().trim();
        String endereco = campoEndereco.getText().trim();
        String tipoPessoa = radioFisica.isSelected() ? "FISICA" : "JURIDICA";

        // 3. Criar objeto Doador
        Doador doador = new Doador(nome, cpf, telefone, email, endereco, tipoPessoa);

        // 4. Tentar adicionar usando o Gerenciador COM EXCEÇÕES PERSONALIZADAS
        try {
            GerenciadorDoadores.adicionar(doador);

            JOptionPane.showMessageDialog(this,
                    "Doador cadastrado com sucesso!\n" +
                            "Nome: " + nome + "\n" +
                            "CPF: " + cpf,
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            limparCampos();
            campoNome.requestFocus();

        } catch (exception.CPFDuplicadoException e) {
            JOptionPane.showMessageDialog(this,
                    "Erro: " + e.getMessage() + "\n" +
                            "Este CPF já está cadastrado no sistema!",
                    "CPF Duplicado",
                    JOptionPane.ERROR_MESSAGE);
            campoCpf.requestFocus();

        } catch (exception.DadosInvalidosException e) {
            JOptionPane.showMessageDialog(this,
                    "Erro: " + e.getMessage(),
                    "Dados Inválidos",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validarCampos() {
        // Validar nome
        if (campoNome.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "O campo NOME é obrigatório!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            campoNome.requestFocus();
            return false;
        }

        // Validar CPF
        String cpf = campoCpf.getText().trim();
        if (cpf.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "O campo CPF é obrigatório!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            campoCpf.requestFocus();
            return false;
        }

        // Validar formato CPF (apenas números e tamanho)
        String cpfNumeros = cpf.replaceAll("[^0-9]", "");
        if (cpfNumeros.length() != 11) {
            JOptionPane.showMessageDialog(this,
                    "CPF inválido! Deve conter 11 dígitos.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            campoCpf.requestFocus();
            return false;
        }

        return true;
    }

    private void limparCampos() {
        campoNome.setText("");
        campoCpf.setText("");
        campoTelefone.setText("");
        campoEmail.setText("");
        campoEndereco.setText("");
        radioFisica.setSelected(true);
        campoNome.requestFocus();
    }

    private void voltar() {
        dispose(); // Fecha esta janela
    }
}