package com.example.thecoffeehouse.model;

public class Product {
    private String name_product_355;
    private int price_prodcuct_355;
    private byte[] image_product_355;
    private int id_product_355;
    private String size;
    private int thanhTien;
    private int soluong;
    public Product(String name_product_355, int id_product_355, String size, int thanhTien, int soluong) {
        this.name_product_355 = name_product_355;
        this.id_product_355 = id_product_355;
        this.size = size;
        this.thanhTien = thanhTien;
        this.soluong = soluong;
    }

    public Product(String name_product_355, int id_product_355, String size, int thanhTien, int soluong,byte[] image_product_355, int price_prodcuct_355) {
        this.name_product_355 = name_product_355;
        this.id_product_355 = id_product_355;
        this.size = size;
        this.thanhTien = thanhTien;
        this.soluong = soluong;
        this.image_product_355 = image_product_355;
        this.price_prodcuct_355 = price_prodcuct_355;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Product(String name_product_355, int price_prodcuct_355, byte[] image_product_355, int id_product_355) {
        this.name_product_355 = name_product_355;
        this.price_prodcuct_355 = price_prodcuct_355;
        this.image_product_355 = image_product_355;
        this.id_product_355 = id_product_355;
    }

    public String getName_product_355() {
        return name_product_355;
    }

    public void setName_product_355(String name_product_355) {
        this.name_product_355 = name_product_355;
    }

    public int getPrice_prodcuct_355() {
        return price_prodcuct_355;
    }

    public void setPrice_prodcuct_355(int price_prodcuct_355) {
        this.price_prodcuct_355 = price_prodcuct_355;
    }

    public byte[] getImage_product_355() {
        return image_product_355;
    }

    public void setImage_product_355(byte[] image_product_355) {
        this.image_product_355 = image_product_355;
    }

    public int getId_product_355() {
        return id_product_355;
    }

    public void setId_product_355(int id_product_355) {
        this.id_product_355 = id_product_355;
    }
}
