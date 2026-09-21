import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("       GERENCIADOR DE GRAFO");
        System.out.println("=================================");

        boolean direcionada = lerBoolean(
                "O grafo sera direcionado? (s/n): "
        );

        Lista lista = new Lista(new ArrayList<>(), direcionada);

        int opcao;

        do {
            mostrarMenu(lista);

            opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {

                case 1:
                    adicionarVertice(lista);
                    break;

                case 2:
                    removerVertice(lista);
                    break;

                case 3:
                    editarVertice(lista);
                    break;

                case 4:
                    adicionarAresta(lista);
                    break;

                case 5:
                    removerAresta(lista);
                    break;

                case 6:
                    editarAresta(lista);
                    break;

                case 7:
                    mostrarGrafo(lista);
                    break;

                case 8:
                    mostrarMatrizes(lista);
                    break;

                case 9:
                    executarAGM(lista);
                    break;

                case 10:
                    executarDFS(lista);
                    break;

                case 11:
                    executarRoy(lista);
                    break;

                case 0:
                    System.out.println("\nPrograma encerrado.");
                    break;

                default:
                    System.out.println("\nOpcao invalida.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    // =========================================================
    // MENU
    // =========================================================

    public static void mostrarMenu(Lista lista) {

        System.out.println("\n=================================");
        System.out.println("             MENU");
        System.out.println("=================================");
        System.out.println("Vertices: " + lista.getVertices());
        System.out.println("Arestas:  " + lista.getLinhas());
        System.out.println("---------------------------------");

        System.out.println("1  - Adicionar vertice");
        System.out.println("2  - Remover vertice");
        System.out.println("3  - Editar vertice");

        System.out.println("4  - Adicionar aresta");
        System.out.println("5  - Remover aresta");
        System.out.println("6  - Editar aresta");

        System.out.println("---------------------------------");

        System.out.println("7  - Mostrar grafo");
        System.out.println("8  - Mostrar matrizes");

        System.out.println("9  - Executar AGM");
        System.out.println("10 - Executar DFS");
        System.out.println("11 - Executar Roy");

        System.out.println("---------------------------------");
        System.out.println("0  - Sair");
        System.out.println("=================================");
    }

    // =========================================================
    // VERTICES
    // =========================================================

    public static void adicionarVertice(Lista lista) {

        System.out.println("\n--- ADICIONAR VERTICE ---");

        int id = lerInteiro("ID do vertice: ");

        if (buscarVerticePorId(lista, id) != null) {
            System.out.println("Ja existe um vertice com esse ID.");
            return;
        }

        String nome = lerTexto("Nome do vertice: ");

        Node vertice = new Node(id, nome);

        lista.addVertice(vertice);

        System.out.println("Vertice adicionado com sucesso.");
    }

    public static void removerVertice(Lista lista) {

        System.out.println("\n--- REMOVER VERTICE ---");

        if (lista.getListaDeAdjacencia().isEmpty()) {
            System.out.println("Nao existem vertices no grafo.");
            return;
        }

        int id = lerInteiro("ID do vertice que deseja remover: ");

        Node vertice = buscarVerticePorId(lista, id);

        if (vertice == null) {
            System.out.println("Vertice nao encontrado.");
            return;
        }

        removerVerticeSeguro(lista, vertice);

        System.out.println("Vertice removido com sucesso.");
    }

    public static void editarVertice(Lista lista) {

        System.out.println("\n--- EDITAR VERTICE ---");

        if (lista.getListaDeAdjacencia().isEmpty()) {
            System.out.println("Nao existem vertices no grafo.");
            return;
        }

        int id = lerInteiro("ID do vertice que deseja editar: ");

        Node vertice = buscarVerticePorId(lista, id);

        if (vertice == null) {
            System.out.println("Vertice nao encontrado.");
            return;
        }

        System.out.println("Nome atual: " + vertice.getNome());

        String novoNome = lerTexto("Novo nome: ");

        vertice.setNome(novoNome);

        System.out.println("Vertice alterado com sucesso.");
    }

    // =========================================================
    // ARESTAS
    // =========================================================

    public static void adicionarAresta(Lista lista) {

        System.out.println("\n--- ADICIONAR ARESTA ---");

        if (lista.getVertices() < 2) {
            System.out.println("E necessario ter pelo menos 2 vertices.");
            return;
        }

        int idOrigem = lerInteiro("ID da origem: ");
        Node origem = buscarVerticePorId(lista, idOrigem);

        if (origem == null) {
            System.out.println("Vertice de origem nao encontrado.");
            return;
        }

        int idDestino = lerInteiro("ID do destino: ");
        Node destino = buscarVerticePorId(lista, idDestino);

        if (destino == null) {
            System.out.println("Vertice de destino nao encontrado.");
            return;
        }

        int peso = lerInteiro("Peso da aresta: ");

        lista.addAresta(origem, destino, peso);

        System.out.println("Aresta adicionada com sucesso.");
    }

    public static void removerAresta(Lista lista) {

        System.out.println("\n--- REMOVER ARESTA ---");

        if (lista.getLinhas() == 0) {
            System.out.println("Nao existem arestas no grafo.");
            return;
        }

        int idOrigem = lerInteiro("ID da origem: ");
        int idDestino = lerInteiro("ID do destino: ");

        Node origem = buscarVerticePorId(lista, idOrigem);
        Node destino = buscarVerticePorId(lista, idDestino);

        if (origem == null || destino == null) {
            System.out.println("Um dos vertices nao foi encontrado.");
            return;
        }

        Linha linha = buscarAresta(lista, origem, destino);

        if (linha == null) {
            System.out.println("Aresta nao encontrada.");
            return;
        }

        lista.removeAresta(linha);

        System.out.println("Aresta removida com sucesso.");
    }

    public static void editarAresta(Lista lista) {

        System.out.println("\n--- EDITAR ARESTA ---");

        if (lista.getLinhas() == 0) {
            System.out.println("Nao existem arestas no grafo.");
            return;
        }

        int idOrigem = lerInteiro("ID da origem: ");
        int idDestino = lerInteiro("ID do destino: ");

        Node origem = buscarVerticePorId(lista, idOrigem);
        Node destino = buscarVerticePorId(lista, idDestino);

        if (origem == null || destino == null) {
            System.out.println("Um dos vertices nao foi encontrado.");
            return;
        }

        Linha linha = buscarAresta(lista, origem, destino);

        if (linha == null) {
            System.out.println("Aresta nao encontrada.");
            return;
        }

        System.out.println("Peso atual: " + linha.getPeso());

        int novoPeso = lerInteiro("Novo peso: ");

        linha.setPeso(novoPeso);

        System.out.println("Aresta alterada com sucesso.");
    }

    // =========================================================
    // BUSCAS
    // =========================================================

    public static Node buscarVerticePorId(Lista lista, int id) {

        for (Node vertice : lista.getListaDeAdjacencia()) {

            if (vertice.getId() == id) {
                return vertice;
            }
        }

        return null;
    }

    public static Linha buscarAresta(
            Lista lista,
            Node origem,
            Node destino) {

        for (Node vertice : lista.getListaDeAdjacencia()) {

            for (Linha linha : vertice.getAdjacencia()) {

                if (lista.isDirecionada()) {

                    if (linha.getOrigem() == origem
                            && linha.getDestino() == destino) {

                        return linha;
                    }

                } else {

                    if ((linha.getOrigem() == origem
                            && linha.getDestino() == destino)
                            ||
                            (linha.getOrigem() == destino
                                    && linha.getDestino() == origem)) {

                        return linha;
                    }
                }
            }
        }

        return null;
    }

    // =========================================================
    // REMOVER VERTICE
    // =========================================================

    public static void removerVerticeSeguro(
            Lista lista,
            Node vertice) {

        List<Linha> linhasParaRemover = new ArrayList<>();

        /*
         * Procuramos todas as arestas que possuem o vertice.
         * Isso tambem encontra arestas de entrada em grafos direcionados.
         */

        for (Node atual : lista.getListaDeAdjacencia()) {

            for (Linha linha : atual.getAdjacencia()) {

                if (linha.getOrigem() == vertice
                        || linha.getDestino() == vertice) {

                    if (!linhasParaRemover.contains(linha)) {
                        linhasParaRemover.add(linha);
                    }
                }
            }
        }

        /*
         * Remove as arestas das listas de adjacencia.
         */

        for (Linha linha : linhasParaRemover) {

            linha.getOrigem().removeAdjacencia(linha);
            linha.getDestino().removeAdjacencia(linha);
        }

        /*
         * Remove o vertice da lista principal.
         */

        lista.getListaDeAdjacencia().remove(vertice);

        lista.setVertices(lista.getListaDeAdjacencia().size());

        lista.setLinhas(
                Math.max(0, lista.getLinhas() - linhasParaRemover.size())
        );
    }

    // =========================================================
    // MOSTRAR GRAFO
    // =========================================================

    public static void mostrarGrafo(Lista lista) {

        if (lista.getVertices() == 0) {
            System.out.println("\nO grafo esta vazio.");
            return;
        }

        System.out.println("\nGerando imagem do grafo...");

        String arquivo = "grafo.png";

        ImagemGrafo.salvar(lista, arquivo);

        System.out.println("Grafo gerado em: " + arquivo);

        mostrarImagem(arquivo);
    }

    // =========================================================
    // MATRIZES
    // =========================================================

    public static void mostrarMatrizes(Lista lista) {

        if (lista.getVertices() == 0) {
            System.out.println("\nO grafo esta vazio.");
            return;
        }

        System.out.println("\n--- MATRIZ DE ADJACENCIA ---");

        int[][] matrizAdjacencia =
                Matrizes.matrizAdjacencia(lista);

        imprimirMatriz(matrizAdjacencia);

        System.out.println("\n--- MATRIZ DE INCIDENCIA ---");

        int[][] matrizIncidencia =
                Matrizes.matrizIncidencia(lista);

        imprimirMatriz(matrizIncidencia);
    }

    public static void imprimirMatriz(int[][] matriz) {

        for (int[] linha : matriz) {

            for (int valor : linha) {
                System.out.print(valor + "\t");
            }

            System.out.println();
        }
    }

    // =========================================================
    // AGM
    // =========================================================

    public static void executarAGM(Lista lista) {

        if (lista.getVertices() == 0) {
            System.out.println("\nO grafo esta vazio.");
            return;
        }

        if (lista.isDirecionada()) {
            System.out.println(
                    "\nAGM nao pode ser executada nesse grafo direcionado."
            );
            return;
        }

        System.out.println("\nCalculando AGM...");

        Lista agm = Agm.calcularAgm(lista);

        System.out.println("AGM calculada com sucesso.");

        String arquivo = "AGM.png";

        ImagemGrafo.salvar(agm, arquivo);

        System.out.println("Imagem da AGM salva em: " + arquivo);

        mostrarImagem(arquivo);
    }

    // =========================================================
    // DFS
    // =========================================================

    public static void executarDFS(Lista lista) {

        if (lista.getVertices() == 0) {
            System.out.println("\nO grafo esta vazio.");
            return;
        }

        System.out.println("\n--- BUSCA EM PROFUNDIDADE ---");

        int id = lerInteiro(
                "ID do vertice onde deseja iniciar a DFS: "
        );

        Node vertice = buscarVerticePorId(lista, id);

        if (vertice == null) {
            System.out.println("Vertice nao encontrado.");
            return;
        }

        int indice =
                lista.getListaDeAdjacencia().indexOf(vertice);

        BuscaProfundidade.executar(lista, indice);

        System.out.println("DFS executada com sucesso.");
    }

    // =========================================================
    // ROY
    // =========================================================

    public static void executarRoy(Lista lista) {

        if (lista.getVertices() == 0) {
            System.out.println("\nO grafo esta vazio.");
            return;
        }

        System.out.println("\nExecutando Roy...");

        List<List<Node>> componentes =
                Roy.executar(lista);

        System.out.println("Roy executado com sucesso.");

        System.out.println(
                "Componentes encontrados: " + componentes.size()
        );

        String arquivo = "Roy.png";

        ImagemGrafo.salvarRoy(
                lista,
                componentes,
                arquivo
        );

        System.out.println(
                "Imagem do resultado salva em: " + arquivo
        );

        mostrarImagem(arquivo);
    }

    // =========================================================
    // EXIBIR IMAGEM
    // =========================================================

    public static void mostrarImagem(String caminho) {

        File arquivo = new File(caminho);

        if (!arquivo.exists()) {
            System.out.println(
                    "Nao foi possivel encontrar a imagem."
            );
            return;
        }

        JFrame janela = new JFrame("Grafo");

        ImageIcon imagemOriginal =
                new ImageIcon(caminho);

        Image imagem =
                imagemOriginal.getImage();

        int largura = imagemOriginal.getIconWidth();
        int altura = imagemOriginal.getIconHeight();

        int larguraMaxima = 1000;
        int alturaMaxima = 700;

        if (largura > larguraMaxima
                || altura > alturaMaxima) {

            double escalaX =
                    (double) larguraMaxima / largura;

            double escalaY =
                    (double) alturaMaxima / altura;

            double escala =
                    Math.min(escalaX, escalaY);

            largura = (int) (largura * escala);
            altura = (int) (altura * escala);

            imagem = imagem.getScaledInstance(
                    largura,
                    altura,
                    Image.SCALE_SMOOTH
            );
        }

        JLabel label = new JLabel(
                new ImageIcon(imagem)
        );

        label.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        janela.add(
                new JScrollPane(label),
                BorderLayout.CENTER
        );

        janela.setSize(
                Math.min(largura + 50, 1100),
                Math.min(altura + 80, 800)
        );

        janela.setLocationRelativeTo(null);

        janela.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        janela.setVisible(true);

        label.setFocusable(true);
        label.requestFocusInWindow();

        label.addKeyListener(
                new java.awt.event.KeyAdapter() {

                    @Override
                    public void keyPressed(
                            java.awt.event.KeyEvent e) {

                        if (e.getKeyCode()
                                == java.awt.event.KeyEvent.VK_ENTER) {

                            janela.dispose();
                        }
                    }
                }
        );

        System.out.println(
                "Pressione ENTER na janela da imagem para voltar ao menu."
        );
    }

    // =========================================================
    // LEITURA DE DADOS
    // =========================================================

    public static int lerInteiro(String mensagem) {

        while (true) {

            System.out.print(mensagem);

            String entrada = scanner.nextLine();

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {

                System.out.println(
                        "Digite apenas um numero inteiro."
                );
            }
        }
    }

    public static String lerTexto(String mensagem) {

        while (true) {

            System.out.print(mensagem);

            String texto = scanner.nextLine().trim();

            if (!texto.isEmpty()) {
                return texto;
            }

            System.out.println(
                    "O texto nao pode ficar vazio."
            );
        }
    }

    public static boolean lerBoolean(String mensagem) {

        while (true) {

            System.out.print(mensagem);

            String resposta =
                    scanner.nextLine().trim().toLowerCase();

            if (resposta.equals("s")
                    || resposta.equals("sim")) {

                return true;
            }

            if (resposta.equals("n")
                    || resposta.equals("nao")
                    || resposta.equals("não")) {

                return false;
            }

            System.out.println(
                    "Digite apenas S ou N."
            );
        }
    }
}