import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) {
        try (ServerSocket socket = new ServerSocket()) {
            socket.bind(new InetSocketAddress("localhost", 5000));
            while (true) {
                System.out.println("Waiting for a client...");
                Socket sock = socket.accept();
                System.out.println("Client was accepted");
                Thread thread = new Thread(() -> {
                    try (
                            final DataInputStream dis = new DataInputStream(sock.getInputStream());
                            final DataOutputStream dos = new DataOutputStream(sock.getOutputStream())
                    ) {
                        double a = dis.readDouble();
                        System.out.println("a = " + a);
                        double b = dis.readDouble();
                        System.out.println("b = " + b);
                        dos.writeDouble(a * b);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Client resolved");
                });
                thread.run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
