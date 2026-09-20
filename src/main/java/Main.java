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

        // =====================================================
        // TESTE DO ALGORITMO DE ROY
        // =====================================================

        System.out.println("\n==============================");
        System.out.println("TESTE DO ALGORITMO DE ROY");
        System.out.println("==============================");

        Roy.executar(lista);


        // =====================================================
        // TESTE ROY - GRAFO DIRECIONADO
        // =====================================================

        System.out.println("\n==============================");
        System.out.println("TESTE ROY - GRAFO DIRECIONADO");
        System.out.println("==============================");

        // true = grafo direcionado
        Lista grafoRoy = new Lista(
            new ArrayList<>(),
                true
        );


        // -----------------------------------------------------
        // Criação dos vértices
        // -----------------------------------------------------

        Node rA = new Node(101, "A");
        Node rB = new Node(102, "B");
        Node rC = new Node(103, "C");
        Node rD = new Node(104, "D");
        Node rE = new Node(105, "E");
        Node rF = new Node(106, "F");


        // -----------------------------------------------------
        // Adiciona os vértices
        // -----------------------------------------------------

        grafoRoy.addVertice(rA);
        grafoRoy.addVertice(rB);
        grafoRoy.addVertice(rC);
        grafoRoy.addVertice(rD);
        grafoRoy.addVertice(rE);
        grafoRoy.addVertice(rF);


        // =====================================================
        // COMPONENTE 1
        //
        // A -> B -> C
        // ^         |
        // |_________|
        //
        // A, B e C conseguem chegar uns aos outros.
        // =====================================================

        grafoRoy.addAresta(rA, rB, 1);

        grafoRoy.addAresta(rB, rC, 1);

        grafoRoy.addAresta(rC, rA, 1);


        // =====================================================
        // Ligação da componente 1 para a componente 2
        //
        // C -> D
        //
        // IMPORTANTE:
        // não existe caminho de D de volta para C.
        // =====================================================

        grafoRoy.addAresta(rC, rD, 1);


        // =====================================================
        // COMPONENTE 2
        //
        // D <-> E
        //
        // D consegue chegar em E
        // E consegue chegar em D
        // =====================================================

        grafoRoy.addAresta(rD, rE, 1);

        grafoRoy.addAresta(rE, rD, 1);


        // =====================================================
        // Ligação até F
        //
        // E -> F
        //
        // F não consegue voltar.
        // Portanto F ficará sozinho.
        // =====================================================

        grafoRoy.addAresta(rE, rF, 1);


        // -----------------------------------------------------
        // Mostra o grafo criado
        // -----------------------------------------------------

        System.out.println("\nGrafo usado para testar Roy:");

        grafoRoy.mostrarLista();


        // -----------------------------------------------------
        // Executa o algoritmo
        // -----------------------------------------------------

        System.out.println("\nComponentes encontradas:");

        Roy.executar(grafoRoy);
    }
}