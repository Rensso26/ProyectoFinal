package ec.edu.uce.interfaz.Interfaces;

import ec.edu.uce.interfaz.state.ProductRequest;

public interface ProductRequestObserver {
    void update(ProductRequest request);
}