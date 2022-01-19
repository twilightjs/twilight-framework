package com.twilight.server.io.response;

import com.twilight.server.http.Twilight;
import com.twilight.server.http.AsyncHttpServerImplementation;
import com.twilight.server.io.components.builder.ResponseBuilder;
import com.twilight.server.io.components.builder.ResponseBuilderImplementation;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AsyncHttpResponseImplementation {
//    private final int LENGTH = 2;
//    private final int RESPONSE = 0;
//    private final int MESSAGE_BODY = 1;
//    private ByteBuffer[] buffers = new ByteBuffer[LENGTH];
//
//    protected void addResponse(String response) {
//        this.buffers[RESPONSE] = ByteBuffer.wrap(response.getBytes(StandardCharsets.UTF_8));
//    }
//
//    protected void addMessageBody(String messageBody) {
//        this.buffers[MESSAGE_BODY] = ByteBuffer.wrap(messageBody.getBytes(StandardCharsets.UTF_8));
//    }
//
//    protected void addFile(String path) {
//        try {
//            this.buffers[MESSAGE_BODY] = ByteBuffer.wrap(Files.readAllBytes(Paths.get(path)));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private final int LENGTH = 2;
    private final int RESPONSE = 0;
    private final int MESSAGE_BODY = 1;
    private ByteBuffer[] buffers = new ByteBuffer[LENGTH];

    protected void addResponse(String response) {
        writeBuffer("responseBuffer", response.getBytes(StandardCharsets.UTF_8));
    }

    protected void addMessageBody(String messageBody) {
        writeBuffer("messageBody", messageBody.getBytes(StandardCharsets.UTF_8));
    }

    protected void addFile(String path) {
        try {
            writeBuffer("messageBody", Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeBuffer(String nameField, byte[] buffer) {
        try {
            Field fieldMessage = Twilight.class.getDeclaredField(nameField);
            fieldMessage.setAccessible(true);
            fieldMessage.set(Twilight.class, buffer);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected void writeResponse() {
        try {
            Field fieldMessage = AsyncHttpServerImplementation.class.getDeclaredField("byteBuffers");
            fieldMessage.setAccessible(true);
            fieldMessage.set(AsyncHttpServerImplementation.class, this.buffers);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected void write(ByteBuffer bufferResponse, ByteBuffer bufferMessageBody) {
        try {
            Field fieldMessage = AsyncHttpServerImplementation.class.getDeclaredField("byteBuffers");
            fieldMessage.setAccessible(true);
            fieldMessage.set(AsyncHttpServerImplementation.class, buffers(bufferResponse, bufferMessageBody));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected void write() {
        try {
            Field fieldMessage = AsyncHttpServerImplementation.class.getDeclaredField("byteBuffers");
            fieldMessage.setAccessible(true);
            fieldMessage.set(AsyncHttpServerImplementation.class, this.buffers);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private ByteBuffer[] buffers(ByteBuffer bufferResponse, ByteBuffer bufferMessageBody) {
        ByteBuffer[] buffers = new ByteBuffer[2];
        buffers[0] = bufferResponse;
        buffers[1] = bufferMessageBody;
        return buffers;
    }

    protected ResponseBuilder getResponseBuilder() {
        return new ResponseBuilderImplementation();
    }
}
