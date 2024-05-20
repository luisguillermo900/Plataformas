package com.idnp2024a.loginsample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class AccountActivity extends AppCompatActivity {
    public final static String ACCOUNT_RECORD = "ACCOUNT_RECORD";
    public final static Integer ACCOUNT_ACEPTAR = 100;
    public final static Integer ACCOUNT_CANCEL = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Button btnAceptar = findViewById(R.id.btnOK);
        Button btnCancelar = findViewById(R.id.btnCancel);
        EditText edtFirstname = findViewById(R.id.edtFirstname);
        EditText edtLastname = findViewById(R.id.edtLastname);
        EditText edtEmail = findViewById(R.id.edtEmail);
        EditText edtPhone = findViewById(R.id.edtPhone);
        EditText edtUsername2 = findViewById(R.id.edtUsername2);
        EditText edtPassword2 = findViewById(R.id.edtPassword2);

        btnAceptar.setOnClickListener(v -> {
            String firstname = edtFirstname.getText().toString();
            String lastname = edtLastname.getText().toString();
            String email = edtEmail.getText().toString();
            String phone = edtPhone.getText().toString();
            String username = edtUsername2.getText().toString();
            String password = edtPassword2.getText().toString();

            // Validación de campos
            if (firstname.isEmpty() || lastname.isEmpty() || email.isEmpty() ||
                    phone.isEmpty() || username.isEmpty() || password.isEmpty()) {
                // Mostrar mensaje de error
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                return;
            }

            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setFirstname(firstname);
            accountEntity.setLastname(lastname);
            accountEntity.setEmail(email);
            accountEntity.setPhone(phone);
            accountEntity.setUsername(username);
            accountEntity.setPassword(password);

            Gson gson = new Gson();
            String accountJson = gson.toJson(accountEntity);
            Intent data = new Intent();
            data.putExtra(ACCOUNT_RECORD, accountJson);

            setResult(ACCOUNT_ACEPTAR, data);
            finish();
        });

        btnCancelar.setOnClickListener(v -> {
            setResult(ACCOUNT_CANCEL);
            finish();
        });
    }
}
