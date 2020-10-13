package video.api.java.sdk.infrastructure.unirest.video;

import java.io.*;

class ChunkInputStream extends FileInputStream {
    private final int  length;
    private       long readBytes = 0;

    public ChunkInputStream(File file, long start, int length) throws IOException {
        super(file);
        this.length = length;

        //noinspection ResultOfMethodCallIgnored
        this.skip(start);
    }

    @Override
    public int read(byte[] b) throws IOException {
        if (readBytes >= this.length) {
            return -1;
        }

        int length = b.length;
        if (b.length > this.length) {
            length = this.length;
        }

        readBytes += length;

        return super.read(b, 0, length);
    }
}
