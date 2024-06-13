package LOJA;

import java.util.ArrayList;

public class Frete {
    private int id;
    private int cepInicial;
    private int cepFinal;
    private double valorPorKilo;

    public Frete(int id, int cepInicial, int cepFinal, double valorPorKilo) {
        this.setId(id);
        this.cepInicial = cepInicial;
        this.cepFinal = cepFinal;
        this.valorPorKilo = valorPorKilo;
    }

    public int getCepInicial() {
        return cepInicial;
    }

    public int getCepFinal() {
        return cepFinal;
    }

    public double getValorPorKilo() {
        return valorPorKilo;
    }

    public double calcularFrete(Carrinho carrinho, ArrayList<ItensCarrinho> itens, ArrayList<Produto> produtos, int cepEntrega) {
        double pesoTotal = 0.0;
        for (ItensCarrinho item : itens) {
            for (Produto produto : produtos) {
                if (item.getUpc().equals(produto.getUpc())) {
                    pesoTotal += produto.getPeso() * item.getQuantidade();
                }
            }
        }
        return valorPorKilo * pesoTotal;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}