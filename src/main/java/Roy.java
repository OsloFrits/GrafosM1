public class Roy {

    /*
     * Executa o algoritmo de Roy.
     *
     * Para grafo direcionado:
     * encontra componentes fortemente conexas.
     *
     * Para grafo não direcionado:
     * encontra componentes conexas.
     */
    public static void executar(Lista grafo) {

        int quantidade = grafo.getListaDeAdjacencia().size();

        /*
         * Marca os vertices que ja pertencem
         * a alguma componente encontrada.
         */
        boolean[] usado = new boolean[quantidade];

        int numeroComponente = 1;


        /*
         * Percorre todos os vertices.
         */
        for (int inicio = 0; inicio < quantidade; inicio++) {

            /*
             * Se o vertice ja pertence a uma
             * componente, nao precisamos verificar novamente.
             */
            if (usado[inicio]) {
                continue;
            }


            /*
             * positivos:
             * vertices que conseguimos alcancar
             * partindo do vertice inicial.
             */
            boolean[] positivos = new boolean[quantidade];


            /*
             * negativos:
             * vertices que conseguem chegar
             * ate o vertice inicial.
             */
            boolean[] negativos = new boolean[quantidade];


            // =========================================
            // MARCACAO POSITIVA
            // =========================================

            buscaPositiva(
                    grafo,
                    inicio,
                    positivos,
                    usado
            );


            // =========================================
            // MARCACAO NEGATIVA
            // =========================================

            buscaNegativa(
                    grafo,
                    inicio,
                    negativos,
                    usado
            );


            // =========================================
            // INTERSECAO
            // =========================================

            System.out.print(
                    "S" + numeroComponente + " = { "
            );


            /*
             * Se um vertice recebeu marcacao positiva
             * E marcacao negativa, ele pertence
             * a componente.
             */
            for (int i = 0; i < quantidade; i++) {

                if (positivos[i] && negativos[i]) {

                    Node vertice =
                            grafo.getListaDeAdjacencia().get(i);

                    System.out.print(
                            vertice.getNome() + " "
                    );


                    /*
                     * Marca o vertice como usado,
                     * pois sua componente ja foi encontrada.
                     */
                    usado[i] = true;
                }
            }


            System.out.println("}");

            numeroComponente++;
        }
    }


    // =====================================================
    // BUSCA POSITIVA
    // =====================================================

    /*
     * Descobre todos os vertices que podem ser
     * alcancados partindo do vertice atual.
     */
    private static void buscaPositiva(
            Lista grafo,
            int atual,
            boolean[] visitados,
            boolean[] usado
    ) {

        /*
         * Se ja visitamos esse vertice
         * ou ele ja pertence a outra componente,
         * paramos.
         */
        if (visitados[atual] || usado[atual]) {
            return;
        }


        /*
         * Marca o vertice atual como visitado.
         */
        visitados[atual] = true;


        Node verticeAtual =
                grafo.getListaDeAdjacencia().get(atual);


        /*
         * Verifica todos os outros vertices
         * do grafo.
         */
        for (int i = 0;
             i < grafo.getListaDeAdjacencia().size();
             i++) {


            /*
             * Se ja pertence a outra componente,
             * ignoramos.
             */
            if (usado[i]) {
                continue;
            }


            Node destino =
                    grafo.getListaDeAdjacencia().get(i);


            /*
             * Verifica se existe uma aresta:
             *
             * verticeAtual -> destino
             */
            if (existeAresta(
                    grafo,
                    verticeAtual,
                    destino)) {


                /*
                 * Continua a busca a partir
                 * do novo vertice.
                 */
                buscaPositiva(
                        grafo,
                        i,
                        visitados,
                        usado
                );
            }
        }
    }


    // =====================================================
    // BUSCA NEGATIVA
    // =====================================================

    /*
     * Descobre todos os vertices que conseguem
     * chegar ate o vertice atual.
     *
     * Em um grafo direcionado, funciona como
     * percorrer as arestas ao contrario.
     */
    private static void buscaNegativa(
            Lista grafo,
            int atual,
            boolean[] visitados,
            boolean[] usado
    ) {


        /*
         * Evita visitar novamente o mesmo vertice.
         */
        if (visitados[atual] || usado[atual]) {
            return;
        }


        /*
         * Marca o vertice atual.
         */
        visitados[atual] = true;


        Node verticeAtual =
                grafo.getListaDeAdjacencia().get(atual);


        /*
         * Procura vertices que possuem caminho
         * chegando no vertice atual.
         */
        for (int i = 0;
             i < grafo.getListaDeAdjacencia().size();
             i++) {


            if (usado[i]) {
                continue;
            }


            Node origem =
                    grafo.getListaDeAdjacencia().get(i);


            /*
             * Agora fazemos a verificacao ao contrario.
             *
             * Procuramos:
             *
             * origem -> verticeAtual
             */
            if (existeAresta(
                    grafo,
                    origem,
                    verticeAtual)) {


                /*
                 * Continua procurando para tras.
                 */
                buscaNegativa(
                        grafo,
                        i,
                        visitados,
                        usado
                );
            }
        }
    }


    // =====================================================
    // VERIFICAR ARESTA
    // =====================================================

    /*
     * Verifica se existe uma aresta entre
     * dois vertices.
     */
    private static boolean existeAresta(
            Lista grafo,
            Node origem,
            Node destino
    ) {


        /*
         * Percorre todas as arestas associadas
         * ao vertice de origem.
         */
        for (Linha linha : origem.getAdjacencia()) {


            // =========================================
            // GRAFO DIRECIONADO
            // =========================================

            if (grafo.isDirecionada()) {


                /*
                 * Em um grafo direcionado:
                 *
                 * A -> B
                 *
                 * nao significa:
                 *
                 * B -> A
                 *
                 * Por isso respeitamos origem e destino.
                 */
                if (linha.getOrigem() == origem
                        && linha.getDestino() == destino) {

                    return true;
                }
            }


            // =========================================
            // GRAFO NAO DIRECIONADO
            // =========================================

            else {


                /*
                 * Em um grafo nao direcionado:
                 *
                 * A -- B
                 *
                 * pode ser percorrido:
                 *
                 * A -> B
                 *
                 * ou
                 *
                 * B -> A
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


        /*
         * Se percorremos todas as arestas
         * e nao encontramos nenhuma,
         * nao existe ligacao.
         */
        return false;
    }
}