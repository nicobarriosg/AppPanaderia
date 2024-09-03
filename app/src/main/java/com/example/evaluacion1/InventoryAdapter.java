package com.example.evaluacion1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class InventoryAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Producto> inventoryList;
    private LayoutInflater inflater;

    public InventoryAdapter(Context context, ArrayList<Producto> inventoryList) {
        this.context = context;
        this.inventoryList = inventoryList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return inventoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return inventoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_inventory, parent, false);
        }

        ImageView productImage = convertView.findViewById(R.id.productImage);
        TextView productName = convertView.findViewById(R.id.productName);
        TextView productQuantity = convertView.findViewById(R.id.productQuantity);
        TextView productBarcode = convertView.findViewById(R.id.productBarcode);

        Producto product = inventoryList.get(position);

        // Setear la imagen, nombre, existencias y código de barras
        productImage.setImageResource(product.getImageResId()); // Asume que el ID de la imagen está en el producto
        productName.setText(product.getName());
        productQuantity.setText("Existencias: " + product.getQuantity());
        productBarcode.setText("Código de barras: " + product.getBarcode());

        return convertView;
    }
}
