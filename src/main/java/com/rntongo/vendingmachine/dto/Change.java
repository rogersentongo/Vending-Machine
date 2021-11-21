/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rntongo.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 *
 * @author rogersentongo
 */
public class Change {
    
    //member variables
    BigDecimal change;
    int quarters;
    int dimes;
    int nickels;
    int pennies;

    
    
    
    
    public Change(BigDecimal change){
        this.change = change.setScale(2, RoundingMode.HALF_UP);
    }

    //calculate coins
    public void calcCoins()
    {
        
        //convert Change to cents
        BigDecimal cents = change.multiply(new BigDecimal("100").setScale(2, RoundingMode.HALF_UP));
        
        //Convert to int
        int coins = cents.intValue();
        
        int qtrs = coins / 25;
        setQuarters(qtrs);
        int remainder = coins %25;
        int dms = remainder /10;
        setDimes(dms);
        remainder = remainder %10;
        int nckls = remainder /5;
        setNickels(nckls);
        remainder = remainder %5;
        int ps = remainder/1;
        setPennies(ps);
      
    }

    public void setQuarters(int quarters) {
        this.quarters = quarters;
    }

    public void setDimes(int dimes) {
        this.dimes = dimes;
    }

    public void setNickels(int nickels) {
        this.nickels = nickels;
    }

    public void setPennies(int pennies) {
        this.pennies = pennies;
    }


    public int getQuarters() {
        
        return quarters;
    }

    public int getDimes() {
        
        return dimes;
    }

    public int getNickels() {
        
        return nickels;
    }

    public int getPennies() {
        
        return pennies;
    }
    
    
    
    
}
