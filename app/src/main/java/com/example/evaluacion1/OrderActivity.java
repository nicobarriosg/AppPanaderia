package com.example.evaluacion1;

import android.annotation.SuppressLint;
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
import java.util.HashSet;
import java.util.Set;

import android.content.SharedPreferences;

public class OrderActivity extends AppCompatActivity {

    private Spinner productSpinner;
    private Spinner providerSpinner;
    private EditText quantityEditText;
    private Button placeOrderButton;
    private Button viewOrdersButton;
    private ListView ordersListView;
    private ArrayAdapter<String> ordersAdapter;
    private ArrayList<String> ordersList;
    private SharedPreferences sharedPreferences;
    private static final String ORDERS_KEY = "ordersList";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        sharedPreferences = getSharedPreferences("com.example.evaluacion1", MODE_PRIVATE);
        productSpinner = findViewById(R.id.productSpinner);
        providerSpinner = findViewById(R.id.providerSpinner);
        quantityEditText = findViewById(R.id.quantityEditText);
        placeOrderButton = findViewById(R.id.placeOrderButton);
        ordersListView = findViewById(R.id.ordersListView);
        viewOrdersButton = findViewById(R.id.viewOrdersButton);

        // Cargar la lista de pedidos desde SharedPreferences
        ordersList = loadOrders();
        ordersAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ordersList);
        ordersListView.setAdapter(ordersAdapter);

        // Configurar Spinner de Productos
        String[] products = {"Pan", "Croissant", "Baguette", "Donut"};
        ArrayAdapter<String> productAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, products);
        productSpinner.setAdapter(productAdapter);

        // Configurar Spinner de Proveedores
        String[] providers = {"Proveedor A", "Proveedor B", "Proveedor C"};
        ArrayAdapter<String> providerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, providers);
        providerSpinner.setAdapter(providerAdapter);

        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedProduct = productSpinner.getSelectedItem().toString();
                String selectedProvider = providerSpinner.getSelectedItem().toString();
                String quantityStr = quantityEditText.getText().toString();

                if (!quantityStr.isEmpty()) {
                    int quantity = Integer.parseInt(quantityStr);

                    // Crear y registrar el pedido
                    String order = "Producto: " + selectedProduct + " | Proveedor: " + selectedProvider + " | Cantidad: " + quantity;
                    ordersList.add(order);
                    ordersAdapter.notifyDataSetChanged();

                    // Guardar la lista de pedidos
                    saveOrders(ordersList);

                    quantityEditText.setText(""); // Limpiar el campo de cantidad

                    Toast.makeText(OrderActivity.this, "Pedido realizado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(OrderActivity.this, "Por favor, ingrese una cantidad", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewOrdersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToViewOrdersActivity();
            }
        });
    }

    private void saveOrders(ArrayList<String> ordersList) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(ORDERS_KEY, new HashSet<>(ordersList));
        editor.apply();
    }

    private ArrayList<String> loadOrders() {
        Set<String> ordersSet = sharedPreferences.getStringSet(ORDERS_KEY, new HashSet<>());
        return new ArrayList<>(ordersSet);
    }

    private void goToViewOrdersActivity() {
        Intent intent = new Intent(OrderActivity.this, ViewOrdersActivity.class);
        intent.putStringArrayListExtra("ordersList", ordersList);
        startActivity(intent);
    }
}
