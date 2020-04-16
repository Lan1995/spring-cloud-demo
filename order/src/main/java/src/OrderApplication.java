package src;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @RestController
    class DemoController {

        @Value("${server.port}")
        private Integer port;

        @GetMapping("echo")
        public String getEcho(String name) {
            try {
                Thread.sleep(5000);
                System.out.println("实例1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return port + "-provider: " + name;
        }
    }
}
