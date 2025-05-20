package com.example.controller;


import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理者関連機能の制御を行うコントローラ.
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
     * ログイン画面に移動する.
     *
     * @param form フォーム
     * @return ログイン画面
     */
    @GetMapping("/")
    public String toLogin(LoginForm form){
        return "administrator/login";
    }

    /**
     * 管理者情報のログイン処理をします.
     *
     * @param form フォーム
     * @param model リクエストスコープ
     */
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
     * 管理者情報登録画面に移動する
     *
     * @param form フォーム
     */
    @GetMapping("to-insert")
    public String toInsert(InsertAdministratorForm form){
        return "administrator/insert";
    }

    /**
     * 管理者情報をDBに登録する.
     *
     * @param form
     * @return
     */
    @PostMapping("/insert")
    public String insert(InsertAdministratorForm form){
        Administrator administrator = new Administrator();
        BeanUtils.copyProperties(form, administrator);
        System.out.println(administrator);
        administratorService.insert(administrator);
        return "redirect:/";
    }
}
