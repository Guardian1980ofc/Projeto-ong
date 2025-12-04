package gerenciador;

import model.Funcionario;
import model.Turnos;
import exception.CPFDuplicadoException;
import exception.DadosInvalidosException;
import exception.RegistroNaoEncontradoException;

import java.util.ArrayList;
import java.util.List;

/**
 * Gerencia os funcionários em memória (COM EXCEÇÕES PERSONALIZADAS)
 */
public class GerenciadorFuncionarios {

    private static ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private static int proximoId = 1;

    /**
     * Adiciona um novo funcionário
     * @throws CPFDuplicadoException se o CPF já existe
     * @throws DadosInvalidosException se os dados são inválidos
     */
    public static void adicionar(Funcionario funcionario) throws CPFDuplicadoException, DadosInvalidosException {
        // Validação: Funcionário não pode ser null
        if (funcionario == null) {
            throw new DadosInvalidosException("Funcionário não pode ser nulo");
        }

        // Validação: Nome obrigatório
        if (funcionario.getNome() == null || funcionario.getNome().trim().isEmpty()) {
            throw new DadosInvalidosException("nome", "Nome é obrigatório");
        }

        // Validação: CPF obrigatório
        if (funcionario.getCpf() == null || funcionario.getCpf().trim().isEmpty()) {
            throw new DadosInvalidosException("cpf", "CPF é obrigatório");
        }

        // Validação: CPF único
        if (buscarPorCpf(funcionario.getCpf()) != null) {
            throw new CPFDuplicadoException(funcionario.getCpf());
        }

        // Validação: Cargo obrigatório
        if (funcionario.getCargo() == null || funcionario.getCargo().trim().isEmpty()) {
            throw new DadosInvalidosException("cargo", "Cargo é obrigatório");
        }

        // Validação: Salário não pode ser negativo
        if (funcionario.getSalario() < 0) {
            throw new DadosInvalidosException("salario", "Salário não pode ser negativo");
        }

        // Se passou todas as validações, adiciona
        funcionario.setId(proximoId++);
        funcionarios.add(funcionario);
        System.out.println("✓ Funcionário adicionado: " + funcionario.getNome());
    }

    /**
     * Busca um funcionário pelo ID
     * @throws RegistroNaoEncontradoException se não encontrar
     */
    public static Funcionario buscarPorIdComExcecao(int id) throws RegistroNaoEncontradoException {
        Funcionario funcionario = buscarPorId(id);
        if (funcionario == null) {
            throw new RegistroNaoEncontradoException("Funcionário", id);
        }
        return funcionario;
    }

    /**
     * Busca um funcionário pelo ID (sem exceção)
     */
    public static Funcionario buscarPorId(int id) {
        for (Funcionario f : funcionarios) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    /**
     * Busca um funcionário pelo CPF
     */
    public static Funcionario buscarPorCpf(String cpf) {
        for (Funcionario f : funcionarios) {
            if (f.getCpf().equals(cpf)) {
                return f;
            }
        }
        return null;
    }

    /**
     * Busca funcionários pelo nome
     */
    public static List<Funcionario> buscarPorNome(String nome) {
        ArrayList<Funcionario> resultado = new ArrayList<>();
        String nomeBusca = nome.toLowerCase();

        for (Funcionario f : funcionarios) {
            if (f.getNome().toLowerCase().contains(nomeBusca)) {
                resultado.add(f);
            }
        }
        return resultado;
    }

    /**
     * Retorna todos os funcionários
     */
    public static List<Funcionario> listarTodos() {
        return new ArrayList<>(funcionarios);
    }

    /**
     * Retorna apenas funcionários ativos
     */
    public static List<Funcionario> listarAtivos() {
        ArrayList<Funcionario> ativos = new ArrayList<>();
        for (Funcionario f : funcionarios) {
            if (f.isAtivo()) {
                ativos.add(f);
            }
        }
        return ativos;
    }

    /**
     * Lista funcionários por cargo
     */
    public static List<Funcionario> listarPorCargo(String cargo) {
        ArrayList<Funcionario> resultado = new ArrayList<>();
        for (Funcionario f : funcionarios) {
            if (f.getCargo().equalsIgnoreCase(cargo)) {
                resultado.add(f);
            }
        }
        return resultado;
    }

    /**
     * Lista funcionários por turno
     */
    public static List<Funcionario> listarPorTurno(Turnos turno) {
        ArrayList<Funcionario> resultado = new ArrayList<>();
        for (Funcionario f : funcionarios) {
            if (f.getTurno() == turno) {
                resultado.add(f);
            }
        }
        return resultado;
    }

    /**
     * Atualiza os dados de um funcionário
     * @throws RegistroNaoEncontradoException se não encontrar
     * @throws DadosInvalidosException se os dados são inválidos
     */
    public static void atualizar(Funcionario funcionario) throws RegistroNaoEncontradoException, DadosInvalidosException {
        if (funcionario == null) {
            throw new DadosInvalidosException("Funcionário não pode ser nulo");
        }

        if (funcionario.getSalario() < 0) {
            throw new DadosInvalidosException("salario", "Salário não pode ser negativo");
        }

        boolean encontrado = false;
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getId() == funcionario.getId()) {
                funcionarios.set(i, funcionario);
                System.out.println("✓ Funcionário atualizado: " + funcionario.getNome());
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            throw new RegistroNaoEncontradoException("Funcionário", funcionario.getId());
        }
    }

    /**
     * Remove um funcionário
     * @throws RegistroNaoEncontradoException se não encontrar
     */
    public static void remover(int id) throws RegistroNaoEncontradoException {
        Funcionario funcionario = buscarPorId(id);
        if (funcionario != null) {
            funcionarios.remove(funcionario);
            System.out.println("✓ Funcionário removido: " + funcionario.getNome());
        } else {
            throw new RegistroNaoEncontradoException("Funcionário", id);
        }
    }

    /**
     * Desativa um funcionário
     * @throws RegistroNaoEncontradoException se não encontrar
     */
    public static void desativar(int id) throws RegistroNaoEncontradoException {
        Funcionario funcionario = buscarPorId(id);
        if (funcionario != null) {
            funcionario.setAtivo(false);
            System.out.println("✓ Funcionário desativado: " + funcionario.getNome());
        } else {
            throw new RegistroNaoEncontradoException("Funcionário", id);
        }
    }

    /**
     * Calcula a folha de pagamento (apenas ativos)
     */
    public static double calcularFolhaPagamento() {
        double total = 0;
        for (Funcionario f : funcionarios) {
            if (f.isAtivo()) {
                total += f.getSalario();
            }
        }
        return total;
    }

    /**
     * Retorna a quantidade total de funcionários
     */
    public static int contarTotal() {
        return funcionarios.size();
    }

    /**
     * Retorna a quantidade de funcionários ativos
     */
    public static int contarAtivos() {
        int count = 0;
        for (Funcionario f : funcionarios) {
            if (f.isAtivo()) count++;
        }
        return count;
    }

    /**
     * Limpa todos os funcionários
     */
    public static void limparTodos() {
        funcionarios.clear();
        proximoId = 1;
        System.out.println("✓ Todos os funcionários foram removidos!");
    }
}