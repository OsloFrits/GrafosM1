
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class JanelaGrafo {

    public static void exibir(Lista lista) {

        SwingUtilities.invokeLater(() -> {

            JFrame janela = new JFrame("Visualização do Grafo");

            janela.setDefaultCloseOperation(
                    JFrame.DISPOSE_ON_CLOSE
            );

            janela.setSize(800, 600);

            janela.setLocationRelativeTo(null);

            PainelGrafo painel = new PainelGrafo(lista);

            janela.add(painel);

            janela.setVisible(true);
        });
    }
}