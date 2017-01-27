package com.example.viktor.examplepropertyanimation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public final class PercentPositionUtil {
    public static final float INVALID_VALUE = -1f;

    public static float[] getPercentFromPositionInsideDisplay(@NonNull Context context,
                                                              @NonNull View view,
                                                              @NonNull Pivot pivot) {
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        float viewX;
        float viewY;

        switch (pivot) {
            case LEFT_TOP:
                viewX = view.getX();
                viewY = view.getY();
                break;
            case CENTER:
                viewX = view.getX() + view.getWidth() / 2;
                viewY = view.getY() + view.getHeight() / 2;
                break;
            default:
                viewX = 0f;
                viewY = 0f;
                break;
        }

        float xPercent = viewX / displayMetrics.widthPixels;
        float yPercent = viewY / displayMetrics.heightPixels;

        return new float[]{xPercent, yPercent};
    }

    public static float[] getPositionFromPercentInsideDisplay(@NonNull Context context,
                                                              @NonNull View view,
                                                              @NonNull Pivot pivot,
                                                              float[] newPercentPosition) {
        if (newPercentPosition[0] < 0f || newPercentPosition[0] > 1f
                || newPercentPosition[1] < 0f || newPercentPosition[1] > 1f) {
            return new float[]{INVALID_VALUE, INVALID_VALUE};
        }

        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        float newX;
        float newY;

        switch (pivot) {
            case LEFT_TOP:
                newX = newPercentPosition[0] / 1f * displayMetrics.widthPixels;
                newY = newPercentPosition[1] / 1f * displayMetrics.heightPixels;
                break;
            case CENTER:
                newX = (newPercentPosition[0] / 1f * displayMetrics.widthPixels) -
                        (view.getWidth() / 2);
                newY = (newPercentPosition[1] / 1f * displayMetrics.heightPixels) -
                        (view.getHeight() / 2);
                break;
            default:
                newX = 0f;
                newY = 0f;
                break;
        }

        return new float[]{newX, newY};
    }

    public static float[] getPercentFromPositionInsideViewGroup(@NonNull ViewGroup viewGroup,
                                                                @NonNull View view,
                                                                @NonNull Pivot pivot) {
        float viewX;
        float viewY;

        switch (pivot) {
            case LEFT_TOP:
                viewX = view.getX() - viewGroup.getX();
                viewY = view.getY() - viewGroup.getY();
                break;
            case CENTER:
                viewX = (view.getX() + view.getWidth() / 2) - viewGroup.getX();
                viewY = (view.getY() + view.getHeight() / 2) - viewGroup.getY();
                break;
            default:
                viewX = 0f;
                viewY = 0f;
                break;
        }

        float xPercent = viewX / viewGroup.getWidth();
        float yPercent = viewY / viewGroup.getHeight();

        return new float[]{xPercent, yPercent};
    }

    public static float[] getPositionFromPercentInsideViewGroup(@NonNull ViewGroup viewGroup,
                                                                @NonNull View view,
                                                                @NonNull Pivot pivot,
                                                                float[] newPercentPosition) {
        if (newPercentPosition[0] < 0f || newPercentPosition[0] > 1f
                || newPercentPosition[1] < 0f || newPercentPosition[1] > 1f) {
            return new float[]{INVALID_VALUE, INVALID_VALUE};
        }

        float newX;
        float newY;

        switch (pivot) {
            case LEFT_TOP:
                newX = (newPercentPosition[0] / 1f * viewGroup.getWidth()) + viewGroup.getX();
                newY = (newPercentPosition[1] / 1f * viewGroup.getHeight()) + viewGroup.getY();
                break;
            case CENTER:
                float viewHalfWidth = view.getWidth() / 2;
                newX = (newPercentPosition[0] / 1f * viewGroup.getWidth()) +
                        viewGroup.getX() - viewHalfWidth;
                float viewHalfHeight = view.getHeight() / 2;
                newY = (newPercentPosition[1] / 1f * viewGroup.getHeight()) +
                        viewGroup.getY() - viewHalfHeight;
                break;
            default:
                newX = 0f;
                newY = 0f;
                break;
        }

        return new float[]{newX, newY};
    }

    public enum Pivot {
        LEFT_TOP,
        CENTER
    }
}
