/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rntongo.vendingmachine.service;

import com.rntongo.vendingmachine.dao.VendingMachinePersistenceException;
import com.rntongo.vendingmachine.dto.Snack;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author rogersentongo
 */
public interface VendingMachineServiceLayer {
    
    int checkInventory(String snackId) throws VendingMachinePersistenceException;
    List<Snack> getAllSnacks() throws VendingMachinePersistenceException;
    Snack getSnack(String snackId) throws VendingMachinePersistenceException;
    Snack updateSnack(String snackId) throws VendingMachinePersistenceException;
    Snack purchaseSnack(String snackId, BigDecimal userBalance) throws VendingMachinePersistenceException,VendingMachineNoItemInventoryException,VendingMachineInsufficientFundsException;
    BigDecimal checkFunds();
    BigDecimal getChange();
}
