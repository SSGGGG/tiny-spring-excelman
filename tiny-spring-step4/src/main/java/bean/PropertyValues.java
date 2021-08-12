package bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/12 下午3:11
 * 由于一个bean的属性可能会有很多，所以采用一个list装载PropertyValue
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv){
        this.propertyValueList.add(pv);
    }

    /**
     * 返回propertyValue列表生成的数组，而不是直接返回可修改的列表
     * @return
     */
    public PropertyValue[] getPropertyValues(){
        return propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName){
        for(PropertyValue pv:this.propertyValueList){
            if(pv.getName().equals(propertyName))
                return pv;
        }
        return null;
    }
}
