import java.util.List;

public class Node {
    int id;
    String nome;
    List<Linha> adjacencia;

    public Node(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public void addAdjacencia(Linha Adjacencia) {
        this.adjacencia.add(Adjacencia);
    }
    public void removeAdjacencia(Linha Adjacencia) {
        this.adjacencia.remove(Adjacencia);
    }

    public List<Linha> getAdjacencia() {
        return adjacencia;
    }

    public void setAdjacencia(List<Linha> adjacencia) {
        this.adjacencia = adjacencia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
