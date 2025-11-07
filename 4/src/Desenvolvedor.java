public class Desenvolvedor extends Colaborador {

    protected String linguagemPrincipal;

    public Desenvolvedor() {
        super("Dev-Padrao");
        this.linguagemPrincipal = "Java";
    }

    public Desenvolvedor(String nome, String linguagemPrincipal) {
        super(nome);
        this.linguagemPrincipal = linguagemPrincipal;
    }

    public Desenvolvedor(String nome, double salarioBase, String linguagemPrincipal) {
        super(nome, salarioBase);
        this.linguagemPrincipal = linguagemPrincipal;
    }

    @Override
    public void trabalhar() {
        System.out.println(nome + " está codando em " + linguagemPrincipal + ".");
    }

    public void codar() {
        System.out.println(nome + " está desenvolvendo novas features.");
    }

    public void codar(String tarefa) {
        System.out.println(nome + " está codando a tarefa: " + tarefa +
                " em " + linguagemPrincipal + ".");
    }
}
