
/*      Setup       */
CREATE DATABASE IF NOT EXISTS WebChess;

USE WebChess;
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS User, Game;
SET FOREIGN_KEY_CHECKS = 1;

CREATE USER IF NOT EXISTS 'webserver' IDENTIFIED BY 'password'; /*todo: Change Password*/
GRANT SELECT, INSERT, UPDATE ON WebChess.* TO 'webserver';

/*      Tables      */

CREATE TABLE User (
    userID INT AUTO_INCREMENT,
    username VARCHAR(16) UNIQUE ,
    password VARCHAR(16),

    PRIMARY KEY (userID)
);

CREATE TABLE Game (
    gameId INT AUTO_INCREMENT,
    winnerUserId INT,
    loserUserId INT,
    data DATETIME DEFAULT current_timestamp() ON UPDATE current_timestamp(),

    PRIMARY KEY (gameId),
    FOREIGN KEY (winnerUserId) REFERENCES User (userID),
    FOREIGN KEY (loserUserId) REFERENCES User (userID)
);

/*      Test Data       */

INSERT INTO User (username, password) VALUES ('root', 'root'), ('aidan', 'morris');

INSERT INTO Game (winnerUserId, loserUserId) VALUES (1, 2);
INSERT INTO Game (winnerUserId, loserUserId) VALUES (2, 1);
INSERT INTO Game (winnerUserId, loserUserId) VALUES (1, 2);
INSERT INTO Game (winnerUserId, loserUserId) VALUES (2, 1);
INSERT INTO Game (winnerUserId, loserUserId) VALUES (2, 1);


