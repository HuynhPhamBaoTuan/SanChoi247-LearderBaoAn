package com.example.SanChoi247.model.repo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.example.SanChoi247.model.entity.ChuSan;

@Repository
public class ChuSanRepo {
    public ChuSan getChuSanById(int chu_san_id) throws Exception {
        Class.forName(Baseconnection.nameClass);
        Connection con = DriverManager.getConnection(Baseconnection.url, Baseconnection.username, Baseconnection.password);
        PreparedStatement ps = con.prepareStatement(
            "SELECT u.uid, u.name, u.dob, u.gender, u.phone, u.email, u.username, u.password, u.avatar, u.role, " +
            "c.chu_san_id, c.website, c.is_active, c.chu_san_address " +
            "FROM users u " +
            "INNER JOIN chuSan c ON u.uid = c.chu_san_id " +
            "WHERE c.chu_san_id = ?"
        );
    
        ps.setInt(1, chu_san_id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int uid = rs.getInt("uid");
            String name = rs.getString("name");
            Date dob = rs.getDate("dob");
            char gender = rs.getString("gender").charAt(0);
            String phone = rs.getString("phone");
            String email = rs.getString("email");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String avatar = rs.getString("avatar");
            char role = rs.getString("role").charAt(0);
            String website = rs.getString("website");
            boolean is_active = rs.getBoolean("is_active");
            String chu_san_address = rs.getString("chu_san_address");

            ChuSan chuSan = new ChuSan(uid, name, dob, gender, phone, email, username, password, avatar, role, 
                                       website, is_active, chu_san_address);
        
            return chuSan;
        }

        // Close resources (ResultSet, PreparedStatement, and Connection)
        rs.close();
        ps.close();
        con.close();

        return null; // Return null if no matching ChuSan is found
    }
}

