import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Lista lista = new Lista(
                new ArrayList<>(),
                false
        );

        Node a = new Node(1, "A");
        Node b = new Node(2, "B");
        Node c = new Node(3, "C");
        Node d = new Node(4, "D");
        Node e = new Node(5, "E");
        Node f = new Node(6, "F");
        Node g = new Node(7, "G");
        Node h = new Node(8, "H");
        Node i = new Node(9, "I");
        Node j = new Node(10, "J");
        Node k = new Node(11, "K");
        Node l = new Node(12, "L");
        Node m = new Node(13, "M");
        Node n = new Node(14, "N");
        Node o = new Node(15, "O");

        lista.addVertice(a);
        lista.addVertice(b);
        lista.addVertice(c);
        lista.addVertice(d);
        lista.addVertice(e);
        lista.addVertice(f);
        lista.addVertice(g);
        lista.addVertice(h);
        lista.addVertice(i);
        lista.addVertice(j);
        lista.addVertice(k);
        lista.addVertice(l);
        lista.addVertice(m);
        lista.addVertice(n);
        lista.addVertice(o);

        lista.addAresta(a, b, 7);
        lista.addAresta(a, c, 4);
        lista.addAresta(a, d, 9);
        lista.addAresta(a, e, 12);

        lista.addAresta(b, c, 2);
        lista.addAresta(b, e, 6);
        lista.addAresta(b, f, 11);
        lista.addAresta(b, g, 8);

        lista.addAresta(c, d, 3);
        lista.addAresta(c, f, 5);
        lista.addAresta(c, g, 10);

        lista.addAresta(d, e, 1);
        lista.addAresta(d, g, 7);
        lista.addAresta(d, h, 13);

        lista.addAresta(e, f, 4);
        lista.addAresta(e, h, 8);
        lista.addAresta(e, i, 15);

        lista.addAresta(f, g, 3);
        lista.addAresta(f, i, 6);
        lista.addAresta(f, j, 9);

        lista.addAresta(g, h, 2);
        lista.addAresta(g, j, 7);
        lista.addAresta(g, k, 14);

        lista.addAresta(h, i, 5);
        lista.addAresta(h, k, 4);
        lista.addAresta(h, l, 10);

        lista.addAresta(i, j, 2);
        lista.addAresta(i, l, 7);
        lista.addAresta(i, m, 11);

        lista.addAresta(j, k, 5);
        lista.addAresta(j, m, 3);
        lista.addAresta(j, n, 12);

        lista.addAresta(k, l, 1);
        lista.addAresta(k, n, 6);
        lista.addAresta(k, o, 9);

        lista.addAresta(l, m, 4);
        lista.addAresta(l, o, 7);

        lista.addAresta(m, n, 2);
        lista.addAresta(n, o, 3);

        System.out.println("GRAFO ORIGINAL:");
        lista.mostrarLista();

        Lista agm = Agm.calcularAgm(lista);

        System.out.println("\nAGM:");
        agm.mostrarLista();

        System.out.println("\nMATRIZ DE ADJACÊNCIA DA AGM:");
        Matrizes.matrizAdjacencia(agm);

        System.out.println("\nMATRIZ DE INCIDÊNCIA DA AGM:");
        Matrizes.matrizIncidencia(agm);

        ImagemGrafo.salvar(lista, "grafo.png");
        ImagemGrafo.salvar(agm, "Agm.png");
    }
}