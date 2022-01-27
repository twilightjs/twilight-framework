package com.twilight.server.http.io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public abstract class InputOutputExchanger {
    protected String request;
    protected Socket socket;
    protected SocketChannel socketChannel;

    public InputOutputExchanger(Socket socket) {
        byte[] buffer = new byte[Short.MAX_VALUE];
        BufferedInputStream input = null;
        try {
            input = new BufferedInputStream(socket.getInputStream());
            input.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.request = new String(buffer).trim();
        this.socket = socket;
    }

    public InputOutputExchanger(SocketChannel socketChannel) {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(Short.MAX_VALUE);
            socketChannel.read(buffer);
            this.request = new String(buffer.array()).trim();
            buffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.socketChannel = socketChannel;
    }

    public String read() {
        return this.request;
    }

    public abstract void write(byte[]... buffers);

    public String getRequest() {
        return this.request;
    }

    public abstract void close();

}
