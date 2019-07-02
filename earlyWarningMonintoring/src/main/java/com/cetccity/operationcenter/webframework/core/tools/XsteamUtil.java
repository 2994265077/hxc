package com.cetccity.operationcenter.webframework.core.tools;

import com.thoughtworks.xstream.XStream;

public class XsteamUtil {

    public static Object toBean(Class<?> clazz, String xml) {
        Object xmlObject;
        XStream xstream = new XStream();
        xstream.processAnnotations(clazz); xstream.autodetectAnnotations(true);
        xmlObject= xstream.fromXML(xml);
        return xmlObject;
    }

}




