/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking;

import dto.Customer;

/**
 *
 * @author bepis
 */
public class InterestCalc {

    public InterestCalc() {
    }
    
    public double calculateDiscount(Customer cus) {
        
        double discount = 0.0;
        
        if (cus.isCoupon()) {
            discount = discount + 20.0;
        }
        if (cus.isLoyaltyCard()) {
            discount = discount + 10.0;
           
        }
        if (cus.isNewCustomer()) {
            discount = discount + 15.0;
        }
        
        return discount;
    }
    
    public double calculateInterest(double balance) {
        
        
        if (balance < 0.0) {
            return balance * 0.0;
        } else if (balance >= 1000.0) {
            return balance * 0.07;
        } else if (balance >= 0 && balance <= 100.0) {
            return balance * 0.03;
        } else if (balance >= 100.01 && balance <= 999.99){
            return balance * 0.05;
        }
        
        return 0.0;
    }
    
}
