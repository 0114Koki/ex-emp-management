package com.example.controller;


import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理者関連機能の制御を行うコントローラ
 *
 * @author koki.kurihara
 */
@Controller
@RequestMapping("/")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    /**
     * ログイン画面に遷移する
     *
     * @param form
     * @return
     */
    @GetMapping("/")
    public String toLogin(LoginForm form){
        return "administration/login";
    }



    /**
     * 管理者情報を登録する
     *
     * @param form
     * @return
     */
    @GetMapping("to-insert")
    public String toInsert(InsertAdministratorForm form){
        return "administrator/insert";
    }
}
