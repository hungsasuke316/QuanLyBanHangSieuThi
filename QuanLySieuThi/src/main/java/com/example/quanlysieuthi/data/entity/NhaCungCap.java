package com.example.quanlysieuthi.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class NhaCungCap {
    @Id
    private String ma;
    private String ten;

    @JsonIgnore
    @OneToMany(mappedBy = "nhaCungCap")
    private Set<PhieuNhap> phieuNhap;
}
