import java.util.List;

public class Lista { //Colocar arqui funções de procura, mostrar e codigos q se aplicam no grafo
    List<Node> ListaDeAdjacencia;
    int tamanho=0;
    int pesoTotal;

    public Lista(List<Node> listaDeAdjacencia) {
        ListaDeAdjacencia = listaDeAdjacencia;
    }
    public void addVertice(Node vertice) {
        this.ListaDeAdjacencia.add(vertice);
        this.tamanho++;
    }
    public void addAresta(Node origem, Node destino, int peso, boolean direcionada) {//Nao sei se isso precisa estar dentro de lista ou fora??
        Linha adjacencia = new Linha(origem, destino, peso, direcionada);
        origem.addAdjacencia(adjacencia);
        if(!direcionada) {
            destino.addAdjacencia(adjacencia);
        }
    }
    public void removeAresta(Linha linha) {//caomo caralhos faço isso???
        linha.getOrigem().removeAdjacencia(linha);
        if(!linha.isDirecionada()) {
            linha.getDestino().removeAdjacencia(linha);
        }
    }
    public void removeVertice(Node vertice) {//vou remover o vertice e todas as arestas ligadas a ele, mas COMO??? so deus sabe, e eu n sou deus. Pq eu existekkkkkkkkkkk
        this.ListaDeAdjacencia.remove(vertice);
        for(Linha linha : vertice.getAdjacencia()) {
            if(linha.isDirecionada()){
                vertice.removeAdjacencia(linha);
            }else{
                linha.getDestino().removeAdjacencia(linha);
            }
        }
        this.tamanho--;
    }
    public void mostrarLista() {
        String R;
        for(Node vertice : ListaDeAdjacencia) {
            String V = String.valueOf(vertice.getId());
            for(Linha linha : vertice.getAdjacencia()) {
                String W = String.valueOf(linha.getDestino().getId());
                if(linha.isDirecionada()) {
                    R = "--" + linha.getPeso() +  "->";
                }else{
                    R = "<-" + linha.getPeso() + "->";
                }
                System.out.println(V + " " + R + " " + W);
            }
        }
    }
}
