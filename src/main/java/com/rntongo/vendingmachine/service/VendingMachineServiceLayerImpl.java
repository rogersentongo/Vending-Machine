/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rntongo.vendingmachine.service;

import com.rntongo.vendingmachine.dao.VendingMachineAuditDao;
import com.rntongo.vendingmachine.dao.VendingMachineDao;
import com.rntongo.vendingmachine.dao.VendingMachinePersistenceException;
import com.rntongo.vendingmachine.dto.Snack;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author rogersentongo
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {
    
    //member variables
    BigDecimal userAmount;
    BigDecimal change;
    VendingMachineDao dao;
    VendingMachineAuditDao auditDao;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    @Override
    public List<Snack> getAllSnacks() throws VendingMachinePersistenceException {
        return dao.getAllSnacks();
    }

    @Override
    public Snack getSnack(String snackId) throws VendingMachinePersistenceException {
        Snack userChoice = dao.getSnack(snackId);
        
        return userChoice;
    }

    @Override
    public Snack updateSnack(String snackId) throws VendingMachinePersistenceException {
        
        auditDao.writeAuditEntry("Snack called "+dao.getSnack(snackId).getName()+" has been updated");
        return dao.updateSnack(snackId);
        
        
    }

    @Override
    public int checkInventory(String snackId) throws VendingMachinePersistenceException {
        Snack thisOne = dao.getSnack(snackId);
        
        return thisOne.getQuantity();
    }

    @Override
    public Snack purchaseSnack(String snackId, BigDecimal userBalance) throws VendingMachinePersistenceException
            ,VendingMachineNoItemInventoryException, VendingMachineInsufficientFundsException
    {
        //make sure snack data is legit
        validateSnackData(snackId);
        
        
        //Set the userbalance to the amount input
        setUserAmount(userBalance);
        //we create our snack
        Snack userSnack = dao.getSnack(snackId);
        
        int sufficientFunds = this.checkFunds().compareTo(userSnack.getPrice());
        
        
        if(sufficientFunds ==-1)
        {
            throw new VendingMachineInsufficientFundsException("Not enough funds");
        }
        
        //we now know there is sufficient funds and the item exists
        
        
        
        //we update our snack
        userSnack = updateSnack(userSnack.getId());
        
        //we get and set the change
        setChange(calcChange(userBalance, userSnack));
        
        auditDao.writeAuditEntry("Snack called "+ dao.getSnack(snackId).getName()+" has been purchased");
        return userSnack;
        
    }
    
    
    public void validateSnackData(String snackId) throws VendingMachinePersistenceException,
            VendingMachineNoItemInventoryException
    {
        
        Snack requiredSnack = dao.getSnack(snackId);
        if(requiredSnack==null) 
        {
                throw new VendingMachineNoItemInventoryException("Item doesn't exist, enter correct Snack Id");    
         
        }
            
        
    }
    
    @Override
    public BigDecimal checkFunds()  
    {
        return userAmount;
    }

    public BigDecimal getUserAmount() {
        return userAmount;
    }

    public void setUserAmount(BigDecimal userAmount) {
        this.userAmount = userAmount;
    }

    @Override
    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }
    
    public BigDecimal calcChange(BigDecimal userCash, Snack userSnack)
    {
        BigDecimal result = userCash.subtract(userSnack.getPrice());
        return result;
    }
    
    
    
}
