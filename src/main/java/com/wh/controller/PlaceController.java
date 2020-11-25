package com.wh.controller;


import com.wh.bean.Place;
import com.wh.bean.Province;
import com.wh.bean.Result;
import com.wh.service.PlaceServiceImpl;
import com.wh.service.ProvinceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.Line;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("place")
@Slf4j
public class PlaceController {
    @Autowired
    PlaceServiceImpl placeService;

    @Value("${upload.dir}")
    private String realPath;

    @GetMapping("getPlace")
    public Place getPlace(String id){
        Place place = new Place();
        place.setId(Integer.parseInt(id));
        try {
            return placeService.getById(place);
        }catch (Exception e){
            throw new RuntimeException("出现了错误！");
        }
    }

    @PostMapping("updatePlace")
    public Result updatePlace(Place place, MultipartFile pic){
        Result result = new Result();
        try {
            if (pic!=null){
                String extension = FilenameUtils.getExtension(pic.getOriginalFilename());
                String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + extension;
                place.setPicpath(Base64Utils.encodeToString(pic.getBytes()));

                File file = new File(realPath);
                pic.transferTo(new File(file,newFileName));
            }

            placeService.update(place);
            result.setMsg("修改成功！");
        }catch (Exception e){
            result.setMsg(e.getMessage());
            result.setState(false);
        }finally {
            return result;
        }
    }


    @PostMapping("addPlace")
    public Result addPlace(Place place, MultipartFile pic){
        Result result = new Result();
        try {
            String extension = FilenameUtils.getExtension(pic.getOriginalFilename());
            String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + extension;
            place.setPicpath(Base64Utils.encodeToString(pic.getBytes()));

            File file = new File(realPath);
            pic.transferTo(new File(file,newFileName));

            placeService.add(place);
            result.setMsg("添加成功！");
        }catch (Exception e){
            result.setMsg(e.getMessage());
            result.setState(false);
        }finally {
            return result;
        }
    }

    @DeleteMapping("deletePlace")
    public Result deletePlace(String placeid, String provinceid){
        Result result = new Result();
        try {
            placeService.delete(placeid,provinceid);
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
                          @RequestParam(value = "rows",defaultValue = "3")Integer rows,
                          @RequestParam("provinceid") String id){
        HashMap<String, Object> map = new HashMap<>();
        List<Place> places = placeService.findByPage(page, rows, Integer.parseInt(id));
        Integer total = placeService.findTotal(id);
        Integer totalPage = total % rows == 0 ? total / rows : total / rows + 1;

        map.put("places",places);
        map.put("total",total);
        map.put("totalPage",totalPage);
        map.put("page",page);
        return map;
    }


}
