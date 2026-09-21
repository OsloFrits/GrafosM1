import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ImagemGrafo {

    public static void salvar(
            Lista lista,
            String caminho
    ) {

        int largura = 800;
        int altura = 550;

        BufferedImage imagem =
                new BufferedImage(
                        largura,
                        altura,
                        BufferedImage.TYPE_INT_RGB
                );

        Graphics2D g2 =
                imagem.createGraphics();

        PainelGrafo painel =
                new PainelGrafo(lista);

        painel.setSize(
                largura,
                altura
        );

        painel.paint(g2);

        g2.dispose();

        salvarImagem(
                imagem,
                caminho
        );
    }

    public static void salvarRoy(
            Lista lista,
            List<List<Node>> componentes,
            String caminho
    ) {

        int largura = 800;
        int altura = 550;

        BufferedImage imagem =
                new BufferedImage(
                        largura,
                        altura,
                        BufferedImage.TYPE_INT_RGB
                );

        Graphics2D g2 =
                imagem.createGraphics();

        PainelGrafo painel =
                new PainelGrafo(
                        lista,
                        componentes
                );

        painel.setSize(
                largura,
                altura
        );

        painel.paint(g2);

        g2.dispose();

        salvarImagem(
                imagem,
                caminho
        );
    }

    private static void salvarImagem(
            BufferedImage imagem,
            String caminho
    ) {

        try {

            ImageIO.write(
                    imagem,
                    "png",
                    new File(caminho)
            );

            System.out.println(
                    "Imagem salva em: " + caminho
            );

        } catch (IOException e) {

            System.out.println(
                    "Erro ao salvar a imagem."
            );

            e.printStackTrace();
        }
    }
}