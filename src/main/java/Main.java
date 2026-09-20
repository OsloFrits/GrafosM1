
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Lista lista = new Lista(
                new ArrayList<>(),
                true
        );

        Node a = new Node(1, "A");
        Node b = new Node(2, "B");
        Node c = new Node(3, "C");
        Node d = new Node(4, "D");

        lista.addVertice(a);
        lista.addVertice(b);
        lista.addVertice(c);
        lista.addVertice(d);

        lista.addAresta(a, b, 2);
        lista.addAresta(a, c, 5);
        lista.addAresta(b, d, 1);
        lista.addAresta(c, d, 3);

        lista = Agm.calcularAgm(lista);

        JanelaGrafo.exibir(lista);
    }
}