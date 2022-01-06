package com.misiontic.account_ms.models;
import org.springframework.data.annotation.Id;
import java.util.Date;

public class Ventas {
    @Id
    private String username;
    private Integer cantidad;
    private Integer preciototal;
    private Date lastChange;

    public Ventas(String username, Integer cantidad, Integer preciototal, Date lastChange) {
        this.username = username;
        this.cantidad = cantidad;
        this.preciototal = preciototal;
        this.lastChange = lastChange;
    }

    public String getNombre() {
        return username;
    }

    public void setNombre(String username) {
        this.username = username;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getPrecio() {
        return preciototal;
    }

    public void setPrecio(Integer precio) {
        this.preciototal = preciototal;
    }

    public Date getlastChange() {
        return lastChange;
    }

    public void setlastChange(Date lastChange) {
        this.lastChange = lastChange;
    }
}