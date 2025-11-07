public class Main {

    private static final double PRECO_UNITARIO = 103.00;

    private static final java.util.ArrayList<Pedido> pedidos = new java.util.ArrayList<>();
    private static final java.util.Scanner sc = new java.util.Scanner(System.in);
    private static final java.time.format.DateTimeFormatter DTF =
            java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Menu ===");
            System.out.println("1) Fazer pedido");
            System.out.println("2) Confirmar entrega");
            System.out.println("3) Ver pedidos confirmados");
            System.out.println("4) Ver pedidos entregues");
            System.out.println("0) Sair");
            System.out.print("Escolha: ");
            String op = sc.nextLine().trim();

            switch (op) {
                case "1": fazerPedido(); break;
                case "2": confirmarEntrega(); break;
                case "3": listarPorStatus(Pedido.StatusPedido.CONFIRMADO); break;
                case "4": listarPorStatus(Pedido.StatusPedido.ENTREGUE); break;
                case "0": System.out.println("Encerrando..."); return;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    private static void fazerPedido() {
        try {
            java.time.LocalDateTime compra = lerDataHora("Data e hora da compra (dd/MM/aaaa HH:mm)");

            System.out.print("Endereço de entrega: ");
            String endereco = sc.nextLine().trim();

            Pedido p = new Pedido(compra, endereco, PRECO_UNITARIO);

            System.out.println("\n--- Prévia do pedido (sem quantidade ainda) ---");
            System.out.println(p.resumo());

            System.out.print("\n[S] Confirmar  [A] Alterar endereço  [C] Cancelar: ");
            String acao = sc.nextLine().trim().toUpperCase();
            if ("C".equals(acao)) {
                System.out.println("Pedido cancelado.");
                return;
            }
            if ("A".equals(acao)) {
                System.out.print("Novo endereço: ");
                String novoEnd = sc.nextLine().trim();
                p.alterarEndereco(novoEnd);
                System.out.println("\nPedido atualizado:");
                System.out.println(p.resumo());
            }

            int quantidadeFinal = lerInteiro("Informe a quantidade de botijões: ");
            p.definirQuantidade(quantidadeFinal);

            p.calcular();
            System.out.println("\nCálculo realizado (+2h e total):");
            System.out.println(p.resumo());

            System.out.print("\nNúmero do cartão de crédito (somente números): ");
            String cartao = sc.nextLine().trim();
            p.confirmarPagamentoComCartao(cartao);

            pedidos.add(p);
            System.out.println("\nPedido CONFIRMADO! Código: " + p.getCodigo());

        } catch (Exception e) {
            System.out.println("Erro ao fazer pedido: " + e.getMessage());
        }
    }

    private static void confirmarEntrega() {
        long codigo = lerLong("Informe o código do pedido: ");

        java.util.Optional<Pedido> opt = pedidos.stream()
                .filter(x -> x.getCodigo() == codigo)
                .findFirst();

        if (opt.isPresent()) {
            opt.get().marcarComoEntregue();
            System.out.println("Status atualizado para ENTREGUE.");
        } else {
            System.out.println("Pedido não localizado.");
        }
    }

    private static void listarPorStatus(Pedido.StatusPedido status) {
        System.out.println("\n--- Pedidos " + status + " ---");
        boolean algum = false;
        for (Pedido p : pedidos) {
            if (p.getStatus() == status) {
                System.out.println(p.resumo());
                System.out.println("------------------------------");
                algum = true;
            }
        }
        if (!algum) System.out.println("Nenhum pedido " + status + ".");
    }

    private static java.time.LocalDateTime lerDataHora(String prompt) {
        System.out.print(prompt);
        String s = sc.nextLine().trim();
        if (s.isEmpty()) {
            return java.time.LocalDateTime.now().withSecond(0).withNano(0);
        }
        return java.time.LocalDateTime.parse(s, DTF);
    }

    private static int lerInteiro(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int v = Integer.parseInt(sc.nextLine().trim());
                if (v > 0) return v;
            } catch (Exception ignored) {}
            System.out.println("Valor inválido. Tente novamente.");
        }
    }

    private static long lerLong(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Long.parseLong(sc.nextLine().trim());
            } catch (Exception ignored) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
    }
}
