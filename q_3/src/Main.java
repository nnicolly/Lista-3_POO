public class Main {

    private static final int MAX_JOGADORES = 11;
    private static final java.util.Scanner SC = new java.util.Scanner(System.in);
    private static final Ranking RANKING = new Ranking("ranking.txt");

    public static void main(String[] args) {
        mostrarTopFive();
        System.out.println("\nPressione ENTER para iniciar o jogo...");
        SC.nextLine();

        java.util.List<Jogador> jogadores = lerJogadores();

        if (jogadores.isEmpty()) {
            System.out.println("Nenhum jogador cadastrado. Encerrando o jogo.");
            return;
        }

        Dado dado1 = new Dado();
        Dado dado2 = new Dado();

        int valor1 = dado1.rolar();
        int valor2 = dado2.rolar();
        int soma = valor1 + valor2;

        System.out.println("\n=== Lançando os dados ===");
        System.out.println("Dado 1: " + valor1);
        System.out.println("Dado 2: " + valor2);
        System.out.println("Soma sorteada: " + soma);

        java.util.List<Jogador> vencedores = new java.util.ArrayList<>();

        for (Jogador p : jogadores) {
            if (p.getAposta() == soma) {
                vencedores.add(p);
            }
        }

        if (vencedores.isEmpty()) {
            System.out.println("\nNenhum jogador acertou. A MÁQUINA venceu!");
        } else {
            System.out.println("\nTemos vencedor(es)!");
            for (Jogador p : vencedores) {
                System.out.println("Jogador vencedor: " + p.getUsername());
                RANKING.registrarVitoria(p.getUsername());
            }
            System.out.println("\nRanking atualizado após esta partida!");
        }
    }

    private static void mostrarTopFive() {
        System.out.println("=== TOP FIVE - Jogo de Dados ===");
        String[] top = RANKING.obterTopFive();

        if (top.length == 0) {
            System.out.println("Nenhum registro de vitórias até o momento.");
        } else {
            for (String linha : top) {
                System.out.println(linha);
            }
        }
    }

    private static java.util.List<Jogador> lerJogadores() {
        java.util.List<Jogador> jogadores = new java.util.ArrayList<>();

        System.out.println("\n=== Cadastro de Jogadores ===");
        System.out.println("Máximo de " + MAX_JOGADORES + " jogadores.");
        System.out.println("Para parar antes, deixe o username em branco e aperte ENTER.\n");

        while (jogadores.size() < MAX_JOGADORES) {
            System.out.print("Username do jogador " + (jogadores.size() + 1) + ": ");
            String username = SC.nextLine().trim();

            if (username.isEmpty()) {
                break;
            }

            if (existeUsername(jogadores, username)) {
                System.out.println("Esse username já está sendo usado. Tente outro.");
                continue;
            }

            int aposta = lerAposta();
            jogadores.add(new Jogador(username, aposta));
        }
        return jogadores;
    }

    private static boolean existeUsername(java.util.List<Jogador> jogadores, String usernameNovo) {
        String novo = usernameNovo.trim().toLowerCase();
        for (Jogador p : jogadores) {
            if (p.getUsername().trim().toLowerCase().equals(novo)) {
                return true;
            }
        }
        return false;
    }

    private static int lerAposta() {
        while (true) {
            System.out.print("Digite o valor da aposta (2 a 12): ");
            String txt = SC.nextLine().trim();

            try {
                int valor = Integer.parseInt(txt);
                if (valor >= 2 && valor <= 12) {
                    return valor;
                }
            } catch (Exception e) {}
            System.out.println("Aposta inválida. Informe um número entre 2 e 12.");
        }
    }
}
