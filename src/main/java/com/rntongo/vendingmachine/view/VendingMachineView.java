/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rntongo.vendingmachine.view;

import com.rntongo.vendingmachine.dto.Change;
import com.rntongo.vendingmachine.dto.Snack;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 *
 * @author rogersentongo
 */
public class VendingMachineView {
    //private members
    private UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }
    
    public int printMenuAndGetSelection()
    {
        io.print("Main menu");
        io.print("1. List Snacks");
        io.print("2. Purchase Snack");
        io.print("3. Exit");
        
        int userChoice=0;
        
        try{
        
            userChoice = io.readInt("Please select from the above choices", 1,3);
        } catch(NumberFormatException e)
        {
            System.err.println("Please input correct format, enter or empty string is not it");
        }
        
        
        
        
        return userChoice;
    }
    
    public String getSnackIdChoice()
    {
        return io.readString("Please enter the snack id of your choice");
    }
    
    public BigDecimal getMoney()
    {
        BigDecimal amount = new BigDecimal("0");
        try{
            amount = new BigDecimal(io.readString("Please enter your money"));
            return amount;
        }catch (NumberFormatException e)
        {
            System.err.println("Please input a number not letters or alphanumeric types");
        }
            
        
        return amount;
    }
    
    public void displaySnack(Snack userChoice)
    {
        String snackInfo = String.format(
                    "%s : %s: %s: %s",
                    userChoice.getId(),
                    userChoice.getName(),
                    userChoice.getPrice(),
                    userChoice.getQuantity()
            );
        
        io.print(snackInfo);        
    }
    
    public void displaySnackList(List<Snack> snackList)
    {
        for(Snack currSnack: snackList)
        {
            String snackInfo = String.format(
                    "%s : %s: %s: %s",
                    currSnack.getId(),
                    currSnack.getName(),
                    currSnack.getPrice(),
                    currSnack.getQuantity()
            );
            
            io.print(snackInfo);
        }
        
        io.readString("Please hit enter to continue");
        
    }
    
    public void displayPurchase(Snack purchasedSnack)
    {
        io.print("You have purchased a "+purchasedSnack.getName());
    }
    
    public void displayChange(BigDecimal change){
        
        io.print("Your change is $"+change.setScale(2, RoundingMode.HALF_UP).toString());
    }
    
    public void displayCoins(BigDecimal change)
    {
        io.print("Your change in coins is:");
        
        Change coins = new Change(change);
        //Calculate coins
        coins.calcCoins();
        io.print("Quarters: $"+coins.getQuarters());
        io.print("Dimes: $"+ coins.getDimes());
        io.print("Nickels: $"+ coins.getNickels());
        io.print("Pennies: $"+ coins.getPennies());
        
    }
    public void displayAllSnackbanner()
    {
        io.print("===Displaying All Snacks===");
    }
    
    public void displaySnackbanner()
    {
        io.print("===Displaying Snack===");
    }
    
    public void displayExitBanner()
    {
        io.print("Thank you for your purchase, Good Bye!");
    }
    
    public void displayUnknownCommandBanner()
    {
        io.print("Unknown Command!!!");
    }
    
    public void displayErrorMessage(String errorMsg)
    {
        io.print("===ERROR===");
        io.print(errorMsg);
    }
    
    
    
    
}
