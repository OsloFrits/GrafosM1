public class Matrizes {
    private int[][] matrizIn, matrizAd;

    public MatrizAdjacencia(Lista listaDeAdjacencia) {
        this.matrizAd = new int[listaDeAdjacencia.getVertices()][listaDeAdjacencia.getVertices()];
    }
    public MatrizIncidencia(Lista listaDeAdjacencia) {
        this.matrizIn = new int[listaDeAdjacencia.size()][listaDeAdjacencia.size()];
    }
}
