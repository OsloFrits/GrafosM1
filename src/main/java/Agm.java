git import java.util.ArrayList;
import java.util.List;

public class Agm {
    private List<Node> verticesVisitados = new ArrayList<>();
    private Lista agm;
    private Linha linhas;
    private Linha menorLinha = null;
    private Node primeiroVertice = null;
    int pesoMenor = Integer.MAX_VALUE;

    public void calcularAgm(Lista lista) {
        List<Node> listaDeAdjacencia = lista.getListaDeAdjacencia();
        for (Node vertice : listaDeAdjacencia) {
            for (Linha linha : vertice.getAdjacencia()) {
                if (linha.getPeso() < pesoMenor) {
                    if(lista.isDirecionada()) {
                        pesoMenor = linha.getPeso();
                        menorLinha = linha;
                        primeiroVertice = linha.getOrigem();
                    }else{
                        pesoMenor = linha.getPeso();
                        menorLinha = linha;
                        primeiroVertice = linha.getDestino();
                    }
                }
            }
        }
        if(lista.isDirecionada()) {
            verticesVisitados.add(primeiroVertice);
        }else{
            verticesVisitados.add(menorLinha.getDestino());
        }
        for(Linha linha : primeiroVertice.getAdjacencia()){
            if(!verticesVisitados.contains(linha.getDestino())) {
                if (linha.getPeso() <= pesoMenor) {
                    pesoMenor = linha.getPeso();
                    menorLinha = linha;
                    primeiroVertice = linha.getDestino();
                    verticesVisitados.add(primeiroVertice);
                }
            }
        }
        for(Node vertice : listaDeAdjacencia){
            verticesVisitados.add(vertice);
            for(Linha linha : vertice.getAdjacencia()){
                if(linha.getPeso() <= pesoMenor) {
                    agm.addAresta(vertice, linha.getDestino(), linha.getPeso());
                    verticesVisitados.add(linha.getDestino());
                }
            }
        }
    }
}
