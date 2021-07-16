USE TWEET_APP_DB;

set character_set_client=utf8;
set character_set_connection=utf8;

##①DESTINATIONテーブル　データ追加 例INSERT INTO TWEET VALUES('名前','分',NOW());
INSERT INTO TWEET VALUES('深瀬大地','これが３度目のつぶやきです。','2020/12/06 23:01:34');
INSERT INTO TWEET VALUES('深瀬大地','これが２度目のつぶやきです。','2020/12/06 23:01:33');
INSERT INTO TWEET VALUES('深瀬大地','これが１度目のつぶやきです。','2020/12/06 23:01:32');
INSERT INTO TWEET VALUES('安倍晋三','総理大臣になりました。いえい。','2020/12/06 23:01:31');
INSERT INTO TWEET VALUES('安倍晋三','総理大臣になりました。','2020/12/06 23:01:30');

##②USERS テーブル
INSERT INTO USERS VALUES ('u0001', 'pass');
INSERT INTO USERS VALUES ('a0001', 'pass');

##③USER_ROLES テーブル
INSERT INTO USER_ROLES VALUES ('u0001', 'users');
INSERT INTO USER_ROLES VALUES ('a0001', 'admins');

COMMIT;
