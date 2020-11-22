package com.wh.controller;


import com.wh.bean.Province;
import com.wh.bean.Result;
import com.wh.service.ProvinceService;
import com.wh.service.ProvinceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("province")
@Slf4j
public class ProvinceController {
    @Autowired
    ProvinceServiceImpl provinceService;

    @GetMapping("getProvince")
    public Province getProvince(String id){
        Province province = new Province();
        province.setId(Integer.parseInt(id));
        try {
            return provinceService.getById(province);
        }catch (Exception e){
            throw new RuntimeException("省份不存在！");
        }
    }

    @PostMapping("updateProvince")
    public Result updateProvince(@RequestBody Province province){
        Result result = new Result();
        try {
            provinceService.update(province);
            result.setMsg("修改成功！");
        }catch (Exception e){
            result.setMsg("修改失败！");
            result.setState(false);
        }finally {
            return result;
        }
    }


    @PostMapping("addProvince")
    public Result addProvince(@RequestBody Province province){
        Result result = new Result();
        try {
            provinceService.add(province);
            result.setMsg("添加成功！");
        }catch (Exception e){
            result.setMsg("添加失败！");
            result.setState(false);
        }finally {
            return result;
        }
    }

    @DeleteMapping("deleteProvince")
    public Result deleteProvince(String id){
        Result result = new Result();
        try {
            provinceService.delete(id);
            result.setMsg("删除成功！");
        }catch (Exception e){
            result.setMsg("删除失败！");
            result.setState(false);
        }finally {
            return result;
        }
    }


    @GetMapping("findByPage")
    public Map findByPage(@RequestParam(value = "page",defaultValue = "1") Integer page,
                          @RequestParam(value = "rows",defaultValue = "4")Integer rows){
        HashMap<String, Object> map = new HashMap<>();
        List<Province> provinces = provinceService.findByPage(page, rows);
        Integer total = provinceService.findTotal();
        Integer totalPage = total % rows == 0 ? total / rows : total / rows + 1;

        map.put("provinces",provinces);
        map.put("total",total);
        map.put("totalPage",totalPage);
        map.put("page",page);
        return map;
    }


}
