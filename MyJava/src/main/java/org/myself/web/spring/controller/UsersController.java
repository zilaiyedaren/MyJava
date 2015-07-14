package org.myself.web.spring.controller;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-12-1
 * Time: 下午9:09
 * To change this template use File | Settings | File Templates.
 */
import javax.servlet.http.HttpServletRequest;

import org.myself.web.spring.serviceimpl.UsersServiceImpl;
import org.myself.web.spring.utils.PageView;
import org.myself.web.spring.vo.TUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UsersController extends BaseController {

    @Autowired
    private UsersServiceImpl usersService;


//    @RequestMapping(value ="/login.html", method = RequestMethod.POST)
//    public String login(TUsers t, Model model, ModelMap modelMap) throws Exception {
//        TUsers tusers = usersService.findLogin(t);
////        Logger logger = Logger.getLogger(UsersController.class);
////        logger.debug("运行");
////        logger.error("错误");
//        if (tusers == null) {
//            System.out.println("用户不存在");
//            model.addAttribute("message", "用户不存在");
//            modelMap.put("error", "用户名或密码错误");
//            return "index";
//        } else {
//            getRequest().getSession().setAttribute("username", tusers.getUsername());
//            System.out.println(getRequest().getSession().getAttribute("username")+"   ses");
//            System.out.println("登陆成功");
//            model.addAttribute("cp", 1);
//            model.addAttribute("ls", 10);
//            model.addAttribute("keyword", "");
//            return "redirect:/list.html";
//        }
//    }

    @RequestMapping(value = "/list.html")
    public String list(String keyword, @RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
                       @RequestParam(value = "ls", required = false, defaultValue = "10") int ls, Model model, HttpServletRequest request)
            throws Exception {
        if (keyword == null) {
            keyword = "";
        }
        PageView<TUsers> all = usersService.findAll(keyword, cp, ls);
        model.addAttribute("all", all);
        model.addAttribute("keyword", keyword);
        return "user/list";
    }

    /*
     * 同样的请求路径 user/add 如果是get请求就转到增加页面去，如果是post请求就做add操作
     */
    @RequestMapping(value = "/add.html", method = RequestMethod.GET)
    public String toAdd(Model model) throws Exception {
        return "user/add";
    }

    @RequestMapping(value = "/add.html", method = RequestMethod.POST)
    public String doAdd(TUsers user, Model model) throws Exception {

        usersService.doCreate(user);
        model.addAttribute("cp", 1);
        model.addAttribute("ls", 10);
        // 重定向，防止重复提交，当然这样不能完全解决重复提交的问题，只是简单处理一下，若要较好的防止重复提交可以结合token做，

        return "redirect:list.html";
    }

    /*
     * Restful模式路径：
     * 注意这里/update/{id}和@PathVariable("id")中id要一致，这样不管用debug模式还是release模式编译都没问题
     * 也可以简写成@PathVariable int
     * id，但这样只能以debug模式编译的时候正确，如果用release编译就不正确了，因为如果用release模式编译会把参数的名字改变的
     * 一般IDE工具都是以debug模式编译的，javac是以release模式编译的 同样的请求路径 user/update
     * 如果是get请求就转到增加页面去，如果是post请求就做update操作
     */
    @RequestMapping(value = "/update/{id}.html", method = RequestMethod.GET)
    public String toUpdate(@PathVariable("id") int id, Model model) throws Exception {
        TUsers tusers = this.usersService.findById(id);
        model.addAttribute("tusers", tusers);
        return "user/update";
    }

    @RequestMapping(value = "/update/{id}.html", method = RequestMethod.POST)
    public String doUpdate(TUsers users, Model model) throws Exception {
        this.usersService.doUpdate(users);
        String keyword = "";
        model.addAttribute("cp", 1);
        model.addAttribute("ls", 10);
        return "redirect:../list.html?keyword=" + keyword;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String doDelete(@PathVariable("id") int id, String keyword, int cp, int ls, Model model) throws Exception {
        this.usersService.doDelete(id);
        model.addAttribute("cp", cp);
        model.addAttribute("ls", ls);
        return "redirect:../list.html";
    }
    @RequestMapping(value = "/login_failure.html")
    public String loginFailure() throws Exception {
        return "index";
    }


}
