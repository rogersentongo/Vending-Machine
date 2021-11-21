/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rntongo.vendingmachine.dao;

import com.rntongo.vendingmachine.dto.Snack;
import java.util.List;

/**
 *
 * @author rogersentongo
 */
public interface VendingMachineDao {
    
    Snack getSnack(String snackId) throws VendingMachinePersistenceException;
    List<Snack> getAllSnacks() throws VendingMachinePersistenceException;
    Snack updateSnack(String snackId) throws VendingMachinePersistenceException;
    
    
    
}
