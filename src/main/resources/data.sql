-- expects the csv on the classpath (src/main/resources)
INSERT INTO GAME (APP_ID, NAME, RELEASE_DATE, ENGLISH, DEVELOPER, PUBLISHER, PLATFORMS, REQUIRED_AGE, CATEGORIES,GENRES, STEAMSPY_TAGS, ACHIEVEMENTS, POSITIVE_RATINGS, NEGATIVE_RATINGS, AVERAGE_PLAYTIME,MEDIAN_PLAYTIME, OWNERS, PRICE) SELECT * FROM CSVREAD('classpath:steam.csv')