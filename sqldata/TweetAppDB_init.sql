## MYSQLユーザーの作成
## 権限付与
GRANT ALL ON TWEET_DB_DEV.* to 'tweetuser'@'%' with grant option;

## 反映
FLUSH PRIVILEGES;

##TOURDBデーターベースの作成
DROP DATABASE IF EXISTS TWEET_DB_DEV;
CREATE DATABASE TWEET_DB_DEV;
USE TWEET_DB_DEV;

##①USERS テーブル
CREATE TABLE USERS (
  USERID CHAR(20) PRIMARY KEY NOT NULL,
  NAME VARCHAR(100),
  PASSWORD VARCHAR(500) NOT NULL,
  ROLE CHAR(20) NOT NULL,
  ICON_URL VARCHAR(500)
);

##②TWEET テーブル
CREATE TABLE TWEET (
  TWEETID BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  USERID CHAR(20) NOT NULL,
  TWEET VARCHAR(250),
  IMG_URL VARCHAR(500),
  DATE  DATETIME,
  FOREIGN KEY(USERID) REFERENCES USERS(USERID)
);

set character_set_client=utf8;
set character_set_connection=utf8;


##①USERS テーブル(passwordはpass)
INSERT INTO USERS VALUES ('u0001','あきら', '2691014f61ad465cdfd4885c5cc5e35e93a6ad43c7d94c4ba7ce616c594b0126f09df66474492216','USER','/img/icon/u0001.jpg');
INSERT INTO USERS VALUES ('a0001','たかし', '30c17acc66db1a90cd0e4533fe11661bf90599eca5624205999b057f4ff75a5e7777a3c1f2aba000','ADMIN','/img/icon/a0001.jpg');

##②DESTINATIONテーブル　データ追加
INSERT INTO TWEET (USERID,TWEET,DATE) VALUES('u0001','これが３度目のつぶやきです。','2020/12/06 23:01:34');
INSERT INTO TWEET (USERID,TWEET,DATE) VALUES('u0001','これが２度目のつぶやきです。','2020/12/06 23:01:33');
INSERT INTO TWEET (USERID,TWEET,DATE) VALUES('a0001','これが１度目のつぶやきです。','2020/12/06 23:01:32');
INSERT INTO TWEET (USERID,TWEET,DATE) VALUES('u0001','総理大臣になりました。いえい。','2020/12/06 23:01:31');
INSERT INTO TWEET (USERID,TWEET,DATE) VALUES('a0001','総理大臣になりました。','2020/12/06 23:01:30');

COMMIT;
