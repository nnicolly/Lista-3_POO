public class FrontendDev extends Desenvolvedor implements BonusElegivel {

    private boolean fullstack;

    public FrontendDev() {
        super("Frontend-Padrao", 3800.0, "JavaScript");
        this.fullstack = false;
    }

    public FrontendDev(String nome, double salarioBase, String linguagem, boolean fullstack) {
        super(nome, salarioBase, linguagem);
        this.fullstack = fullstack;
    }

    @Override
    public void trabalhar() {
        String tipo = fullstack ? "Fullstack" : "Frontend";
        System.out.println(nome + " (" + tipo + ") está construindo interfaces em " +
                linguagemPrincipal + ".");
    }

    @Override
    public double calcularBonus() {
        double bonus = fullstack ? 800.0 : 400.0;
        System.out.println("Bônus de " + nome + ": " + bonus);
        return bonus;
    }

    public void criarTela() {
        System.out.println(nome + " está criando uma nova tela.");
    }

    public void criarTela(String tela) {
        System.out.println(nome + " está criando a tela: " + tela + ".");
    }
}
