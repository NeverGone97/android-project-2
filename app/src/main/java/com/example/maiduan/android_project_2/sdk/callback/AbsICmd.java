package com.example.maiduan.android_project_2.sdk.callback;

/**
 * Author: Lê Công Long Vũ
 * Date: 10/12/2017
 * Email: leconglongvu@gmail.com
 */
public abstract class AbsICmd implements ICmd {

    @Override
    public void run() {
        try {
            invoke();
        } catch (Exception e) {
            exception(e.getMessage());
        }
    }

    protected abstract void invoke();

    protected abstract void exception(String message);
}