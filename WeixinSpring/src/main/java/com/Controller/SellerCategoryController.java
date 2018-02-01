package com.Controller;

import com.dataoObject.ProductCategory;
import com.enums.ResultEnum;
import com.form.CateforyForm;
import com.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author Wudi
 * @Description:  卖家类目
 * @date 8:55  2018/1/18
 */
@RestController
@RequestMapping("/seller/category")
@Slf4j
public class SellerCategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取卖家类目列表
     * @param map
     * @return
     */
    @RequestMapping(value="list",method = RequestMethod.GET)
    public ModelAndView sellerCategoryList(Map<String,Object> map){
        try{

            List<ProductCategory> productCategoryList = categoryService.findAll();
            map.put("productCategoryList",productCategoryList);

        }catch (Exception e){
            log.error("【获取类目列表数据异常】"+e);

            map.put("msg", ResultEnum.RESULT_ERROR);
            return new ModelAndView("",map);
        }
        return new ModelAndView("category/category_list",map);
    }

    @GetMapping("index")
    public ModelAndView sellerCategoryIndex(@RequestParam(value="categoryId",required = false) Integer categoryId,
                                            Map<String,Object> map){
        //含有categoryId修改数据,不含有是添加类目
        if(!StringUtils.isEmpty(categoryId)){
            ProductCategory productCategory = categoryService.findOne(categoryId);
            map.put("productCategoryPage",productCategory);
        }

        return new ModelAndView("category/category_index",map);
    }

    /***
     * 类型变表单的修改和添加
     * @param cateforyForm   form表单上传的数据
     * @param bindingResult  效验不符的条件进行捕获
     * @param map
     * @return
     */
    @PostMapping("addAndUdpate")
    public ModelAndView sellerCategoryAddAndUpdate(@Valid CateforyForm cateforyForm,
                                                   BindingResult bindingResult,
                                                   Map<String,Object> map){
        ProductCategory productCategory = new ProductCategory();
        if (bindingResult.hasErrors()){
            log.info("【卖家类目中判断不符条件】"+bindingResult.getFieldError().getDefaultMessage());
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/category/index?categoryId"+cateforyForm.getCategoryId());

            return new ModelAndView("common/error",map);
        }
        try{
            BeanUtils.copyProperties(cateforyForm,productCategory);
             categoryService.save(productCategory);

        }catch(Exception e){
              log.error("【卖家类目修改和添加数据异常】"+e.getMessage());
              map.put("msg",e.getMessage());
              map.put("url","/sell/seller/category/index?categoryId"+cateforyForm.getCategoryId());

            return new ModelAndView("common/error",map);
        }
            map.put("url","/sell/seller/category/list");

        return new ModelAndView("common/success",map);

    }
}
