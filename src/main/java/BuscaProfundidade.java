public class BuscaProfundidade {

    /*
     * Inicia a busca em profundidade.
     */
    public static void executar(Lista grafo, int inicio) {

        int quantidade = grafo.getListaDeAdjacencia().size();

        // Marca quais vertices ja foram visitados
        boolean[] visitados = new boolean[quantidade];

        System.out.println("BUSCA EM PROFUNDIDADE:");

        // Comeca a busca
        busca(grafo, inicio, visitados);

        System.out.println();
    }


    /*
     * Faz a busca em profundidade de forma recursiva.
     */
    private static void busca(
            Lista grafo,
            int atual,
            boolean[] visitados
    ) {

        // Marca o vertice atual como visitado
        visitados[atual] = true;

        // Pega o vertice atual
        Node verticeAtual =
                grafo.getListaDeAdjacencia().get(atual);

        // Mostra o vertice visitado
        System.out.print(
                verticeAtual.getNome() + " "
        );


        /*
         * Verifica todos os vertices do grafo
         * para descobrir os vizinhos do atual.
         */
        for (int i = 0;
             i < grafo.getListaDeAdjacencia().size();
             i++) {

            // So precisamos olhar quem ainda nao foi visitado
            if (!visitados[i]) {

                Node destino =
                        grafo.getListaDeAdjacencia().get(i);


                /*
                 * Verifica se existe uma aresta
                 * entre o vertice atual e o destino.
                 */
                if (existeAresta(
                        grafo,
                        verticeAtual,
                        destino)) {

                    /*
                     * Entra mais fundo no grafo.
                     */
                    busca(
                            grafo,
                            i,
                            visitados
                    );
                }
            }
        }
    }


    /*
     * Verifica se existe uma aresta entre
     * origem e destino.
     */
    private static boolean existeAresta(
            Lista grafo,
            Node origem,
            Node destino
    ) {

        /*
         * Percorre as arestas do vertice.
         */
        for (Linha linha : origem.getAdjacencia()) {


            // GRAFO DIRECIONADO
            if (grafo.isDirecionada()) {

                /*
                 * Precisamos respeitar:
                 *
                 * origem -> destino
                 */
                if (linha.getOrigem() == origem
                        && linha.getDestino() == destino) {

                    return true;
                }
            }


            // GRAFO NAO DIRECIONADO
            else {

                /*
                 * Em grafo nao direcionado:
                 *
                 * A -- B
                 *
                 * pode ser percorrido
                 * nos dois sentidos.
                 */
                if ((linha.getOrigem() == origem
                        && linha.getDestino() == destino)

                        ||

                        (linha.getOrigem() == destino
                        && linha.getDestino() == origem)) {

                    return true;
                }
            }
        }

        return false;
    }
}