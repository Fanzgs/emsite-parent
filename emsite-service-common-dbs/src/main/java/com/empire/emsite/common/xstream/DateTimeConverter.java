/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.common.xstream;

import java.util.Date;

import com.empire.emsite.common.utils.DateUtils;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * 类DateTimeConverter.java的实现描述：XStream 日期转换类
 * 
 * @author arron 2017年10月30日 下午4:05:55
 */
public class DateTimeConverter implements Converter {

    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        Date date = (Date) source;
        if (date != null) {
            writer.setValue(DateUtils.formatDateTime(date));
        } else {
            writer.setValue("");
        }
    }

    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        try {
            Date date = DateUtils.parseDate(reader.getValue());
            return date;
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("rawtypes")
    public boolean canConvert(Class type) {
        return type.equals(Date.class);
    }

}
