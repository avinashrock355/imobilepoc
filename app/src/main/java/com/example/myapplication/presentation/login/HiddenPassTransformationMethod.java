package com.example.myapplication.presentation.login;

import android.graphics.Rect;
import android.text.method.TransformationMethod;
import android.view.View;

public class HiddenPassTransformationMethod implements TransformationMethod {

    private char DOT = '\u2022';

    @Override
    public CharSequence getTransformation(final CharSequence charSequence, final View view) {
        return new PassCharSequence(charSequence);
    }

    @Override
    public void onFocusChanged(View view, CharSequence sourceText, boolean focused, int direction, Rect previouslyFocusedRect) {

    }

    private class PassCharSequence implements CharSequence {

        private final CharSequence charSequence;

        public PassCharSequence(final CharSequence charSequence) {
            this.charSequence = charSequence;
        }

        @Override
        public char charAt(final int index) {
            return DOT;
        }

        @Override
        public int length() {
            return charSequence.length();
        }

        @Override
        public CharSequence subSequence(final int start, final int end) {
            return new PassCharSequence(charSequence.subSequence(start, end));
        }
    }
}
