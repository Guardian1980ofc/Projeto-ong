package view;

import gerenciador.GerenciadorFuncionarios;
import model.Funcionario;
import model.Turnos;

import javax.swing.*;
import java.awt.*;

/**
 * Tela de Cadastro de Funcionário
 */
public class TelaCadastroFuncionario extends JFrame {

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

    private JLabel labelCargo;
    private JTextField campoCargo;

    private JLabel labelSalario;
    private JTextField campoSalario;

    private JLabel labelTurno;
    private JComboBox<String> comboTurno;

    private JButton btnSalvar;
    private JButton btnLimpar;
    private JButton btnVoltar;

    public TelaCadastroFuncionario() {
        configurarJanela();
        inicializarComponentes();
        configurarLayout();
        configurarEventos();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Cadastrar Funcionário");
        setSize(500, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void inicializarComponentes() {
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(null);
        painelPrincipal.setBackground(new Color(240, 240, 240));

        // Título
        labelTitulo = new JLabel("CADASTRAR FUNCIONÁRIO");
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

        labelCargo = criarLabel("Cargo:*");
        campoCargo = criarCampoTexto();

        labelSalario = criarLabel("Salário (R$):*");
        campoSalario = criarCampoTexto();
        campoSalario.setToolTipText("Digite apenas números. Ex: 1500.00");

        // ComboBox de Turno
        labelTurno = criarLabel("Turno:*");
        comboTurno = new JComboBox<>(new String[]{"MANHA", "TARDE", "NOITE", "INTEGRAL"});
        comboTurno.setFont(new Font("Arial", Font.PLAIN, 13));
        comboTurno.setBackground(Color.WHITE);
        comboTurno.setCursor(new Cursor(Cursor.HAND_CURSOR));

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

        // Cargo
        labelCargo.setBounds(50, y, 400, 20);
        campoCargo.setBounds(50, y + 25, 400, 35);
        painelPrincipal.add(labelCargo);
        painelPrincipal.add(campoCargo);
        y += espacamento;

        // Salário
        labelSalario.setBounds(50, y, 400, 20);
        campoSalario.setBounds(50, y + 25, 400, 35);
        painelPrincipal.add(labelSalario);
        painelPrincipal.add(campoSalario);
        y += espacamento;

        // Turno
        labelTurno.setBounds(50, y, 400, 20);
        comboTurno.setBounds(50, y + 25, 400, 35);
        painelPrincipal.add(labelTurno);
        painelPrincipal.add(comboTurno);
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
        btnSalvar.addActionListener(e -> salvarFuncionario());
        btnLimpar.addActionListener(e -> limparCampos());
        btnVoltar.addActionListener(e -> voltar());
    }

    private void salvarFuncionario() {
        if (!validarCampos()) {
            return;
        }

        String nome = campoNome.getText().trim();
        String cpf = campoCpf.getText().trim();
        String telefone = campoTelefone.getText().trim();
        String email = campoEmail.getText().trim();
        String endereco = campoEndereco.getText().trim();
        String cargo = campoCargo.getText().trim();

        double salario;
        try {
            salario = Double.parseDouble(campoSalario.getText().trim().replace(",", "."));
            if (salario < 0) {
                JOptionPane.showMessageDialog(this,
                        "O salário não pode ser negativo!",
                        "Atenção",
                        JOptionPane.WARNING_MESSAGE);
                campoSalario.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Salário inválido!\nDigite apenas números.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            campoSalario.requestFocus();
            return;
        }

        String turnoStr = (String) comboTurno.getSelectedItem();
        Turnos turno = Turnos.valueOf(turnoStr);

        Funcionario funcionario = new Funcionario(nome, cpf, telefone, email,
                endereco, cargo, salario, turno);

        try {
            GerenciadorFuncionarios.adicionar(funcionario);

            JOptionPane.showMessageDialog(this,
                    "Funcionário cadastrado com sucesso!\n" +
                            "Nome: " + nome + "\n" +
                            "CPF: " + cpf + "\n" +
                            "Cargo: " + cargo + "\n" +
                            "Salário: R$ " + String.format("%.2f", salario),
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

        // Validar cargo
        if (campoCargo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "O campo CARGO é obrigatório!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            campoCargo.requestFocus();
            return false;
        }

        // Validar salário
        if (campoSalario.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "O campo SALÁRIO é obrigatório!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            campoSalario.requestFocus();
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
        campoCargo.setText("");
        campoSalario.setText("");
        comboTurno.setSelectedIndex(0);
        campoNome.requestFocus();
    }

    private void voltar() {
        dispose();
    }
}