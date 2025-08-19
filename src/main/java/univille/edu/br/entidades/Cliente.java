package univille.edu.br.entidades;

public class Cliente {

    long idCliente;
    String nome;
    String cpf;
    String email;

    public Cliente(long idCliente, String nome, String cpf, String email) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }
}
