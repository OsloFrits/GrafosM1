public class BuscaProfundidade {

    public static void executar(Lista grafo, int inicio) {

        int quantidade = grafo.getListaDeAdjacencia().size();
        boolean[] visitados = new boolean[quantidade];
        System.out.println("BUSCA EM PROFUNDIDADE:");

        busca(grafo, inicio, visitados);

        System.out.println();
    }

    private static void busca(
            Lista grafo,
            int atual,
            boolean[] visitados
    ) {

        visitados[atual] = true;
        Node verticeAtual =
                grafo.getListaDeAdjacencia().get(atual);

        System.out.print(
                verticeAtual.getNome() + " "
        );

        for (int i = 0;
             i < grafo.getListaDeAdjacencia().size();
             i++) {

            if (!visitados[i]) {
                Node destino =
                        grafo.getListaDeAdjacencia().get(i);

                if (existeAresta(
                        grafo,
                        verticeAtual,
                        destino)) {
                    busca(
                            grafo,
                            i,
                            visitados
                    );
                }
            }
        }
    }

    private static boolean existeAresta(
            Lista grafo,
            Node origem,
            Node destino
    ) {

        for (Linha linha : origem.getAdjacencia()) {

            if (grafo.isDirecionada()) {

                if (linha.getOrigem() == origem
                        && linha.getDestino() == destino) {

                    return true;
                }
            }

            else {
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