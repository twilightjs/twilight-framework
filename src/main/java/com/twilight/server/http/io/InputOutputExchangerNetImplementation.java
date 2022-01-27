package com.twilight.server.http.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class InputOutputExchangerNetImplementation extends InputOutputExchanger {
    public InputOutputExchangerNetImplementation(Socket socket) {
        super(socket);
    }

    @Override
    public void write(byte[]... buffers) {
        writeToSocket(buffers);
    }


    private void writeToSocket(byte[]... buffers) {
        BufferedOutputStream output = null;
        try {
            output = new BufferedOutputStream(this.socket.getOutputStream());
            for (byte[] buffer : buffers) {
                if(buffer == null) continue;
                output.write(buffer);
            }
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
