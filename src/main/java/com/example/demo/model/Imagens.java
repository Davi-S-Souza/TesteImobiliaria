package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "imagens")
@Entity(name = "imagens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idImg")
public class Imagens {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idImg;
    private String name;
    private String type;
    @Column(length = 50000000)
    private byte[] picByte;

    public Imagens(String name, String type, byte[] picByte) {
        this.name = name;
        this.picByte = picByte;
        this.type = type;
    }
}
