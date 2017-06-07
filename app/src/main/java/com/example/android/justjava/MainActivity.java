package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int quantityValue;
    private String message = "Total: " +
            NumberFormat.getCurrencyInstance().format(this.quantityValue * 2.70);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setQuantity();
        setMessage();
    }


    /**
     * This method is called only when this app is loaded, setting the quantity variable to the
     * one in activity_main.xml
     */
    private void setQuantity() {

        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        CharSequence text = quantityTextView.getText();
        this.quantityValue = charSeqToInt(text);

    }

    /**
     * This method sets the price string.
     */
    private void setMessage() {

        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);

        /*
         * You can use priceTextView.setText(NumberFormat.getCurrencyInstance().format(price)) to
         * display a pricing value with the currency symbol of the phone's language.
         */
        priceTextView.setText(this.message);

    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {


        displayMessage();

    }


    /**
     * This method is called when the + button is pressed.
     */
    public void increment(View view) {

        this.quantityValue++;

        display();

    }


    /**
     * This method is called when the - button is pressed.
     */
    public void decrement(View view) {

        if (this.quantityValue <= 0) {
            this.quantityValue = 0;
        } else {
            this.quantityValue--;
        }

        display();

    }


    /**
     * This method displays quantityValue.
     */
    private void display() {

        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        String value = Integer.toString(this.quantityValue);
        quantityTextView.setText(value);

    }



    private void displayMessage() {

        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);

        this.message = "Total: " +
                NumberFormat.getCurrencyInstance().format(this.quantityValue * 2.70) +
                "\nThank You! :D";

        priceTextView.setText(this.message);

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
     * This method simply brings a value to the power of the exponent
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