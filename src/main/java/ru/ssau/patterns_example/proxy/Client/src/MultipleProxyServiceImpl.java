import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class MultipleProxyServiceImpl implements MultipleService{

    @Override
    public double mul(double a, double b) {
        double result = 0;
        try (Socket socket = new Socket("localhost", 5000)) {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeDouble(a);
            dos.writeDouble(b);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            result = dis.readDouble();
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException("Unknown host error.", e);
        } catch (IOException e) {
            throw new IllegalStateException("IOException", e);
        }
        return result;
    }
}
