package exception;

/**
 * Exceção lançada quando não encontra um registro no sistema
 */
public class RegistroNaoEncontradoException extends Exception {

    private int id;
    private String tipoRegistro;

    public RegistroNaoEncontradoException(String tipoRegistro, int id) {
        super(tipoRegistro + " com ID " + id + " não encontrado!");
        this.tipoRegistro = tipoRegistro;
        this.id = id;
    }

    public RegistroNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public int getId() {
        return id;
    }

    public String getTipoRegistro() {
        return tipoRegistro;
    }
}