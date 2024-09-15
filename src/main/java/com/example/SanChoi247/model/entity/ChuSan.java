package com.example.SanChoi247.model.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChuSan extends User{
    // chu_san_id int not null primary key,
    // foreign key (chu_san_id) references users(uid),
    // website text,
    // is_active tinyint,
    // chu_san_address varchar(256)
    private int chu_san_id;
    private String website;
    private boolean is_active;
    private String chu_san_address;

    public ChuSan(int uid, String name, Date dob, char gender, String phone, String email, 
              String username, String password, String avatar, char role, 
              String website, boolean is_active, String chu_san_address) {
    super(uid, name, dob, gender, phone, email, username, password, avatar, role);
    this.website = website;
    this.is_active = is_active;
    this.chu_san_address = chu_san_address;
    }
}
