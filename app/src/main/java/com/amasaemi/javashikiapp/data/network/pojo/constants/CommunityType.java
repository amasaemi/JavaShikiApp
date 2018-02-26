package com.amasaemi.javashikiapp.data.network.pojo.constants;

/**
 * Created by Alex on 25.02.2018.
 */

public enum CommunityType {
    USER,
    CLUB;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
