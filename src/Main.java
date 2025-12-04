import view.TelaPrincipal;
import util.SalvarDados;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO SISTEMA ONG ===");

        // Carrefga dados do arquivo
        SalvarDados.carregarDoadores();

        // Registra no log
        SalvarDados.registrarLog("Sistema iniciado");

        // Inicia a intertface
        new TelaPrincipal();

        // Quando a janela principal fechar salva os dados
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            SalvarDados.salvarTudo();
            SalvarDados.gerarRelatorioDiario();
            SalvarDados.registrarLog("Sistema encerrado");
            System.out.println("=== SISTEMA ENCERRADO ===");
        }));
    }
}