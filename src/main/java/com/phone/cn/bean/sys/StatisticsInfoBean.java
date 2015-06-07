package com.phone.cn.bean.sys;

import com.phone.cn.entity.sys.StatisticsInfo;

@SuppressWarnings("serial")
public class StatisticsInfoBean extends StatisticsInfo {
    private Integer id;

    private Integer downloadAmount;

    private Integer registAmount;
    
   

    private Integer downloadPeople;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDownloadAmount() {
        return downloadAmount;
    }

    public void setDownloadAmount(Integer downloadAmount) {
        this.downloadAmount = downloadAmount;
    }

    public Integer getRegistAmount() {
        return registAmount;
    }

    public void setRegistAmount(Integer registAmount) {
        this.registAmount = registAmount;
    }

    public Integer getDownloadPeople() {
        return downloadPeople;
    }

    public void setDownloadPeople(Integer downloadPeople) {
        this.downloadPeople = downloadPeople;
    }
}