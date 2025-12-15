package task3;

import java.io.*;

public final class ShiftCipher {
    private ShiftCipher() {}

    public static void encryptFile(String inPath, String outPath, char keyChar) throws IOException {
        int key = (int) keyChar;
        try (InputStream in = new FileInputStream(inPath);
             OutputStream out = new ShiftCipherOutputStream(new FileOutputStream(outPath), key)) {
            in.transferTo(out);
        }
    }

    public static void decryptFile(String inPath, String outPath, char keyChar) throws IOException {
        int key = (int) keyChar;
        try (InputStream in = new ShiftCipherInputStream(new FileInputStream(inPath), key);
             OutputStream out = new FileOutputStream(outPath)) {
            in.transferTo(out);
        }
    }
}
