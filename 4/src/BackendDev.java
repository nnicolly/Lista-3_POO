public class BackendDev extends Desenvolvedor {

    private String frameworkBackend;

    public BackendDev() {
        super("Backend-Padrao", 4000.0, "Java");
        this.frameworkBackend = "Spring";
    }

    public BackendDev(String nome, String linguagem, String frameworkBackend) {
        super(nome, 4500.0, linguagem);
        this.frameworkBackend = frameworkBackend;
    }

    @Override
    public void trabalhar() {
        System.out.println(nome + " (Backend) está implementando APIs com " +
                linguagemPrincipal + " e " + frameworkBackend + ".");
    }

    public void otimizarBanco() {
        System.out.println(nome + " está otimizando consultas no banco de dados.");
    }
}
