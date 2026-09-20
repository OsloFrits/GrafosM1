import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagemGrafo {

    public static void salvar(Lista lista, String caminho) {

        int largura = 800;
        int altura = 550;

        BufferedImage imagem = new BufferedImage(
                largura,
                altura,
                BufferedImage.TYPE_INT_RGB
        );

        Graphics2D g2 = imagem.createGraphics();

        PainelGrafo painel = new PainelGrafo(lista);

        painel.setSize(largura, altura);

        painel.paint(g2);

        g2.dispose();

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