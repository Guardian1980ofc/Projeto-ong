package view;

import gerenciador.GerenciadorDoacoes;
import model.Doacao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Tela de Listagem de Doações
 */
public class TelaListagemDoacoes extends JFrame {

    private JPanel painelPrincipal;
    private JLabel labelTitulo;

    private JLabel labelFiltro;
    private JComboBox<String> comboFiltroTipo;
    private JButton btnFiltrar;
    private JButton btnMostrarTodas;

    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private JScrollPane scrollTabela;

    private JButton btnVoltar;

    public TelaListagemDoacoes() {
        configurarJanela();
        inicializarComponentes();
        configurarLayout();
        configurarEventos();
        carregarDados();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Listagem de Doações");
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
        labelTitulo = new JLabel("LISTAGEM DE DOAÇÕES");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitulo.setForeground(new Color(155, 89, 182));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        // Filtro por tipo
        labelFiltro = new JLabel("Filtrar por tipo:");
        labelFiltro.setFont(new Font("Arial", Font.BOLD, 13));

        comboFiltroTipo = new JComboBox<>(new String[]{
                "TODOS", "DINHEIRO", "ALIMENTO", "ROUPA",
                "MEDICAMENTO", "HIGIENE", "MOVEL", "OUTROS"
        });
        comboFiltroTipo.setFont(new Font("Arial", Font.PLAIN, 13));
        comboFiltroTipo.setBackground(Color.WHITE);

        btnFiltrar = criarBotao("Filtrar", new Color(52, 152, 219));
        btnMostrarTodas = criarBotao("Mostrar Todas", new Color(149, 165, 166));

        // Tabela
        String[] colunas = {"ID", "Doador", "Tipo", "Descrição",
                "Valor", "Quantidade", "Data", "Observações"};
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
        tabela.getColumnModel().getColumn(1).setPreferredWidth(150); // Doador
        tabela.getColumnModel().getColumn(2).setPreferredWidth(100); // Tipo
        tabela.getColumnModel().getColumn(3).setPreferredWidth(200); // Descrição
        tabela.getColumnModel().getColumn(4).setPreferredWidth(100); // Valor
        tabela.getColumnModel().getColumn(5).setPreferredWidth(80);  // Quantidade
        tabela.getColumnModel().getColumn(6).setPreferredWidth(100); // Data
        tabela.getColumnModel().getColumn(7).setPreferredWidth(150); // Observações

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

        // Filtro
        labelFiltro.setBounds(50, 75, 150, 25);
        comboFiltroTipo.setBounds(170, 75, 200, 30);
        btnFiltrar.setBounds(380, 75, 100, 30);
        btnMostrarTodas.setBounds(490, 75, 150, 30);

        painelPrincipal.add(labelFiltro);
        painelPrincipal.add(comboFiltroTipo);
        painelPrincipal.add(btnFiltrar);
        painelPrincipal.add(btnMostrarTodas);

        // Tabela
        scrollTabela.setBounds(50, 120, 1000, 370);
        painelPrincipal.add(scrollTabela);

        // Estatísticas
        JLabel labelTotal = new JLabel("Total de doações: 0");
        labelTotal.setName("labelTotal");
        labelTotal.setFont(new Font("Arial", Font.BOLD, 12));
        labelTotal.setBounds(50, 500, 200, 25);
        painelPrincipal.add(labelTotal);

        JLabel labelValorTotal = new JLabel("Total arrecadado: R$ 0,00");
        labelValorTotal.setName("labelValorTotal");
        labelValorTotal.setFont(new Font("Arial", Font.BOLD, 14));
        labelValorTotal.setForeground(new Color(46, 204, 113));
        labelValorTotal.setBounds(250, 500, 350, 25);
        painelPrincipal.add(labelValorTotal);

        // Botão Voltar
        btnVoltar.setBounds(950, 500, 100, 35);
        painelPrincipal.add(btnVoltar);

        add(painelPrincipal);
    }

    private void configurarEventos() {
        btnFiltrar.addActionListener(e -> filtrarPorTipo());
        btnMostrarTodas.addActionListener(e -> carregarDados());
        btnVoltar.addActionListener(e -> voltar());
    }

    private void carregarDados() {
        List<Doacao> doacoes = GerenciadorDoacoes.listarTodas();
        preencherTabela(doacoes);
        comboFiltroTipo.setSelectedIndex(0); // Volta para "TODOS"
    }

    private void filtrarPorTipo() {
        String tipoSelecionado = (String) comboFiltroTipo.getSelectedItem();

        if ("TODOS".equals(tipoSelecionado)) {
            carregarDados();
            return;
        }

        List<Doacao> resultados = GerenciadorDoacoes.buscarPorTipo(tipoSelecionado);

        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Nenhuma doação encontrada do tipo: " + tipoSelecionado,
                    "Resultado do Filtro",
                    JOptionPane.INFORMATION_MESSAGE);
            preencherTabela(resultados);
        } else {
            preencherTabela(resultados);
        }
    }

    private void preencherTabela(List<Doacao> doacoes) {
        // Limpar tabela
        modeloTabela.setRowCount(0);

        // Adicionar dados
        for (Doacao d : doacoes) {
            Object[] linha = {
                    d.getId(),
                    d.getNomeDoador(),
                    d.getTipo(),
                    d.getDescricao() != null ? d.getDescricao() : "-",
                    "R$ " + String.format("%.2f", d.getValor()),
                    d.getQuantidade(),
                    d.getDataDoacao(),
                    d.getObservacoes() != null && !d.getObservacoes().isEmpty()
                            ? d.getObservacoes() : "-"
            };
            modeloTabela.addRow(linha);
        }

        // Atualizar estatísticas
        atualizarEstatisticas(doacoes);
    }

    private void atualizarEstatisticas(List<Doacao> doacoes) {
        int total = doacoes.size();

        // Calcular total arrecadado
        double valorTotal = 0;
        for (Doacao d : doacoes) {
            valorTotal += d.getValor();
        }

        // Atualizar labels
        for (Component c : painelPrincipal.getComponents()) {
            if (c instanceof JLabel) {
                String nome = c.getName();
                if ("labelTotal".equals(nome)) {
                    ((JLabel) c).setText("Total de doações: " + total);
                } else if ("labelValorTotal".equals(nome)) {
                    ((JLabel) c).setText("Total arrecadado: R$ " +
                            String.format("%.2f", valorTotal));
                }
            }
        }
    }

    private void voltar() {
        dispose();
    }
}