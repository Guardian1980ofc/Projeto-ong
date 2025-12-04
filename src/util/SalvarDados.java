package util;

import java.io.*;
import java.time.LocalDate;

public class SalvarDados {

    //  SALVAR DADOS

    public static void salvarDoadores() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("doadores.txt"))) {
            // cabeçalho
            writer.println("ID;NOME;CPF;TELEFONE;EMAIL;TIPO;ATIVO");

            // cada doador
            for (model.Doador d : gerenciador.GerenciadorDoadores.listarTodos()) {
                writer.printf("%d;%s;%s;%s;%s;%s;%b%n",
                        d.getId(),
                        d.getNome(),
                        d.getCpf(),
                        d.getTelefone() != null ? d.getTelefone() : "",
                        d.getEmail() != null ? d.getEmail() : "",
                        d.getTipoPessoa(),
                        d.isAtivo());
            }
            System.out.println("✓ Doadores salvos em doadores.txt");

        } catch (IOException e) {
            System.err.println("Erro ao salvar doadores: " + e.getMessage());
        }
    }

    public static void salvarDoacoes() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("doacoes.txt"))) {
            writer.println("ID;DOADOR_ID;NOME_DOADOR;TIPO;DESCRICAO;VALOR;QUANTIDADE;DATA;OBSERVACOES");

            for (model.Doacao d : gerenciador.GerenciadorDoacoes.listarTodas()) {
                writer.printf("%d;%d;%s;%s;%s;%.2f;%d;%s;%s%n",
                        d.getId(),
                        d.getIdDoador(),
                        d.getNomeDoador(),
                        d.getTipo(),
                        d.getDescricao() != null ? d.getDescricao() : "",
                        d.getValor(),
                        d.getQuantidade(),
                        d.getDataDoacao(),
                        d.getObservacoes() != null ? d.getObservacoes() : "");
            }
            System.out.println("✓ Doações salvas em doacoes.txt");

        } catch (IOException e) {
            System.err.println("Erro ao salvar doações: " + e.getMessage());
        }
    }

    public static void salvarTudo() {
        salvarDoadores();
        salvarDoacoes();
        System.out.println("✓ Todos os dados salvos!");
    }

    //  CARREGAR DADOS

    public static void carregarDoadores() {
        File arquivo = new File("doadores.txt");
        if (!arquivo.exists()) {
            System.out.println("Arquivo doadores.txt não encontrado. Iniciando sem dados.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha = reader.readLine();

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 7) {
                    model.Doador d = new model.Doador(
                            dados[1], // nome
                            dados[2], // cpf
                            dados[3], // telefone
                            dados[4], // email
                            "",       // endereço
                            dados[5]  // tipoPessoa
                    );
                    d.setId(Integer.parseInt(dados[0]));
                    d.setAtivo(Boolean.parseBoolean(dados[6]));

                }
            }
            System.out.println("✓ Doadores carregados do arquivo");

        } catch (IOException e) {
            System.err.println("Erro ao carregar doadores: " + e.getMessage());
        }
    }

    // GERAR RELATÓRIO SIMPLES

    public static void gerarRelatorioDiario() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("relatorio_" + LocalDate.now() + ".txt"))) {
            writer.println("RELATÓRIO DO SISTEMA - " + LocalDate.now());
            writer.println("=".repeat(40));
            writer.println();
            writer.println("DOADORES: " + gerenciador.GerenciadorDoadores.contarTotal() + " cadastrados");
            writer.println("DOAÇÕES: " + gerenciador.GerenciadorDoacoes.contarTotal() + " registradas");
            writer.println("TOTAL ARRECADADO: R$ " +
                    String.format("%.2f", gerenciador.GerenciadorDoacoes.calcularTotalGeral()));
            writer.println();
            writer.println("=".repeat(40));
            writer.println("Relatório gerado automaticamente pelo sistema.");

            System.out.println("✓ Relatório gerado: relatorio_" + LocalDate.now() + ".txt");

        } catch (IOException e) {
            System.err.println("Erro ao gerar relatório: " + e.getMessage());
        }
    }

    // LOG DE ATIVIDADES

    public static void registrarLog(String atividade) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("log_sistema.txt", true))) {
            writer.println(LocalDate.now() + " " + java.time.LocalTime.now() + " - " + atividade);
        } catch (IOException e) {
            System.err.println("Erro ao registrar log: " + e.getMessage());
        }
    }
}