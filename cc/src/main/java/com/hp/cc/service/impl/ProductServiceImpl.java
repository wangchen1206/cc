package com.hp.cc.service.impl;

import com.hp.cc.entity.Product;
import com.hp.cc.mapper.ProductMapper;
import com.hp.cc.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cc
 * @since 2019-07-22
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
