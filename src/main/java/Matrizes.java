import java.util.List;

public class Matrizes {

    public static int[][] MatrizAdjacencia(Lista lista) {
        int i, j;
        int [][] matriz = new int[lista.getVertices()][lista.getVertices()];//O +1 serve para ........ me esqueci
        List<Node> listaDeAdjacencia = lista.getListaDeAdjacencia();

        for(Node vertice : listaDeAdjacencia){
            List<Linha> ligacao = vertice.getAdjacencia();
            i = vertice.getId();
            for(Linha linha : ligacao){
                 Node destino = linha.getDestino();
                 j = destino.getId();
                 matriz[i][j] = 1;
                 if(!lista.isDirecionada()){
                    matriz[j][i] = 1;
                 }
            }
        }
        return matriz;
    }
    public static int[][] MatrizIncidencia(Lista listaDeAdjacencia) {
        int[][] matriz = new int[listaDeAdjacencia.getVertices()+1][listaDeAdjacencia.getLinhas()+1];

        //aplicar codigo

        return matriz;
    }

}
