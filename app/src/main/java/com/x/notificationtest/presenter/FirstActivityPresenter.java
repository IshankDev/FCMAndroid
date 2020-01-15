package com.x.notificationtest.presenter;

public class FirstActivityPresenter {

    private View view;

    public FirstActivityPresenter(View view) {
        this.view = view;
    }

    public void updateNotiData(String data) {
        view.updateView(data);
    }

    public interface View {

        void updateView(String info);


    }
}
