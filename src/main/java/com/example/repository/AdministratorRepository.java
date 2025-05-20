package com.example.repository;


import com.example.domain.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * administratorsテーブルを操作するリポジトリクラス.
 *
 * @author koki.kurihara
 */
@Repository
public class AdministratorRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
        Administrator administrator = new Administrator();
        administrator.setId(rs.getInt("id"));
        administrator.setName(rs.getString("name"));
        administrator.setMailAddress(rs.getString("mail_address"));
        administrator.setPassword(rs.getString("password"));
        return administrator;
    };

    /**
     * 渡した管理者情報を挿入する.
     *
     * @param administrator 管理者情報
     */
    public void insert(Administrator administrator){
        SqlParameterSource param
                = new BeanPropertySqlParameterSource(administrator);
        String insertSql
                = "INSERT INTO administrator(name, mail_address, password) " +
                " VALUES (:name, :mail_address, :password); ";
        template.update(insertSql, param);
    }


    /**
     * メールアドレスとパスワードから管理者情報を取得する.
     * (1件も存在しない場合はnullを返す。)
     *
     * @param mailAddress メールアドレス
     * @param password パスワード
     * @return 検索された管理者情報
     * */
    public Administrator findByMailAddressAndPassword(String mailAddress, String password){
        String sql = "SELECT id, name, mainAddress, password " +
                " FROM Administrators " +
                " WHERE mail_address=:mailAddress AND password=:password ";
        SqlParameterSource param
                = new MapSqlParameterSource().addValue("mail_address", mailAddress).addValue("password", password);
        List<Administrator> administratorList
                = template.query(sql, param, ADMINISTRATOR_ROW_MAPPER);
        if (administratorList.isEmpty()){
            return null;
        }
        return administratorList.getFirst();
    }
}
