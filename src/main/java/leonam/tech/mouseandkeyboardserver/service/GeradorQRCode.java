package leonam.tech.mouseandkeyboardserver.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.Hashtable;

public class GeradorQRCode {

    public static void gerarQRCode(String dados) {
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
            System.err.println("Erro ao gerar o QR Code: " + e.getMessage());
        }
    }
}
