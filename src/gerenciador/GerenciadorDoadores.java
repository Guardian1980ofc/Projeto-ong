package gerenciador;

import model.Doador;
import exception.CPFDuplicadoException;
import exception.DadosInvalidosException;
import exception.RegistroNaoEncontradoException;

import java.util.ArrayList;
import java.util.List;


public class GerenciadorDoadores {

    private static ArrayList<Doador> doadores = new ArrayList<>();
    private static int proximoId = 1;


    public static void adicionar(Doador doador) throws CPFDuplicadoException, DadosInvalidosException {
        // Validação: Doador não pode ser null
        if (doador == null) {
            throw new DadosInvalidosException("Doador não pode ser nulo");
        }

        // Validação: Nome obrigatório
        if (doador.getNome() == null || doador.getNome().trim().isEmpty()) {
            throw new DadosInvalidosException("nome", "Nome é obrigatório");
        }

        // Validação: CPF obrigatório
        if (doador.getCpf() == null || doador.getCpf().trim().isEmpty()) {
            throw new DadosInvalidosException("cpf", "CPF é obrigatório");
        }

        // Validação: CPF único
        if (buscarPorCpf(doador.getCpf()) != null) {
            throw new CPFDuplicadoException(doador.getCpf());
        }

        // Se passou todas as validações, adiciona
        doador.setId(proximoId++);
        doadores.add(doador);
        System.out.println("✓ Doador adicionado: " + doador.getNome());
    }


    public static Doador buscarPorIdComExcecao(int id) throws RegistroNaoEncontradoException {
        Doador doador = buscarPorId(id);
        if (doador == null) {
            throw new RegistroNaoEncontradoException("Doador", id);
        }
        return doador;
    }

    /**
     * Busca um doador pelo ID (sem exceção - retorna null se não encontrar)
     */
    public static Doador buscarPorId(int id) {
        for (Doador d : doadores) {
            if (d.getId() == id) {
                return d;
            }
        }
        return null;
    }

    /**
     * Busca um doador pelo CPF
     */
    public static Doador buscarPorCpf(String cpf) {
        for (Doador d : doadores) {
            if (d.getCpf().equals(cpf)) {
                return d;
            }
        }
        return null;
    }

    /**
     * Busca doadores pelo nome (parcial)
     */
    public static List<Doador> buscarPorNome(String nome) {
        ArrayList<Doador> resultado = new ArrayList<>();
        String nomeBusca = nome.toLowerCase();

        for (Doador d : doadores) {
            if (d.getNome().toLowerCase().contains(nomeBusca)) {
                resultado.add(d);
            }
        }
        return resultado;
    }

    /**
     * Retorna todos os doadores cadastrados
     */
    public static List<Doador> listarTodos() {
        return new ArrayList<>(doadores);
    }

    /**
     * Retorna apenas doadores ativos
     */
    public static List<Doador> listarAtivos() {
        ArrayList<Doador> ativos = new ArrayList<>();
        for (Doador d : doadores) {
            if (d.isAtivo()) {
                ativos.add(d);
            }
        }
        return ativos;
    }

    /**
     * Atualiza os dados de um doador existente
     * @throws RegistroNaoEncontradoException se não encontrar o doador
     * @throws DadosInvalidosException se os dados são inválidos
     */
    public static void atualizar(Doador doador) throws RegistroNaoEncontradoException, DadosInvalidosException {
        if (doador == null) {
            throw new DadosInvalidosException("Doador não pode ser nulo");
        }

        boolean encontrado = false;
        for (int i = 0; i < doadores.size(); i++) {
            if (doadores.get(i).getId() == doador.getId()) {
                doadores.set(i, doador);
                System.out.println("✓ Doador atualizado: " + doador.getNome());
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            throw new RegistroNaoEncontradoException("Doador", doador.getId());
        }
    }

    /**
     * Remove um doador pelo ID
     * @throws RegistroNaoEncontradoException se não encontrar
     */
    public static void remover(int id) throws RegistroNaoEncontradoException {
        Doador doador = buscarPorId(id);
        if (doador != null) {
            doadores.remove(doador);
            System.out.println("✓ Doador removido: " + doador.getNome());
        } else {
            throw new RegistroNaoEncontradoException("Doador", id);
        }
    }

    /**
     * Desativa um doador
     * @throws RegistroNaoEncontradoException se não encontrar
     */
    public static void desativar(int id) throws RegistroNaoEncontradoException {
        Doador doador = buscarPorId(id);
        if (doador != null) {
            doador.setAtivo(false);
            System.out.println("✓ Doador desativado: " + doador.getNome());
        } else {
            throw new RegistroNaoEncontradoException("Doador", id);
        }
    }

    /**
     * Retorna a quantidade total de doadores
     */
    public static int contarTotal() {
        return doadores.size();
    }

    /**
     * Retorna a quantidade de doadores ativos
     */
    public static int contarAtivos() {
        int count = 0;
        for (Doador d : doadores) {
            if (d.isAtivo()) count++;
        }
        return count;
    }

    /**
     * Limpa todos os doadores
     */
    public static void limparTodos() {
        doadores.clear();
        proximoId = 1;
        System.out.println("✓ Todos os doadores foram removidos!");
    }
}