package univille.edu.br.entidades;

public class Imovel {

    private long idImovel;
    private String nomeImovel;
    private int quantidadeQuartos;
    private boolean disponivel;

    public Imovel(long idImovel, String nomeImovel, int quantidadeQuartos, boolean disponivel) {
        this.idImovel = idImovel;
        this.nomeImovel = nomeImovel;
        this.quantidadeQuartos = quantidadeQuartos;
        this.disponivel = disponivel;
    }

    public long getIdImovel() {
        return idImovel;
    }

    public void setIdImovel(long idImovel) {
        this.idImovel = idImovel;
    }

    public String getNomeImovel() {
        return nomeImovel;
    }

    public void setNomeImovel(String nomeImovel) {
        this.nomeImovel = nomeImovel;
    }

    public int getQuantidadeQuartos() {
        return quantidadeQuartos;
    }

    public void setQuantidadeQuartos(int quantidadeQuartos) {
        this.quantidadeQuartos = quantidadeQuartos;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
