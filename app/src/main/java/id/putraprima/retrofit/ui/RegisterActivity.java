package id.putraprima.retrofit.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import id.putraprima.retrofit.R;
import id.putraprima.retrofit.api.helper.ServiceGenerator;
import id.putraprima.retrofit.api.models.RegisterRequest;
import id.putraprima.retrofit.api.models.RegisterResponse;
import id.putraprima.retrofit.api.services.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText name, email, password, password_confirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        password_confirmation = findViewById(R.id.password_confirmation);
    }

    private void register() {
        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<RegisterResponse> call = service.register(new RegisterRequest(name.getText().toString(), email.getText().toString(), password.getText().toString(), password_confirmation.getText().toString()));
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.body() != null) {
                    Toast.makeText(RegisterActivity.this, "Akun Berhasil Dibuat, Silahkan login :)", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Gagal Koneksi Ke Server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void handleRegister(View view) {
        if(password.length() < 8){
            Toast.makeText(this, "Password Mininal 8 Karakter", Toast.LENGTH_SHORT).show();
        }
        else {
            register();
        }
    }
}
