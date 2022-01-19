package com.twilight.server.http;

import com.twilight.server.dispatcher.Dispatcher;
import com.twilight.server.handler.Manager;
import com.twilight.server.dispatcher.DispatcherExecutor;
import com.twilight.server.handler.Handler;
import com.twilight.server.handler.exception.ExecutorNotFound;
import com.twilight.server.http.io.InputOutputExchangerNetImplementation;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;


public abstract class AsyncHttpServerImplementation {
    private static ByteBuffer[] byteBuffers;
    private static InetSocketAddress address;
    private static byte[] responseBuffer;
    private static byte[] messageBody;
    private static Manager manager;

    protected AsyncHttpServerImplementation(InetSocketAddress address) {
        AsyncHttpServerImplementation.address = address;
    }

    protected AsyncHttpServerImplementation(String hostname, int port) {
        AsyncHttpServerImplementation.address = new InetSocketAddress(hostname, port);
    }

    protected AsyncHttpServerImplementation(int port) {
        AsyncHttpServerImplementation.address = new InetSocketAddress(port);
    }

    protected static HttpServer addListener(InetSocketAddress address) {
        return new AsyncHttpServer(address);
    }


    protected static HttpServer addListener(String hostname, int port) {
        return new AsyncHttpServer(hostname, port);
    }


    protected static HttpServer addListener(int port) {
        return new AsyncHttpServer(port);
    }

    protected void setManager(Manager manager) {
        AsyncHttpServerImplementation.manager = manager;
    }

    protected void startServer() {
        socketServer(manager);
//        if (configureBlocking) blockingServer(manager);
//        if (!configureBlocking) nonBlockingServer(manager);
    }


    protected void socketServer(Manager manager) {
        ServerSocket serverSocket = configureServerSocket();
        while (true) {
            newThreadAccept(serverSocket, manager);
        }
    }

    private ServerSocket configureServerSocket() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(address);
//            serverSocket.setPerformancePreferences(0, 1, 2);
//            serverSocket.setReuseAddress(true);
//            serverSocket.setReceiveBufferSize(Integer.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverSocket;
    }

    private void newThreadAccept(ServerSocket serverSocket, Manager manager) {
        Thread threadFork = new Thread(() -> {
            try {
                Socket socket = configureSocket(serverSocket);
//                oldIsNull();
                InputOutputExchangerNetImplementation buffer = new InputOutputExchangerNetImplementation(socket);
//                dispatcherHandlesExecutors(manager, buffer.read());
                buffer.write(responseBuffer, messageBody);
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        threadFork.start();
        threadFork.interrupt();
    }

    private Socket configureSocket(ServerSocket serverSocket) {
        Socket socket = null;
        try {
            socket = serverSocket.accept();
//            socket.setPerformancePreferences(0, 1, 2);
//            socket.setSendBufferSize(Integer.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }

    private void oldIsNull() {
        if (!(responseBuffer == null)) responseBuffer = null;
        if (!(messageBody == null)) messageBody = null;
    }

    protected void blockingServer(Manager manager) throws ExecutorNotFound {
        ServerSocketChannel serverSocketChannel = getOpenСonfiguredServerSocketChannel(true, null);
        while (true) {
            SocketChannel socketChannel = getСonfiguredServerSocketChannelAccept(serverSocketChannel, true, null);
            dispatcherHandlesExecutors(manager, readRequest(socketChannel, null));
            writeResponse(socketChannel, byteBuffers, null);
            socketClose(socketChannel);
        }
    }

    protected void nonBlockingServer(Manager manager) throws ExecutorNotFound {
        try {
            Selector selector = Selector.open();
            ServerSocketChannel serverSocketChannel = getOpenСonfiguredServerSocketChannel(false, selector);
            while (true) {
                int countSelectors = selector.select(5000L);
                System.out.println("Count selectors: " + countSelectors);
                if (countSelectors < 0) continue;
                channelEventLoop(serverSocketChannel, selector, selector.selectedKeys(), manager);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ServerSocketChannel getOpenСonfiguredServerSocketChannel(boolean configureBlocking, Selector selector) {
        ServerSocketChannel serverSocketChannel = null;
        try {
            serverSocketChannel = ServerSocketChannel
                    .open()
                    .bind(address)
                    .setOption(StandardSocketOptions.SO_RCVBUF, Integer.MAX_VALUE);
            if (!configureBlocking) {
                serverSocketChannel.configureBlocking(false);
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverSocketChannel;
    }

    private static SocketChannel getСonfiguredServerSocketChannelAccept(ServerSocketChannel serverSocketChannel, boolean configureBlocking, Selector selector) {
        SocketChannel socketChannel = null;
        try {
            socketChannel = serverSocketChannel
                    .accept()
                    .setOption(StandardSocketOptions.SO_SNDBUF, Integer.MAX_VALUE)
                    .setOption(StandardSocketOptions.SO_REUSEADDR, true);
            if (!configureBlocking) {
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socketChannel;
    }

    private static void channelEventLoop(ServerSocketChannel serverSocketChannel, Selector selector, Set<SelectionKey> keySet, Manager manager) throws ExecutorNotFound {
        Iterator<SelectionKey> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();
            iterator.remove();
            if (key.isAcceptable()) {
                getСonfiguredServerSocketChannelAccept(serverSocketChannel, false, selector);
            }
            if (key.isReadable()) {
                SocketChannel socketChannel = (SocketChannel) key.channel();
                dispatcherHandlesExecutors(manager, readRequest(socketChannel, key));
                key.interestOps(SelectionKey.OP_WRITE);
            }

            if (key.isWritable()) {
                SocketChannel socketChannel = (SocketChannel) key.channel();
                writeResponse(socketChannel, byteBuffers, key);
//                socketClose(socketChannel);
                key.cancel();
//                long writtenBytes = 0;
//                while (writtenBytes <= byteBuffers.length) {
//                writtenBytes +=

//                    System.out.println(writtenBytes);
//                    if (writtenBytes == b yteBuffers.length) break;
//                }
//                System.out.println("loop end, size transfer: " + writtenBytes);

            }
        }
//        keySet.clear();
    }

    private static String readRequest(SocketChannel clientChannel, SelectionKey key) {
        String request = "";
        try {
            ByteBuffer buffer = ByteBuffer.allocate(Short.MAX_VALUE);
            clientChannel.read(buffer);
            request = new String(buffer.array()).trim();
            buffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
            socketClose(clientChannel);
            key.cancel();
        }
        return request;
    }

    private static void dispatcherHandlesExecutors(Manager manager, String request) {
        Dispatcher dispatcher = DispatcherExecutor.createDispatcher(manager, request);
//        dispatcher.invokeDoExecute(dispatcher.defineExecutor(), ResponseBuffer responseBuffer, MessageBody messageBody);
    }

    private static Handler getExecutorSelected(Manager manager, String request) throws ExecutorNotFound {
        Dispatcher dispatcher = DispatcherExecutor.createDispatcher(manager, request);
        return dispatcher.defineExecutor();
    }

    private static long writeResponse(SocketChannel clientChannel, ByteBuffer[] buffers, SelectionKey key) {
        long writtenBytes = 0;
        try {
            System.out.println("Is open: " + clientChannel.isOpen());
            if (!clientChannel.isOpen()) {
                socketClose(clientChannel);
                key.cancel();
            }
            if (clientChannel.isOpen()) writtenBytes += clientChannel.write(buffers, 0, buffers.length);
        } catch (IOException | CancelledKeyException e) {
            e.printStackTrace();
            socketClose(clientChannel);
            key.cancel();
        }
        return writtenBytes;
    }

    private static void socketClose(SocketChannel socketChannel) {
        try {
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//    protected void startServer(Manager manager) throws ExecutorNotFound {
//        try {
//            ServerSocketChannel serverSocketChannel = ServerSocketChannel
//                    .open()
//                    .bind(address);
//            serverSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, Integer.MAX_VALUE);
//
//            while (true) {
//                SocketChannel socketChannel = serverSocketChannel.accept();
//                setSocketOptions(socketChannel);
//
//                String request = readRequest(socketChannel);
//                HttpRequest req = new AsyncHttpRequest(request);
//                HttpResponse response = new AsyncHttpResponse();
////                System.out.println(request);
//                Dispatcher dispatcher = DispatcherExecutor.createDispatcher();
//                Executor executor = dispatcher.defineExecutor(manager, request);
//                executor.doExecute(req, response);
//                writeResponse(socketChannel, byteBuffers);
//                socketChannel.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }