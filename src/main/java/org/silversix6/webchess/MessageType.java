package org.silversix6.webchess;

import com.google.gson.annotations.SerializedName;

public enum MessageType {
    @SerializedName(value = "0")
    LOGIN,
    @SerializedName(value = "1")
    LEADERBOARD
}
