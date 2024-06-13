package LOJA;

public class ItensCarrinho {
    private int id;
    private int idCarrinho;
    private String upc;
    private double valor;
    private int qtde;

    public ItensCarrinho(int id, int idCarrinho, String upc, double valor, int qtde) {
        this.id = id;
        this.idCarrinho = idCarrinho;
        this.upc = upc;
        this.valor = valor;
        this.qtde = qtde;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCarrinho() {
        return idCarrinho;
    }

    public void setIdCarrinho(int idCarrinho) {
        this.idCarrinho = idCarrinho;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

	public double getQuantidade() {
		// TODO Auto-generated method stub
		return 0;
	}
}
