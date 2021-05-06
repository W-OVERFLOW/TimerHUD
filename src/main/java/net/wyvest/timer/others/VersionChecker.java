package net.wyvest.timer.others;

import com.google.gson.Gson;
import net.wyvest.lib.util.Multithreading;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;
/**
 * @author Pinkulu
 */

public class VersionChecker {
    public static String version;

    public static void getVersion() {
        OkHttpClient client = new OkHttpClient();
        Multithreading.runAsync(() -> {
            Request request = new Request.Builder()
                    .url("https://wyvest.net/timerhud.json")
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                        assert response.body() != null;
                        String myRespones = Objects.requireNonNull(response.body()).string();
                        if (!myRespones.contains("error")) {
                            JsonResponse Jresponse = new Gson().fromJson(myRespones, JsonResponse.class);
                            version = Jresponse.version;
                        }
                    }
                }
            });
        });
    }
}

