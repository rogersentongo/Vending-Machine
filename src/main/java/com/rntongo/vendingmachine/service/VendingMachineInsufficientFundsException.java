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
public class VendingMachineInsufficientFundsException extends Exception {
    
    VendingMachineInsufficientFundsException(String message)
    {
        super(message);
    }
    
    VendingMachineInsufficientFundsException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
