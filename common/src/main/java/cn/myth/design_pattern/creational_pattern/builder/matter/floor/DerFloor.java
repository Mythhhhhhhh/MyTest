package cn.myth.design_pattern.creational_pattern.builder.matter.floor;


import cn.myth.design_pattern.creational_pattern.builder.matter.Matter;

import java.math.BigDecimal;

/**
 * 地板
 * 品牌；德尔(Der)
 */
public class DerFloor implements Matter {

    public String scene() {
        return "地板";
    }

    public String brand() {
        return "德尔(Der)";
    }

    public String model() {
        return "A+";
    }

    public BigDecimal price() {
        return new BigDecimal(119);
    }

    public String desc() {
        return "DER德尔集团是全球领先的专业木地板制造商，北京2008年奥运会家装和公装地板供应商";
    }
    
}
