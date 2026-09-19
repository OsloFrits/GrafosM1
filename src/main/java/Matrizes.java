import java.util.List;

public class Matrizes {

    public static int[][] matrizAdjacencia(Lista lista) {
        int [][] matriz = new int[lista.getVertices()][lista.getVertices()];//O +1 serve para ........ me esqueci
        List<Node> listaDeAdjacencia = lista.getListaDeAdjacencia();

        for(Node vertice : listaDeAdjacencia){
            List<Linha> ligacao = vertice.getAdjacencia();
            int i = listaDeAdjacencia.indexOf(vertice);
            for(Linha linha : ligacao){
                 Node destino = linha.getDestino();
                 int j = listaDeAdjacencia.indexOf(destino);
                 matriz[i][j] = 1;
                 if(!lista.isDirecionada()){
                    matriz[j][i] = 1;
                 }
            }
        }
        return matriz;
    }
    public static int[][] matrizIncidencia(Lista lista) {
        int[][] matriz = new int[lista.getVertices()][lista.getLinhas()];
        List<Node> listaDeAdjacencia = lista.getListaDeAdjacencia();

        for(Node vertice : listaDeAdjacencia){
            List<Linha> ligacao = vertice.getAdjacencia();
            int i = listaDeAdjacencia.indexOf(vertice);
            for(Linha linha : ligacao){
                int j = ligacao.indexOf(linha);
                matriz[i][j] = 1;
                i = listaDeAdjacencia.indexOf(linha.getDestino());
                if (lista.isDirecionada()) {
                    matriz[i][j] = -1;
                } else {
                    matriz[i][j] = 1;
                }
            }
        }
        return matriz;
    }

    public static void mostraMatriz(){

    }

}
