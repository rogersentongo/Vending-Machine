/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rntongo.vendingmachine.controller;

import com.rntongo.vendingmachine.dao.VendingMachinePersistenceException;
import com.rntongo.vendingmachine.dto.Snack;
import com.rntongo.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.rntongo.vendingmachine.service.VendingMachineNoItemInventoryException;
import com.rntongo.vendingmachine.service.VendingMachineServiceLayer;
import com.rntongo.vendingmachine.view.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author rogersentongo
 */
public class VendingMachineController {
    
    //member variables
    private VendingMachineServiceLayer service;
    private VendingMachineView view;

    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }
    
    public void run() throws VendingMachineNoItemInventoryException
    {
        boolean keepGoing = true;
        int menuSelection = 0;
        
        try{
            while(keepGoing)
            {
                menuSelection = getMenuSelection();
                
                switch(menuSelection)
                {
                    case 1:
                        listSnacks();
                        break;
                    case 2:
                        purchaseSnack();
                        break;
                    case 3:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                    
                    
                    
                }
                
            }
            
            
            exitMessage();
            
        } catch (VendingMachinePersistenceException e){
            view.displayErrorMessage(e.getMessage());
        }
        
    }
    
    public int getMenuSelection()
    {
        return view.printMenuAndGetSelection();
    }
    
    public void listSnacks() throws VendingMachinePersistenceException
    {
        view.displayAllSnackbanner();
        List<Snack> snackList = service.getAllSnacks();
        view.displaySnackList(snackList);
    }
    
    public void getSnack() throws VendingMachinePersistenceException
    {
        String snackId = view.getSnackIdChoice();
        view.displaySnackbanner();
        Snack userSnack = service.getSnack(snackId);
        view.displaySnack(userSnack);
        System.out.println("Finished");
        
    }
    
    public void purchaseSnack() throws VendingMachinePersistenceException
    {
        //get snack id choice
        String snackId = view.getSnackIdChoice();
        //Get money from user
        BigDecimal userAmount = view.getMoney();
        
        boolean hasErrors = false;
        
        do{
            
            try{
                Snack purchasedSnack = service.purchaseSnack(snackId, userAmount);
                view.displayPurchase(purchasedSnack);
                BigDecimal change = service.getChange();
                view.displayChange(change);
                view.displayCoins(change);
        
        

            } catch (VendingMachineNoItemInventoryException | VendingMachineInsufficientFundsException e){
                view.displayErrorMessage(e.getMessage());
            } catch (Exception e)
            {
                hasErrors = true;
                view.displayErrorMessage("Something else happened");
            }
        } while (hasErrors);
        
        
        
        
        
        
    }
    
    
    
    public void unknownCommand()
    {
        view.displayUnknownCommandBanner();
    }
    
    public void exitMessage()
    {
        view.displayExitBanner();
    }
    
    
    
    
}
