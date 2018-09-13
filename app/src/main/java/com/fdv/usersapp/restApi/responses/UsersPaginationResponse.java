package com.fdv.usersapp.restApi.responses;

import com.fdv.usersapp.models.Info;
import com.fdv.usersapp.models.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsersPaginationResponse {

    @SerializedName("results")
    private List<User> results;
    @SerializedName("info")
    private Info info;

    public List<User> getResults() {
        return results;
    }

    public void setResults(List<User> results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
