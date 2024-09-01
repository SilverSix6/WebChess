package org.silversix6.webchess;

import com.google.gson.annotations.SerializedName;

public enum MessageType {
    @SerializedName(value = "0")
    LOGIN,
    @SerializedName(value = "1")
    LEADERBOARD,
    @SerializedName(value = "2")
    MOVE,
    @SerializedName(value = "3")
    CHECKMATE,
    @SerializedName(value = "4")
    NEW_GAME,
    @SerializedName(value = "5")
    JOIN_GAME,
    @SerializedName(value = "6")
    GAME_START,
    @SerializedName(value = "7")
    SIGNUP,
    @SerializedName(value = "8")
    TURN
}
