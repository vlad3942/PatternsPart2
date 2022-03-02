public class ClientMain {

    public static void main(String[] args) {
        MultipleService ms = new MultipleProxyServiceImpl();
        double res = ms.mul(12, 3);
        System.out.println("res = " + res);
    }
}
