package LOJA;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Criar produtos
        ArrayList<Produto> produtos = new ArrayList<>();
        Produto prod1 = new Produto("Shampoo", "456789", "sdfrg456", "p/ cabelo", 12.34, 0.500);
        Produto prod2 = new Produto("Condicionador", "55555", "ajj555", "p/ cabelo", 12.45, 0.500);
        produtos.add(prod1);
        produtos.add(prod2);

        // Criar carrinho
        Carrinho carrinho = new Carrinho();
        carrinho.setId(1);

        // Criar itens do carrinho
        ArrayList<ItensCarrinho> itens = new ArrayList<>();
        ItensCarrinho item1 = new ItensCarrinho(1, 1, "456789", 12.34, 2);
        ItensCarrinho item2 = new ItensCarrinho(2, 1, "55555", 12.45, 3);
        itens.add(item1);
        itens.add(item2);

        // Criar objeto Menu e processar
        Menu menu = new Menu(produtos, carrinho, itens);
        menu.Processamento();

        try (// Calcular frete
            Scanner sc = new Scanner(System.in)) {
            System.out.println("Digite o CEP de entrega: ");
            int cepEntrega = sc.nextInt();

            double freteFinal = calcularFrete(carrinho, itens, produtos, cepEntrega, "fretes.txt");
            if (freteFinal != -1) {
                System.out.println("O valor do frete é: R$" + freteFinal);
            } else {
                System.out.println("CEP de entrega não encontrado na lista de fretes.");
            }
        }
    }

    public static double calcularFrete(Carrinho carrinho, ArrayList<ItensCarrinho> itens, ArrayList<Produto> produtos, int cepEntrega, String nomeArquivo) {
        double pesoTotal = 0.0;
        for (ItensCarrinho item : itens) {
            for (Produto produto : produtos) {
                if (item.getUpc().equals(produto.getUpc())) {
                    pesoTotal += produto.getPeso() * item.getQuantidade();
                }
            }
        }

        try {
            File arq = new File(nomeArquivo);
            Scanner leitor = new Scanner(arq);
            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                String[] dados = linha.split(";");
                int cepInicial = Integer.parseInt(dados[0].replaceAll("[^\\d]", ""));
                int cepFinal = Integer.parseInt(dados[1].replaceAll("[^\\d]", ""));
                double valorPorKilo = Double.parseDouble(dados[2].trim());

                if (cepEntrega >= cepInicial && cepEntrega <= cepFinal) {
                    leitor.close();
                    return valorPorKilo * pesoTotal;
                }
            }
            leitor.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro na leitura do arquivo: " + e.getMessage());
        }
        return -1;
    }
}
