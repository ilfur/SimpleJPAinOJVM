/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ichag.simplejpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MPFEIFER
 */
@Entity
@Table(name = "WORKERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Workers.findAll", query = "SELECT w FROM Workers w"),
    @NamedQuery(name = "Workers.findByWid", query = "SELECT w FROM Workers w WHERE w.wid = :wid"),
    @NamedQuery(name = "Workers.findByWname", query = "SELECT w FROM Workers w WHERE w.wname = :wname"),
    @NamedQuery(name = "Workers.findByWposition", query = "SELECT w FROM Workers w WHERE w.wposition = :wposition"),
    @NamedQuery(name = "Workers.findByWsalary", query = "SELECT w FROM Workers w WHERE w.wsalary = :wsalary")})
public class Workers implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private BigDecimal wid;
    @Column(name = "WNAME")
    private String wname;
    @Column(name = "WPOSITION")
    private String wposition;
    @Column(name = "WSALARY")
    private BigInteger wsalary;

    public Workers() {
    }

    public Workers(BigDecimal wid) {
        this.wid = wid;
    }

    public BigDecimal getWid() {
        return wid;
    }

    public void setWid(BigDecimal wid) {
        this.wid = wid;
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }

    public String getWposition() {
        return wposition;
    }

    public void setWposition(String wposition) {
        this.wposition = wposition;
    }

    public BigInteger getWsalary() {
        return wsalary;
    }

    public void setWsalary(BigInteger wsalary) {
        this.wsalary = wsalary;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wid != null ? wid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Workers)) {
            return false;
        }
        Workers other = (Workers) object;
        if ((this.wid == null && other.wid != null) || (this.wid != null && !this.wid.equals(other.wid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ichag.simplejpa.Workers[ wid=" + wid + " ]";
    }
    
}
