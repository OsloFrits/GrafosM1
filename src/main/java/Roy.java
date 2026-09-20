public class Roy {

    public static void executar(Lista grafo) {

        int quantidade = grafo.getListaDeAdjacencia().size();

        boolean[] usado = new boolean[quantidade];

        int numeroComponente = 1;

        for (int inicio = 0; inicio < quantidade; inicio++) {

            if (usado[inicio]) {
                continue;
            }

            boolean[] positivos = new boolean[quantidade];

            boolean[] negativos = new boolean[quantidade];

            buscaPositiva(
                    grafo,
                    inicio,
                    positivos,
                    usado
            );

            buscaNegativa(
                    grafo,
                    inicio,
                    negativos,
                    usado
            );
            System.out.print(
                    "S" + numeroComponente + " = { "
            );
            for (int i = 0; i < quantidade; i++) {

                if (positivos[i] && negativos[i]) {
                    Node vertice =
                            grafo.getListaDeAdjacencia().get(i);
                    System.out.print(
                            vertice.getNome() + " "
                    );
                    usado[i] = true;
                }
            }
            System.out.println("}");
            numeroComponente++;
        }
    }

    private static void buscaPositiva(
            Lista grafo,
            int atual,
            boolean[] visitados,
            boolean[] usado
    ) {

        if (visitados[atual] || usado[atual]) {
            return;
        }

        visitados[atual] = true;

        Node verticeAtual =
                grafo.getListaDeAdjacencia().get(atual);

        for (int i = 0;
             i < grafo.getListaDeAdjacencia().size();
             i++) {

            if (usado[i]) {
                continue;
            }

            Node destino =
                    grafo.getListaDeAdjacencia().get(i);

            if (existeAresta(
                    grafo,
                    verticeAtual,
                    destino)) {

                buscaPositiva(
                        grafo,
                        i,
                        visitados,
                        usado
                );
            }
        }
    }

    private static void buscaNegativa(
            Lista grafo,
            int atual,
            boolean[] visitados,
            boolean[] usado
    ) {
        if (visitados[atual] || usado[atual]) {
            return;
        }

        visitados[atual] = true;

        Node verticeAtual =
                grafo.getListaDeAdjacencia().get(atual);

        for (int i = 0;
             i < grafo.getListaDeAdjacencia().size();
             i++) {

            if (usado[i]) {
                continue;
            }

            Node origem =
                    grafo.getListaDeAdjacencia().get(i);

            if (existeAresta(
                    grafo,
                    origem,
                    verticeAtual)) {

                buscaNegativa(
                        grafo,
                        i,
                        visitados,
                        usado
                );
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