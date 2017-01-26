import com.andersen.ClientTCP;
import com.andersen.ServerTCP;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Integration test for TCP server and 1000 clients in multithreading.
 */
public class TCPClientServerIntegrationTest {

    private String host = "localhost";
    private int port = 3128;
    private final ExecutorService executor = Executors.newFixedThreadPool(50);
    private String randomRequest = null;
    private String response = null;
    private int i = 0;

    @Test
    public void integrationTest() throws InterruptedException {
        List<String> requests = new ArrayList<String>();
        requests.add("Request №1");
        requests.add("Request №2");
        requests.add("Request №3");
        requests.add("Request №4");
        requests.add("Request №5");
        requests.add("Request №6");
        requests.add("Request №7");
        requests.add("Request №8");
        requests.add("Request №9");
        requests.add("Request №10");
        ServerTCP serverTCP = new ServerTCP(host, port);
        Thread serverThread = new Thread(serverTCP);
        serverThread.start();
        for (; i < 1000 ; i++) {
            randomRequest = requests.get((int)(Math.random()*10-1));
            executor.execute(new Runnable() {
                public void run() {
                    response = new ClientTCP(host, port).sendAndReceive(randomRequest);
                    System.out.println("\n" + "Request: " + randomRequest + ". Response: " + response + "\n");
                    assertEquals(randomRequest + " from server.", response);
                }
            });
        }
        executor.shutdown();
        executor.awaitTermination(60, TimeUnit.SECONDS);
    }
}
