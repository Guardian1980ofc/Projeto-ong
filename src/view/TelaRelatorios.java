package view;

import gerenciador.*;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Tela de Relatórios - Resumo Geral do Sistema
 */
public class TelaRelatorios extends JFrame {

    private JPanel painelPrincipal;
    private JLabel labelTitulo;

    public TelaRelatorios() {
        configurarJanela();
        inicializarComponentes();
        configurarLayout();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Relatórios do Sistema");
        setSize(700, 750);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void inicializarComponentes() {
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(null);
        painelPrincipal.setBackground(new Color(240, 240, 240));

        // Título
        labelTitulo = new JLabel("RELATÓRIOS DO SISTEMA");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        labelTitulo.setForeground(new Color(230, 126, 34));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void configurarLayout() {
        // Título
        labelTitulo.setBounds(50, 20, 600, 30);
        painelPrincipal.add(labelTitulo);

        JSeparator separador = new JSeparator();
        separador.setBounds(50, 60, 600, 2);
        painelPrincipal.add(separador);

        int y = 80;

        // ========== SEÇÃO DOADORES ==========
        JPanel painelDoadores = criarPainelSecao("DOADORES", new Color(46, 204, 113));
        painelDoadores.setBounds(50, y, 600, 120);

        int totalDoadores = GerenciadorDoadores.contarTotal();
        int doadoresAtivos = GerenciadorDoadores.contarAtivos();
        int doadoresInativos = totalDoadores - doadoresAtivos;

        adicionarLinha(painelDoadores, "Total de doadores cadastrados:",
                String.valueOf(totalDoadores), 30);
        adicionarLinha(painelDoadores, "Doadores ativos:",
                String.valueOf(doadoresAtivos), 55);
        adicionarLinha(painelDoadores, "Doadores inativos:",
                String.valueOf(doadoresInativos), 80);

        painelPrincipal.add(painelDoadores);
        y += 135;

        // ========== SEÇÃO IDOSOS ==========
        JPanel painelIdosos = criarPainelSecao("IDOSOS ASSISTIDOS", new Color(52, 152, 219));
        painelIdosos.setBounds(50, y, 600, 120);

        int totalIdosos = GerenciadorIdosos.contarTotal();
        double idadeMedia = calcularIdadeMedia();

        adicionarLinha(painelIdosos, "Total de idosos:",
                String.valueOf(totalIdosos), 30);
        adicionarLinha(painelIdosos, "Idade média:",
                String.format("%.1f anos", idadeMedia), 55);
        adicionarLinha(painelIdosos, "Idosos acima de 70 anos:",
                String.valueOf(contarIdososAcimaDe70()), 80);

        painelPrincipal.add(painelIdosos);
        y += 135;

        // ========== SEÇÃO FUNCIONÁRIOS ==========
        JPanel painelFuncionarios = criarPainelSecao("FUNCIONÁRIOS", new Color(155, 89, 182));
        painelFuncionarios.setBounds(50, y, 600, 120);

        int totalFuncionarios = GerenciadorFuncionarios.contarTotal();
        int funcionariosAtivos = GerenciadorFuncionarios.contarAtivos();
        double folhaPagamento = GerenciadorFuncionarios.calcularFolhaPagamento();

        adicionarLinha(painelFuncionarios, "Total de funcionários:",
                String.valueOf(totalFuncionarios), 30);
        adicionarLinha(painelFuncionarios, "Funcionários ativos:",
                String.valueOf(funcionariosAtivos), 55);
        adicionarLinha(painelFuncionarios, "Folha de pagamento mensal:",
                "R$ " + String.format("%.2f", folhaPagamento), 80);

        painelPrincipal.add(painelFuncionarios);
        y += 135;

        // ========== SEÇÃO DOAÇÕES ==========
        JPanel painelDoacoes = criarPainelSecao("DOAÇÕES", new Color(230, 126, 34));
        painelDoacoes.setBounds(50, y, 600, 145);

        int totalDoacoes = GerenciadorDoacoes.contarTotal();
        double totalArrecadado = GerenciadorDoacoes.calcularTotalGeral();
        int doacoesDinheiro = GerenciadorDoacoes.contarPorTipo("DINHEIRO");
        double totalDinheiro = GerenciadorDoacoes.calcularTotalPorTipo("DINHEIRO");

        adicionarLinha(painelDoacoes, "Total de doações registradas:",
                String.valueOf(totalDoacoes), 30);
        adicionarLinha(painelDoacoes, "Total arrecadado:",
                "R$ " + String.format("%.2f", totalArrecadado), 55);
        adicionarLinha(painelDoacoes, "Doações em dinheiro:",
                String.valueOf(doacoesDinheiro), 80);
        adicionarLinha(painelDoacoes, "Total em dinheiro:",
                "R$ " + String.format("%.2f", totalDinheiro), 105);

        painelPrincipal.add(painelDoacoes);
        y += 160;

        // ========== RESUMO GERAL ==========
        JPanel painelResumo = new JPanel();
        painelResumo.setLayout(null);
        painelResumo.setBackground(new Color(44, 62, 80));
        painelResumo.setBorder(BorderFactory.createLineBorder(new Color(44, 62, 80), 2));
        painelResumo.setBounds(50, y, 600, 60);

        JLabel labelResumo = new JLabel("RESUMO FINANCEIRO");
        labelResumo.setFont(new Font("Arial", Font.BOLD, 14));
        labelResumo.setForeground(Color.WHITE);
        labelResumo.setBounds(20, 10, 200, 20);
        painelResumo.add(labelResumo);

        double saldoMensal = totalArrecadado - folhaPagamento;
        JLabel labelSaldo = new JLabel("Saldo (Arrecadado - Folha): R$ " +
                String.format("%.2f", saldoMensal));
        labelSaldo.setFont(new Font("Arial", Font.BOLD, 16));
        labelSaldo.setForeground(saldoMensal >= 0 ? new Color(46, 204, 113) : new Color(231, 76, 60));
        labelSaldo.setBounds(20, 32, 560, 20);
        painelResumo.add(labelSaldo);

        painelPrincipal.add(painelResumo);
        y += 75;

        // Botão Voltar
        JButton btnVoltar = criarBotao("Voltar", new Color(149, 165, 166));
        btnVoltar.setBounds(550, y, 100, 35);
        btnVoltar.addActionListener(e -> dispose());
        painelPrincipal.add(btnVoltar);

        add(painelPrincipal);
    }

    private JPanel criarPainelSecao(String titulo, Color cor) {
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBackground(Color.WHITE);
        painel.setBorder(BorderFactory.createLineBorder(cor, 3));

        JLabel labelTitulo = new JLabel(titulo);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        labelTitulo.setForeground(cor);
        labelTitulo.setBounds(15, 5, 300, 20);
        painel.add(labelTitulo);

        return painel;
    }

    private void adicionarLinha(JPanel painel, String texto, String valor, int y) {
        JLabel labelTexto = new JLabel(texto);
        labelTexto.setFont(new Font("Arial", Font.PLAIN, 13));
        labelTexto.setBounds(20, y, 350, 20);
        painel.add(labelTexto);

        JLabel labelValor = new JLabel(valor);
        labelValor.setFont(new Font("Arial", Font.BOLD, 13));
        labelValor.setHorizontalAlignment(SwingConstants.RIGHT);
        labelValor.setBounds(380, y, 200, 20);
        painel.add(labelValor);
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

    private double calcularIdadeMedia() {
        List<Idoso> idosos = GerenciadorIdosos.listarTodos();
        if (idosos.isEmpty()) return 0;

        int somaIdades = 0;
        for (Idoso i : idosos) {
            somaIdades += i.getIdade();
        }
        return (double) somaIdades / idosos.size();
    }

    private int contarIdososAcimaDe70() {
        List<Idoso> idosos = GerenciadorIdosos.listarTodos();
        int count = 0;
        for (Idoso i : idosos) {
            if (i.getIdade() >= 70) count++;
        }
        return count;
    }
}