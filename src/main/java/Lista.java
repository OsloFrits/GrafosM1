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
    }
    public void addAresta(Node origem, Node destino, int peso, boolean direcionada) {//Nao sei se isso precisa estar dentro de lista ou fora??
        Linha adjacencia = new Linha(origem, destino, peso, direcionada);
    }
    public void removeAresta(Node origem, Node destino) {//caomo caralhos faço isso???

    }
    public void removeVertice(Node vertice) {
        this.ListaDeAdjacencia.remove(vertice);
    }
}
