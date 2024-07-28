package ec.edu.uce.interfaz.config;

import ec.edu.uce.interfaz.observer.FabricationObserver;
import ec.edu.uce.interfaz.service.ManufacturingService;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class ObserverConfig {

    private final ManufacturingService manufacturingService;
    private final FabricationObserver fabricationObserver;

    public ObserverConfig(ManufacturingService manufacturingService, FabricationObserver fabricationObserver) {
        this.manufacturingService = manufacturingService;
        this.fabricationObserver = fabricationObserver;
    }

    @PostConstruct
    public void init() {
        manufacturingService.addObserver(fabricationObserver);
    }
}
