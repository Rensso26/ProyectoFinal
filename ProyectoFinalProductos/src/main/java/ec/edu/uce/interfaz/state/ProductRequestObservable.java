package ec.edu.uce.interfaz.state;

import ec.edu.uce.interfaz.Interfaces.ProductRequestObserver;

import java.util.ArrayList;
import java.util.List;

public class ProductRequestObservable {
    private List<ProductRequestObserver> observers = new ArrayList<>();

    public void addObserver(ProductRequestObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ProductRequestObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(ProductRequest request) {
        for (ProductRequestObserver observer : observers) {
            observer.update(request);
        }
    }
}


