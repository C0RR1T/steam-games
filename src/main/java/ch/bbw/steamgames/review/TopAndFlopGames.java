package ch.bbw.steamgames.review;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public interface TopAndFlopGames {
    String getName();
    String getScore();
}
