package com.example.background.workers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.background.R;

public class BlurWorker extends Worker {

    private static final String TAG ="BLURWORKER";

    public BlurWorker(
            @NonNull Context appContext,
            @NonNull WorkerParameters workerParams) {
        super(appContext, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        // 1
        Context applicationContext = getApplicationContext();

        // 7(a)
        try {
            // 2
            Bitmap picture = BitmapFactory.decodeResource(
                    applicationContext.getResources(),
                    R.drawable.android_cupcake);

            // 3
            Bitmap output = WorkerUtils.blurBitmap(picture, applicationContext);

            // 4
            Uri outputUri = WorkerUtils.writeBitmapToFile(applicationContext, output);

            // 5
            WorkerUtils.makeStatusNotification(outputUri.toString(), applicationContext);

            // 6
            return Result.success();

        // 7(b)
        } catch (Throwable throwable) {

            // 8
            Log.e(TAG, "Error applying blur", throwable);

            // 9
            return Result.failure()
        }

    }
}
