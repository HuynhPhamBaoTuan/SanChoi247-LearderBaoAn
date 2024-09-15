package com.example.SanChoi247.model.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.SanChoi247.model.entity.ChuSan;
import com.example.SanChoi247.model.entity.LoaiSan;
import com.example.SanChoi247.model.entity.San;

@Repository
public class SanRepo {
    @Autowired
    LoaiSanRepo loaiSanRepo;
    @Autowired
    ChuSanRepo chuSanRepo;
    public ArrayList<San> getAllSan() throws Exception {
        ArrayList<San> sanList = new ArrayList<>();
        Class.forName(Baseconnection.nameClass);
        Connection con = DriverManager.getConnection(Baseconnection.url, Baseconnection.username, Baseconnection.password);
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM san");
        while (rs.next()) {
            int san_id = rs.getInt("san_id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            String location = rs.getString("location");
            int loai_san_id = rs.getInt("loai_san_id");
            LoaiSan loaiSan = loaiSanRepo.getIn4LoaiSanById(loai_san_id);
            int chu_san_id = rs.getInt("chu_san_id");
            ChuSan chuSan = chuSanRepo.getChuSanById(chu_san_id);
            String poster = rs.getString("poster");
            String banner = rs.getString("banner");
            int is_approve = rs.getInt("is_approve");
            San san = new San(san_id, name, description, location, loaiSan, chuSan, poster, banner, is_approve);
            sanList.add(san);
        }
        rs.close();
        stm.close();
        con.close();
        return sanList;
    }

    public void addNewSan(San san) throws Exception {
        Class.forName(Baseconnection.nameClass);
        Connection con = DriverManager.getConnection(Baseconnection.url, Baseconnection.username,
                Baseconnection.password);
        PreparedStatement ps = con.prepareStatement(
                "insert into san(name, description, location, loai_san_id, chu_san_id, poster, banner, is_approve) values(?,?,?,?,?,?,?,?)");
        ps.setString(1, san.getName());
        ps.setString(2, san.getDescription());
        ps.setString(3, san.getLocation());
        ps.setInt(4, san.getLoaiSan().getLoai_san_id());
        ps.setInt(5, san.getChuSan().getChu_san_id());
        ps.setString(6, san.getPoster());
        ps.setString(7, san.getBanner());
        ps.setInt(8, san.getIs_approve());
        ps.executeUpdate();
        ps.close();
    }

    public San getSanById(int san_id) throws Exception {
        Class.forName(Baseconnection.nameClass);
        Connection con = DriverManager.getConnection(Baseconnection.url, Baseconnection.username,
                Baseconnection.password);
        PreparedStatement ps = con.prepareStatement("select * from san where san_id = ?");
        ps.setInt(1, san_id);
        ps.executeQuery();
        ResultSet rs = ps.getResultSet();
        rs.next();
        int san_id1 = rs.getInt("san_id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        String location = rs.getString("location");
        int loai_san_id = rs.getInt("loai_san_id");
        LoaiSan loaiSan = loaiSanRepo.getIn4LoaiSanById(loai_san_id);
        int chu_san_id = rs.getInt("chu_san_id");
        ChuSan chuSan = chuSanRepo.getChuSanById(chu_san_id);
        String poster = rs.getString("poster");
        String banner = rs.getString("banner");
        San san = new San(san_id1, name, description, location, loaiSan, chuSan, poster, banner, chu_san_id);
        return san;
    }
}
