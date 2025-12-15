package task3;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class ShiftCipherInputStream extends FilterInputStream {
    private final int key;

    public ShiftCipherInputStream(InputStream in, int key) {
        super(in);
        this.key = key;
    }

    @Override
    public int read() throws IOException {
        int b = in.read();
        if (b == -1) return -1;
        return (b - key) & 0xFF;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int n = in.read(b, off, len);
        if (n == -1) return -1;
        for (int i = 0; i < n; i++) {
            b[off + i] = (byte) ((b[off + i] - key) & 0xFF);
        }
        return n;
    }
}
