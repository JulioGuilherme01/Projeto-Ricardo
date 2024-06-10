package app.util;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ProvaCorrigida {

    public static List<String> corrigirProva(BufferedImage img, int numQuestoes, int numOpcoes) {
        int largura = img.getWidth();
        int altura = img.getHeight();

        // Pré-processamento da imagem
        BufferedImage procImg = PreProcessamento(img);

        // Exibir imagem binarizada para verificação
        //exibirImagem(processedImg, "Imagem Binarizada");

        int PrimeiroPixelX = 80; // Coordenada x de início da primeira opção
        int PrimeiroPixelY = 0; // Coordenada y de início da questão
        int DelimitacaoAltura = altura / numQuestoes;
        int DelimitacaoLargura = (largura - PrimeiroPixelX - 20) / numOpcoes; // Largura da opção
        int MargemInterna = 10;

        List<String> respostas = new ArrayList<>();
        String resposta = null;
        double minMarcacao = 0.5; // pixels mínimo para uma marcação válida (50%)

        for (int questao = 0; questao < numQuestoes; questao++) {
            int yInicio = PrimeiroPixelY + questao * DelimitacaoAltura;
            int yFim = yInicio + DelimitacaoAltura;

            int numMarcacoes = 0;
            for (int opcao = 0; opcao < numOpcoes; opcao++) {
                int xInicio = PrimeiroPixelX + opcao * DelimitacaoLargura + MargemInterna;
                int xFim = xInicio + DelimitacaoLargura - 2 * MargemInterna;
                int yInicioAjustado = yInicio + MargemInterna;
                int yFimAjustado = yFim - MargemInterna;

                if (opcaoMarcada(procImg, xInicio, xFim, yInicioAjustado, yFimAjustado, minMarcacao)) {
                    numMarcacoes++;
                    resposta =" opção " + (char) ('A' + opcao);
                    // Marcar a opção como selecionada (inicialmente sem cor específica)
                    MarcarOpcao(img, xInicio, yInicioAjustado, xFim - xInicio, yFimAjustado - yInicioAjustado, Color.YELLOW);
                }
            }

            if (numMarcacoes == 0) {
                resposta = " anulada (nenhuma opcao marcada ou marcacao insuficiente)";
            } else if (numMarcacoes > 1) {
                resposta =  " anulada (multiplas opcoes marcadas)";
            }

            respostas.add(resposta);
        }

        return respostas;
    }
    //PreProcessamento da imagem
    private static BufferedImage PreProcessamento(BufferedImage img) {
        int largura = img.getWidth();
        int altura = img.getHeight();
        BufferedImage grayImg = new BufferedImage(largura, altura, BufferedImage.TYPE_BYTE_GRAY);

        // Converter para escala de cinza
        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                Color cor = new Color(img.getRGB(x, y));
                int gray = (int) (cor.getRed() * 0.3 + cor.getGreen() * 0.59 + cor.getBlue() * 0.11);
                grayImg.setRGB(x, y, new Color(gray, gray, gray).getRGB());
            }
        }

        // Aplicar limiar binário
        BufferedImage binarizacao = new BufferedImage(largura, altura, BufferedImage.TYPE_BYTE_BINARY);
        int threshold = 128;
        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                Color grayColor = new Color(grayImg.getRGB(x, y));
                int cinza = grayColor.getRed();
                if (cinza < threshold) {
                    binarizacao.setRGB(x, y, Color.BLACK.getRGB());
                } else {
                    binarizacao.setRGB(x, y, Color.WHITE.getRGB());
                }
            }
        }
        return binarizacao;
    }
    //Checa se uma opção está marcada
    private static boolean opcaoMarcada(BufferedImage img, int xInicio, int xFim, int yInicio, int yFim, double minMarcacao) {
        int count = 0;
        int totalPixels = (xFim - xInicio) * (yFim - yInicio);
        for (int y = yInicio; y < yFim; y++) {
            for (int x = xInicio; x < xFim; x++) {
                if ((img.getRGB(x, y) & 0xff) == 0) { // Verifica se o pixel é preto
                    count++;
                }
            }
        }
        return count > (totalPixels * minMarcacao); // Verifica se o número de pixels marcados é maior que o limiar mínimo
    }

    // Marca a opção para visualização do usuario com uma cor específica
    private static void MarcarOpcao(BufferedImage img, int xInicio, int yInicio, int largura, int altura, Color cor) {
        Graphics2D g = img.createGraphics();
        g.setColor(cor);
        g.drawRect(xInicio, yInicio, largura, altura);
        g.dispose();
    }

    // Metodo para exibir a imagem com um título específico
    public static void exibirImagem(BufferedImage img, String titulo) {
        JFrame frame = new JFrame(titulo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new JLabel(new ImageIcon(img)));
        frame.pack();
        frame.setVisible(true);
    }
    //Metodo para contar os acertos do aluno
    public static int contarAcertos(List<String> respostasAluno, List<String> respostasProfessor, BufferedImage imgAluno) {
        int acertos = 0;
        int largura = imgAluno.getWidth();
        int altura = imgAluno.getHeight();
        int numQuestoes = respostasAluno.size();
        int numOpcoes = 5;
        int PrimeiroPixelX = 80;
        int PrimeiroPixelY = 0;
        int DelimitacaoAltura = altura / numQuestoes;
        int DelimitacaoLargura = (largura - PrimeiroPixelX - 20) / numOpcoes;
        int MargemInterna = 10;

        for (int i = 0; i < respostasAluno.size(); i++) {
            int yInicio = PrimeiroPixelY + i * DelimitacaoAltura;
            int yFim = yInicio + DelimitacaoAltura;

            if (respostasAluno.get(i).equals(respostasProfessor.get(i))) {
                acertos++;
                // Marcar a questão correta com um quadrado verde
                for (int opcao = 0; opcao < numOpcoes; opcao++) {
                    int xInicio = PrimeiroPixelX + opcao * DelimitacaoLargura + MargemInterna;
                    int xFim = xInicio + DelimitacaoLargura - 2 * MargemInterna;
                    int yInicioAjustado = yInicio + MargemInterna;
                    int yFimAjustado = yFim - MargemInterna;
                    if (respostasAluno.get(i).contains(String.valueOf((char) ('A' + opcao)))) {
                        MarcarOpcao(imgAluno, xInicio, yInicioAjustado, xFim - xInicio, yFimAjustado - yInicioAjustado, Color.GREEN);
                    }
                }
            } else {
                // Marcar a questão incorreta com um quadrado vermelho
                for (int opcao = 0; opcao < numOpcoes; opcao++) {
                    int xInicio = PrimeiroPixelX + opcao * DelimitacaoLargura + MargemInterna;
                    int xFim = xInicio + DelimitacaoLargura - 2 * MargemInterna;
                    int yInicioAjustado = yInicio + MargemInterna;
                    int yFimAjustado = yFim - MargemInterna;
                    if (respostasAluno.get(i).contains(String.valueOf((char) ('A' + opcao)))) {
                        MarcarOpcao(imgAluno, xInicio, yInicioAjustado, xFim - xInicio, yFimAjustado - yInicioAjustado, Color.RED);
                    }
                }
            }
        }
        return acertos;


    }
}
