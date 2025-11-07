public class Main {

    public static void main(String[] args) {

        Desenvolvedor dev = new Desenvolvedor("Ana Dev", 3500.0, "Java");
        dev.exibirInfo();
        dev.trabalhar();
        dev.codar();
        dev.codar("Módulo de pagamentos");

        Gestor gestor = new Gestor("Bruno Gestor", 7500.0, 8);
        gestor.exibirInfo();
        gestor.trabalhar();
        gestor.calcularBonus();
        gestor.alocarEmProjeto("App Mobile");
        gestor.avaliarDesempenho("Ana Dev", 9);
        gestor.realizarReuniao();
        gestor.realizarReuniao("Planejamento Q4");

        BackendDev back = new BackendDev("Carlos Backend", "Kotlin", "Spring Boot");
        back.exibirInfo();
        back.trabalhar();
        back.codar("API de usuários");
        back.otimizarBanco();

        FrontendDev front = new FrontendDev("Diana Front", 3900.0, "TypeScript", true);
        front.exibirInfo();
        front.trabalhar();
        front.criarTela();
        front.criarTela("Dashboard");
        front.calcularBonus();

        Colaborador c1 = dev;
        Colaborador c2 = gestor;
        Colaborador c3 = back;
        Colaborador c4 = front;

        c1.trabalhar();
        c2.trabalhar();
        c3.trabalhar();
        c4.trabalhar();

        BonusElegivel b1 = gestor;
        BonusElegivel b2 = front;

        b1.calcularBonus();
        b2.calcularBonus();
    }
}
