package Serialization;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Buffer {
    private static final int BUFFER_SIZE = 80;

    private BufferedInputStream stream;
    private byte[] buffer;
    private int bufferIdx;
    private int bufferCap;

    public Buffer() {
        setBuffer();
    }

    public Buffer(InputStream inputStream) {
        this();
        setStream(inputStream);
    }

    private void setStream(InputStream inputStream) {
        stream = new BufferedInputStream(inputStream);
    }

    public boolean isNull() {
        return stream == null;
    }

    private void setBuffer() {
        bufferIdx = 0;
        bufferCap = 0;
        buffer = new byte[BUFFER_SIZE];
    }

    private boolean hasCapaicity(int amount) {
        return (bufferCap - bufferIdx) >= amount;
    }

    private void fillBuffer() {
        if(bufferCap != bufferIdx) {
            int tmpIdx = bufferIdx;
            int tmpCap = bufferCap;
            byte[] tmpBuffer = buffer;

            setBuffer();

            for(int i = tmpIdx; i < tmpCap; i++) {
                buffer[bufferCap++] = tmpBuffer[i];
            }
        } else {
            setBuffer();
        }
        bufferCap += read(buffer, bufferIdx, (buffer.length - bufferCap));
    }

    private int read(byte[] buf, int index, int amount) {
        int bytesRead = 0;
        while(bytesRead < amount) {
            try {
                bytesRead += stream.read(buf, index, (amount - bytesRead));
            } catch(IOException e) {
                e.printStackTrace();
            }
            index += bytesRead;
        }
        return bytesRead;
    }

    public void get(byte[] buf) throws IOException {
        if(buf.length > 0) { //if data needs to be read
            for(int i = 0; i < buf.length ; i++) {
                if(!hasCapaicity(buf.length)) {
                    fillBuffer();
                }
                buf[i] = buffer[bufferIdx++];
            }
        }
    }
}
