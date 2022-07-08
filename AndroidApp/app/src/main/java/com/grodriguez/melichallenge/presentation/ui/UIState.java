package com.grodriguez.melichallenge.presentation.ui;

import com.grodriguez.melichallenge.framework.utils.UIStatus;

public class UIState {

    private UIStatus currentStatus = UIStatus.UI_IDLE;
    private String message = "";
    private Throwable error;

    public UIState() {
    }

    public UIState(UIStatus status) {
        setCurrentStatus(status);
    }

    public UIState(String message, UIStatus status) {
        setCurrentStatus(status);
        setMessage(message);
    }

    public UIState(Throwable e) {
        setCurrentStatus(UIStatus.UI_ON_ERROR);
        setMessage(e.getMessage());
        setError(e);
    }

    // region GET-SET

    public UIStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(UIStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    // endregion

}// End Class
