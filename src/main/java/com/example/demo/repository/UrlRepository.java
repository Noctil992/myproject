package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Url;




@Repository
public interface UrlRepository extends JpaRepository <Url, Integer> {
    List<Url> findAllByloginUserId(String loginUserId);
    Url findOneByid(Integer id);
    List<Url> findTop5ByPublicflagOrderByIdDesc(Integer publicflag);
    Url findTop1ByPublicflagOrderByGoodcounterDesc(Integer publicflag);
    Url findTop1ByPublicflagOrderByIdDesc(Integer publicflag);
    
}
