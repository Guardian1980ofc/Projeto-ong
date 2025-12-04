package view;

import gerenciador.GerenciadorFuncionarios;
import model.Funcionario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Tela de Listagem de Funcionários
 */
public class TelaListagemFuncionarios extends JFrame {

    private JPanel painelPrincipal;
    private JLabel labelTitulo;

    private JLabel labelBusca;
    private JTextField campoBusca;
    private JButton btnBuscar;
    private JButton btnMostrarTodos;
    private JButton btnMostrarAtivos;

    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private JScrollPane scrollTabela;

    private JButton btnVoltar;

    public TelaListagemFuncionarios() {
        configurarJanela();
        inicializarComponentes();
        configurarLayout();
        configurarEventos();
        carregarDados();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Listagem de Funcionários");
        setSize(1100, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
    }

    private void inicializarComponentes() {
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(null);
        painelPrincipal.setBackground(new Color(240, 240, 240));

        // Título
        labelTitulo = new JLabel("LISTAGEM DE FUNCIONÁRIOS");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitulo.setForeground(new Color(155, 89, 182));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        // Busca
        labelBusca = new JLabel("Buscar por nome:");
        labelBusca.setFont(new Font("Arial", Font.BOLD, 13));

        campoBusca = new JTextField();
        campoBusca.setFont(new Font("Arial", Font.PLAIN, 13));

        btnBuscar = criarBotao("Buscar", new Color(52, 152, 219));
        btnMostrarTodos = criarBotao("Todos", new Color(149, 165, 166));
        btnMostrarAtivos = criarBotao("Apenas Ativos", new Color(46, 204, 113));

        // Tabela
        String[] colunas = {"ID", "Nome", "CPF", "Cargo", "Salário",
                "Turno", "Data Contratação", "Ativo"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabela = new JTable(modeloTabela);
        tabela.setFont(new Font("Arial", Font.PLAIN, 12));
        tabela.setRowHeight(25);
        tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        tabela.getTableHeader().setBackground(new Color(155, 89, 182));
        tabela.getTableHeader().setForeground(Color.WHITE);
        tabela.setSelectionBackground(new Color(155, 89, 182, 100));

        // Ajustar largura das colunas
        tabela.getColumnModel().getColumn(0).setPreferredWidth(40);  // ID
        tabela.getColumnModel().getColumn(1).setPreferredWidth(180); // Nome
        tabela.getColumnModel().getColumn(2).setPreferredWidth(110); // CPF
        tabela.getColumnModel().getColumn(3).setPreferredWidth(130); // Cargo
        tabela.getColumnModel().getColumn(4).setPreferredWidth(100); // Salário
        tabela.getColumnModel().getColumn(5).setPreferredWidth(90);  // Turno
        tabela.getColumnModel().getColumn(6).setPreferredWidth(120); // Data Contratação
        tabela.getColumnModel().getColumn(7).setPreferredWidth(60);  // Ativo

        scrollTabela = new JScrollPane(tabela);
        scrollTabela.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));

        btnVoltar = criarBotao("Voltar", new Color(149, 165, 166));
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
        labelTitulo.setBounds(50, 20, 1000, 30);
        painelPrincipal.add(labelTitulo);

        // Separador
        JSeparator separador = new JSeparator();
        separador.setBounds(50, 60, 1000, 2);
        painelPrincipal.add(separador);

        // Busca
        labelBusca.setBounds(50, 75, 150, 25);
        campoBusca.setBounds(170, 75, 380, 30);
        btnBuscar.setBounds(560, 75, 100, 30);
        btnMostrarTodos.setBounds(670, 75, 100, 30);
        btnMostrarAtivos.setBounds(780, 75, 140, 30);

        painelPrincipal.add(labelBusca);
        painelPrincipal.add(campoBusca);
        painelPrincipal.add(btnBuscar);
        painelPrincipal.add(btnMostrarTodos);
        painelPrincipal.add(btnMostrarAtivos);

        // Tabela
        scrollTabela.setBounds(50, 120, 1000, 370);
        painelPrincipal.add(scrollTabela);

        // Estatísticas
        JLabel labelTotal = new JLabel("Total: 0");
        labelTotal.setName("labelTotal");
        labelTotal.setFont(new Font("Arial", Font.BOLD, 12));
        labelTotal.setBounds(50, 500, 150, 25);
        painelPrincipal.add(labelTotal);

        JLabel labelAtivos = new JLabel("Ativos: 0");
        labelAtivos.setName("labelAtivos");
        labelAtivos.setFont(new Font("Arial", Font.BOLD, 12));
        labelAtivos.setBounds(200, 500, 150, 25);
        painelPrincipal.add(labelAtivos);

        JLabel labelFolha = new JLabel("Folha de Pagamento: R$ 0,00");
        labelFolha.setName("labelFolha");
        labelFolha.setFont(new Font("Arial", Font.BOLD, 12));
        labelFolha.setForeground(new Color(46, 204, 113));
        labelFolha.setBounds(350, 500, 300, 25);
        painelPrincipal.add(labelFolha);

        // Botão Voltar
        btnVoltar.setBounds(950, 500, 100, 35);
        painelPrincipal.add(btnVoltar);

        add(painelPrincipal);
    }

    private void configurarEventos() {
        btnBuscar.addActionListener(e -> buscarFuncionarios());
        btnMostrarTodos.addActionListener(e -> carregarDados());
        btnMostrarAtivos.addActionListener(e -> mostrarAtivos());
        btnVoltar.addActionListener(e -> voltar());
        campoBusca.addActionListener(e -> buscarFuncionarios());
    }

    private void carregarDados() {
        List<Funcionario> funcionarios = GerenciadorFuncionarios.listarTodos();
        preencherTabela(funcionarios);
        campoBusca.setText("");
    }

    private void mostrarAtivos() {
        List<Funcionario> funcionarios = GerenciadorFuncionarios.listarAtivos();
        preencherTabela(funcionarios);
        campoBusca.setText("");
    }

    private void buscarFuncionarios() {
        String textoBusca = campoBusca.getText().trim();

        if (textoBusca.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Digite um nome para buscar!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<Funcionario> resultados = GerenciadorFuncionarios.buscarPorNome(textoBusca);

        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Nenhum funcionário encontrado com o nome: " + textoBusca,
                    "Resultado da Busca",
                    JOptionPane.INFORMATION_MESSAGE);
            preencherTabela(resultados);
        } else {
            preencherTabela(resultados);
        }
    }

    private void preencherTabela(List<Funcionario> funcionarios) {
        // Limpar tabela
        modeloTabela.setRowCount(0);

        // Adicionar dados
        for (Funcionario f : funcionarios) {
            Object[] linha = {
                    f.getId(),
                    f.getNome(),
                    f.getCpf(),
                    f.getCargo(),
                    "R$ " + String.format("%.2f", f.getSalario()),
                    f.getTurno(),
                    f.getDataContratacao(),
                    f.isAtivo() ? "Sim" : "Não"
            };
            modeloTabela.addRow(linha);
        }

        // Atualizar estatísticas
        atualizarEstatisticas(funcionarios);
    }

    private void atualizarEstatisticas(List<Funcionario> funcionarios) {
        int total = funcionarios.size();

        // Contar ativos
        int ativos = 0;
        for (Funcionario f : funcionarios) {
            if (f.isAtivo()) ativos++;
        }

        // Calcular folha de pagamento (apenas ativos)
        double folhaPagamento = 0;
        for (Funcionario f : funcionarios) {
            if (f.isAtivo()) {
                folhaPagamento += f.getSalario();
            }
        }

        // Atualizar labels
        for (Component c : painelPrincipal.getComponents()) {
            if (c instanceof JLabel) {
                String nome = c.getName();
                if ("labelTotal".equals(nome)) {
                    ((JLabel) c).setText("Total: " + total);
                } else if ("labelAtivos".equals(nome)) {
                    ((JLabel) c).setText("Ativos: " + ativos);
                } else if ("labelFolha".equals(nome)) {
                    ((JLabel) c).setText("Folha de Pagamento: R$ " +
                            String.format("%.2f", folhaPagamento));
                }
            }
        }
    }

    private void voltar() {
        dispose();
    }
}