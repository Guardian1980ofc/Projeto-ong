package view;

import gerenciador.GerenciadorDoadores;
import gerenciador.GerenciadorDoacoes;
import model.Doacao;
import model.Doador;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Tela de Registro de Doação
 */
public class TelaCadastroDoacao extends JFrame {

    // Componentes
    private JPanel painelPrincipal;
    private JLabel labelTitulo;

    private JLabel labelDoador;
    private JComboBox<String> comboDoador;

    private JLabel labelTipo;
    private JComboBox<String> comboTipo;

    private JLabel labelDescricao;
    private JTextField campoDescricao;

    private JLabel labelValor;
    private JTextField campoValor;

    private JLabel labelQuantidade;
    private JTextField campoQuantidade;

    private JLabel labelObservacoes;
    private JTextArea campoObservacoes;
    private JScrollPane scrollObservacoes;

    private JButton btnSalvar;
    private JButton btnLimpar;
    private JButton btnVoltar;

    private List<Doador> listaDoadores;

    public TelaCadastroDoacao() {
        carregarDoadores();
        configurarJanela();
        inicializarComponentes();
        configurarLayout();
        configurarEventos();
        setVisible(true);
    }

    private void carregarDoadores() {
        listaDoadores = GerenciadorDoadores.listarAtivos();

        // Verifica se há doadores cadastrados
        if (listaDoadores.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Nenhum doador cadastrado!\n" +
                            "Cadastre pelo menos um doador antes de registrar doações.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void configurarJanela() {
        setTitle("Registrar Doação");
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
        labelTitulo = new JLabel("REGISTRAR DOAÇÃO");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitulo.setForeground(new Color(52, 152, 219));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        // ComboBox de Doador
        labelDoador = criarLabel("Doador:*");
        comboDoador = new JComboBox<>();
        comboDoador.setFont(new Font("Arial", Font.PLAIN, 13));
        comboDoador.setBackground(Color.WHITE);
        comboDoador.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Preencher ComboBox com doadores
        comboDoador.addItem("--- Selecione um doador ---");
        for (Doador doador : listaDoadores) {
            comboDoador.addItem(doador.getId() + " - " + doador.getNome());
        }

        // ComboBox de Tipo
        labelTipo = criarLabel("Tipo de Doação:*");
        comboTipo = new JComboBox<>(new String[]{
                "DINHEIRO", "ALIMENTO", "ROUPA", "MEDICAMENTO",
                "HIGIENE", "MOVEL", "OUTROS"
        });
        comboTipo.setFont(new Font("Arial", Font.PLAIN, 13));
        comboTipo.setBackground(Color.WHITE);
        comboTipo.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Campos
        labelDescricao = criarLabel("Descrição:*");
        campoDescricao = criarCampoTexto();

        labelValor = criarLabel("Valor Estimado (R$):*");
        campoValor = criarCampoTexto();
        campoValor.setToolTipText("Digite o valor em reais. Ex: 500.00");

        labelQuantidade = criarLabel("Quantidade (unidades):");
        campoQuantidade = criarCampoTexto();
        campoQuantidade.setToolTipText("Para itens físicos. Ex: 10 cestas básicas");
        campoQuantidade.setText("0");

        labelObservacoes = criarLabel("Observações:");
        campoObservacoes = new JTextArea();
        campoObservacoes.setFont(new Font("Arial", Font.PLAIN, 13));
        campoObservacoes.setLineWrap(true);
        campoObservacoes.setWrapStyleWord(true);

        scrollObservacoes = new JScrollPane(campoObservacoes);
        scrollObservacoes.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));

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

        JSeparator separador = new JSeparator();
        separador.setBounds(50, 60, 400, 2);
        painelPrincipal.add(separador);

        int y = 80;
        int espacamento = 60;

        // Doador
        labelDoador.setBounds(50, y, 400, 20);
        comboDoador.setBounds(50, y + 25, 400, 35);
        painelPrincipal.add(labelDoador);
        painelPrincipal.add(comboDoador);
        y += espacamento;

        // Tipo
        labelTipo.setBounds(50, y, 400, 20);
        comboTipo.setBounds(50, y + 25, 400, 35);
        painelPrincipal.add(labelTipo);
        painelPrincipal.add(comboTipo);
        y += espacamento;

        // Descrição
        labelDescricao.setBounds(50, y, 400, 20);
        campoDescricao.setBounds(50, y + 25, 400, 35);
        painelPrincipal.add(labelDescricao);
        painelPrincipal.add(campoDescricao);
        y += espacamento;

        // Valor
        labelValor.setBounds(50, y, 400, 20);
        campoValor.setBounds(50, y + 25, 400, 35);
        painelPrincipal.add(labelValor);
        painelPrincipal.add(campoValor);
        y += espacamento;

        // Quantidade
        labelQuantidade.setBounds(50, y, 400, 20);
        campoQuantidade.setBounds(50, y + 25, 400, 35);
        painelPrincipal.add(labelQuantidade);
        painelPrincipal.add(campoQuantidade);
        y += espacamento;

        // Observações
        labelObservacoes.setBounds(50, y, 400, 20);
        scrollObservacoes.setBounds(50, y + 25, 400, 80);
        painelPrincipal.add(labelObservacoes);
        painelPrincipal.add(scrollObservacoes);
        y += 110;

        // Nota
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
        btnSalvar.addActionListener(e -> salvarDoacao());
        btnLimpar.addActionListener(e -> limparCampos());
        btnVoltar.addActionListener(e -> voltar());
    }

    private void salvarDoacao() {
        if (!validarCampos()) {
            return;
        }

        int indexDoador = comboDoador.getSelectedIndex();
        if (indexDoador == 0) {
            JOptionPane.showMessageDialog(this,
                    "Selecione um doador!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Doador doadorSelecionado = listaDoadores.get(indexDoador - 1);
        String tipo = (String) comboTipo.getSelectedItem();
        String descricao = campoDescricao.getText().trim();

        double valor;
        try {
            valor = Double.parseDouble(campoValor.getText().trim().replace(",", "."));
            if (valor < 0) {
                JOptionPane.showMessageDialog(this,
                        "O valor não pode ser negativo!",
                        "Atenção",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Valor inválido!\nDigite apenas números.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            campoValor.requestFocus();
            return;
        }

        int quantidade;
        try {
            quantidade = Integer.parseInt(campoQuantidade.getText().trim());
            if (quantidade < 0) quantidade = 0;
        } catch (NumberFormatException e) {
            quantidade = 0;
        }

        String observacoes = campoObservacoes.getText().trim();

        Doacao doacao = new Doacao(doadorSelecionado.getId(),
                doadorSelecionado.getNome(),
                tipo, descricao, quantidade, valor);
        doacao.setObservacoes(observacoes);

        // USANDO EXCEÇÕES PERSONALIZADAS
        try {
            GerenciadorDoacoes.adicionar(doacao);

            JOptionPane.showMessageDialog(this,
                    "Doação registrada com sucesso!\n" +
                            "Doador: " + doadorSelecionado.getNome() + "\n" +
                            "Tipo: " + tipo + "\n" +
                            "Valor: R$ " + String.format("%.2f", valor) + "\n" +
                            "Quantidade: " + quantidade,
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            limparCampos();
            comboDoador.requestFocus();

        } catch (exception.DadosInvalidosException e) {
            JOptionPane.showMessageDialog(this,
                    "Erro: " + e.getMessage(),
                    "Dados Inválidos",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validarCampos() {
        if (comboDoador.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this,
                    "Selecione um DOADOR!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (campoDescricao.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "O campo DESCRIÇÃO é obrigatório!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            campoDescricao.requestFocus();
            return false;
        }

        if (campoValor.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "O campo VALOR é obrigatório!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            campoValor.requestFocus();
            return false;
        }

        return true;
    }

    private void limparCampos() {
        comboDoador.setSelectedIndex(0);
        comboTipo.setSelectedIndex(0);
        campoDescricao.setText("");
        campoValor.setText("");
        campoQuantidade.setText("0");
        campoObservacoes.setText("");
    }

    private void voltar() {
        dispose();
    }
}
