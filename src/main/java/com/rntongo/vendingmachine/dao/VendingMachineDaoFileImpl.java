/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rntongo.vendingmachine.dao;

import com.rntongo.vendingmachine.dto.Snack;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author rogersentongo
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {
    
    
    //member variables
    private final String SNACK_FILE;
    private static final String DELIMITER = "::";
    private Map<String, Snack> snacks = new HashMap<>();
    
    
    //constructor creates files
    public VendingMachineDaoFileImpl()
    {
        SNACK_FILE = "snacks.txt";
    }
    
    public VendingMachineDaoFileImpl(String snackTextFile)
    {
        SNACK_FILE = snackTextFile;
    }
    
    //convert text into object
    private Snack unmarshallSnack(String snackAsText) 
    {
        //Get a line from the file and tokenize it 
        String [] snackAsToken = snackAsText.split(DELIMITER);
        
        String snackId = snackAsToken[0];
        
        //Create new snack object
        Snack outputObj = new Snack(snackId);
        //Create BigDecimal
        BigDecimal newbig = new BigDecimal(snackAsToken[3]);
        
        outputObj.setName(snackAsToken[1]);
        outputObj.setQuantity(Integer.parseInt(snackAsToken[2]));
        outputObj.setPrice(newbig);
        
        //stub
        return outputObj;
    }
    
    //convert object into text
    private String marshallSnack(Snack aSnack){
        
        String snackAsText = aSnack.getId()+DELIMITER;
        snackAsText += aSnack.getName()+DELIMITER;
        snackAsText += aSnack.getQuantity()+DELIMITER;
        snackAsText += aSnack.getPrice().toString();
        
        return snackAsText;
        
    }
    
    //load snack objects from file by unmarshalling them from text into objects
    
    private void loadSnacks() throws VendingMachinePersistenceException{
        
        //create scanner object
        Scanner scanner;
        
        try{
            scanner = new Scanner(new BufferedReader(new FileReader(SNACK_FILE)));
            
        }catch(FileNotFoundException e){
            
            throw new VendingMachinePersistenceException(
            "-_- Could not load snack data into memory", e);
        }
        
        //create objects to catch data
        String currentLine;
        Snack aSnack;
        
        while(scanner.hasNextLine())
        {
            currentLine = scanner.nextLine();
            aSnack = unmarshallSnack(currentLine);
            snacks.put(aSnack.getId(), aSnack);
        }
        
        scanner.close();
        
        
    }
    
    //we need to update the file with the snacks and their amounts
    private void writeSnack() throws VendingMachinePersistenceException{
        
        //create writer object
        PrintWriter out;
        
        try{
            
            out = new PrintWriter(new FileWriter(SNACK_FILE));
            
        } catch(IOException e){
            
            throw new VendingMachinePersistenceException(
            "Could not save snack data", e);
        }
        
        //String to store a marshalled snack
        String marshalledSnack;
        
        //Retrieve all the snacks
        List<Snack> allSnacks = this.getAllSnacks();
        
        //Iterate through and marshall the snack objects
        for(Snack currSnack: allSnacks)
        {
            marshalledSnack = marshallSnack(currSnack);
            out.println(marshalledSnack);
            out.flush();
        }
        
        out.close();
        
        
    }
    
    
    
    
    
    
    
    
    

    @Override
    public Snack getSnack(String snackId) throws VendingMachinePersistenceException {
        
        //We load snacks into our object snack collection.
        loadSnacks();
        return snacks.get(snackId);
    }

    @Override
    public List<Snack> getAllSnacks() throws VendingMachinePersistenceException{
        loadSnacks();
        
        return new ArrayList<>(snacks.values());
    }
    
    @Override
    public Snack updateSnack(String snackId)throws VendingMachinePersistenceException
    {
        //Create a new snack object
        Snack newSnack = snacks.get(snackId);
        int value = newSnack.getQuantity()-1;
        newSnack.setQuantity(value);
        snacks.put(snackId, newSnack);
        writeSnack();
        return newSnack;
    }
    
}
