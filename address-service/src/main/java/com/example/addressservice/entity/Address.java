package com.example.addressservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer addressId;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "address2")
    private String address2;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "city_id", nullable = false)
    private Short cityId;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "last_update", nullable = false)
    private OffsetDateTime lastUpdate;

    @PrePersist
    public void setLastUpdate() {
        this.lastUpdate = OffsetDateTime.now();
    }

    @PreUpdate
    public void updateLastUpdate() {
        this.lastUpdate = OffsetDateTime.now();
    }


}
