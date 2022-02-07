package org.twilightframework.http.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TwilightMultiThreadingServer extends Twilight {

    private final ExecutorService threadPool = Executors.newCachedThreadPool();

    public TwilightMultiThreadingServer(TwilightBuilder twilightBuilder, NotifierTwilightMultiThreadingServer notifierTwilightMultiThreadingServer) {
        super(twilightBuilder, notifierTwilightMultiThreadingServer);
    }

    @Override
    public void start() {
        ServerSocket serverSocket = configureServerSocket();
        while (true) {
            this.threadPool.execute(new RequestHandlerNet(configureSocket(serverSocket), this.handlers, this.notificationBehavior));
        }
    }



    private ServerSocket configureServerSocket() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(this.address);
//            serverSocket.setPerformancePreferences(0, 1, 2);
//            serverSocket.setReuseAddress(true);
//            serverSocket.setReceiveBufferSize(Integer.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverSocket;
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

//    private void newThreadAccept(ServerSocket serverSocket) {
//        Thread threadFork = new Thread(() -> {
//            Socket socket = null;
//            try {
//                socket = configureSocket(serverSocket);
//                InputOutputExchanger inputOutputExchanger = new InputOutputExchangerNetImplementation(socket);
//                HandlerSelector handlerSelector = new HandlerSelector(handlers, inputOutputExchanger);
////                notifyHandler(handlerSelector.select(), inputOutputExchanger);
////                new HandlerSelector(this.handlers, ioBuffer.read(), socket).select();
////                inputOutputExchanger.write(responseBuffer, messageBody);
//                socket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//                socketClose(socket);
//            }
//        });
//        threadFork.start();
//        threadFork.interrupt();
//    }




//    private void dispatcherHandlesExecutors(String request) {
//        Dispatcher dispatcher = DispatcherExecutor.createDispatcher(this.manager, request);
//        dispatcher.invokeDoExecute(dispatcher.defineExecutor());
//    }
//
//    private void socketClose(Socket socket) {
//        try {
//            socket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//

}
