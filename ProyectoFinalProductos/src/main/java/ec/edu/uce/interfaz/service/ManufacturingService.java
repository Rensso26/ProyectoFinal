package ec.edu.uce.interfaz.service;

import ec.edu.uce.interfaz.repository.MessageRepository;
import ec.edu.uce.interfaz.repository.ToyRepository;
import ec.edu.uce.interfaz.state.Message;
import ec.edu.uce.interfaz.state.Toy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Service
public class ManufacturingService {

    @Autowired
    private ToyRepository toyRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private PeticionService peticionService;

    @Async
    public CompletableFuture<Void> fabricateToys(int toyId, int copies) {
        Toy toy = toyRepository.findById(toyId).orElseThrow(() -> new RuntimeException("Toy not found"));
        double totalTime = toy.getCreationForm().getTimeEnsable()
                + toy.getCreationForm().getTimePackaging()
                + toy.getCreationForm().getTimePinter();

        for (int i = 1; i <= copies; i++) {
            try {
                Thread.sleep((long) (totalTime * 1000L));
                String messageContent = toy.getName() + " numero:" + i + " fabricado en " + totalTime + " segundos";
                System.out.println("#############################");
                System.out.println("");
                System.out.println(messageContent);
                System.out.println("");
                System.out.println("#############################");

                // Guardar o actualizar el mensaje en la base de datos
                Message message = messageRepository.findById(1L).orElse(new Message());
                message.setContent(messageContent);
                messageRepository.save(message);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Fabricación interrumpida por " + toy.getName() + " copia " + i);
            }
        }
        peticionService.delete((long) toyId);
        return CompletableFuture.completedFuture(null);
    }
}
