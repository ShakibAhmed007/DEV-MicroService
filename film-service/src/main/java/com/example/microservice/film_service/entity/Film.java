package com.example.microservice.film_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "film")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Film implements Serializable {

    @Id
    @Column(name = "film_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    @NotNull
    private String title;

    @Column(name = "description")
    @NotNull
    private String description;

    @Column(name = "release_year")
    @NotNull
    private String releaseYear;

    @Column(name = "language_id")
    @NotNull
    private Integer languageId;

    @Column(name = "original_language_id")
    @NotNull
    private Integer originalLanguageId;

    @Column(name = "rental_duration")
    @NotNull
    private Integer rentalDuration;

    @Column(name="rental_rate")
    @NotNull
    private BigDecimal rentalRate;

    @Column(name = "length")
    @NotNull
    private Short length;

    @Column(name = "replacement_cost")
    @NotNull
    private BigDecimal replacementCost;

    @Column(name = "rating")
    @NotNull
    private String rating;

    @Column(name = "last_update")
    @NotNull
    private OffsetDateTime lastUpdate;

    @Column(name = "fulltext")
    @NotNull
    private String fulltext;
}
