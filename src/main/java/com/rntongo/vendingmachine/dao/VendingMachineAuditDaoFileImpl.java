/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rntongo.vendingmachine.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author rogersentongo
 */
public class VendingMachineAuditDaoFileImpl implements VendingMachineAuditDao {

    //member variable
    private static final String AUDIT_FILE = "audit.txt";
    
    @Override
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException {
        
        PrintWriter out;
        
        try{
            out = new PrintWriter(new FileWriter(AUDIT_FILE));
            
        } catch (IOException ex){
            throw new VendingMachinePersistenceException("Cannot persist to file", ex);
            
        }
        
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString()+" : "+ entry);
        out.flush();
        
    }
    
}
