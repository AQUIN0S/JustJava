package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.TextView;
import java.text.NumberFormat;
import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int quantityValue = 0;

    @BindView(R.id.quantity_text_view) TextView quantityTextView;
    @BindView(R.id.order_summary_text_view) TextView orderSummaryTextView;
    @BindView(R.id.whipped_cream_check) CheckBox whippedCreamCheck;
    @BindView(R.id.chocolate_check) CheckBox chocolateCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    /**
     * This method is called when the order button is clicked.
     */

    @OnClick(R.id.order_button)
    public void submitOrder() {

        double price = calculatePrice();
        boolean whippedCream = whippedCreamCheck.isChecked();
        boolean chocolate = chocolateCheck.isChecked();
        String message = createOrderSummary(price, whippedCream, chocolate);
        displayMessage(message);

    }


    /**
     * Methods to increment/decrement the quantity TextView
     */

    @OnClick(R.id.increment_button)
    public void increment() {

        this.quantityValue += 1;
        displayQuantity(this.quantityValue);

    }

    @OnClick(R.id.decrement_button)
    public void decrement() {

        if (this.quantityValue <= 0) {
            this.quantityValue = 0;
        } else {
            this.quantityValue -= 1;
        }
        displayQuantity(this.quantityValue);

    }


    /**
     * Method to calculate the price based on quantity
     */

    private double calculatePrice() {

        return this.quantityValue * 2.70;

    }


    /**
     * Methods to display quantity and to create and display the order summary
     */

    private void displayQuantity(int quantity) {

        String value = Integer.toString(quantity);
        this.quantityTextView.setText(value);

    }

    private String createOrderSummary(double price, boolean whippedCream, boolean chocolate) {


        String message = "Name: Daniel Schimanski";
        message += "\nAdd whipped cream? " + whippedCream;
        message += "\nAdd chocolate? " + chocolate;
        message += "\nQuantity: " + this.quantityValue;
        message += "\nTotal: " + NumberFormat.getCurrencyInstance().format(price);
        message += "\nThank you!";
        return message;

    }

    private void displayMessage(String message) {

        this.orderSummaryTextView.setText(message);

    }

}