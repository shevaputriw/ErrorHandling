package id.putraprima.retrofit.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import id.putraprima.retrofit.R;
import id.putraprima.retrofit.api.helper.ServiceGenerator;
import id.putraprima.retrofit.api.models.ApiError;
import id.putraprima.retrofit.api.models.ErrorUtils;
import id.putraprima.retrofit.api.models.LoginRequest;
import id.putraprima.retrofit.api.models.LoginResponse;
import id.putraprima.retrofit.api.services.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView name,version;
    EditText email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        name = findViewById(R.id.mainTxtAppName);
        version = findViewById(R.id.mainTxtAppVersion);
        email = findViewById(R.id.edtEmail);
        password = findViewById(R.id.edtPassword);

        name.setText(extras.getString("APP_KEY"));
        version.setText(extras.getString("VER_KEY"));
    }

    public void login() {
        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<LoginResponse> call = service.login(new LoginRequest(email.getText().toString(), password.getText().toString()));
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, response.body().getToken(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    intent.putExtra("TOKEN",response.body().token);
                    intent.putExtra("TOKEN_TYPE",response.body().token_type);
                    startActivity(intent);
                }
                else {
                    ApiError error = ErrorUtils.parseError(response);
                    if(error.getError().getEmail()!=null && error.getError().getPassword()!=null) {
                        Toast.makeText(MainActivity.this, error.getError().getEmail().get(0) + " and " + error.getError().getPassword().get(0), Toast.LENGTH_SHORT).show();
                    }
                    else if(error.getError().getEmail()!=null) {
                        Toast.makeText(MainActivity.this, error.getError().getEmail().get(0), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, error.getError().getPassword().get(0), Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal Koneksi Ke Server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void handleLogin(View view) {
        login();
    }

    public void registerMain(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
