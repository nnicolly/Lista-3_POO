public class Robo {

    public enum StatusRobo { ATIVO }

    private int linha;
    private int coluna;
    private int passo;
    private StatusRobo status = StatusRobo.ATIVO;

    public Robo(int linhaInicial, int colunaInicial, int passo) {
        this.linha = linhaInicial;
        this.coluna = colunaInicial;
        this.passo = passo;
    }

    public String mostrarPosicaoAtual() {
        return "Posição do robô -> linha (X): " + linha + ", coluna (Y): " + coluna + " (passo: " + passo + ")";
    }

    public void andarFrente(int limiteVertical) {
        int destino = coluna - passo;
        if (destino < 0) {
            coluna = 0;
        } else {
            coluna = destino;
        }
    }

    public void andarTras(int limiteVertical) {
        int destino = coluna + passo;
        if (destino >= limiteVertical) {
            coluna = limiteVertical - 1;
        } else {
            coluna = destino;
        }
    }

    public void andarDireita(int limiteHorizontal) {
        int destino = linha + passo;
        if (destino >= limiteHorizontal) {
            linha = limiteHorizontal - 1;
        } else {
            linha = destino;
        }
    }

    public void andarEsquerda() {
        int destino = linha - passo;
        if (destino < 0) {
            linha = 0;
        } else {
            linha = destino;
        }
    }

    public int getLinha()  { return linha; }
    public int getColuna() { return coluna; }
}
