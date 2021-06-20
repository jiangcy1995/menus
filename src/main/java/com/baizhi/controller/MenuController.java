package com.baizhi.controller;

import com.baizhi.entity.Menu;
import com.baizhi.entity.User;
import com.baizhi.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @RequestMapping("findAll")
    public String findAll(HttpServletRequest request){
        List<Menu> menus=menuService.findAll();
        request.setAttribute("menus",menus);
        return "/menu/back/list";
    }
    @RequestMapping("save")
    public String save(Menu menu, MultipartFile photos,HttpServletRequest request){
        try {
//        1、上传文件

            String realPath = request.getServletContext().getRealPath("/menu/static/imgs");
            String newFileName= UUID.randomUUID().toString()+"."+FilenameUtils.getExtension(photos.getOriginalFilename());
            photos.transferTo(new File(realPath,newFileName));
//        2、保存菜单
            log.info("{}",menu);
            User user = (User) request.getSession().getAttribute("user");
            menu.setCreatePerson(user.getUsername());
            menu.setPhoto("/"+newFileName);
            menuService.save(menu);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("文件上传失败");
        }
        return "redirect:/menu/findAll";
    }
    @RequestMapping("update")
    public String update(Menu menu,MultipartFile photos,HttpServletRequest request){
        log.info("修改{}",photos.getOriginalFilename());
        Menu one = menuService.findOne(menu.getId());
        try {
//        1、上传文件
            if (!photos.getOriginalFilename().equals("")){
                String realPath = request.getServletContext().getRealPath("/menu/static/imgs");
                String newFileName= UUID.randomUUID().toString()+"."+ FilenameUtils.getExtension(photos.getOriginalFilename());
                photos.transferTo(new File(realPath,newFileName));
                menu.setPhoto("/"+newFileName);
            }else{
                log.info("修改{}",one.getPhoto());
                menu.setPhoto(one.getPhoto());

            }
//        2、修改菜单
            log.info("{}",menu);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("文件修改失败");
        }
        menu.setCreateDate(one.getCreateDate());
        menu.setCreatePerson(one.getCreatePerson());
        menuService.update(menu);
        return "redirect:/menu/findAll";
    }
    @RequestMapping("findOne")
    public String findOne(String id,HttpServletRequest request){
        Menu menu=menuService.findOne(id);
        request.setAttribute("menu",menu);
        return "/menu/back/update";
    }
    @RequestMapping("delete")
    public String delete(String id){
        menuService.delete(id);
        return "redirect:/menu/findAll";
    }
    @RequestMapping("searchAll")
    public String searchAll(String message, HttpServletRequest request){

        List<Menu> menus = menuService.querySearch(message);
        request.setAttribute("menus",menus);

        return "/menu/front/index";
    }
    @RequestMapping("deleteAll")
    @ResponseBody
    public Map<String, Object> deleteAll(){
        Map<String, Object> map = menuService.deleteAll();
        return map;
    }
    @RequestMapping("createIndex")
    @ResponseBody
    public Map<String, Object> createIndex(){
        menuService.deleteAll();
        Map<String, Object> map = menuService.createIndex();
        return map;
    }
   /* @RequestMapping("queryAll")
    public String queryAll(HttpServletRequest request){
        List<Menu> menus=menuService.queryAll();
        request.setAttribute("menus",menus);
        return "/menu/front/index";
    }*/
    @RequestMapping("getDetail")
    public String getDetail(String id,HttpServletRequest request){
        Menu menu=menuService.getDetail(id);
        request.setAttribute("menu",menu);
        return "/menu/front/detail";
    }
    @RequestMapping("queryByPage")
    public String queryByPage(Integer currentPage,HttpServletRequest request){

        log.info("currentpage{}",currentPage);
        Integer count=menuService.getCount();
        int size=4;
        int page=count%size==0?count/size:count/size+1;
        log.info("count{}",count);
        List<Menu> menus=menuService.query(currentPage-1,size);
        request.setAttribute("menus",menus);
        request.setAttribute("page",page);
        request.setAttribute("currentPage",currentPage);
        return "/menu/front/index";
    }
}
