public abstract class Colaborador {

    protected String nome;
    protected double salarioBase;

    public Colaborador() {
        this.nome = "SemNome";
        this.salarioBase = 1000.0;
    }

    public Colaborador(String nome) {
        this.nome = nome;
        this.salarioBase = 1000.0;
    }

    public Colaborador(String nome, double salarioBase) {
        this.nome = nome;
        this.salarioBase = salarioBase;
    }

    public void exibirInfo() {
        System.out.println("Colaborador: " + nome + " | Salário base: " + salarioBase);
    }

    public abstract void trabalhar();
}
