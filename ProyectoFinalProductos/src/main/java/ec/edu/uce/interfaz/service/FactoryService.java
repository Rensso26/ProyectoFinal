package ec.edu.uce.interfaz.service;

import ec.edu.uce.interfaz.state.Toy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@EnableAsync
public class FactoryService {

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Async
    public CompletableFuture<String> fabricarProducto(Toy toy) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Cortando madera...");
                Thread.sleep(5000);
                System.out.println("Armando materiales...");
                Thread.sleep(4000);
                System.out.println("Pintando piezas...");
                Thread.sleep(7000);
                System.out.println("Empaquetando...");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "Error en la fabricación de " + toy.getName();
            }
            return "Producto " + toy.getName() + " fabricado con éxito!";
        }, executorService);
    }
}
