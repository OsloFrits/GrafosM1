import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PainelGrafo extends JPanel {

    private Lista lista;

    private Map<Node, Point> posicoes =
            new HashMap<>();

    private List<List<Node>> componentes;

    private final int RAIO_VERTICE = 25;

    public PainelGrafo(Lista lista) {

        this.lista = lista;

        setBackground(Color.GRAY);

        calcularPosicoes();
    }

    public PainelGrafo(
            Lista lista,
            List<List<Node>> componentes
    ) {

        this.lista = lista;
        this.componentes = componentes;

        setBackground(Color.GRAY);

        calcularPosicoes();
    }

    private void calcularPosicoes() {

        List<Node> vertices =
                lista.getListaDeAdjacencia();

        int largura = 800;
        int altura = 550;

        int centroX = largura / 2;
        int centroY = altura / 2;

        int raio =
                Math.min(largura, altura) / 3;

        int quantidade = vertices.size();

        if (quantidade == 0) {
            return;
        }

        for (int i = 0; i < quantidade; i++) {

            Node vertice = vertices.get(i);

            double angulo =
                    2 * Math.PI * i / quantidade;

            int x = (int) (
                    centroX
                            + raio * Math.cos(angulo)
            );

            int y = (int) (
                    centroY
                            + raio * Math.sin(angulo)
            );

            posicoes.put(
                    vertice,
                    new Point(x, y)
            );
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 =
                (Graphics2D) g.create();

        try {

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            desenharArestas(g2);
            desenharVertices(g2);

        } finally {

            g2.dispose();
        }
    }

    private void desenharArestas(
            Graphics2D g2
    ) {

        Set<Linha> arestasDesenhadas =
                new HashSet<>();

        for (Node vertice :
                lista.getListaDeAdjacencia()) {

            Point origem =
                    posicoes.get(vertice);

            if (origem == null) {
                continue;
            }

            for (Linha linha :
                    vertice.getAdjacencia()) {

                if (!lista.isDirecionada()
                        && arestasDesenhadas
                        .contains(linha)) {

                    continue;
                }

                Node destino;

                if (lista.isDirecionada()) {

                    destino =
                            linha.getDestino();

                } else {

                    destino =
                            linha.getOutraPonta(
                                    vertice
                            );
                }

                Point pontoDestino =
                        posicoes.get(destino);

                if (pontoDestino == null) {
                    continue;
                }

                if (vertice == destino) {

                    desenharLoop(
                            g2,
                            origem,
                            linha.getPeso()
                    );

                } else {

                    desenharAresta(
                            g2,
                            origem,
                            pontoDestino,
                            linha.getPeso(),
                            lista.isDirecionada()
                    );
                }

                arestasDesenhadas.add(linha);
            }
        }
    }

    private void desenharAresta(
            Graphics2D g2,
            Point origem,
            Point destino,
            int peso,
            boolean direcionada
    ) {

        double dx =
                destino.x - origem.x;

        double dy =
                destino.y - origem.y;

        double distancia =
                Math.sqrt(
                        dx * dx + dy * dy
                );

        if (distancia == 0) {
            return;
        }

        double ux =
                dx / distancia;

        double uy =
                dy / distancia;

        int inicioX =
                (int) (
                        origem.x
                                + ux * RAIO_VERTICE
                );

        int inicioY =
                (int) (
                        origem.y
                                + uy * RAIO_VERTICE
                );

        int fimX =
                (int) (
                        destino.x
                                - ux * RAIO_VERTICE
                );

        int fimY =
                (int) (
                        destino.y
                                - uy * RAIO_VERTICE
                );

        g2.setColor(Color.DARK_GRAY);

        g2.setStroke(
                new BasicStroke(4)
        );

        g2.drawLine(
                inicioX,
                inicioY,
                fimX,
                fimY
        );

        if (direcionada) {

            desenharSeta(
                    g2,
                    inicioX,
                    inicioY,
                    fimX,
                    fimY
            );
        }

        double deslocamento = 10;

        double deslocamentoX =
                -uy * deslocamento;

        double deslocamentoY =
                ux * deslocamento;

        int meioX =
                (inicioX + fimX) / 2;

        int meioY =
                (inicioY + fimY) / 2;

        g2.setColor(Color.RED);

        g2.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        g2.drawString(
                String.valueOf(peso),
                (int) (
                        meioX + deslocamentoX
                ),
                (int) (
                        meioY + deslocamentoY
                )
        );
    }

    private void desenharSeta(
            Graphics2D g2,
            int inicioX,
            int inicioY,
            int fimX,
            int fimY
    ) {

        double angulo =
                Math.atan2(
                        fimY - inicioY,
                        fimX - inicioX
                );

        int tamanho = 12;

        double angulo1 =
                angulo + Math.PI * 0.8;

        double angulo2 =
                angulo - Math.PI * 0.8;

        int x1 =
                (int) (
                        fimX
                                + tamanho * Math.cos(angulo1)
                );

        int y1 =
                (int) (
                        fimY
                                + tamanho * Math.sin(angulo1)
                );

        int x2 =
                (int) (
                        fimX
                                + tamanho * Math.cos(angulo2)
                );

        int y2 =
                (int) (
                        fimY
                                + tamanho * Math.sin(angulo2)
                );

        g2.drawLine(
                fimX,
                fimY,
                x1,
                y1
        );

        g2.drawLine(
                fimX,
                fimY,
                x2,
                y2
        );
    }

    private void desenharLoop(
            Graphics2D g2,
            Point centro,
            int peso
    ) {

        int tamanho = 35;

        g2.setColor(Color.DARK_GRAY);

        g2.setStroke(
                new BasicStroke(2)
        );

        g2.drawOval(
                centro.x - tamanho / 2,
                centro.y - RAIO_VERTICE - tamanho,
                tamanho,
                tamanho
        );

        g2.setColor(Color.BLUE);

        g2.drawString(
                String.valueOf(peso),
                centro.x + 15,
                centro.y - RAIO_VERTICE - tamanho
        );
    }

    private void desenharVertices(
            Graphics2D g2
    ) {

        for (Node vertice :
                lista.getListaDeAdjacencia()) {

            Point ponto =
                    posicoes.get(vertice);

            if (ponto == null) {
                continue;
            }

            int x =
                    ponto.x - RAIO_VERTICE;

            int y =
                    ponto.y - RAIO_VERTICE;

            g2.setColor(
                    obterCorVertice(vertice)
            );

            g2.fillOval(
                    x,
                    y,
                    RAIO_VERTICE * 2,
                    RAIO_VERTICE * 2
            );

            g2.setColor(Color.BLACK);

            g2.setStroke(
                    new BasicStroke(2)
            );

            g2.drawOval(
                    x,
                    y,
                    RAIO_VERTICE * 2,
                    RAIO_VERTICE * 2
            );

            String texto =
                    String.valueOf(
                            vertice.getId()
                    );

            g2.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            14
                    )
            );

            int larguraTexto =
                    g2.getFontMetrics()
                            .stringWidth(texto);

            g2.drawString(
                    texto,
                    ponto.x
                            - larguraTexto / 2,
                    ponto.y + 5
            );

            if (componentes != null) {

                int numero =
                        numeroComponente(vertice);

                if (numero != -1) {

                    g2.setFont(
                            new Font(
                                    "Arial",
                                    Font.BOLD,
                                    12
                            )
                    );

                    g2.drawString(
                            "S" + (numero + 1),
                            ponto.x - 10,
                            ponto.y - 32
                    );
                }
            }
        }
    }

    private Color obterCorVertice(
            Node vertice
    ) {

        if (componentes == null) {
            return new Color(
                    220,
                    235,
                    255
            );
        }

        int numero =
                numeroComponente(vertice);

        if (numero == -1) {
            return new Color(
                    220,
                    235,
                    255
            );
        }

        Color[] cores = {

                new Color(
                        255,
                        180,
                        180
                ),

                new Color(
                        180,
                        220,
                        255
                ),

                new Color(
                        180,
                        255,
                        180
                ),

                new Color(
                        255,
                        230,
                        150
                ),

                new Color(
                        220,
                        180,
                        255
                ),

                new Color(
                        180,
                        255,
                        240
                ),

                new Color(
                        255,
                        200,
                        150
                )
        };

        return cores[
                numero % cores.length
                ];
    }

    private int numeroComponente(
            Node vertice
    ) {

        if (componentes == null) {
            return -1;
        }

        for (int i = 0;
             i < componentes.size();
             i++) {

            if (componentes.get(i)
                    .contains(vertice)) {

                return i;
            }
        }

        return -1;
    }
}