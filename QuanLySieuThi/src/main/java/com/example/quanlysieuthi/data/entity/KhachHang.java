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
public class KhachHang {
    @Id
    private String ma;
    private String ten;
    private String cccd;
    private Integer sdt;

    @JsonIgnore
    @OneToMany(mappedBy = "khachHang")
    private Set<HoaDon> hoaDon;
}
