import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // 1) Cria os vertices (nos) do grafo
        Node a = new Node(0, "A");
        Node b = new Node(1, "B");
        Node c = new Node(2, "C");
        Node d = new Node(3, "D");

        List<Node> listaDeAdjacencia = new ArrayList<>();

        // false = grafo NAO direcionado. Troque para true para testar o caso direcionado.
        Lista grafo = new Lista(listaDeAdjacencia, false);

        grafo.addVertice(a);
        grafo.addVertice(b);
        grafo.addVertice(c);
        grafo.addVertice(d);

        // 2) Cria as arestas (arbitrarias, so para ter algo pra testar)
        grafo.addAresta(a, b, 1);
        grafo.addAresta(a, c, 4);
        grafo.addAresta(b, c, 2);
        grafo.addAresta(c, d, 5);

        // 3) Testa mostrarLista()
        System.out.println("=== Lista de adjacencia ===");
        grafo.mostrarLista();

        // 4) Testa loop()
        System.out.println("\n=== Existe laco? ===");
        System.out.println(grafo.loop());

        // 5) Testa matrizAdjacencia()
        System.out.println("\n=== Matriz de adjacencia ===");
        int[][] adjacencia = Matrizes.matrizAdjacencia(grafo);
        imprimeMatriz(adjacencia);

        // 6) Testa matrizIncidencia()
        System.out.println("\n=== Matriz de incidencia ===");
        int[][] incidencia = Matrizes.matrizIncidencia(grafo);
        imprimeMatriz(incidencia);
    }

    // mostraMatriz() em Matrizes.java ainda esta vazia, entao uso esta
    // funcao auxiliar aqui no Main so para poder ver o resultado no console
    private static void imprimeMatriz(int[][] matriz) {
        for (int[] linha : matriz) {
            for (int valor : linha) {
                System.out.print(valor + " ");
            }
            System.out.println();
        }
    }
}