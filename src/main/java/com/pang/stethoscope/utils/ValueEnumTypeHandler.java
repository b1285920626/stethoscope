package com.pang.stethoscope.utils;

import com.pang.stethoscope.constant.enums.BaseValueEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author BaiPang
 * @date 2020/3/11 16:01
 */
public class ValueEnumTypeHandler<E extends Enum<?> & BaseValueEnum> extends BaseTypeHandler {
    /**
     * 枚举的class
     */
    private Class<E> type;
    /**
     * 枚举的每个子类枚
     */
    private E[] enums;

    /**
     * 一定要有默认的构造函数, 不然抛出 not found method 异常
     */
    public ValueEnumTypeHandler() {
    }

    /**
     * 设置配置文件设置的转换类以及枚举类内容, 供其他方法更便捷高效的实现
     *
     * @param type 配置文件中设置的转换类
     */
    public ValueEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
        this.enums = type.getEnumConstants();
        if (this.enums == null) {
            throw new IllegalArgumentException(type.getSimpleName()
                    + " does not represent an enum type.");
        }
    }

    /**
     * 用于定义设置参数时，该如何把Java类型的参数转换为对应的数据库类型
     * */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter,
                                    JdbcType jdbcType) throws SQLException {
        /*
         * BaseTypeHandler已经帮我们做了parameter的null判断
         * 数据库存储的是枚举的值, 所以我们这里使用 value ,  如果需要存储 name, 可以自定义修改
         */
        if (jdbcType == null) {
            ps.setString(i, Objects.toString(((BaseValueEnum)parameter).getValue()));
        } else {
            ps.setObject(i, ((BaseValueEnum)parameter).getValue(), jdbcType.TYPE_CODE);
        }
    }

    /**
     * 用于定义通过字段名称获取字段数据时，如何把数据库类型转换为对应的Java类型
     * */
    @Override
    public E getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        String i = rs.getString(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            return locateEnumStatus(i);
        }
    }

    /**
     * 用于定义通过字段索引获取字段数据时，如何把数据库类型转换为对应的Java类型
     * */
    @Override
    public E getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        String i = rs.getString(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            return locateEnumStatus(i);
        }
    }

    /**
     * 用定义调用存储过程后，如何把数据库类型转换为对应的Java类型
     * */
    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        String i = cs.getString(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            return locateEnumStatus(i);
        }
    }

    /**
     * 枚举类型转换
     *
     * @param value 数据库中存储的自定义value属性
     * @return value 对应的枚举类
     */
    private E locateEnumStatus(String value) {
        for (E e : enums) {
            if (Objects.toString(e.getValue()).equals(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException("未知的枚举类型：" + value + ",请核对"
                + type.getSimpleName());
    }

}
