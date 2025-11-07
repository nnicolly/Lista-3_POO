public class Ranking {

    private static class Registro {
        String nome;
        int vitorias;

        Registro(String nome, int vitorias) {
            this.nome = nome;
            this.vitorias = vitorias;
        }
    }

    private java.util.Map<String, Registro> registros = new java.util.HashMap<>();
    private String nomeArquivo;

    public Ranking(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        carregarDoArquivo();
    }

    private String normalizar(String username) {
        return username.trim().toLowerCase();
    }

    public void registrarVitoria(String usernameOriginal) {
        String chave = normalizar(usernameOriginal);

        Registro r = registros.get(chave);
        if (r == null) {
            r = new Registro(usernameOriginal.trim(), 1);
            registros.put(chave, r);
        } else {
            r.vitorias++;
        }

        salvarNoArquivo();
    }

    public String[] obterTopFive() {
        java.util.List<Registro> lista = new java.util.ArrayList<>(registros.values());

        lista.sort(new java.util.Comparator<Registro>() {
            public int compare(Registro a, Registro b) {
                if (b.vitorias != a.vitorias) {
                    return b.vitorias - a.vitorias;
                }
                return a.nome.compareToIgnoreCase(b.nome);
            }
        });

        int tamanho = lista.size() < 5 ? lista.size() : 5;
        String[] top = new String[tamanho];

        for (int i = 0; i < tamanho; i++) {
            Registro r = lista.get(i);
            top[i] = (i + 1) + "º - " + r.nome + " (" + r.vitorias + " vitória"
                    + (r.vitorias > 1 ? "s" : "") + ")";
        }

        return top;
    }

    private void carregarDoArquivo() {
        registros.clear();

        java.io.File arquivo = new java.io.File(nomeArquivo);
        if (!arquivo.exists()) {
            return;
        }

        java.io.BufferedReader br = null;
        try {
            br = new java.io.BufferedReader(new java.io.FileReader(arquivo));
            String linha = null;

            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty()) {
                    continue;
                }

                String[] partes = linha.split(";");
                if (partes.length != 2) {
                    continue;
                }

                String nome = partes[0].trim();
                String vitStr = partes[1].trim();
                int vit = 0;
                try {
                    vit = Integer.parseInt(vitStr);
                } catch (Exception e) {
                    continue;
                }

                String chave = normalizar(nome);
                Registro existente = registros.get(chave);
                if (existente == null) {
                    registros.put(chave, new Registro(nome, vit));
                } else {
                    existente.vitorias += vit;
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar ranking: " + e.getMessage());
        } finally {
            if (br != null) {
                try { br.close(); } catch (Exception e) {}
            }
        }
    }

    private void salvarNoArquivo() {
        java.io.PrintWriter pw = null;
        try {
            pw = new java.io.PrintWriter(new java.io.FileWriter(nomeArquivo, false));

            for (Registro r : registros.values()) {
                pw.println(r.nome + ";" + r.vitorias);
            }

        } catch (Exception e) {
            System.out.println("Erro ao salvar ranking: " + e.getMessage());
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
}
