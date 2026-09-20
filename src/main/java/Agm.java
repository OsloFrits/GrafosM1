import java.util.ArrayList;
import java.util.List;

public class Agm {

    public static Lista calcularAgm(Lista lista) {

        Lista agm = new Lista(new ArrayList<>(), false);

        List<Node> listaDeAdjacencia = lista.getListaDeAdjacencia();
        List<Node> verticesVisitados = new ArrayList<>();

        Linha menorLinha = null;
        Node proximoVertice = null;
        Node origem = null;
        Node destino = null;
        int pesoMenor = Integer.MAX_VALUE;

        Node verticeInicial = listaDeAdjacencia.get(0);

        verticesVisitados.add(verticeInicial);

        for (Node vertice : listaDeAdjacencia) {
            agm.addVertice(
                    new Node(
                            vertice.getId(),
                            vertice.getNome()
                    )
            );
        }

        while (verticesVisitados.size() < listaDeAdjacencia.size()) {
            pesoMenor = Integer.MAX_VALUE;
            menorLinha = null;
            proximoVertice = null;

            for (Node vertice : verticesVisitados) {
                for (Linha linha : vertice.getAdjacencia()) {

                    Node outroVertice;

                    if (linha.getOrigem() == vertice) {
                        outroVertice = linha.getDestino();
                    } else {
                        outroVertice = linha.getOrigem();
                    }

                    if (verticesVisitados.contains(outroVertice)) {
                        continue;
                    }

                    if (linha.getPeso() < pesoMenor) {
                        pesoMenor = linha.getPeso();
                        menorLinha = linha;
                        proximoVertice = outroVertice;
                    }
                }
            }

            if (menorLinha == null) {
                break;
            }

            for (Node vertice : agm.getListaDeAdjacencia()) {

                if (vertice.getId() == menorLinha.getOrigem().getId()) {
                    origem = vertice;
                }
            }

            for (Node vertice : agm.getListaDeAdjacencia()) {

                if (vertice.getId() == menorLinha.getDestino().getId()) {
                    destino = vertice;
                }
            }

            agm.addAresta(
                    origem,
                    destino,
                    menorLinha.getPeso()
            );

            verticesVisitados.add(proximoVertice);
        }

        return agm;
    }

    private static Node buscarVertice(Lista lista, int id) {

        for (Node vertice : lista.getListaDeAdjacencia()) {

            if (vertice.getId() == id) {
                return vertice;
            }
        }

        return null;
    }
}