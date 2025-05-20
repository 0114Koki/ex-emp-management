package com.example.service;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 管理者情報を操作するサービス.
 *
 * @author koki.kurihara
 */
@Service
@Transactional
public class AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;

    /**
     * ログイン処理をする.
     *
     * @param mailAddress メールアドレス
     * @param password パスワード
     */
    public Administrator login(String mailAddress, String password){
        return administratorRepository.findByMailAddressAndPassword(mailAddress, password);
    }

    /**
     * 管理者情報を挿入する.
     *
     * @param administrator 管理者情報
     */
    public void insert(Administrator administrator){
        administratorRepository.insert(administrator);
    }
}
