package exception;

/**
 * Exceção lançada quando tenta cadastrar um CPF que já existe
 */
public class CPFDuplicadoException extends Exception {

    private String cpf;

    public CPFDuplicadoException(String cpf) {
        super("CPF já cadastrado no sistema: " + cpf);
        this.cpf = cpf;
    }

    public CPFDuplicadoException(String cpf, String mensagem) {
        super(mensagem);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }
}