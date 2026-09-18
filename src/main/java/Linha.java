public class Linha {
    Node origem, destino;
    int peso;
    boolean direcionada;
    public Linha(Node origem, Node destino, int peso, boolean direcionada) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
        this.direcionada = direcionada;
    }

    public void setOrigem(Node origem) {
        this.origem = origem;
    }

    public void setDestino(Node destino) {
        this.destino = destino;
    }

    public void setDirecionada(boolean direcionada) {
        this.direcionada = direcionada;
    }

    public Node getOrigem() {
        return origem;
    }
    public Node getDestino() {
        return destino;
    }
    public int getPeso() {
        return peso;
    }
    public void setPeso(int peso) {
    }
    public boolean isDirecionada() {
        return direcionada;
    }
}
