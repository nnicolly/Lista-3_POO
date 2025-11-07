public class Gestor extends Colaborador implements BonusElegivel, Lideranca {

    private int tamanhoEquipe;

    public Gestor() {
        super("Gestor-Padrao", 5000.0);
        this.tamanhoEquipe = 5;
    }

    public Gestor(String nome, int tamanhoEquipe) {
        super(nome, 6000.0);
        this.tamanhoEquipe = tamanhoEquipe;
    }

    public Gestor(String nome, double salarioBase, int tamanhoEquipe) {
        super(nome, salarioBase);
        this.tamanhoEquipe = tamanhoEquipe;
    }

    @Override
    public void trabalhar() {
        System.out.println(nome + " está coordenando a equipe de " +
                tamanhoEquipe + " pessoas.");
    }

    @Override
    public double calcularBonus() {
        double bonus = tamanhoEquipe * 150.0;
        System.out.println("Bônus de " + nome + ": " + bonus);
        return bonus;
    }

    @Override
    public void alocarEmProjeto(String nomeProjeto) {
        System.out.println(nome + " alocou a equipe no projeto: " + nomeProjeto);
    }

    @Override
    public void avaliarDesempenho(String nomeColaborador, int nota) {
        System.out.println(nome + " avaliou " + nomeColaborador +
                " com nota " + nota + ".");
    }

    public void realizarReuniao() {
        System.out.println(nome + " está conduzindo uma reunião geral.");
    }

    public void realizarReuniao(String tema) {
        System.out.println(nome + " está conduzindo uma reunião sobre: " + tema);
    }
}
