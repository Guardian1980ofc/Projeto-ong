package exception;

/**
 * Exceção lançada quando os dados fornecidos são inválidos
 */
public class DadosInvalidosException extends Exception {

    private String campo;

    public DadosInvalidosException(String campo, String mensagem) {
        super("Dados inválidos no campo '" + campo + "': " + mensagem);
        this.campo = campo;
    }

    public DadosInvalidosException(String mensagem) {
        super(mensagem);
    }

    public String getCampo() {
        return campo;
    }
}