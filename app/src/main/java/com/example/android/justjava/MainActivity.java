package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        incrementOrder();
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void incrementOrder() {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        CharSequence text = quantityTextView.getText();
        int value = charSeqToInt(text) + 1;
        String incrementedValue = Integer.toString(value);
        quantityTextView.setText(incrementedValue);
    }

    /**
     * This method converts a CharSequence that represents an integer to an integer.
     */
    private int charSeqToInt(CharSequence sequence) {
        int convertedInt = 0;

        for (int i = 1; i <= sequence.length(); i++) {
            int position = sequence.length() - i;

            if (Character.getNumericValue(sequence.charAt(position)) < 0 ||
                    Character.getNumericValue(sequence.charAt(position)) > 9) {
                return -1;
            } else {
                convertedInt += Character.getNumericValue(sequence.charAt(position)) * powerOf(10, i - 1);
            }

        }

        return convertedInt;
    }

    /**
     * This method simply brings the value to the power of the exponent
     */
    private int powerOf(int value, int exponential) {
        int temp = value;

        if (exponential < 0) {
            return -1;
        } else if (exponential == 0) {
            return 1;
        } else {

            for (int i = 1; i < exponential; i++) {
                temp *= value;
            }

            return temp;
        }

    }
}