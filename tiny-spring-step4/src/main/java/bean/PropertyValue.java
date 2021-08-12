package bean;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/12 下午3:07
× 属性值
 */
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name,Object value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
