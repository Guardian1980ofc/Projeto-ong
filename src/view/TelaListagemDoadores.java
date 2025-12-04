package view;

import gerenciador.GerenciadorDoadores;
import model.Doador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Tela de Listagem de Doadores
 */
public class TelaListagemDoadores extends JFrame {

    // Componentes
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

    public TelaListagemDoadores() {
        configurarJanela();
        inicializarComponentes();
        configurarLayout();
        configurarEventos();
        carregarDados();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Listagem de Doadores");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
    }

    private void inicializarComponentes() {
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(null);
        painelPrincipal.setBackground(new Color(240, 240, 240));

        // Título
        labelTitulo = new JLabel("LISTAGEM DE DOADORES");
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
        String[] colunas = {"ID", "Nome", "CPF", "Telefone", "Email", "Tipo Pessoa", "Ativo"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Não permite editar células
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
        tabela.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
        tabela.getColumnModel().getColumn(1).setPreferredWidth(200); // Nome
        tabela.getColumnModel().getColumn(2).setPreferredWidth(120); // CPF
        tabela.getColumnModel().getColumn(3).setPreferredWidth(120); // Telefone
        tabela.getColumnModel().getColumn(4).setPreferredWidth(180); // Email
        tabela.getColumnModel().getColumn(5).setPreferredWidth(100); // Tipo
        tabela.getColumnModel().getColumn(6).setPreferredWidth(80);  // Ativo

        scrollTabela = new JScrollPane(tabela);
        scrollTabela.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));

        // Botão Voltar
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
        labelTitulo.setBounds(50, 20, 800, 30);
        painelPrincipal.add(labelTitulo);

        // Separador
        JSeparator separador = new JSeparator();
        separador.setBounds(50, 60, 800, 2);
        painelPrincipal.add(separador);

        // Busca
        labelBusca.setBounds(50, 75, 150, 25);
        campoBusca.setBounds(170, 75, 400, 30);
        btnBuscar.setBounds(580, 75, 100, 30);
        btnMostrarTodos.setBounds(690, 75, 160, 30);

        painelPrincipal.add(labelBusca);
        painelPrincipal.add(campoBusca);
        painelPrincipal.add(btnBuscar);
        painelPrincipal.add(btnMostrarTodos);

        // Tabela
        scrollTabela.setBounds(50, 120, 800, 370);
        painelPrincipal.add(scrollTabela);

        // Label de total
        JLabel labelTotal = new JLabel("Total de doadores: 0");
        labelTotal.setName("labelTotal"); // Para atualizar depois
        labelTotal.setFont(new Font("Arial", Font.BOLD, 12));
        labelTotal.setBounds(50, 500, 200, 25);
        painelPrincipal.add(labelTotal);

        // Botão Voltar
        btnVoltar.setBounds(750, 500, 100, 35);
        painelPrincipal.add(btnVoltar);

        add(painelPrincipal);
    }

    private void configurarEventos() {
        btnBuscar.addActionListener(e -> buscarDoadores());
        btnMostrarTodos.addActionListener(e -> carregarDados());
        btnVoltar.addActionListener(e -> voltar());

        // Buscar ao pressionar Enter no campo de busca
        campoBusca.addActionListener(e -> buscarDoadores());
    }

    private void carregarDados() {
        List<Doador> doadores = GerenciadorDoadores.listarTodos();
        preencherTabela(doadores);
        campoBusca.setText("");
    }

    private void buscarDoadores() {
        String textoBusca = campoBusca.getText().trim();

        if (textoBusca.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Digite um nome para buscar!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<Doador> resultados = GerenciadorDoadores.buscarPorNome(textoBusca);

        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Nenhum doador encontrado com o nome: " + textoBusca,
                    "Resultado da Busca",
                    JOptionPane.INFORMATION_MESSAGE);
            preencherTabela(resultados); // Limpa a tabela
        } else {
            preencherTabela(resultados);
        }
    }

    private void preencherTabela(List<Doador> doadores) {
        // Limpar tabela
        modeloTabela.setRowCount(0);

        // Adicionar dados
        for (Doador d : doadores) {
            Object[] linha = {
                    d.getId(),
                    d.getNome(),
                    d.getCpf(),
                    d.getTelefone() != null ? d.getTelefone() : "-",
                    d.getEmail() != null ? d.getEmail() : "-",
                    d.getTipoPessoa(),
                    d.isAtivo() ? "Sim" : "Não"
            };
            modeloTabela.addRow(linha);
        }

        // Atualizar label de total
        atualizarTotal(doadores.size());
    }

    private void atualizarTotal(int quantidade) {
        // Encontrar label de total e atualizar
        for (Component c : painelPrincipal.getComponents()) {
            if (c instanceof JLabel && "labelTotal".equals(c.getName())) {
                ((JLabel) c).setText("Total de doadores: " + quantidade);
                break;
            }
        }
    }

    private void voltar() {
        dispose();
    }
}