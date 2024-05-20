package com.idnp2024a.loginsample;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Obtener los TextViews
        TextView txtBienvenido = findViewById(R.id.txtBienvenido);
        TextView txtNombre = findViewById(R.id.txtNombre);
        TextView txtCorreo = findViewById(R.id.txtCorreo);
        TextView txtNumero = findViewById(R.id.txtNumero);

        // Extraer los datos del Intent
        String accountEntityString = getIntent().getStringExtra("ACCOUNT");
        if (accountEntityString != null) {
            Gson gson = new Gson();
            AccountEntity accountEntity = gson.fromJson(accountEntityString, AccountEntity.class);

            // Mostrar los datos en los TextViews
            txtBienvenido.setText("Bienvenido, " + accountEntity.getUsername());
            txtNombre.setText("Nombre: " + accountEntity.getFirstname() + " " + accountEntity.getLastname());
            txtCorreo.setText("Correo: " + accountEntity.getEmail());
            txtNumero.setText("Número: " + accountEntity.getPhone());
        } else {
            Log.d(TAG, "No se recibieron datos de la cuenta.");
        }
    }
}
