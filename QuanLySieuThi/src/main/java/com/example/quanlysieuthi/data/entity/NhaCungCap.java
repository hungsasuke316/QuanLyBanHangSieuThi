package com.example.quanlysieuthi.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
