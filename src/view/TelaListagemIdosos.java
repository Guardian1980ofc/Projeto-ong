package view;

import gerenciador.GerenciadorIdosos;
import model.Idoso;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Tela de Listagem de Idosos
 */
public class TelaListagemIdosos extends JFrame {

    private JPanel painelPrincipal;
    private JLabel labelTitulo;

    private JLabel labelBusca;
    private JTextField campoBusca;
    private JButton btnBuscar;
    private JButton btnMostrarTodos;

    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private JScrollPane scrollTabela;

    private JButton btnVoltar;

    public TelaListagemIdosos() {
        configurarJanela();
        inicializarComponentes();
        configurarLayout();
        configurarEventos();
        carregarDados();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Listagem de Idosos");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
    }

    private void inicializarComponentes() {
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(null);
        painelPrincipal.setBackground(new Color(240, 240, 240));

        // Título
        labelTitulo = new JLabel("LISTAGEM DE IDOSOS");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitulo.setForeground(new Color(155, 89, 182));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        // Busca
        labelBusca = new JLabel("Buscar por nome:");
        labelBusca.setFont(new Font("Arial", Font.BOLD, 13));

        campoBusca = new JTextField();
        campoBusca.setFont(new Font("Arial", Font.PLAIN, 13));

        btnBuscar = criarBotao("Buscar", new Color(52, 152, 219));
        btnMostrarTodos = criarBotao("Mostrar Todos", new Color(149, 165, 166));

        // Tabela
        String[] colunas = {"ID", "Nome", "CPF", "Idade", "Telefone",
                "Contato Emerg.", "Tel. Emerg.", "Data Ingresso"};
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
        tabela.getColumnModel().getColumn(3).setPreferredWidth(60);  // Idade
        tabela.getColumnModel().getColumn(4).setPreferredWidth(110); // Telefone
        tabela.getColumnModel().getColumn(5).setPreferredWidth(150); // Contato Emerg
        tabela.getColumnModel().getColumn(6).setPreferredWidth(110); // Tel Emerg
        tabela.getColumnModel().getColumn(7).setPreferredWidth(120); // Data Ingresso

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
        labelTitulo.setBounds(50, 20, 900, 30);
        painelPrincipal.add(labelTitulo);

        // Separador
        JSeparator separador = new JSeparator();
        separador.setBounds(50, 60, 900, 2);
        painelPrincipal.add(separador);

        // Busca
        labelBusca.setBounds(50, 75, 150, 25);
        campoBusca.setBounds(170, 75, 450, 30);
        btnBuscar.setBounds(630, 75, 100, 30);
        btnMostrarTodos.setBounds(740, 75, 160, 30);

        painelPrincipal.add(labelBusca);
        painelPrincipal.add(campoBusca);
        painelPrincipal.add(btnBuscar);
        painelPrincipal.add(btnMostrarTodos);

        // Tabela
        scrollTabela.setBounds(50, 120, 900, 370);
        painelPrincipal.add(scrollTabela);

        // Label de total e idade média
        JLabel labelTotal = new JLabel("Total de idosos: 0");
        labelTotal.setName("labelTotal");
        labelTotal.setFont(new Font("Arial", Font.BOLD, 12));
        labelTotal.setBounds(50, 500, 200, 25);
        painelPrincipal.add(labelTotal);

        JLabel labelIdadeMedia = new JLabel("Idade média: 0 anos");
        labelIdadeMedia.setName("labelIdadeMedia");
        labelIdadeMedia.setFont(new Font("Arial", Font.BOLD, 12));
        labelIdadeMedia.setBounds(250, 500, 200, 25);
        painelPrincipal.add(labelIdadeMedia);

        // Botão Voltar
        btnVoltar.setBounds(850, 500, 100, 35);
        painelPrincipal.add(btnVoltar);

        add(painelPrincipal);
    }

    private void configurarEventos() {
        btnBuscar.addActionListener(e -> buscarIdosos());
        btnMostrarTodos.addActionListener(e -> carregarDados());
        btnVoltar.addActionListener(e -> voltar());
        campoBusca.addActionListener(e -> buscarIdosos());
    }

    private void carregarDados() {
        List<Idoso> idosos = GerenciadorIdosos.listarTodos();
        preencherTabela(idosos);
        campoBusca.setText("");
    }

    private void buscarIdosos() {
        String textoBusca = campoBusca.getText().trim();

        if (textoBusca.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Digite um nome para buscar!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<Idoso> resultados = GerenciadorIdosos.buscarPorNome(textoBusca);

        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Nenhum idoso encontrado com o nome: " + textoBusca,
                    "Resultado da Busca",
                    JOptionPane.INFORMATION_MESSAGE);
            preencherTabela(resultados);
        } else {
            preencherTabela(resultados);
        }
    }

    private void preencherTabela(List<Idoso> idosos) {
        // Limpar tabela
        modeloTabela.setRowCount(0);

        // Adicionar dados
        for (Idoso i : idosos) {
            Object[] linha = {
                    i.getId(),
                    i.getNome(),
                    i.getCpf(),
                    i.getIdade() + " anos",
                    i.getTelefone() != null ? i.getTelefone() : "-",
                    i.getContatoEmergencia() != null ? i.getContatoEmergencia() : "-",
                    i.getTelefoneEmergencia() != null ? i.getTelefoneEmergencia() : "-",
                    i.getDataIngresso()
            };
            modeloTabela.addRow(linha);
        }

        // Atualizar estatísticas
        atualizarEstatisticas(idosos);
    }

    private void atualizarEstatisticas(List<Idoso> idosos) {
        int total = idosos.size();

        // Calcular idade média
        double idadeMedia = 0;
        if (total > 0) {
            int somaIdades = 0;
            for (Idoso i : idosos) {
                somaIdades += i.getIdade();
            }
            idadeMedia = (double) somaIdades / total;
        }

        // Atualizar labels
        for (Component c : painelPrincipal.getComponents()) {
            if (c instanceof JLabel) {
                if ("labelTotal".equals(c.getName())) {
                    ((JLabel) c).setText("Total de idosos: " + total);
                } else if ("labelIdadeMedia".equals(c.getName())) {
                    ((JLabel) c).setText("Idade média: " + String.format("%.1f", idadeMedia) + " anos");
                }
            }
        }
    }

    private void voltar() {
        dispose();
    }
}