/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author bepis
 */
public class Customer {

    public boolean newCustomer;
    public boolean loyaltyCard;
    public boolean coupon;

    public Customer(boolean newCustomer, boolean loyaltyCard, boolean coupon) {
        this.newCustomer = newCustomer;
        this.loyaltyCard = loyaltyCard;
        this.coupon = coupon;
    }

    public boolean isNewCustomer() {
        return newCustomer;
    }

    public void setNewCustomer(boolean newCustomer) {
        this.newCustomer = newCustomer;
    }

    public boolean isLoyaltyCard() {
        return loyaltyCard;
    }

    public void setLoyaltyCard(boolean loyaltyCard) {
        this.loyaltyCard = loyaltyCard;
    }

    public boolean isCoupon() {
        return coupon;
    }

    public void setCoupon(boolean coupon) {
        this.coupon = coupon;
    }

}
