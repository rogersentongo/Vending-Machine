/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rntongo.vendingmachine.service;

/**
 *
 * @author rogersentongo
 */
public class VendingMachineNoItemInventoryException extends Exception {
    
    VendingMachineNoItemInventoryException(String message)
    {
        super(message);
    }
    
    VendingMachineNoItemInventoryException(String message, Throwable cause)
    {
        super(message, cause);
    }
    
}
