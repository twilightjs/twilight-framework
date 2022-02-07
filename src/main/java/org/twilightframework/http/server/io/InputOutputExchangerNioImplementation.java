package org.twilightframework.http.server.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class InputOutputExchangerNioImplementation extends InputOutputExchanger {
    public InputOutputExchangerNioImplementation(SocketChannel socketChannel) {
        super(socketChannel);
    }

    @Override
    public void write(byte[]... buffers) {
        writeToSocketChannel(buffers);
    }


    private void writeToSocketChannel(byte[]... buffers) {
        ByteBuffer[] byteBuffer = new ByteBuffer[buffers.length];
        for (int i = 0; i < buffers.length; i++) {
            byteBuffer[i] = ByteBuffer.wrap(buffers[i]);
        }
        try {
            this.socketChannel.write(byteBuffer, 0, buffers.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            this.socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
