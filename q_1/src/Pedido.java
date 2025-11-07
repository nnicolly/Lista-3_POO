public class Pedido {

    public enum StatusPedido { NOVO, CONFIRMADO, ENTREGUE }

    private static long proximoCodigo = 1;

    private final long codigo = proximoCodigo++;
    private java.time.LocalDateTime dataHoraCompra;
    private java.time.LocalDateTime dataHoraEntrega;
    private String enderecoEntrega;
    private int quantidade;
    private double precoUnitario;
    private double total;
    private String cartaoMascarado;
    private StatusPedido status = StatusPedido.NOVO;

    public Pedido(java.time.LocalDateTime dataHoraCompra, String enderecoEntrega, double precoUnitario) {
        this.dataHoraCompra = dataHoraCompra;
        this.enderecoEntrega = enderecoEntrega;
        this.precoUnitario = precoUnitario;
        this.quantidade = 0;
    }

    public long getCodigo() { return codigo; }
    public StatusPedido getStatus() { return status; }

    public void alterarEndereco(String novoEndereco) {
        this.enderecoEntrega = novoEndereco;
    }

    public void definirQuantidade(int quantidadeFinal) {
        this.quantidade = quantidadeFinal;
    }

    public void calcular() {
        this.total = precoUnitario * quantidade;
        this.dataHoraEntrega = dataHoraCompra.plusHours(2);
    }

    public void confirmarPagamentoComCartao(String numeroCartao) {
        this.cartaoMascarado = mascarar(numeroCartao);
        this.status = StatusPedido.CONFIRMADO;
    }

    public void marcarComoEntregue() {
        this.status = StatusPedido.ENTREGUE;
    }

    private String mascarar(String numero) {
        String digitos = numero.replaceAll("\\D", "");
        String ult4 = (digitos.length() <= 4) ? digitos : digitos.substring(digitos.length() - 4);
        return "**** **** **** " + ult4;
    }

    public String resumo() {
        return  "Código: " + codigo +
                "\nStatus: " + status +
                "\nCompra: " + dataHoraCompra +
                "\nEntrega (+2h): " + (dataHoraEntrega == null ? "-" : dataHoraEntrega) +
                "\nEndereço: " + enderecoEntrega +
                "\nQuantidade: " + (quantidade == 0 ? "-" : quantidade) +
                String.format("\nPreço unitário: R$ %.2f", precoUnitario) +
                String.format("\nTotal: R$ %.2f", total) +
                "\nCartão: " + (cartaoMascarado == null ? "-" : cartaoMascarado);
    }
}
