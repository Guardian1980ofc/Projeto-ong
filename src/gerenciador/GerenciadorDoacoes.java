package gerenciador;

import model.Doacao;
import exception.DadosInvalidosException;
import exception.RegistroNaoEncontradoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Gerencia as doações em memória (COM EXCEÇÕES PERSONALIZADAS)
 */
public class GerenciadorDoacoes {

    private static ArrayList<Doacao> doacoes = new ArrayList<>();
    private static int proximoId = 1;

    /**
     * Adiciona uma nova doação
     * @throws DadosInvalidosException se os dados são inválidos
     */
    public static void adicionar(Doacao doacao) throws DadosInvalidosException {
        // Validação: Doação não pode ser null
        if (doacao == null) {
            throw new DadosInvalidosException("Doação não pode ser nula");
        }

        // Validação: ID do doador obrigatório
        if (doacao.getIdDoador() <= 0) {
            throw new DadosInvalidosException("idDoador", "ID do doador é obrigatório");
        }

        // Validação: Tipo obrigatório
        if (doacao.getTipo() == null || doacao.getTipo().trim().isEmpty()) {
            throw new DadosInvalidosException("tipo", "Tipo de doação é obrigatório");
        }

        // Validação: Descrição obrigatória
        if (doacao.getDescricao() == null || doacao.getDescricao().trim().isEmpty()) {
            throw new DadosInvalidosException("descricao", "Descrição é obrigatória");
        }

        // Validação: Valor não pode ser negativo
        if (doacao.getValor() < 0) {
            throw new DadosInvalidosException("valor", "Valor não pode ser negativo");
        }

        // Validação: Quantidade não pode ser negativa
        if (doacao.getQuantidade() < 0) {
            throw new DadosInvalidosException("quantidade", "Quantidade não pode ser negativa");
        }

        // Se passou todas as validações, adiciona
        doacao.setId(proximoId++);
        doacoes.add(doacao);
        System.out.println("✓ Doação registrada: " + doacao.getTipo() +
                " - R$ " + String.format("%.2f", doacao.getValor()));
    }

    /**
     * Busca uma doação pelo ID
     * @throws RegistroNaoEncontradoException se não encontrar
     */
    public static Doacao buscarPorIdComExcecao(int id) throws RegistroNaoEncontradoException {
        Doacao doacao = buscarPorId(id);
        if (doacao == null) {
            throw new RegistroNaoEncontradoException("Doação", id);
        }
        return doacao;
    }

    /**
     * Busca uma doação pelo ID (sem exceção)
     */
    public static Doacao buscarPorId(int id) {
        for (Doacao d : doacoes) {
            if (d.getId() == id) {
                return d;
            }
        }
        return null;
    }

    /**
     * Busca doações por doador
     */
    public static List<Doacao> buscarPorDoador(int idDoador) {
        ArrayList<Doacao> resultado = new ArrayList<>();
        for (Doacao d : doacoes) {
            if (d.getIdDoador() == idDoador) {
                resultado.add(d);
            }
        }
        return resultado;
    }

    /**
     * Busca doações por tipo
     */
    public static List<Doacao> buscarPorTipo(String tipo) {
        ArrayList<Doacao> resultado = new ArrayList<>();
        for (Doacao d : doacoes) {
            if (d.getTipo().equalsIgnoreCase(tipo)) {
                resultado.add(d);
            }
        }
        return resultado;
    }

    /**
     * Busca doações por período
     */
    public static List<Doacao> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        ArrayList<Doacao> resultado = new ArrayList<>();
        for (Doacao d : doacoes) {
            LocalDate data = d.getDataDoacao();
            if ((data.isEqual(dataInicio) || data.isAfter(dataInicio)) &&
                    (data.isEqual(dataFim) || data.isBefore(dataFim))) {
                resultado.add(d);
            }
        }
        return resultado;
    }

    /**
     * Retorna todas as doações
     */
    public static List<Doacao> listarTodas() {
        return new ArrayList<>(doacoes);
    }

    /**
     * Lista as últimas N doações
     */
    public static List<Doacao> listarUltimas(int quantidade) {
        ArrayList<Doacao> ultimas = new ArrayList<>();
        int inicio = Math.max(0, doacoes.size() - quantidade);

        for (int i = inicio; i < doacoes.size(); i++) {
            ultimas.add(doacoes.get(i));
        }
        return ultimas;
    }

    /**
     * Atualiza uma doação
     * @throws RegistroNaoEncontradoException se não encontrar
     * @throws DadosInvalidosException se os dados são inválidos
     */
    public static void atualizar(Doacao doacao) throws RegistroNaoEncontradoException, DadosInvalidosException {
        if (doacao == null) {
            throw new DadosInvalidosException("Doação não pode ser nula");
        }

        if (doacao.getValor() < 0) {
            throw new DadosInvalidosException("valor", "Valor não pode ser negativo");
        }

        boolean encontrado = false;
        for (int i = 0; i < doacoes.size(); i++) {
            if (doacoes.get(i).getId() == doacao.getId()) {
                doacoes.set(i, doacao);
                System.out.println("✓ Doação atualizada");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            throw new RegistroNaoEncontradoException("Doação", doacao.getId());
        }
    }

    /**
     * Remove uma doação
     * @throws RegistroNaoEncontradoException se não encontrar
     */
    public static void remover(int id) throws RegistroNaoEncontradoException {
        Doacao doacao = buscarPorId(id);
        if (doacao != null) {
            doacoes.remove(doacao);
            System.out.println("✓ Doação removida");
        } else {
            throw new RegistroNaoEncontradoException("Doação", id);
        }
    }

    /**
     * Calcula o total geral arrecadado
     */
    public static double calcularTotalGeral() {
        double total = 0;
        for (Doacao d : doacoes) {
            total += d.getValor();
        }
        return total;
    }

    /**
     * Calcula o total por tipo
     */
    public static double calcularTotalPorTipo(String tipo) {
        double total = 0;
        for (Doacao d : doacoes) {
            if (d.getTipo().equalsIgnoreCase(tipo)) {
                total += d.getValor();
            }
        }
        return total;
    }

    /**
     * Calcula o total por doador
     */
    public static double calcularTotalPorDoador(int idDoador) {
        double total = 0;
        for (Doacao d : doacoes) {
            if (d.getIdDoador() == idDoador) {
                total += d.getValor();
            }
        }
        return total;
    }

    /**
     * Calcula o total por período
     */
    public static double calcularTotalPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        double total = 0;
        for (Doacao d : doacoes) {
            LocalDate data = d.getDataDoacao();
            if ((data.isEqual(dataInicio) || data.isAfter(dataInicio)) &&
                    (data.isEqual(dataFim) || data.isBefore(dataFim))) {
                total += d.getValor();
            }
        }
        return total;
    }

    /**
     * Retorna a quantidade total de doações
     */
    public static int contarTotal() {
        return doacoes.size();
    }

    /**
     * Conta doações por tipo
     */
    public static int contarPorTipo(String tipo) {
        int count = 0;
        for (Doacao d : doacoes) {
            if (d.getTipo().equalsIgnoreCase(tipo)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Limpa todas as doações
     */
    public static void limparTodas() {
        doacoes.clear();
        proximoId = 1;
        System.out.println("✓ Todas as doações foram removidas!");
    }
}