/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author GUI
 */
@Entity
@Table(name="voo_agendado")
public class VooAgendado implements Serializable {
    
    @Id
    @SequenceGenerator(name="seq_voo_agendado", sequenceName="seq_voo_agendado_id", allocationSize=1)
    @GeneratedValue(generator="seq_voo_agendado", strategy=GenerationType.SEQUENCE)
    private Integer id;
    
    @NotBlank(message="A aeronave deve ser informado")
    @Length(max=50, message="O nome da aeronave não pode ter mais que {max} caracteres")
    @Column(name="aeronave", length=50, nullable=false)
    private String aeronave;
    
    @Temporal(TemporalType.DATE)
    @NotNull(message="A data da compra deve ser informada")
    @Column(name="data", nullable=false)
    private Calendar data;
    
    @NotNull(message="O total de passageiros deve ser informado")
    @Column(name="total_passageiros", nullable=false)
    private Integer totalPassageiros;
    
    @OneToMany(mappedBy="vooAgendado", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
    private List<Passagem> passagens = new ArrayList<>();
    
    @NotNull(message="O voo deve ser informado")
    @ManyToOne
    @JoinColumn(name="voo_id", referencedColumnName="id", nullable=false)
    private Voo voo;
    
    public VooAgendado() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAeronave() {
        return aeronave;
    }

    public void setAeronave(String aeronave) {
        this.aeronave = aeronave;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Integer getTotalPassageiros() {
        return totalPassageiros;
    }

    public void setTotalPassageiros(Integer totalPassageiros) {
        this.totalPassageiros = totalPassageiros;
    }

    public List<Passagem> getPassagens() {
        return passagens;
    }

    public void setPassagens(List<Passagem> passagens) {
        this.passagens = passagens;
    }
    
    public void setPassagem(Passagem passagem) {
        if (this.passagens == null) {
            this.passagens = new ArrayList();
        }
        passagem.setVooAgendado(this);
        this.passagens.add(passagem);
    }
    
    public void removerPassagem(int index){
        this.passagens.remove(index);
    }

    public Voo getVoo() {
        return voo;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }
   
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VooAgendado other = (VooAgendado) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
