package com.example.ros;

public class Orders {
    public int Id;
    public String ProductName;
    public String ProductPrice;
    public String TotalPrice;
    public String TableName;
    public String TotalProduct;
    public int Uid;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String tableName) {
        TableName = tableName;
    }

    public String getTotalProduct() {
        return TotalProduct;
    }

    public void setTotalProduct(String totalProduct) {
        TotalProduct = totalProduct;
    }

    public int getUid() {
        return Uid;
    }

    public void setUid(int uid) {
        Uid = uid;
    }
}
