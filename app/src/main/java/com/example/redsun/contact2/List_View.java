package com.example.redsun.contact2;

/**
 * Created by arul on 20/5/17.
 */

public class List_View {

    private String head;
    private String desc;


    public List_View(String head, String desc, String status) {
        this.head = head;
        this.desc = desc;

    }



    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
