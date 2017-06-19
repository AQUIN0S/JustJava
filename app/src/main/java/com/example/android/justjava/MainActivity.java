package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.quantity_text_view)
    TextView quantityTextView;
    @BindView(R.id.whipped_cream_check)
    CheckBox whippedCreamCheck;
    @BindView(R.id.chocolate_check)
    CheckBox chocolateCheck;
    @BindView(R.id.name_edit_text_view)
    EditText nameEditTextView;

    private int quantityValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        displayQuantity();

    }


    /**
     * This method is called when the order button is clicked.
     */

    @OnClick(R.id.order_button)
    public void submitOrder() {

        double price = calculatePrice();
        boolean whippedCream = whippedCreamCheck.isChecked();
        boolean chocolate = chocolateCheck.isChecked();
        String name = nameEditTextView.getText().toString();
        String message = createOrderSummary(name, price, whippedCream, chocolate);

        sendEmail(message);

    }


    /**
     * Methods to increment/decrement the quantity TextView
     */

    @OnClick(R.id.increment_button)
    public void increment() {

        if (quantityValue >= 100) {
            Toast.makeText(this, getString(R.string.toast_too_high), Toast.LENGTH_SHORT).show();

            return;
        }

        quantityValue += 1;
        displayQuantity();

    }

    @OnClick(R.id.decrement_button)
    public void decrement() {

        if (quantityValue <= 0) {
            Toast.makeText(this, getString(R.string.toast_too_low), Toast.LENGTH_SHORT).show();
            return;
        }

        quantityValue -= 1;
        displayQuantity();

    }


    /**
     * Method to calculate the price based on quantity
     */

    private double calculatePrice() {

        double basePrice = 2.70;

        if (whippedCreamCheck.isChecked()) {
            basePrice += 1.00;
        }
        if (chocolateCheck.isChecked()) {
            basePrice += 2.00;
        }

        return basePrice * quantityValue;

    }


    /**
     * Methods to display quantity and to create and display the order summary
     */

    private void displayQuantity() {

        String value = Integer.toString(quantityValue);

        quantityTextView.setText(value);

    }

    private String createOrderSummary(String name, double price, boolean whippedCream, boolean chocolate) {

        String message = getString(R.string.order_summary_name, name);
        message += "\n" + getString(R.string.order_summary_whipped_cream);
        if (whippedCream) {
            message += getString(R.string.yes);
        } else {
            message += getString(R.string.no);
        }
        message += "\n" + getString(R.string.order_summary_chocolate);
        if (chocolate) {
            message += getString(R.string.yes);
        } else {
            message += getString(R.string.no);
        }
        message += "\n" + getString(R.string.order_summary_quantity, quantityValue);
        message += "\n" + getString(R.string.order_summary_price, NumberFormat.getCurrencyInstance().format(price));
        message += "\n" + getString(R.string.gratitude);

        return message;

    }

    private void sendEmail(String message) {

        String subject = "Coffee Order";
        Intent intent = new Intent(Intent.ACTION_SENDTO);

        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

}