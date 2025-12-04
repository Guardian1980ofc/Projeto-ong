package view;

import gerenciador.GerenciadorIdosos;
import model.Idoso;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Tela de Cadastro de Idoso
 */
public class TelaCadastroIdoso extends JFrame {

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

    private JLabel labelDataNascimento;
    private JTextField campoDataNascimento;

    private JLabel labelNecessidades;
    private JTextField campoNecessidades;

    private JLabel labelContatoEmergencia;
    private JTextField campoContatoEmergencia;

    private JLabel labelTelefoneEmergencia;
    private JTextField campoTelefoneEmergencia;

    private JButton btnSalvar;
    private JButton btnLimpar;
    private JButton btnVoltar;

    public TelaCadastroIdoso() {
        configurarJanela();
        inicializarComponentes();
        configurarLayout();
        configurarEventos();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Cadastrar Idoso");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void inicializarComponentes() {
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(null);
        painelPrincipal.setBackground(new Color(240, 240, 240));

        // Título
        labelTitulo = new JLabel("CADASTRAR IDOSO");
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

        labelDataNascimento = criarLabel("Data de Nascimento:* (dd/mm/aaaa)");
        campoDataNascimento = criarCampoTexto();
        campoDataNascimento.setToolTipText("Formato: 15/03/1950");

        labelNecessidades = criarLabel("Necessidades Especiais:");
        campoNecessidades = criarCampoTexto();

        labelContatoEmergencia = criarLabel("Contato de Emergência:");
        campoContatoEmergencia = criarCampoTexto();

        labelTelefoneEmergencia = criarLabel("Telefone de Emergência:");
        campoTelefoneEmergencia = criarCampoTexto();

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

        int y = 80;
        int espacamento = 60;

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

        // Data de Nascimento
        labelDataNascimento.setBounds(50, y, 400, 20);
        campoDataNascimento.setBounds(50, y + 25, 400, 35);
        painelPrincipal.add(labelDataNascimento);
        painelPrincipal.add(campoDataNascimento);
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

        // Necessidades Especiais
        labelNecessidades.setBounds(50, y, 400, 20);
        campoNecessidades.setBounds(50, y + 25, 400, 35);
        painelPrincipal.add(labelNecessidades);
        painelPrincipal.add(campoNecessidades);
        y += espacamento;

        // Contato de Emergência
        labelContatoEmergencia.setBounds(50, y, 400, 20);
        campoContatoEmergencia.setBounds(50, y + 25, 400, 35);
        painelPrincipal.add(labelContatoEmergencia);
        painelPrincipal.add(campoContatoEmergencia);
        y += espacamento;

        // Telefone de Emergência
        labelTelefoneEmergencia.setBounds(50, y, 400, 20);
        campoTelefoneEmergencia.setBounds(50, y + 25, 400, 35);
        painelPrincipal.add(labelTelefoneEmergencia);
        painelPrincipal.add(campoTelefoneEmergencia);
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
        btnSalvar.addActionListener(e -> salvarIdoso());
        btnLimpar.addActionListener(e -> limparCampos());
        btnVoltar.addActionListener(e -> voltar());
    }

    private void salvarIdoso() {
        if (!validarCampos()) {
            return;
        }

        String nome = campoNome.getText().trim();
        String cpf = campoCpf.getText().trim();
        String telefone = campoTelefone.getText().trim();
        String email = campoEmail.getText().trim();
        String endereco = campoEndereco.getText().trim();
        String necessidades = campoNecessidades.getText().trim();
        String contatoEmergencia = campoContatoEmergencia.getText().trim();
        String telefoneEmergencia = campoTelefoneEmergencia.getText().trim();

        LocalDate dataNascimento;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataNascimento = LocalDate.parse(campoDataNascimento.getText().trim(), formatter);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this,
                    "Data de nascimento inválida!\nUse o formato: dd/mm/aaaa",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            campoDataNascimento.requestFocus();
            return;
        }

        Idoso idoso = new Idoso(nome, cpf, telefone, email, endereco,
                dataNascimento, contatoEmergencia, telefoneEmergencia);
        idoso.setNecessidadesEspeciais(necessidades);

        try {
            GerenciadorIdosos.adicionar(idoso);

            JOptionPane.showMessageDialog(this,
                    "Idoso cadastrado com sucesso!\n" +
                            "Nome: " + nome + "\n" +
                            "CPF: " + cpf + "\n" +
                            "Idade: " + idoso.getIdade() + " anos",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            limparCampos();
            campoNome.requestFocus();

        } catch (exception.CPFDuplicadoException e) {
            JOptionPane.showMessageDialog(this,
                    "Erro: " + e.getMessage() + "\n" +
                            "Este CPF já está cadastrado!",
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

        // Validar formato CPF
        String cpfNumeros = cpf.replaceAll("[^0-9]", "");
        if (cpfNumeros.length() != 11) {
            JOptionPane.showMessageDialog(this,
                    "CPF inválido! Deve conter 11 dígitos.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            campoCpf.requestFocus();
            return false;
        }

        // Validar data de nascimento
        if (campoDataNascimento.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "O campo DATA DE NASCIMENTO é obrigatório!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            campoDataNascimento.requestFocus();
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
        campoDataNascimento.setText("");
        campoNecessidades.setText("");
        campoContatoEmergencia.setText("");
        campoTelefoneEmergencia.setText("");
        campoNome.requestFocus();
    }

    private void voltar() {
        dispose();
    }
}