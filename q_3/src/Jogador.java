public class Jogador {

    private String username;
    private int aposta;

    public Jogador(String username, int aposta) {
        this.username = username;
        this.aposta = aposta;
    }

    public String getUsername() {
        return username;
    }

    public int getAposta() {
        return aposta;
    }
}
