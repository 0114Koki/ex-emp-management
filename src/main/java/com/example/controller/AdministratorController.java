package com.example.controller;


import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @Autowired
    private HttpSession session;
    /**
     * ログイン画面に遷移
     *
     * @param form
     * @return
     */
    @GetMapping("/")
    public String toLogin(LoginForm form){
        return "administrator/login";
    }
    @PostMapping("/login")
    public String login(LoginForm form, Model model){
        Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());
        if(administrator == null){
            model.addAttribute("errorMessage", "メールアドレスまたはパスワードが不正です。");
            return "administrator/login";
        }
        session.setAttribute("administratorName", administrator);
        return "redirect:/employee/showList";
    }

    /**
     * 管理者情報を登録
     *
     * @param form
     * @return
     */
    @GetMapping("to-insert")
    public String toInsert(InsertAdministratorForm form){
        return "administrator/insert";
    }
}
