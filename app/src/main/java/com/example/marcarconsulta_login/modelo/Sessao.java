package com.example.marcarconsulta_login.modelo;


public class Sessao {
    private String uid;
    private String area;
    private String local;
    private String data;


    public Sessao() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return area;/*"Sessao{" +
                "area='" + area + '\'' +
                '}'; */
    }
}
