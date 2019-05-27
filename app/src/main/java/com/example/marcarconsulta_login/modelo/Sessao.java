package com.example.marcarconsulta_login.modelo;


import android.widget.Spinner;

public class Sessao {
    private String uid;
    private String area;
    private String local;
    private String data;
    private String hora;

    public Sessao() {
    }

    public String getHora() { return hora; }

    public void setHora(String hora) { this.hora = hora; }

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
        String msg = ("Area: " + area
                + "Local: " + local +
                "Data: " + data + hora);
        return msg;/*"Sessao{" +
                "area='" + area + '\'' +
                '}'; */
    }
}
