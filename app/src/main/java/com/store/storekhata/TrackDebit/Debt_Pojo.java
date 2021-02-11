package com.store.storekhata.TrackDebit;

public class Debt_Pojo {

     String debtId;
     String itemName;
     String quantity;
     String priceOfOne;
     String uid;
     String name;
     String total;
     String Date;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Debt_Pojo(){

     }

    public Debt_Pojo(String debtId, String itemName, String quantity, String priceOfOne, String uid, String name, String total) {       // constructor for giving fake data
        this.debtId = debtId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.priceOfOne = priceOfOne;
        this.uid = uid;
        this.name = name;
        this.total = total;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }


    public String getDebtId() {
        return debtId;
    }

    public void setDebtId(String debtId) {
        this.debtId = debtId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPriceOfOne() {
        return priceOfOne;
    }

    public void setPriceOfOne(String priceOfOne) {
        this.priceOfOne = priceOfOne;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
