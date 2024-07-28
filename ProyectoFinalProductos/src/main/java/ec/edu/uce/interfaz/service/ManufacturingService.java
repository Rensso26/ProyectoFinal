package ec.edu.uce.interfaz.service;

import ec.edu.uce.interfaz.repository.ToyRepository;
import ec.edu.uce.interfaz.observer.Observer;
import ec.edu.uce.interfaz.observer.Subject;
import ec.edu.uce.interfaz.state.Toy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ManufacturingService implements Subject {

    @Autowired
    private ToyRepository toyRepository; // para inyeccion de dependencias
    @Autowired
    private PeticionService peticionService;

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    @Async
    public CompletableFuture<Void> fabricateToys(int toyId, int copies) {
        Toy toy = toyRepository.findById(toyId).orElseThrow(() -> new RuntimeException("Toy not found"));
        double totalTime = toy.getCreationForm().getTimeEnsable() // inyeccion de dependecias
                + toy.getCreationForm().getTimePackaging() +
                toy.getCreationForm().getTimePinter();

        for (int i = 1; i <= copies; i++) {
            try {
                Thread.sleep((long) (totalTime * 1000L));
                String message = toy.getName() + " Copia " + i + " fabricado en " + totalTime + " segundos";
                notifyObservers(message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                notifyObservers("Fabricacio interrupida por " + toy.getName() + " copia " + i);
            }
        }
        peticionService.delete((long) toyId);
        return CompletableFuture.completedFuture(null);
    }
}
