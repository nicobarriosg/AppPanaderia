package com.example.evaluacion1;

import java.util.ArrayList;
import java.util.HashMap;

public class InventoryManager {
    private static InventoryManager instance;
    private HashMap<String, Producto> inventory;

    private InventoryManager() {
        inventory = new HashMap<>();
        // Datos iniciales de inventario
        inventory.put("Pan", new Producto("Pan", 10, "1234567890", R.drawable.pan));
        inventory.put("Croissant", new Producto("Croissant", 15, "0987654321", R.drawable.croissant));
        inventory.put("Baguette", new Producto("Baguette", 8, "1122334455", R.drawable.baguette));
    }

    public static synchronized InventoryManager getInstance() {
        if (instance == null) {
            instance = new InventoryManager();
        }
        return instance;
    }

    public void addToInventory(String product, int quantity) {
        if (inventory.containsKey(product)) {
            Producto existingProduct = inventory.get(product);
            existingProduct.setQuantity(existingProduct.getQuantity() + quantity);
        } else {
            inventory.put(product, new Producto(product, quantity, "0000000000", R.drawable.pan));
        }
    }

    public ArrayList<Producto> getInventoryList() {
        return new ArrayList<>(inventory.values());
    }
}
