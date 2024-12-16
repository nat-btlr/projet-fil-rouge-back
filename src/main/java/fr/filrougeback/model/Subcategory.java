package fr.filrougeback.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "subcategories")
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSubcategory")
    int idSubcategory;

    @Column(name = "nameSubcategory", nullable = false, unique = true)
    String nameSubcategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCategory", nullable = false)
    Category category;
}
