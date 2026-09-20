import java.util.ArrayList;
import java.util.List;

public class Agm {

    public Lista calcularAgm(Lista lista) {
        Lista agm = new Lista(new ArrayList<>(), false);

        List<Node> listaDeAdjacencia = lista.getListaDeAdjacencia();
        List<Node> verticesVisitados = new ArrayList<>();

        Linha menorLinha = null;
        Node proximoVertice = null;
        int pesoMenor = Integer.MAX_VALUE;

        verticesVisitados.add(listaDeAdjacencia.get(0));

        while(verticesVisitados.size() < listaDeAdjacencia.size()) {
            pesoMenor = Integer.MAX_VALUE;
            menorLinha = null;
            proximoVertice = null;
            for (Node vertice : verticesVisitados) {
                for (Linha linha : vertice.getAdjacencia()) {
                    if (linha.getPeso() < pesoMenor && !verticesVisitados.contains(linha.getDestino())) {
                        pesoMenor = linha.getPeso();
                        menorLinha = linha;
                        proximoVertice = linha.getDestino();
                    }
                }
            }
            agm.addVertice(proximoVertice);
            if (menorLinha == null) {
                break;
            }else {
                agm.addAresta(menorLinha.getOrigem(), menorLinha.getDestino(), menorLinha.getPeso());
            }
            verticesVisitados.add(menorLinha.getDestino());
        }
        return agm;
    }
}
