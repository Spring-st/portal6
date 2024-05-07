package com.aof.web.struts.action;

import com.shcnc.struts.action.ExceptionHandler;
import java.util.HashSet;
import java.util.Set;

public class ExceptionHandler2 extends ExceptionHandler {
    private Set setDialogActionPath;

    public ExceptionHandler2() {
        this.setDialogActionPath = new HashSet();
        this.setDialogActionPath.add("/newUser");
        this.setDialogActionPath.add("/insertUser");
        this.setDialogActionPath.add("/editUser");
        this.setDialogActionPath.add("/updateUser");
        this.setDialogActionPath.add("/editFunction");
        this.setDialogActionPath.add("/updateFunction");
        this.setDialogActionPath.add("/newCurrency");
        this.setDialogActionPath.add("/insertCurrency");
        this.setDialogActionPath.add("/editCurrency");
        this.setDialogActionPath.add("/updateCurrency");
        this.setDialogActionPath.add("/newPurchaseCategory");
        this.setDialogActionPath.add("/insertPurchaseCategory");
        this.setDialogActionPath.add("/editPurchaseCategory");
        this.setDialogActionPath.add("/updatePurchaseCategory");
        this.setDialogActionPath.add("/newPurchaseSubCategory");
        this.setDialogActionPath.add("/insertPurchaseSubCategory");
        this.setDialogActionPath.add("/editPurchaseSubCategory");
        this.setDialogActionPath.add("/updatePurchaseSubCategory");
        this.setDialogActionPath.add("/newExchangeRate");
        this.setDialogActionPath.add("/insertExchangeRate");
        this.setDialogActionPath.add("/editExchangeRate");
        this.setDialogActionPath.add("/updateExchangeRate");
        this.setDialogActionPath.add("/selectSupplier");
    }

    public Set getDialogActionPathSet() {
        return this.setDialogActionPath;
    }
}
