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
        hints.put(EncodeHintType.MARGIN, 1);

        try {
            BitMatrix matrix = new MultiFormatWriter().encode(dados, BarcodeFormat.QR_CODE, 0, 0, hints);
            int scale = 2;

            for (int y = 0; y < matrix.getHeight(); y++) {
                for (int x = 0; x < matrix.getWidth(); x++) {
                    if (matrix.get(x, y)) {
                        for (int i = 0; i < scale; i++) {
                            for (int j = 0; j < scale; j++) {
                                System.out.print("█");
                            }
                        }
                    } else {
                        for (int i = 0; i < scale; i++) {
                            for (int j = 0; j < scale; j++) {
                                System.out.print(" ");
                            }
                        }
                    }
                }
                System.out.println();
            }
        } catch (WriterException e) {
            System.err.println("Erro ao gerar o QR Code: " + e.getMessage());
        }
    }
}
