
























package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 校长
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/xiaozhang")
public class XiaozhangController {
    private static final Logger logger = LoggerFactory.getLogger(XiaozhangController.class);

    @Autowired
    private XiaozhangService xiaozhangService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private LaoshiService laoshiService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("学员".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("教师".equals(role))
            params.put("laoshiId",request.getSession().getAttribute("userId"));
        else if("校长".equals(role))
            params.put("xiaozhangId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = xiaozhangService.queryPage(params);

        //字典表数据转换
        List<XiaozhangView> list =(List<XiaozhangView>)page.getList();
        for(XiaozhangView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        XiaozhangEntity xiaozhang = xiaozhangService.selectById(id);
        if(xiaozhang !=null){
            //entity转view
            XiaozhangView view = new XiaozhangView();
            BeanUtils.copyProperties( xiaozhang , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody XiaozhangEntity xiaozhang, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,xiaozhang:{}",this.getClass().getName(),xiaozhang.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");

        Wrapper<XiaozhangEntity> queryWrapper = new EntityWrapper<XiaozhangEntity>()
            .eq("username", xiaozhang.getUsername())
            .or()
            .eq("xiaozhang_phone", xiaozhang.getXiaozhangPhone())
            .or()
            .eq("xiaozhang_id_number", xiaozhang.getXiaozhangIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XiaozhangEntity xiaozhangEntity = xiaozhangService.selectOne(queryWrapper);
        if(xiaozhangEntity==null){
            xiaozhang.setCreateTime(new Date());
            xiaozhang.setPassword("123456");
            xiaozhangService.insert(xiaozhang);
            return R.ok();
        }else {
            return R.error(511,"账户或者校长手机号或者校长身份证号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody XiaozhangEntity xiaozhang, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,xiaozhang:{}",this.getClass().getName(),xiaozhang.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(StringUtil.isEmpty(role))
//            return R.error(511,"权限为空");
        //根据字段查询是否有相同数据
        Wrapper<XiaozhangEntity> queryWrapper = new EntityWrapper<XiaozhangEntity>()
            .notIn("id",xiaozhang.getId())
            .andNew()
            .eq("username", xiaozhang.getUsername())
            .or()
            .eq("xiaozhang_phone", xiaozhang.getXiaozhangPhone())
            .or()
            .eq("xiaozhang_id_number", xiaozhang.getXiaozhangIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XiaozhangEntity xiaozhangEntity = xiaozhangService.selectOne(queryWrapper);
        if("".equals(xiaozhang.getXiaozhangPhoto()) || "null".equals(xiaozhang.getXiaozhangPhoto())){
                xiaozhang.setXiaozhangPhoto(null);
        }
        if(xiaozhangEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      xiaozhang.set
            //  }
            xiaozhangService.updateById(xiaozhang);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"账户或者校长手机号或者校长身份证号已经被使用");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        xiaozhangService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<XiaozhangEntity> xiaozhangList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            XiaozhangEntity xiaozhangEntity = new XiaozhangEntity();
//                            xiaozhangEntity.setUsername(data.get(0));                    //账户 要改的
//                            //xiaozhangEntity.setPassword("123456");//密码
//                            xiaozhangEntity.setXiaozhangName(data.get(0));                    //校长姓名 要改的
//                            xiaozhangEntity.setXiaozhangPhone(data.get(0));                    //校长手机号 要改的
//                            xiaozhangEntity.setXiaozhangIdNumber(data.get(0));                    //校长身份证号 要改的
//                            xiaozhangEntity.setXiaozhangPhoto("");//照片
//                            xiaozhangEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            xiaozhangEntity.setXiaozhangEmail(data.get(0));                    //电子邮箱 要改的
//                            xiaozhangEntity.setCreateTime(date);//时间
                            xiaozhangList.add(xiaozhangEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //校长手机号
                                if(seachFields.containsKey("xiaozhangPhone")){
                                    List<String> xiaozhangPhone = seachFields.get("xiaozhangPhone");
                                    xiaozhangPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> xiaozhangPhone = new ArrayList<>();
                                    xiaozhangPhone.add(data.get(0));//要改的
                                    seachFields.put("xiaozhangPhone",xiaozhangPhone);
                                }
                                //校长身份证号
                                if(seachFields.containsKey("xiaozhangIdNumber")){
                                    List<String> xiaozhangIdNumber = seachFields.get("xiaozhangIdNumber");
                                    xiaozhangIdNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> xiaozhangIdNumber = new ArrayList<>();
                                    xiaozhangIdNumber.add(data.get(0));//要改的
                                    seachFields.put("xiaozhangIdNumber",xiaozhangIdNumber);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<XiaozhangEntity> xiaozhangEntities_username = xiaozhangService.selectList(new EntityWrapper<XiaozhangEntity>().in("username", seachFields.get("username")));
                        if(xiaozhangEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(XiaozhangEntity s:xiaozhangEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //校长手机号
                        List<XiaozhangEntity> xiaozhangEntities_xiaozhangPhone = xiaozhangService.selectList(new EntityWrapper<XiaozhangEntity>().in("xiaozhang_phone", seachFields.get("xiaozhangPhone")));
                        if(xiaozhangEntities_xiaozhangPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(XiaozhangEntity s:xiaozhangEntities_xiaozhangPhone){
                                repeatFields.add(s.getXiaozhangPhone());
                            }
                            return R.error(511,"数据库的该表中的 [校长手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //校长身份证号
                        List<XiaozhangEntity> xiaozhangEntities_xiaozhangIdNumber = xiaozhangService.selectList(new EntityWrapper<XiaozhangEntity>().in("xiaozhang_id_number", seachFields.get("xiaozhangIdNumber")));
                        if(xiaozhangEntities_xiaozhangIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(XiaozhangEntity s:xiaozhangEntities_xiaozhangIdNumber){
                                repeatFields.add(s.getXiaozhangIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [校长身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        xiaozhangService.insertBatch(xiaozhangList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }


    /**
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        XiaozhangEntity xiaozhang = xiaozhangService.selectOne(new EntityWrapper<XiaozhangEntity>().eq("username", username));
        if(xiaozhang==null || !xiaozhang.getPassword().equals(password))
            return R.error("账号或密码不正确");
        //  // 获取监听器中的字典表
        // ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        // Map<String, Map<Integer, String>> dictionaryMap= (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        // Map<Integer, String> role_types = dictionaryMap.get("role_types");
        // role_types.get(.getRoleTypes());
        String token = tokenService.generateToken(xiaozhang.getId(),username, "xiaozhang", "校长");
        R r = R.ok();
        r.put("token", token);
        r.put("role","校长");
        r.put("username",xiaozhang.getXiaozhangName());
        r.put("tableName","xiaozhang");
        r.put("userId",xiaozhang.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody XiaozhangEntity xiaozhang){
//    	ValidatorUtils.validateEntity(user);
        Wrapper<XiaozhangEntity> queryWrapper = new EntityWrapper<XiaozhangEntity>()
            .eq("username", xiaozhang.getUsername())
            .or()
            .eq("xiaozhang_phone", xiaozhang.getXiaozhangPhone())
            .or()
            .eq("xiaozhang_id_number", xiaozhang.getXiaozhangIdNumber())
            ;
        XiaozhangEntity xiaozhangEntity = xiaozhangService.selectOne(queryWrapper);
        if(xiaozhangEntity != null)
            return R.error("账户或者校长手机号或者校长身份证号已经被使用");
        xiaozhang.setCreateTime(new Date());
        xiaozhangService.insert(xiaozhang);
        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id){
        XiaozhangEntity xiaozhang = new XiaozhangEntity();
        xiaozhang.setPassword("123456");
        xiaozhang.setId(id);
        xiaozhangService.updateById(xiaozhang);
        return R.ok();
    }


    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        XiaozhangEntity xiaozhang = xiaozhangService.selectOne(new EntityWrapper<XiaozhangEntity>().eq("username", username));
        if(xiaozhang!=null){
            xiaozhang.setPassword("123456");
            boolean b = xiaozhangService.updateById(xiaozhang);
            if(!b){
               return R.error();
            }
        }else{
           return R.error("账号不存在");
        }
        return R.ok();
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrXiaozhang(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        XiaozhangEntity xiaozhang = xiaozhangService.selectById(id);
        if(xiaozhang !=null){
            //entity转view
            XiaozhangView view = new XiaozhangView();
            BeanUtils.copyProperties( xiaozhang , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = xiaozhangService.queryPage(params);

        //字典表数据转换
        List<XiaozhangView> list =(List<XiaozhangView>)page.getList();
        for(XiaozhangView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        XiaozhangEntity xiaozhang = xiaozhangService.selectById(id);
            if(xiaozhang !=null){


                //entity转view
                XiaozhangView view = new XiaozhangView();
                BeanUtils.copyProperties( xiaozhang , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody XiaozhangEntity xiaozhang, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,xiaozhang:{}",this.getClass().getName(),xiaozhang.toString());
        Wrapper<XiaozhangEntity> queryWrapper = new EntityWrapper<XiaozhangEntity>()
            .eq("username", xiaozhang.getUsername())
            .or()
            .eq("xiaozhang_phone", xiaozhang.getXiaozhangPhone())
            .or()
            .eq("xiaozhang_id_number", xiaozhang.getXiaozhangIdNumber())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XiaozhangEntity xiaozhangEntity = xiaozhangService.selectOne(queryWrapper);
        if(xiaozhangEntity==null){
            xiaozhang.setCreateTime(new Date());
        xiaozhang.setPassword("123456");
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      xiaozhang.set
        //  }
        xiaozhangService.insert(xiaozhang);
            return R.ok();
        }else {
            return R.error(511,"账户或者校长手机号或者校长身份证号已经被使用");
        }
    }


}
