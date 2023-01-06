package com.ubk.simplifai;

public class items {
    private int bg_id;
    private String text;

    public int getBg_id() {
        return bg_id;
    }

    public void setBg_id(int bg_id) {
        this.bg_id = bg_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    items(int bg_id, String text) {
        bg_id = bg_id;
        this.text = text;
    }
}
