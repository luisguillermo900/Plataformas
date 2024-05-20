package com.idnp2024a.loginsample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.idnp2024a.loginsample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private AccountEntity accountEntity;
    private String accountEntityString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        EditText edtUsername = binding.edtUsername;
        EditText edtPassword = binding.edtPassword;
        Button btnLogin = binding.btnLogin;
        Button btnAddAccount = binding.btnAddAccount;

        btnLogin.setOnClickListener(v -> {
            if (edtUsername.getText().toString().equals("admin") && edtPassword.getText().toString().equals("admin")) {
                Toast.makeText(getApplicationContext(), "Bienvenido a mi App", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Bienvenido a mi App");

                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("ACCOUNT", accountEntityString);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Error en la autenticacion", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Error en la autenticacion");
            }
        });

        btnAddAccount.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
            activityResultLauncher.launch(intent);
        });

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                activityResult -> {
                    Integer resultCode = activityResult.getResultCode(); // Recuperar el valor
                    if (resultCode == AccountActivity.ACCOUNT_ACEPTAR) {
                        Intent data = activityResult.getData();
                        accountEntityString = data.getStringExtra(AccountActivity.ACCOUNT_RECORD);
                        Gson gson = new Gson();
                        accountEntity = gson.fromJson(accountEntityString, AccountEntity.class);
                        String firstname = accountEntity.getFirstname();
                        Toast.makeText(getApplicationContext(), "Nombre: " + firstname, Toast.LENGTH_SHORT).show();
                    } else if (resultCode == AccountActivity.ACCOUNT_CANCEL) {
                        Toast.makeText(getApplicationContext(), "Cancelado", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}
