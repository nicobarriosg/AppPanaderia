package com.example.evaluacion1;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ViewInventoryActivity extends AppCompatActivity {

    private ListView viewInventoryListView;
    private InventoryAdapter inventoryAdapter;
    private InventoryManager inventoryManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_inventory);

        viewInventoryListView = findViewById(R.id.viewInventoryListView);

        // Obtener instancia de InventoryManager
        inventoryManager = InventoryManager.getInstance();

        // Obtener el inventario actual y mostrarlo en la lista
        ArrayList<Producto> inventoryList = inventoryManager.getInventoryList();

        // Configurar el adaptador personalizado
        inventoryAdapter = new InventoryAdapter(this, inventoryList);
        viewInventoryListView.setAdapter(inventoryAdapter);
    }

}
