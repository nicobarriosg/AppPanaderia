package com.example.evaluacion1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class InventoryActivity extends AppCompatActivity {

    private Spinner productSpinner;
    private EditText quantityEditText;
    private Button addButton;
    private ListView currentAdditionsListView;
    private ArrayAdapter<String> currentAdditionsAdapter;
    private ArrayList<String> currentAdditionsList;

    private InventoryManager inventoryManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        productSpinner = findViewById(R.id.productSpinner);
        quantityEditText = findViewById(R.id.quantityEditText);
        addButton = findViewById(R.id.addButton);
        currentAdditionsListView = findViewById(R.id.inventoryListView);

        inventoryManager = InventoryManager.getInstance();
        currentAdditionsList = new ArrayList<>();
        currentAdditionsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, currentAdditionsList);
        currentAdditionsListView.setAdapter(currentAdditionsAdapter);

        String[] products = {"Pan", "Croissant", "Baguette", "Donut"};
        ArrayAdapter<String> productAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, products);
        productSpinner.setAdapter(productAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedProduct = productSpinner.getSelectedItem().toString();
                String quantityStr = quantityEditText.getText().toString();

                if (!quantityStr.isEmpty()) {
                    int quantity = Integer.parseInt(quantityStr);
                    inventoryManager.addToInventory(selectedProduct, quantity);

                    String addition = selectedProduct + " - Cantidad: " + quantity;
                    currentAdditionsList.add(addition);
                    currentAdditionsAdapter.notifyDataSetChanged();

                    quantityEditText.setText(""); // Limpiar el campo de cantidad
                    Toast.makeText(InventoryActivity.this, "Producto agregado al inventario", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(InventoryActivity.this, "Por favor, ingrese una cantidad", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Añadir botón para ir a la vista del inventario
        Button viewInventoryButton = findViewById(R.id.viewInventoryButton);
        viewInventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryActivity.this, ViewInventoryActivity.class);
                startActivity(intent);
            }
        });
    }
}
