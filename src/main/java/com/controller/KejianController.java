
























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
 * 课件
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/kejian")
public class KejianController {
    private static final Logger logger = LoggerFactory.getLogger(KejianController.class);

    @Autowired
    private KejianService kejianService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private LaoshiService laoshiService;

    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private XiaozhangService xiaozhangService;


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
        params.put("kejianDeleteStart",1);params.put("kejianDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = kejianService.queryPage(params);

        //字典表数据转换
        List<KejianView> list =(List<KejianView>)page.getList();
        for(KejianView c:list){
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
        KejianEntity kejian = kejianService.selectById(id);
        if(kejian !=null){
            //entity转view
            KejianView view = new KejianView();
            BeanUtils.copyProperties( kejian , view );//把实体数据重构到view中

                //级联表
                LaoshiEntity laoshi = laoshiService.selectById(kejian.getLaoshiId());
                if(laoshi != null){
                    BeanUtils.copyProperties( laoshi , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setLaoshiId(laoshi.getId());
                }
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
    public R save(@RequestBody KejianEntity kejian, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,kejian:{}",this.getClass().getName(),kejian.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("教师".equals(role))
            kejian.setLaoshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<KejianEntity> queryWrapper = new EntityWrapper<KejianEntity>()
            .eq("laoshi_id", kejian.getLaoshiId())
            .eq("kejian_name", kejian.getKejianName())
            .eq("kejian_types", kejian.getKejianTypes())
            .eq("shangxia_types", kejian.getShangxiaTypes())
            .eq("kejian_delete", kejian.getKejianDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        KejianEntity kejianEntity = kejianService.selectOne(queryWrapper);
        if(kejianEntity==null){
            kejian.setShangxiaTypes(2);
            kejian.setKejianDelete(1);
            kejian.setCreateTime(new Date());
            kejianService.insert(kejian);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody KejianEntity kejian, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,kejian:{}",this.getClass().getName(),kejian.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(StringUtil.isEmpty(role))
//            return R.error(511,"权限为空");
//        else if("教师".equals(role))
//            kejian.setLaoshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<KejianEntity> queryWrapper = new EntityWrapper<KejianEntity>()
            .notIn("id",kejian.getId())
            .andNew()
            .eq("laoshi_id", kejian.getLaoshiId())
            .eq("kejian_name", kejian.getKejianName())
            .eq("kejian_types", kejian.getKejianTypes())
            .eq("shangxia_types", kejian.getShangxiaTypes())
            .eq("kejian_delete", kejian.getKejianDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        KejianEntity kejianEntity = kejianService.selectOne(queryWrapper);
        if("".equals(kejian.getKejianPhoto()) || "null".equals(kejian.getKejianPhoto())){
                kejian.setKejianPhoto(null);
        }
        if("".equals(kejian.getKejianFile()) || "null".equals(kejian.getKejianFile())){
                kejian.setKejianFile(null);
        }
        if(kejianEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      kejian.set
            //  }
            kejianService.updateById(kejian);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        ArrayList<KejianEntity> list = new ArrayList<>();
        for(Integer id:ids){
            KejianEntity kejianEntity = new KejianEntity();
            kejianEntity.setId(id);
            kejianEntity.setKejianDelete(2);
            list.add(kejianEntity);
        }
        if(list != null && list.size() >0){
            kejianService.updateBatchById(list);
        }
        return R.ok();
    }

    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<KejianEntity> kejianList = new ArrayList<>();//上传的东西
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
                            KejianEntity kejianEntity = new KejianEntity();
//                            kejianEntity.setLaoshiId(Integer.valueOf(data.get(0)));   //教师 要改的
//                            kejianEntity.setKejianName(data.get(0));                    //课件名称 要改的
//                            kejianEntity.setKejianPhoto("");//照片
//                            kejianEntity.setKejianFile(data.get(0));                    //课件文件 要改的
//                            kejianEntity.setKejianTypes(Integer.valueOf(data.get(0)));   //课件类型 要改的
//                            kejianEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            kejianEntity.setKejianDelete(1);//逻辑删除字段
//                            kejianEntity.setKejianContent("");//照片
//                            kejianEntity.setCreateTime(date);//时间
                            kejianList.add(kejianEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        kejianService.insertBatch(kejianList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
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
        PageUtils page = kejianService.queryPage(params);

        //字典表数据转换
        List<KejianView> list =(List<KejianView>)page.getList();
        for(KejianView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        KejianEntity kejian = kejianService.selectById(id);
            if(kejian !=null){


                //entity转view
                KejianView view = new KejianView();
                BeanUtils.copyProperties( kejian , view );//把实体数据重构到view中

                //级联表
                    LaoshiEntity laoshi = laoshiService.selectById(kejian.getLaoshiId());
                if(laoshi != null){
                    BeanUtils.copyProperties( laoshi , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setLaoshiId(laoshi.getId());
                }
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
    public R add(@RequestBody KejianEntity kejian, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,kejian:{}",this.getClass().getName(),kejian.toString());
        Wrapper<KejianEntity> queryWrapper = new EntityWrapper<KejianEntity>()
            .eq("laoshi_id", kejian.getLaoshiId())
            .eq("kejian_name", kejian.getKejianName())
            .eq("kejian_types", kejian.getKejianTypes())
            .eq("shangxia_types", kejian.getShangxiaTypes())
            .eq("kejian_delete", kejian.getKejianDelete())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        KejianEntity kejianEntity = kejianService.selectOne(queryWrapper);
        if(kejianEntity==null){
            kejian.setKejianDelete(1);
            kejian.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      kejian.set
        //  }
        kejianService.insert(kejian);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


}
