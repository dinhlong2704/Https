package com.example.http.ui.theme;

import static androidx.fragment.app.FragmentManager.TAG;
import static com.example.http.ui.theme.util.Constants.BASE_URL;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.http.databinding.LoginActivityBinding;
import com.example.http.databinding.MainActivityBinding;
import com.example.http.ui.theme.api.API;
import com.example.http.ui.theme.api.res.AuthenRes;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private LoginActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = LoginActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private void initView() {
        binding.btnLogin.setOnClickListener(v -> getAuthen());
    }

    void getAuthen() {
        // tạo retrofit
        Retrofit rs = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().callTimeout(15, TimeUnit.SECONDS).build())
                .build();
        // Chỏ đến class API
        API api = rs.create(API.class);
        // call api có tên là getAuthen và phải call trên enqueue

        /*

        ✅ enqueue(): Gọi bất đồng bộ (asynchronous)
        Gọi API trên luồng nền (background thread).
        Không chặn UI thread.
        Kết quả trả về thông qua callback (onResponse() hoặc onFailure()).
        Dùng phổ biến trong ứng dụng Android để không làm treo UI.


        ✅ execute(): Gọi đồng bộ (synchronous)
        Gọi API trên cùng thread hiện tại (thường là main thread nếu không dùng thread riêng).
        Chặn luồng cho đến khi có kết quả (blocking call).
        Không dùng được trong UI thread nếu không tạo thread riêng (sẽ gây lỗi NetworkOnMainThreadException trong Android).

        */
        api.getAuthen().enqueue(new Callback<AuthenRes>() {
            @Override
            public void onResponse(Call<AuthenRes> call, Response<AuthenRes> response) {
                if (response.code() == 200 || response.code() == 201) {
                    handleSuccess(response.body());
                } else {
                    handleFailure(response);
                }
            }

            @Override
            public void onFailure(Call<AuthenRes> call, Throwable t) {
                Log.e(TAG, "handleSuccess: " + t.getMessage());
            }
        });
    }

    private void handleFailure(Response<AuthenRes> response) {
        Log.e(TAG, "handleSuccess: " + response.code());
        Log.e(TAG, "handleSuccess: " + response.errorBody());

    }

    private void handleSuccess(AuthenRes body) {
        Log.i(TAG, "handleSuccess: " + body);
    }
}
