package gerenciador;

import model.Idoso;
import exception.CPFDuplicadoException;
import exception.DadosInvalidosException;
import exception.RegistroNaoEncontradoException;

import java.util.ArrayList;
import java.util.List;

/**
 * Gerencia os idosos em memória (COM EXCEÇÕES PERSONALIZADAS)
 */
public class GerenciadorIdosos {

    private static ArrayList<Idoso> idosos = new ArrayList<>();
    private static int proximoId = 1;

    /**
     * Adiciona um novo idoso
     * @throws CPFDuplicadoException se o CPF já existe
     * @throws DadosInvalidosException se os dados são inválidos
     */
    public static void adicionar(Idoso idoso) throws CPFDuplicadoException, DadosInvalidosException {
        // Validação: Idoso não pode ser null
        if (idoso == null) {
            throw new DadosInvalidosException("Idoso não pode ser nulo");
        }

        // Validação: Nome obrigatório
        if (idoso.getNome() == null || idoso.getNome().trim().isEmpty()) {
            throw new DadosInvalidosException("nome", "Nome é obrigatório");
        }

        // Validação: CPF obrigatório
        if (idoso.getCpf() == null || idoso.getCpf().trim().isEmpty()) {
            throw new DadosInvalidosException("cpf", "CPF é obrigatório");
        }

        // Validação: CPF único
        if (buscarPorCpf(idoso.getCpf()) != null) {
            throw new CPFDuplicadoException(idoso.getCpf());
        }

        // Validação: Data de nascimento obrigatória
        if (idoso.getDataNascimento() == null) {
            throw new DadosInvalidosException("dataNascimento", "Data de nascimento é obrigatória");
        }

        // Se passou todas as validações, adiciona
        idoso.setId(proximoId++);
        idosos.add(idoso);
        System.out.println("✓ Idoso adicionado: " + idoso.getNome());
    }

    /**
     * Busca um idoso pelo ID
     * @throws RegistroNaoEncontradoException se não encontrar
     */
    public static Idoso buscarPorIdComExcecao(int id) throws RegistroNaoEncontradoException {
        Idoso idoso = buscarPorId(id);
        if (idoso == null) {
            throw new RegistroNaoEncontradoException("Idoso", id);
        }
        return idoso;
    }

    /**
     * Busca um idoso pelo ID (sem exceção)
     */
    public static Idoso buscarPorId(int id) {
        for (Idoso i : idosos) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    /**
     * Busca um idoso pelo CPF
     */
    public static Idoso buscarPorCpf(String cpf) {
        for (Idoso i : idosos) {
            if (i.getCpf().equals(cpf)) {
                return i;
            }
        }
        return null;
    }

    /**
     * Busca idosos pelo nome (parcial)
     */
    public static List<Idoso> buscarPorNome(String nome) {
        ArrayList<Idoso> resultado = new ArrayList<>();
        String nomeBusca = nome.toLowerCase();

        for (Idoso i : idosos) {
            if (i.getNome().toLowerCase().contains(nomeBusca)) {
                resultado.add(i);
            }
        }
        return resultado;
    }

    /**
     * Retorna todos os idosos
     */
    public static List<Idoso> listarTodos() {
        return new ArrayList<>(idosos);
    }

    /**
     * Lista idosos com idade mínima
     */
    public static List<Idoso> listarPorIdadeMinima(int idadeMinima) {
        ArrayList<Idoso> resultado = new ArrayList<>();
        for (Idoso i : idosos) {
            if (i.getIdade() >= idadeMinima) {
                resultado.add(i);
            }
        }
        return resultado;
    }

    /**
     * Atualiza os dados de um idoso
     * @throws RegistroNaoEncontradoException se não encontrar
     * @throws DadosInvalidosException se os dados são inválidos
     */
    public static void atualizar(Idoso idoso) throws RegistroNaoEncontradoException, DadosInvalidosException {
        if (idoso == null) {
            throw new DadosInvalidosException("Idoso não pode ser nulo");
        }

        boolean encontrado = false;
        for (int i = 0; i < idosos.size(); i++) {
            if (idosos.get(i).getId() == idoso.getId()) {
                idosos.set(i, idoso);
                System.out.println("✓ Idoso atualizado: " + idoso.getNome());
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            throw new RegistroNaoEncontradoException("Idoso", idoso.getId());
        }
    }

    /**
     * Remove um idoso
     * @throws RegistroNaoEncontradoException se não encontrar
     */
    public static void remover(int id) throws RegistroNaoEncontradoException {
        Idoso idoso = buscarPorId(id);
        if (idoso != null) {
            idosos.remove(idoso);
            System.out.println("✓ Idoso removido: " + idoso.getNome());
        } else {
            throw new RegistroNaoEncontradoException("Idoso", id);
        }
    }

    /**
     * Retorna a quantidade total de idosos
     */
    public static int contarTotal() {
        return idosos.size();
    }

    /**
     * Limpa todos os idosos
     */
    public static void limparTodos() {
        idosos.clear();
        proximoId = 1;
        System.out.println("✓ Todos os idosos foram removidos!");
    }
}