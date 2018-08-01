package com.reizx.luaj.bean.event;

import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

/**
 * Tip窗口信息事件，一般我们需要连续弹出TIP的时候才使用TipEvent进行弹窗处理，否则直接调用view中的showtip即可
 */
public class TipEvent {
    private String clazzName;
    private TipAction action;
    private int iconType;
    private String tipWord;

    /**
     * 构造Tip事件
     * @param clazzName 显示Tip的view的类名
     * @param action 行为，显示/销毁
     * @param iconType 图标类型, 当为-1的时候，不显示图标, 当为action为DISMISS时，没有作用
     * @param tipWord 提示语句, 当为action为DISMISS时，没有作用
     */
    public TipEvent(String clazzName, TipAction action, int iconType, String tipWord) {
        this.clazzName = clazzName;
        this.action = action;
        this.iconType = iconType;
        this.tipWord = tipWord;
    }

    public enum  TipAction {
        SHOW,//显示
        DISMISS//销毁
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public int getIconType() {
        return iconType;
    }

    public void setIconType(int iconType) {
        this.iconType = iconType;
    }

    public String getTipWord() {
        return tipWord;
    }

    public void setTipWord(String tipWord) {
        this.tipWord = tipWord;
    }

    public TipAction getAction() {
        return action;
    }

    public void setAction(TipAction action) {
        this.action = action;
    }
}
