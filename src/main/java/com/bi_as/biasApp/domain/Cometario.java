package com.bi_as.biasApp.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "cometario")
@NamedQueries({
        @NamedQuery(name = "Cometario.findAll", query = "SELECT c FROM Cometario c"),
        @NamedQuery(name = "Cometario.findByIdComentario", query = "SELECT c FROM Cometario c WHERE c.idComentario = :idComentario"),
        @NamedQuery(name = "Cometario.findByCalificacion", query = "SELECT c FROM Cometario c WHERE c.calificacion = :calificacion"),
        @NamedQuery(name = "Cometario.findByCometario", query = "SELECT c FROM Cometario c WHERE c.cometario = :cometario")})
public class Cometario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_comentario")
    private Integer idComentario;
    @Basic(optional = false)
    @Column(name = "calificacion")
    private int calificacion;
    @Basic(optional = false)
    @Column(name = "cometario")
    private String cometario;
    @JoinColumn(name = "user_client_id_userclient", referencedColumnName = "id_userclient")
    @ManyToOne(optional = false)
    private UserClient userClientIdUserclient;
    @JoinColumn(name = "product_id_product", referencedColumnName = "id_product")
    @ManyToOne(optional = false)
    private Product productIdProduct;

    public Cometario() {
    }

    public Cometario(Integer idComentario) {
        this.idComentario = idComentario;
    }

    public Cometario(Integer idComentario, int calificacion, String cometario) {
        this.idComentario = idComentario;
        this.calificacion = calificacion;
        this.cometario = cometario;
    }

    public Integer getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Integer idComentario) {
        this.idComentario = idComentario;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getCometario() {
        return cometario;
    }

    public void setCometario(String cometario) {
        this.cometario = cometario;
    }

    public UserClient getUserClientIdUserclient() {
        return userClientIdUserclient;
    }

    public void setUserClientIdUserclient(UserClient userClientIdUserclient) {
        this.userClientIdUserclient = userClientIdUserclient;
    }

    public Product getProductIdProduct() {
        return productIdProduct;
    }

    public void setProductIdProduct(Product productIdProduct) {
        this.productIdProduct = productIdProduct;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComentario != null ? idComentario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cometario)) {
            return false;
        }
        Cometario other = (Cometario) object;
        if ((this.idComentario == null && other.idComentario != null) || (this.idComentario != null && !this.idComentario.equals(other.idComentario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.ucb.tdd.biasecommercev4.Cometario[ idComentario=" + idComentario + " ]";
    }

}
