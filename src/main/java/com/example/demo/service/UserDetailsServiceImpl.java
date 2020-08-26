package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.models.Users;
import com.example.demo.repository.LoginUserDao;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
  //DBからユーザ情報を検索するメソッドを実装したクラス
    @Autowired
    private LoginUserDao userDao;
    
    /**
     * UserDetailsServiceインタフェースの実装メソッド
     * フォームから取得したユーザ名でDBを検索し、合致するものが存在したとき、
     * パスワード、権限情報と共にUserDetailsオブジェクトを生成
     * コンフィグクラスで上入力値とDBから取得したパスワードと比較し、ログイン判定を行う
     */
    @Override
    public UserDetails loadUserByUsername(String login_id) throws UsernameNotFoundException {

        Users user = userDao.findUser(login_id);

        if (user == null) {
            throw new UsernameNotFoundException("User" + login_id + "was not found in the database");
        }
        //権限のリスト
        //AdminやUserなどが存在するが、今回は利用しないのでUSERのみを仮で設定
        //権限を利用する場合は、DB上で権限テーブル、ユーザ権限テーブルを作成し管理が必要

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority("USER");

        grantList.add(authority);

        //rawDataのパスワードは渡すことができないので、暗号化
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        //UserDetailsはインタフェースなのでUserクラスのコンストラクタで生成したユーザオブジェクトをキャスト
        UserDetails userDetails = (UserDetails)new User(user.getLogin_id(), encoder.encode(user.getPassword()),grantList);

        return userDetails;
    }
}