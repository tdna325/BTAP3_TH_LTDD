package com.example.thecoffeehouse.model;

public class Poster {
    private String Title_355;
    private String Noidung_355;
    private byte[] Image_355;
    private String Btn_355;

    public Poster(String title_355, String noidung_355, byte[] image_355, String btn_355) {
        Title_355 = title_355;
        Noidung_355 = noidung_355;
        Image_355 = image_355;
        Btn_355 = btn_355;
    }

    public String getTitle_355() {
        return Title_355;
    }

    public void setTitle_355(String title_355) {
        Title_355 = title_355;
    }

    public String getNoidung_355() {
        return Noidung_355;
    }

    public void setNoidung_355(String noidung_355) {
        Noidung_355 = noidung_355;
    }

    public byte[] getImage_355() {
        return Image_355;
    }

    public void setImage_355(byte[] image_355) {
        Image_355 = image_355;
    }

    public String getBtn_355() {
        return Btn_355;
    }

    public void setBtn_355(String btn_355) {
        Btn_355 = btn_355;
    }
}
