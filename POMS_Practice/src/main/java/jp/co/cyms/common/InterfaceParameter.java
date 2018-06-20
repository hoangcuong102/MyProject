/*
 * CLASS   : InterfaceParameter
 * PACKAGE : jp.co.cyms.common
 * VERSION : 1.0
 * HISTORY : 2014-08-01　LSC　LU CREATE
 */
package jp.co.cyms.common;

/**
 * <p>
 * [クラス] InterfaceParameter インターフェースパラメーター
 * </p>
 * <p>
 * [概 要] インターフェースのパラメーターを取得且つセットする。
 * </p>
 * <p>
 * [詳 細] インターフェースのパラメーターを取得且つセットする。
 * </p>
 * 
 * @author LSC LU
 * @since 2014/08/01
 */
public class InterfaceParameter {

    /**
     * InterfaceParameterのコンストラクタ
     * 
     * @author LSC LU
     * @since 2014/08/01
     */
    public InterfaceParameter() {
    }

    /**
     * パラメーター保存
     * 
     * @param key String キー
     * @param value Object パラメーター
     */
    static public void saveInterfaceParameter(String key, Object value) {
        SystemCommon.addSession(key, value);
    }

    /**
     * パラメーター取得
     * 
     * @param key String キー
     * @param <P> Class
     * @param p p
     * @return P パラメーター
     */
    @SuppressWarnings("unchecked")
    static public <P> P readInterfaceParameter(String key, Class<P> p) {
        P parameter = (P) SystemCommon.getSession(key);
        // SystemCommon.removeSession(key);
        return parameter;
    }
}
