package com.example.ros;


enum Category{
    Salad,
    Meat,
    Burger,
    Starter,
    Desert,
    SoftDrink,
    AlcoholicDrink
}

public class Product {
    public int Id;
    public String Name;
    public String Category;
    public String Price;
    public String Amount;
    public int Uid;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public int getUid() {
        return Uid;
    }

    public void setUid(int uid) {
        Uid = uid;
    }
}
