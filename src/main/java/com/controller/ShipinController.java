
























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
 * 视频
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/shipin")
public class ShipinController {
    private static final Logger logger = LoggerFactory.getLogger(ShipinController.class);

    @Autowired
    private ShipinService shipinService;


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
        params.put("shipinDeleteStart",1);params.put("shipinDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = shipinService.queryPage(params);

        //字典表数据转换
        List<ShipinView> list =(List<ShipinView>)page.getList();
        for(ShipinView c:list){
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
        ShipinEntity shipin = shipinService.selectById(id);
        if(shipin !=null){
            //entity转view
            ShipinView view = new ShipinView();
            BeanUtils.copyProperties( shipin , view );//把实体数据重构到view中

                //级联表
                LaoshiEntity laoshi = laoshiService.selectById(shipin.getLaoshiId());
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
    public R save(@RequestBody ShipinEntity shipin, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,shipin:{}",this.getClass().getName(),shipin.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("教师".equals(role))
            shipin.setLaoshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<ShipinEntity> queryWrapper = new EntityWrapper<ShipinEntity>()
            .eq("laoshi_id", shipin.getLaoshiId())
            .eq("shipin_name", shipin.getShipinName())
            .eq("shipin_video", shipin.getShipinVideo())
            .eq("shipin_types", shipin.getShipinTypes())
            .eq("shangxia_types", shipin.getShangxiaTypes())
            .eq("shipin_delete", shipin.getShipinDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShipinEntity shipinEntity = shipinService.selectOne(queryWrapper);
        if(shipinEntity==null){
            shipin.setShangxiaTypes(2);
            shipin.setShipinDelete(1);
            shipin.setCreateTime(new Date());
            shipinService.insert(shipin);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ShipinEntity shipin, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,shipin:{}",this.getClass().getName(),shipin.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(StringUtil.isEmpty(role))
//            return R.error(511,"权限为空");
//        else if("教师".equals(role))
//            shipin.setLaoshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<ShipinEntity> queryWrapper = new EntityWrapper<ShipinEntity>()
            .notIn("id",shipin.getId())
            .andNew()
            .eq("laoshi_id", shipin.getLaoshiId())
            .eq("shipin_name", shipin.getShipinName())
            .eq("shipin_video", shipin.getShipinVideo())
            .eq("shipin_types", shipin.getShipinTypes())
            .eq("shangxia_types", shipin.getShangxiaTypes())
            .eq("shipin_delete", shipin.getShipinDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShipinEntity shipinEntity = shipinService.selectOne(queryWrapper);
        if("".equals(shipin.getShipinPhoto()) || "null".equals(shipin.getShipinPhoto())){
                shipin.setShipinPhoto(null);
        }
        if("".equals(shipin.getShipinVideo()) || "null".equals(shipin.getShipinVideo())){
                shipin.setShipinVideo(null);
        }
        if(shipinEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      shipin.set
            //  }
            shipinService.updateById(shipin);//根据id更新
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
        ArrayList<ShipinEntity> list = new ArrayList<>();
        for(Integer id:ids){
            ShipinEntity shipinEntity = new ShipinEntity();
            shipinEntity.setId(id);
            shipinEntity.setShipinDelete(2);
            list.add(shipinEntity);
        }
        if(list != null && list.size() >0){
            shipinService.updateBatchById(list);
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
            List<ShipinEntity> shipinList = new ArrayList<>();//上传的东西
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
                            ShipinEntity shipinEntity = new ShipinEntity();
//                            shipinEntity.setLaoshiId(Integer.valueOf(data.get(0)));   //教师 要改的
//                            shipinEntity.setShipinName(data.get(0));                    //视频名称 要改的
//                            shipinEntity.setShipinPhoto("");//照片
//                            shipinEntity.setShipinVideo(data.get(0));                    //视频 要改的
//                            shipinEntity.setShipinTypes(Integer.valueOf(data.get(0)));   //视频类型 要改的
//                            shipinEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            shipinEntity.setShipinDelete(1);//逻辑删除字段
//                            shipinEntity.setShipinContent("");//照片
//                            shipinEntity.setCreateTime(date);//时间
                            shipinList.add(shipinEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        shipinService.insertBatch(shipinList);
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
        PageUtils page = shipinService.queryPage(params);

        //字典表数据转换
        List<ShipinView> list =(List<ShipinView>)page.getList();
        for(ShipinView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ShipinEntity shipin = shipinService.selectById(id);
            if(shipin !=null){


                //entity转view
                ShipinView view = new ShipinView();
                BeanUtils.copyProperties( shipin , view );//把实体数据重构到view中

                //级联表
                    LaoshiEntity laoshi = laoshiService.selectById(shipin.getLaoshiId());
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
    public R add(@RequestBody ShipinEntity shipin, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,shipin:{}",this.getClass().getName(),shipin.toString());
        Wrapper<ShipinEntity> queryWrapper = new EntityWrapper<ShipinEntity>()
            .eq("laoshi_id", shipin.getLaoshiId())
            .eq("shipin_name", shipin.getShipinName())
            .eq("shipin_video", shipin.getShipinVideo())
            .eq("shipin_types", shipin.getShipinTypes())
            .eq("shangxia_types", shipin.getShangxiaTypes())
            .eq("shipin_delete", shipin.getShipinDelete())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShipinEntity shipinEntity = shipinService.selectOne(queryWrapper);
        if(shipinEntity==null){
            shipin.setShipinDelete(1);
            shipin.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      shipin.set
        //  }
        shipinService.insert(shipin);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


}
