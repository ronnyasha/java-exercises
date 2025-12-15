package task3;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public final class ShiftCipherOutputStream extends FilterOutputStream {
    private final int key;

    public ShiftCipherOutputStream(OutputStream out, int key) {
        super(out);
        this.key = key;
    }

    @Override
    public void write(int b) throws IOException {
        int enc = (b + key) & 0xFF;
        out.write(enc);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        byte[] tmp = new byte[len];
        for (int i = 0; i < len; i++) {
            tmp[i] = (byte) ((b[off + i] + key) & 0xFF);
        }
        out.write(tmp, 0, len);
    }
}
