package com.Controller;

import com.Utils.KeyUtils;
import com.dataoObject.ProductCategory;
import com.dataoObject.ProductInfo;
import com.form.ProductForm;
import com.service.CategoryService;
import com.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


/**
 * @author Wudi
 * @Description:
 * @date 10:22  2018/1/17
 */
@RestController
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("list")
    public ModelAndView productList(@RequestParam(value="page",defaultValue = "1") Integer page,
                                    @RequestParam(value="size",defaultValue = "10") Integer size,
                                    Map<String,Object> map){

            PageRequest pageRequest = new PageRequest(page - 1, size);
            Page<ProductInfo> productInfoPage = productInfoService.findAll(pageRequest);
            map.put("productInfoPage",productInfoPage); //总数据
            map.put("contentPage",page);   //当前页
            map.put("contentSize",size);   //当前页数据

        return new ModelAndView("product/product_list",map);

    }

    /**
     * 商品上架
     * @param productId  商品id
     * @param map
     * @return
     */
    @RequestMapping(value = "onSale",method = RequestMethod.GET)
    public ModelAndView onSale(@RequestParam(value = "productId") String productId,
                               Map<String,Object> map){
        log.info("【商品上架初始化参数】"+productId);

        try {
            ProductInfo productInfo = productInfoService.onSale(productId);
        }catch(Exception e){
            log.error("【上架商品数据异常】"+e);
            map.put("msg",e.getMessage());
            map.put("url","common/error");

            return new ModelAndView("common/error",map);
        }

            map.put("url","/sell/seller/product/list");

        return new ModelAndView("common/success",map);

    }

    /**
     * 商品下架
     * @param productId 商品id
     * @param map
     * @return
     */
    @RequestMapping(value = "offSale",method = RequestMethod.GET)
    public ModelAndView offSale(@RequestParam(value = "productId") String productId,
                               Map<String,Object> map){
        log.info("【下架商品初始化参数】"+productId);

        try {
            ProductInfo productInfo = productInfoService.offSale(productId);
        }catch(Exception e){
            log.error("【下架商品数据异常】"+e);
            map.put("msg",e.getMessage());
            map.put("url","common/error");

            return new ModelAndView("common/error",map);
        }
            map.put("url","/sell/seller/product/list");

        return new ModelAndView("common/success",map);

    }

    /**
     * 商品的添加和修改
     * @param productId
     * @return
     */
    @RequestMapping(value="index",method = RequestMethod.GET)
    public ModelAndView addAndUpdate(@RequestParam(value = "productId",required = false) String productId,
                                     Map<String,Object> map){
        log.info("【商品的添加和修改初始化数据】"+productId);
        if(!StringUtils.isEmpty(productId)){
            //通过商品id查询商品的基本数据
            ProductInfo productInfoBase = productInfoService.findOne(productId);
            map.put("productInfoBase",productInfoBase);
        }
           //查询所有的类目
           List<ProductCategory> categoryServiceAll = categoryService.findAll();
           map.put("contentCategoryAll",categoryServiceAll);

        return new ModelAndView("product/product_index",map);
    }

    /**
     * 商品的添加和修改
     * @param productForm form表单提交并且效验的数据
     * @param bindingResult  效验数据返回的信息
     * @param map
     * @return
     */
    @RequestMapping(value="addAndUdpate",method= RequestMethod.POST)
    public ModelAndView addAndUpdate(@Valid ProductForm productForm,
                                     BindingResult bindingResult,
                                     Map<String,Object> map){


            ProductInfo productInfo = new ProductInfo();
            String keys= productForm.getProductId() == null? KeyUtils.getUniqueKey(): productForm.getProductId();
            productForm.setProductId(keys);
            map.put("url","/sell/seller/product/list");

            //效验添加或者修改的参数
            if (bindingResult.hasErrors()){//有效验错的时候,bindingResult.hasErrors()为true
                    // throw new SellException(1,bindingResult.getFieldError().getDefaultMessage())
                    map.put("msg",bindingResult.getFieldError().getDefaultMessage());

                return new ModelAndView("common/error",map);
            }

            //商品添加或者修改
            BeanUtils.copyProperties(productForm,productInfo);
            try {

                ProductInfo save = productInfoService.save(productInfo);

            }catch (Exception e){
                log.error("【后台商品修改数据异常】"+e);

                return new ModelAndView("common/error",map);
            }

        return new ModelAndView("common/success",map);
    }

}
