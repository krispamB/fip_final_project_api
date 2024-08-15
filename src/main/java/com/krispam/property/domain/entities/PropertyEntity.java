package com.krispam.property.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "properties")
public class PropertyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "property_seq_gen")
    @SequenceGenerator(name = "property_seq_gen", sequenceName = "property_id_seq", allocationSize = 1)
    private Long id;
    private String title;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;
    private String address;
    private double price;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity owner;
}
