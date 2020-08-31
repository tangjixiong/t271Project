package com.t271.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.t271.entity.Role;
import com.t271.entity.User;
import com.t271.service.RoleService;
import com.t271.service.UserService;
import com.t271.util.Page;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/sys/")
public class UserController extends  BaseController{
    @Resource(name="userService")
    private UserService userService;
    @Resource(name="roleService")
    private RoleService roleService;

    //进入首页
    @RequestMapping("/main.html")
    public String main(){
        return "frame";
    }

    //用户分页查询
    @RequestMapping("/userlist.html")
    public  String userlist(Integer pageIndex,String queryname,Integer queryUserRole,Model model){
        HashMap<String,Object> hash=new HashMap<>();
        hash.put("curpage",pageIndex);
        hash.put("pagesize",3);
        hash.put("queryname",queryname);
        hash.put("queryUserRole",queryUserRole);

        model.addAttribute("queryUserName",queryname);
        model.addAttribute("queryUserRole",queryUserRole);

        Page page=userService.pagationUser(hash);
        model.addAttribute("page",page);

        //查询所有角色
        List<Role> rlist=roleService.getAllRoles();
        model.addAttribute("roleList",rlist);
        return "userlist";
    }

    //跳转到新增用户界面
    @RequestMapping(value = "/useradd.html",method = RequestMethod.GET)
    public String touseradd(@ModelAttribute("user")User user){
        return "useradd";
    }

    //保存数据,单文件上传
   /* @RequestMapping(value = "/useradd.html",method = RequestMethod.POST)
    public String useradd(@Valid User user, BindingResult br,HttpServletRequest request,
                          @RequestParam(value ="attach", required = false)MultipartFile attach){
        String idPicPath = null;
        //判断文件是否为空
        if(!attach.isEmpty()){
            String path = request.getSession().getServletContext().getRealPath("static"+ File.separator+"uploadfiles");
            String oldFileName = attach.getOriginalFilename();//原文件名
            String prefix= FilenameUtils.getExtension(oldFileName);//原文件后缀
            int filesize = 5000000;
            if(attach.getSize() >  filesize){//上传大小不得超过 500k
                request.setAttribute("uploadFileError", " * 上传大小不得超过 5000k");
                return "useradd";
            }else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
                    || prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")){//上传图片格式不正确
                String fileName = System.currentTimeMillis()+ RandomUtils.nextInt(1000000)+"_Personal.jpg";

                File targetFile = new File(path, fileName);
                if(!targetFile.exists()){
                    targetFile.mkdirs();
                }
                //保存
                try {
                    attach.transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("uploadFileError", " * 上传失败！");
                    return "useradd";
                }
                idPicPath = path+File.separator+fileName;
            }else{
                request.setAttribute("uploadFileError", " * 上传图片格式不正确");
                return "useradd";
            }
        }

        if(br.hasErrors()){
            System.out.println("====================数据验证错误=====================");
            return "useradd";
        }
        System.out.println("文件上传路径："+idPicPath);
        user.setIdPicPath(idPicPath);
        user.setCreationDate(new Date());
        //调用新增方法
        Integer result=userService.insertUser(user);
        if(result>0){
            return "redirect:/userlist.html";
        }else{
            return "redirect:/useradd.html";
        }
    }*/

   //多文件上传
    @RequestMapping(value = "/useradd.html",method = RequestMethod.POST)
    public String useradd( User user, BindingResult br,HttpServletRequest request,
                          @RequestParam(value ="attachs", required = false)MultipartFile [] attachs){
        String idPicPath = null;
        String workPicPath = null;
        String errorInfo = null;
        boolean flag = true;
        String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
        for(int i = 0;i < attachs.length ;i++){
            MultipartFile attach = attachs[i];
            if(!attach.isEmpty()){
                if(i == 0){
                    errorInfo = "uploadFileError";
                }else if(i == 1){
                    errorInfo = "uploadWpError";
                }
                String oldFileName = attach.getOriginalFilename();//原文件名
                String prefix=FilenameUtils.getExtension(oldFileName);//原文件后缀
                int filesize = 5000000;
                if(attach.getSize() >  filesize){//上传大小不得超过 5000k
                    request.setAttribute(errorInfo, " * 上传大小不得超过 5000k");
                    flag = false;
                }else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
                        || prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")){//上传图片格式不正确
                    String fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal.jpg";
                    File targetFile = new File(path, fileName);
                    if(!targetFile.exists()){
                        targetFile.mkdirs();
                    }
                    //保存
                    try {
                        attach.transferTo(targetFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                        request.setAttribute(errorInfo, " * 上传失败！");
                        flag = false;
                    }
                    if(i == 0){
                        idPicPath = path+File.separator+fileName;
                    }else if(i == 1){
                        workPicPath = path+File.separator+fileName;
                    }
                }else{
                    request.setAttribute(errorInfo, " * 上传图片格式不正确");
                    flag = false;
                }
            }
        }
       /* if(br.hasErrors()){
            System.out.println("====================数据验证错误=====================");
            return "useradd";
        }*/
        System.out.println("文件上传路径："+idPicPath);
        user.setIdPicPath(idPicPath);
        user.setWorkPicPath(workPicPath);
        user.setCreationDate(new Date());
        //调用新增方法
        Integer result=userService.insertUser(user);
        if(result>0){
            return "redirect:/userlist.html";
        }else{
            return "redirect:/useradd.html";
        }
    }

    //跳转到修改页面
    @RequestMapping(value = "/usermodify.html",method =RequestMethod.GET)
    public  String toUserModify(Integer uid,Model model){
        //根据id查询用户信息
        User user=userService.getUserById(uid);
        model.addAttribute("user",user);
        return "usermodify";
    }

    //修改用户,保存到数据库
    @RequestMapping(value = "usermodify.html",method = RequestMethod.POST)
    public String userModify(@Valid User user, BindingResult br,HttpSession session){
        if(br.hasErrors()){
            System.out.println("====================数据验证错误=====================");
            return "usermodify";
        }

        user.setCreationDate(new Date());
        //调用修改方法
        Integer result=userService.updateUser(user);
        if(result>0){
            return "redirect:/userlist.html";
        }else{
            return "redirect:/usermodify.html?uid="+user.getId();
        }
    }
/*    @RequestMapping("/viewuser/{id}")//Rest风格
    public  String viewuser(@PathVariable Integer id,Model model){
        //根据id查询用户信息
        User user=userService.getUserById(id);
        model.addAttribute("user",user);
        return "userview";
    }*/

    //检查UserCode是否存在
    @RequestMapping("/checkUserCode")
    @ResponseBody
    public String checkUserCode(String userCode){
        User user=userService.getUserByUserCode(userCode);
        HashMap<String,Object> hash=new HashMap<>();
        if(user!=null){
            hash.put("userCode","exist");
        }else{
            hash.put("userCode","noexist");
        }
        return JSONArray.toJSONString(hash);
    }

    //,produces = {"application/json;charset=UTF-8"}
    @RequestMapping(value = "/viewuser.json")//Rest风格
    @ResponseBody
    public  Object viewuser(Integer id){
        //根据id查询用户信息
        User user=userService.getUserById(id);
        if(user==null){
            return "nodata";
        }
        //return JSON.toJSONString(user);
        return user;
    }

}
