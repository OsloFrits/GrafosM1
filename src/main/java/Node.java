import java.util.List;

public class Node {
    int id;
    String nome;
    List<Node> adjacencia;

    public Node(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public void addVizinho(Node vizinho) {
        this.adjacencia.add(vizinho);
    }
    public void removeVizinho(Node vizinho) {
        this.adjacencia.remove(vizinho);
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
