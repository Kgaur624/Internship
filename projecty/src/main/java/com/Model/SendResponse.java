package com.Model;

public class SendResponse {
    private String message;
    /**
     * Used to send response on tweet posted.
     * @param message is a response message.
     */
    public SendResponse(String message) {
        this.message = message;
    }

    public SendResponse() {
    }
    public String getMessage() {
        return message;
    }

    public String setMessage(String message) {
        this.message = message;
        return message;
    }


}

