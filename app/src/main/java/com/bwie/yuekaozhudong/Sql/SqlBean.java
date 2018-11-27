package com.bwie.yuekaozhudong.Sql;

public class SqlBean {
    private int id;
    private String content;
    private String itemdata;
    public SqlBean(int id, String content, String itemdata) {
        this.id = id;
        this.content = content;
        this.itemdata = itemdata;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getItemdata() {
        return itemdata;
    }

    public void setItemdata(String itemdata) {
        this.itemdata = itemdata;
    }

    @Override
    public String toString() {
        return "SqlBean{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", itemdata='" + itemdata + '\'' +
                '}';
    }
}
