package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.ProductInfo;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductInfo> selectAll();
}
