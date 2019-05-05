package com.sqlist.web.domain;

import javax.persistence.*;

/**
 * @author SqList
 * @date 2019/5/1 0:52
 * @description
 **/
@Table(name = "task_unit_output")
public class TaskUnitOutput extends TaskUnitJob {

    /**
     * 目标的类型(kafka,http)
     */
    private String type;

    /**
     * 目标的ip
     */
    private String ip;

    /**
     * 目标端口
     */
    private String port;

    /**
     * 目标url
     */
    private String url;

    /**
     * 获取目标的类型(kafka,http)
     *
     * @return type - 目标的类型(kafka,http)
     */
    public String getType() {
        return type;
    }

    /**
     * 设置目标的类型(kafka,http)
     *
     * @param type 目标的类型(kafka,http)
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取目标的ip
     *
     * @return ip - 目标的ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置目标的ip
     *
     * @param ip 目标的ip
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * 获取目标端口
     *
     * @return port - 目标端口
     */
    public String getPort() {
        return port;
    }

    /**
     * 设置目标端口
     *
     * @param port 目标端口
     */
    public void setPort(String port) {
        this.port = port == null ? null : port.trim();
    }

    /**
     * 获取目标url
     *
     * @return url - 目标url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置目标url
     *
     * @param url 目标url
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}