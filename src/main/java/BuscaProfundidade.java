public class BuscaProfundidade {

    public static class ResultadoBusca {
        public Linha[] arvore;
        public int quantidadeArvore;
        public boolean encontrado;
        public Node[] caminho;
        public int tamanhoCaminho;
    }

    public static ResultadoBusca executar(Lista grafo, Node origem, Node destino) {
        ResultadoBusca resultado = new ResultadoBusca();

        int n = grafo.getVertices();
        Node[] vertices = new Node[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = grafo.getListaDeAdjacencia().get(i);
        }

        boolean[] visitado = new boolean[n];
        Node[] predecessor = new Node[n]; // predecessor[i] = quem descobriu o vertice i

        resultado.arvore = new Linha[n > 0 ? n - 1 : 0];
        resultado.quantidadeArvore = 0;

        resultado.encontrado = buscar(origem, destino, vertices, visitado, predecessor, resultado);

        if (resultado.encontrado) {
            int tamanho = 0;
            Node atual = destino;
            while (atual != null) {
                tamanho++;
                atual = predecessor[indiceDoNode(vertices, atual)];
            }

            resultado.caminho = new Node[tamanho];
            resultado.tamanhoCaminho = tamanho;

            atual = destino;
            for (int pos = tamanho - 1; pos >= 0; pos--) {
                resultado.caminho[pos] = atual;
                int i = indiceDoNode(vertices, atual);
                atual = (i >= 0) ? predecessor[i] : null;
            }
        } else {
            resultado.caminho = new Node[0];
            resultado.tamanhoCaminho = 0;
        }

        return resultado;
    }

    private static boolean buscar(Node atual, Node destino, Node[] vertices,
                                   boolean[] visitado, Node[] predecessor, ResultadoBusca resultado) {
        int i = indiceDoNode(vertices, atual);
        visitado[i] = true;

        if (atual == destino) {
            return true;
        }

        for (int k = 0; k < atual.getAdjacencia().size(); k++) {
            Linha l = atual.getAdjacencia().get(k);
            Node vizinho = l.getOutraPonta(atual);
            int j = indiceDoNode(vertices, vizinho);

            if (!visitado[j]) {
                predecessor[j] = atual;
                resultado.arvore[resultado.quantidadeArvore] = l;
                resultado.quantidadeArvore++;

                if (buscar(vizinho, destino, vertices, visitado, predecessor, resultado)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static int indiceDoNode(Node[] vertices, Node alvo) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] == alvo) {
                return i;
            }
        }
        return -1;
    }
}
