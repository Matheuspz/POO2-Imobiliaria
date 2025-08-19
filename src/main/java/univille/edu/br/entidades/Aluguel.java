package univille.edu.br.entidades;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Aluguel {

    private long idAluguel;
    private long idCliente;
    private long idImovel;
    private BigDecimal preco;
    private LocalDateTime dataAluguel;
    private LocalDateTime dataVencimento;

    public Aluguel(long idAluguel, long idCliente, long idImovel, BigDecimal preco, LocalDateTime dataAluguel, LocalDateTime dataVencimento) {
        this.idAluguel = idAluguel;
        this.idCliente = idCliente;
        this.idImovel = idImovel;
        this.preco = preco;
        this.dataAluguel = dataAluguel;
        this.dataVencimento = dataVencimento;
    }

    public long getIdAluguel() {
        return idAluguel;
    }

    public void setIdAluguel(long idAluguel) {
        this.idAluguel = idAluguel;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public long getIdImovel() {
        return idImovel;
    }

    public void setIdImovel(long idImovel) {
        this.idImovel = idImovel;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public LocalDateTime getDataAluguel() {
        return dataAluguel;
    }

    public void setDataAluguel(LocalDateTime dataAluguel) {
        this.dataAluguel = dataAluguel;
    }

    public LocalDateTime getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDateTime dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
}
