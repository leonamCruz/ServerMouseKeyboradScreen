package leonam.tech.mouseandkeyboardserver.service;

import com.google.zxing.BarcodeFormat; // Importa o formato do código de barras (QR Code)
import com.google.zxing.EncodeHintType; // Importa tipos de dicas para codificação (ex. margem)
import com.google.zxing.MultiFormatWriter; // Importa a classe responsável por gerar códigos de barras
import com.google.zxing.WriterException; // Exceção lançada se houver erro ao gerar o código
import com.google.zxing.common.BitMatrix; // Representação interna do QR Code como uma matriz de bits

import java.util.Hashtable; // Usada para armazenar as dicas (hints) para a geração do QR Code

public class GeradorQRCode {

    public static void gerarQRCode(String dados) {
        // Cria um objeto Hashtable para armazenar as dicas (hints) para o gerador de QR Code
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.MARGIN, 5);
        hints.put(EncodeHintType.QR_COMPACT, true);

        try {
            // Gera a matriz de bits (QR Code) com os dados fornecidos
            // O método encode cria um QR Code em uma matriz de bits
            BitMatrix matrix = new MultiFormatWriter().encode(dados, BarcodeFormat.QR_CODE, 0, 0, hints);

            int scale = 2; // Fator de escala para aumentar o tamanho do QR Code no console (2x2)

            // Itera por cada linha do QR Code (matriz de bits)
            for (int y = 0; y < matrix.getHeight(); y++) {
                // Itera por cada coluna do QR Code (matriz de bits)
                for (int x = 0; x < matrix.getWidth(); x++) {
                    if (matrix.get(x, y)) { // Se o valor na posição (x, y) for "true" (preto)
                        // Imprime um bloco preenchido "█" várias vezes, dependendo da escala
                        for (int i = 0; i < scale; i++) {
                            for (int j = 0; j < scale; j++) {
                                System.out.print(" "); // Imprime um espaço (em branco)
                            }
                        }
                    } else { // Se o valor na posição (x, y) for "false" (branco)
                        // Imprime um espaço em branco várias vezes, dependendo da escala
                        for (int i = 0; i < scale; i++) {
                            for (int j = 0; j < scale; j++) {
                                System.out.print("█"); // Imprime um quadrado preto
                            }
                        }
                    }
                }
                System.out.println(); // Ao final de cada linha, pula para a próxima linha
            }
        } catch (WriterException e) {
            // Captura erros na geração do QR Code e imprime a mensagem de erro
            System.err.println("Erro ao gerar o QR Code: " + e.getMessage());
        }
    }
}
