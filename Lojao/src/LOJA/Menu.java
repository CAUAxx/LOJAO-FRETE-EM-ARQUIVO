package LOJA;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private ArrayList<Produto> produtos;
    private Carrinho carrinho;
    private ArrayList<ItensCarrinho> itens;

    public Menu(ArrayList<Produto> produtos, Carrinho carrinho, ArrayList<ItensCarrinho> itens) {
        this.produtos = produtos;
        this.carrinho = carrinho;
        this.itens = itens;
    }

    public void Processamento() {
        try (Scanner sc = new Scanner(System.in)) {
            int opcao;

            do {
                System.out.println("Digite a opção:");
                System.out.println("1- Incluir Produto");
                System.out.println("2- Alterar Produto");
                System.out.println("3- Excluir Produto");
                System.out.println("4- Listar Produtos");
                System.out.println("5- Buscar Produto");
                System.out.println("6- Calcular Frete");
                System.out.println("7- Sair ");

                opcao = sc.nextInt();

                switch (opcao) {
                    case 1:
                        IncluirProduto();
                        break;
                    case 2:
                        AlterarProduto();
                        break;
                    case 3:
                        ExcluirProduto();
                        break;
                    case 4:
                        ListarProduto();
                        break;
                    case 5:
                        BuscarProduto();
                        break;
                    case 6:
                        CalcularFrete();
                        break;
                    case 7:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente!");
                }
            } while (opcao != 7);
        }
    }

    private void IncluirProduto() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Digite o nome do produto: ");
            String nome = sc.nextLine();
            System.out.println("Digite o UPC do produto: ");
            String upc = sc.nextLine();
            System.out.println("Digite o SKU do produto: ");
            String sku = sc.nextLine();
            System.out.println("Digite a descrição do produto: ");
            String descricao = sc.nextLine();
            System.out.println("Digite o valor do produto: ");
            double valor = sc.nextDouble();
            System.out.println("Digite o peso do produto: ");
            double peso = sc.nextDouble();

            produtos.add(new Produto(nome, upc, sku, descricao, valor, peso));
        }
        System.out.println("Produto incluído com sucesso!");
    }

    private void AlterarProduto() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Digite o UPC do produto a ser alterado: ");
            String upc = sc.nextLine();

            for (Produto produto : produtos) {
                if (produto.getUpc().equals(upc)) {
                    System.out.println("Digite o novo valor do produto: ");
                    double novoValor = sc.nextDouble();
                    produto.setValor(novoValor);
                    System.out.println("Produto alterado com sucesso!");
                    return;
                }
            }
        }
        System.out.println("Produto não encontrado.");
    }

    private void ExcluirProduto() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Digite o UPC do produto a ser excluído: ");
            String upc = sc.nextLine();

            for (Produto produto : produtos) {
                if (produto.getUpc().equals(upc)) {
                    produtos.remove(produto);
                    System.out.println("Produto excluído com sucesso!");
                    return;
                }
            }
        }
        System.out.println("Produto não encontrado.");
    }

    private void ListarProduto() {
        System.out.println("Lista de Produtos:");
        for (Produto produto : produtos) {
            System.out.println(produto.getNome() + " - " + produto.getValor());
        }
    }

    private void BuscarProduto() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Digite o UPC do produto a ser buscado: ");
            String upc = sc.nextLine();

            for (Produto produto : produtos) {
                if (produto.getUpc().equals(upc)) {
                    System.out.println("Produto encontrado:");
                    System.out.println("Nome: " + produto.getNome());
                    System.out.println("Valor: " + produto.getValor());
                    return;
                }
            }
        }
        System.out.println("Produto não encontrado.");
    }

    private void CalcularFrete() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Digite o CEP de entrega: ");
            int cepEntrega = sc.nextInt();

            double freteFinal = Main.calcularFrete(carrinho, itens, produtos, cepEntrega, "fretes.txt");
            if (freteFinal != -1) {
                System.out.println("O valor do frete é: R$" + freteFinal);
            } else {
                System.out.println("CEP de entrega não encontrado na lista de fretes.");
            }
        }
    }
}