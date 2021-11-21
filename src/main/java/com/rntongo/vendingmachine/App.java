/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rntongo.vendingmachine;

import com.rntongo.vendingmachine.controller.VendingMachineController;
import com.rntongo.vendingmachine.dao.VendingMachineAuditDao;
import com.rntongo.vendingmachine.dao.VendingMachineAuditDaoFileImpl;
import com.rntongo.vendingmachine.dao.VendingMachineDao;
import com.rntongo.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.rntongo.vendingmachine.service.VendingMachineNoItemInventoryException;
import com.rntongo.vendingmachine.service.VendingMachineServiceLayer;
import com.rntongo.vendingmachine.service.VendingMachineServiceLayerImpl;
import com.rntongo.vendingmachine.view.UserIO;
import com.rntongo.vendingmachine.view.UserIOConsoleImpl;
import com.rntongo.vendingmachine.view.VendingMachineView;

/**
 *
 * @author rogersentongo
 */
public class App {
    
    public static void main(String[] args) throws VendingMachineNoItemInventoryException {
        UserIO myIo = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIo);
        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoFileImpl();
        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao, myAuditDao);
        VendingMachineController controller = new VendingMachineController(myService, myView);
        controller.run();
    }
    
}
