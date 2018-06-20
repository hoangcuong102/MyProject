package jp.co.cyms.apps.faa001.bean;

import jp.co.cyms.common.StringUtil;

/**
 * Created by luanvv on 06/02/2017.
 */
public class MenuQueryParam {
    private String functionId;
    private String screenId;

    public MenuQueryParam(String functionId, String screenId) {
        if(StringUtil.isEmpty(functionId) || StringUtil.isEmpty(screenId)){
            throw new NullPointerException();
        }
        this.functionId = functionId;
        this.screenId = screenId;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }
}
