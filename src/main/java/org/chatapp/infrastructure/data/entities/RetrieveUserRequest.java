package org.chatapp.infrastructure.data.entities;

public class RetrieveUserRequest {
    private String email;

    RetrieveUserRequest(){
    }

    public RetrieveUserRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
