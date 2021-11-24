package com.example.donationappv2;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Donations implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int id;

    int paymentMethod; // 1 for credit and 2 for paypal
    double amount;

    @Ignore
    int[] sharingApps;

    public Donations() {}

    public Donations(int id, int paymentMethod, double amount, int[] sharingApps) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.sharingApps = sharingApps;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int PaymentMethod) {
        this.paymentMethod = PaymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double Amount) {
        this.amount = Amount;
    }

}
