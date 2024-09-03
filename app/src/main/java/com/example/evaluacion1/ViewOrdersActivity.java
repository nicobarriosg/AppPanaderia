package com.example.evaluacion1;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import android.content.SharedPreferences;

public class ViewOrdersActivity extends AppCompatActivity {

    private ListView ordersListView;
    private ArrayAdapter<String> ordersAdapter;
    private ArrayList<String> ordersList;
    private SharedPreferences sharedPreferences;
    private static final String ORDERS_KEY = "ordersList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);

        ordersListView = findViewById(R.id.ordersListView);
        sharedPreferences = getSharedPreferences("com.example.evaluacion1", MODE_PRIVATE);

        // Cargar la lista de pedidos desde SharedPreferences
        ordersList = loadOrders();

        // Configurar el ArrayAdapter para mostrar los pedidos en el ListView
        ordersAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ordersList);
        ordersListView.setAdapter(ordersAdapter);
    }

    private ArrayList<String> loadOrders() {
        Set<String> ordersSet = sharedPreferences.getStringSet(ORDERS_KEY, new HashSet<>());
        return new ArrayList<>(ordersSet);
    }
}

