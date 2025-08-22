package univille.edu.br.entidades;

public class MelhoresClientes {
    private final int idCliente;
    private final String nome;
    private final int totalContratos;

    public MelhoresClientes(int idCliente, String nome, int totalContratos) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.totalContratos = totalContratos;
    }

    public int getIdCliente() { return idCliente; }
    public String getNome() { return nome; }
    public int getTotalContratos() { return totalContratos; }

    @Override
    public String toString() {
        return "idCliente: " + idCliente + " | Nome: " + nome + " | Total de contratos: " + totalContratos;
    }
}
