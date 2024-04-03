package com.bootx.util.wechat.response.datacube;

import com.bootx.util.wechat.BaseResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author black
 */
public class DataCubePublisherAdposGeneralResponse extends BaseResponse {

    @JsonProperty("base_resp")
    private BaseRespBean baseResp = new BaseRespBean();
    private SummaryBean summary = new SummaryBean();
    private List<ListBean> list = new ArrayList();

    /**
     * list返回总条数
     */
    @JsonProperty("total_num")
    private Integer totalNum;

    public BaseRespBean getBaseResp() {
        return baseResp;
    }

    public void setBaseResp(BaseRespBean baseResp) {
        this.baseResp = baseResp;
    }

    public SummaryBean getSummary() {
        return summary;
    }

    public void setSummary(SummaryBean summary) {
        this.summary = summary;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class BaseRespBean {
        @JsonProperty("err_msg")
        private String errMsg;
        @JsonProperty("ret")
        private Integer ret;

        public String getErrMsg() {
            return errMsg;
        }

        public void setErrMsg(String errMsg) {
            this.errMsg = errMsg;
        }

        public Integer getRet() {
            return ret;
        }

        public void setRet(Integer ret) {
            this.ret = ret;
        }
    }

    public static class SummaryBean {

        /**
         *总点击量
         */
        @JsonProperty("click_count")
        private Integer clickCount;
        /**
         *总点击率
         */
        @JsonProperty("click_rate")
        private Integer clickRate;
        /**
         *广告千次曝光收益(分)
         */
        private Integer ecpm;
        /**
         *总曝光量
         */
        @JsonProperty("exposure_count")
        private Integer exposureCount;
        /**
         *总曝光率
         */
        @JsonProperty("exposure_rate")
        private Integer exposureRate;
        /**
         *总收入(分)
         */
        private Integer income;
        /**
         *总拉取量
         */
        @JsonProperty("req_succ_count")
        private Integer reqSuccCount;

        public Integer getClickCount() {
            return clickCount;
        }

        public void setClickCount(Integer clickCount) {
            this.clickCount = clickCount;
        }

        public Integer getClickRate() {
            return clickRate;
        }

        public void setClickRate(Integer clickRate) {
            this.clickRate = clickRate;
        }

        public Integer getEcpm() {
            return ecpm;
        }

        public void setEcpm(Integer ecpm) {
            this.ecpm = ecpm;
        }

        public Integer getExposureCount() {
            return exposureCount;
        }

        public void setExposureCount(Integer exposureCount) {
            this.exposureCount = exposureCount;
        }

        public Integer getExposureRate() {
            return exposureRate;
        }

        public void setExposureRate(Integer exposureRate) {
            this.exposureRate = exposureRate;
        }

        public Integer getIncome() {
            return income;
        }

        public void setIncome(Integer income) {
            this.income = income;
        }

        public Integer getReqSuccCount() {
            return reqSuccCount;
        }

        public void setReqSuccCount(Integer reqSuccCount) {
            this.reqSuccCount = reqSuccCount;
        }
    }


    public static class ListBean {

        /**
         *广告位类型名称
         */
        @JsonProperty("ad_slot")
        private String adSlot;

        /**
         *点击量
         */
        @JsonProperty("click_count")
        private Integer clickCount;

        /**
         *点击率
         */
        @JsonProperty("click_rate")
        private Integer clickRate;

        /**
         *日期
         */
        private String date;

        /**
         *广告千次曝光收益(分)
         */
        private Double ecpm;

        /**
         *曝光量
         */
        @JsonProperty("exposure_count")
        private Integer exposureCount;

        /**
         *	曝光率
         */
        @JsonProperty("exposure_rate")
        private Double exposureRate;

        /**
         *收入(分)
         */
        private Integer income;

        /**
         *拉取量
         */
        @JsonProperty("req_succ_count")
        private Integer reqSuccCount;

        /**
         *广告位类型id
         */
        @JsonProperty("slot_id")
        private Long slotId;

        /**
         *
         */
        @JsonProperty("slot_str")
        private Long slotStr;

        public String getAdSlot() {
            return adSlot;
        }

        public void setAdSlot(String adSlot) {
            this.adSlot = adSlot;
        }

        public Integer getClickCount() {
            return clickCount;
        }

        public void setClickCount(Integer clickCount) {
            this.clickCount = clickCount;
        }

        public Integer getClickRate() {
            return clickRate;
        }

        public void setClickRate(Integer clickRate) {
            this.clickRate = clickRate;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Double getEcpm() {
            return ecpm;
        }

        public void setEcpm(Double ecpm) {
            this.ecpm = ecpm;
        }

        public Integer getExposureCount() {
            return exposureCount;
        }

        public void setExposureCount(Integer exposureCount) {
            this.exposureCount = exposureCount;
        }

        public Double getExposureRate() {
            return exposureRate;
        }

        public void setExposureRate(Double exposureRate) {
            this.exposureRate = exposureRate;
        }

        public Integer getIncome() {
            return income;
        }

        public void setIncome(Integer income) {
            this.income = income;
        }

        public Integer getReqSuccCount() {
            return reqSuccCount;
        }

        public void setReqSuccCount(Integer reqSuccCount) {
            this.reqSuccCount = reqSuccCount;
        }

        public Long getSlotId() {
            return slotId;
        }

        public void setSlotId(Long slotId) {
            this.slotId = slotId;
        }

        public Long getSlotStr() {
            return slotStr;
        }

        public void setSlotStr(Long slotStr) {
            this.slotStr = slotStr;
        }
    }
}
