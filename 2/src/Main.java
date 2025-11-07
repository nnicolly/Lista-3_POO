public class Main {

    private static final int SALA_LARGURA = 40;
    private static final int SALA_ALTURA = 20;
    private static final java.util.Scanner SC = new java.util.Scanner(System.in);

    public static void main(String[] args) {
        Robo r1 = new Robo(0, 0, 1);
        desenharSala(r1);
        System.out.println(r1.mostrarPosicaoAtual());

        while (true) {
            System.out.println("\n=== Controle do Robô ===");
            System.out.println("1 - Andar para Frente  (Cima)");
            System.out.println("2 - Andar para Trás    (Baixo)");
            System.out.println("3 - Andar para Direita");
            System.out.println("4 - Andar para Esquerda");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            String op = SC.nextLine().trim();

            if ("0".equals(op)) {
                System.out.println("Encerrando...");
                return;
            }

            switch (op) {
                case "1":
                    r1.andarFrente(SALA_ALTURA);
                    break;
                case "2":
                    r1.andarTras(SALA_ALTURA);
                    break;
                case "3":
                    r1.andarDireita(SALA_LARGURA);
                    break;
                case "4":
                    r1.andarEsquerda();
                    break;
                default:
                    System.out.println("Opção inválida.");
                    continue;
            }

            desenharSala(r1);
            System.out.println(r1.mostrarPosicaoAtual());
        }
    }

    private static void desenharSala(Robo r) {
        int roboX = r.getLinha();
        int roboY = r.getColuna();

        System.out.print('+');
        for (int x = 0; x < SALA_LARGURA; x++) {
            System.out.print("--");
        }
        System.out.println('+');

        for (int y = 0; y < SALA_ALTURA; y++) {
            System.out.print('|');
            for (int x = 0; x < SALA_LARGURA; x++) {
                if (x == roboX && y == roboY) {
                    System.out.print("r1 ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println('|');
        }

        System.out.print('+');
        for (int x = 0; x < SALA_LARGURA; x++) {
            System.out.print("--");
        }
        System.out.println('+');
    }
}
