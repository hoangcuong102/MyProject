/*
 * CLASS   : Language
 * PACKAGE : jp.co.cyms.common
 * VERSION : 1.0
 * HISTORY : 2014-08-01　LSC　LU CREATE
 */
package jp.co.cyms.common;

import java.util.Properties;

/**
 * <p>
 * [クラス] Language 言語
 * </p>
 * <p>
 * [概要] メッセージおよびラベルを取得する。
 * </p>
 * <p>
 * [詳細] メッセージおよびラベルを取得する。
 * </p>
 * 
 * @author LSC LU
 * @since 2014/08/01
 */
public class Language {

    /**
     * Languageのコンストラクタ
     */
    public Language() {
    }

    /**
     * メッセージ取得
     * 
     * @param msgId String メッセージID
     * @return String パラメーター
     * @throws Exception Exception
     */
    static public String getMessage(String msgId) throws Exception {
        return SystemCommon.message(msgId, new String[] {});
    }

    /**
     * メッセージ取得
     * 
     * @param msgId String メッセージID
     * @param params String[] パラメーター
     * @return String メッセージ
     * @throws Exception Exception
     */
    static public String getMessage(String msgId, String[] params) throws Exception {
        return SystemCommon.message(msgId, params);
    }

    /**
     * ラベル取得
     * 
     * @param labelId String ラベルID
     * @return String ラベル
     * @throws なし
     */
    static public String getLabel(String labelId) {
        try {
            return SystemCommon.label(labelId, new String[] {});
        } catch (ClassCastException | NullPointerException ex) {
            return "";
        }
    }

    /**
     * ラベル取得
     * 
     * @param labelId String ラベルID
     * @param params String[] パラメーター
     * @return String ラベル
     */
    static public String getLabel(String labelId, String[] params) {
        try {
            return SystemCommon.label(labelId, params);
        } catch (ClassCastException | NullPointerException ex) {
            return "";
        }
    }

    /**
     * ラベル取得
     * 
     * @return Properties ラベル
     * @throws なし
     */
    static public Properties getLabels() {
        try {
            return SystemCommon.labels();
        } catch (ClassCastException | NullPointerException ex) {
            return new Properties();
        }
    }
}