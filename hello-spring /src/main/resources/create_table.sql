CREATE TABLE `dev`.`HUGO_DECLARATION` (
    `DECLA_IDX` BIGINT NOT NULL,
    `REASON` VARCHAR(50) NULL,
    `ID` VARCHAR(50) NULL,
    `EVENT_REPLY_IDX` BIGINT NULL,
    `CONTENTS` VARCHAR(4000) NULL,
    PRIMARY KEY (`DECLA_IDX`));

CREATE TABLE `dev`.`HUGO_EVENT_BOARD` (
    `EVENT_IDX` BIGINT NOT NULL,
    `TITLE` VARCHAR(1000) NOT NULL,
    `CONTENT` VARCHAR(4000) NULL,
    `EVENT_PERIOD` VARCHAR(50) NULL,
    `ID` VARCHAR(50) NOT NULL,
    `OFILE` VARCHAR(200) NULL,
    `LIKE_COUNT` BIGINT NOT NULL DEFAULT 0,
    `VISIT_COUNT` BIGINT NOT NULL DEFAULT 0,
    `CREATE_DATE` DATETIME NULL DEFAULT NOW(),
    `WRITEHEADER` VARCHAR(50) NULL,
    `BOARDER` VARCHAR(50) NOT NULL DEFAULT 'runningEvent',
    PRIMARY KEY (`EVENT_IDX`));

CREATE TABLE `dev`.`HUGO_EVENT_IMAGE` (
    `IMG_IDX` BIGINT NOT NULL,
    `IMG_NAME` VARCHAR(100) NOT NULL,
    `EVENT_IDX` BIGINT NOT NULL,
    PRIMARY KEY (`IMG_IDX`));

CREATE TABLE `dev`.`HUGO_EVENT_LIKE` (
    `LIKE_IDX` BIGINT NOT NULL,
    `LIKE_YN` INT NOT NULL DEFAULT 0,
    `EVENT_IDX` BIGINT NOT NULL,
    `ID` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`LIKE_IDX`));

CREATE TABLE `dev`.`HUGO_EVENT_REPLY` (
    `EVENT_REPLY_IDX` BIGINT NOT NULL,
    `NICKNAME` VARCHAR(50) NOT NULL,
    `WRITEDATE` DATETIME NOT NULL DEFAULT NOW(),
    `CONTENT` VARCHAR(2000) NOT NULL,
    `EVENT_IDX` BIGINT NOT NULL,
    PRIMARY KEY (`EVENT_REPLY_IDX`));

CREATE TABLE `dev`.`HUGO_USER_INFO` (
    `ID` VARCHAR(50) NOT NULL,
    `PWD` VARCHAR(50) NOT NULL,
    `NAME` VARCHAR(50) NOT NULL,
    `NICKNAME` VARCHAR(50) NOT NULL,
    `EMAIL` VARCHAR(100) NOT NULL,
    `BIRTHDAY` DATETIME NOT NULL,
    `GENDER` VARCHAR(20) NOT NULL,
    `CALLNUM` VARCHAR(50) NOT NULL,
    `INTEREST` VARCHAR(200) NULL,
    `JOINDATE` DATETIME NULL DEFAULT NOW(),
    PRIMARY KEY (`ID`));

ALTER TABLE `dev`.`HUGO_USER_INFO`
CHANGE COLUMN `ID` `id` VARCHAR(50) NOT NULL ,
CHANGE COLUMN `PWD` `pwd` VARCHAR(50) NOT NULL ,
CHANGE COLUMN `NAME` `name` VARCHAR(50) NOT NULL ,
CHANGE COLUMN `NICKNAME` `nick_name` VARCHAR(50) NOT NULL ,
CHANGE COLUMN `EMAIL` `email` VARCHAR(100) NOT NULL ,
CHANGE COLUMN `BIRTHDAY` `birth_dat` DATE NOT NULL ,
CHANGE COLUMN `GENDER` `gender` VARCHAR(20) NOT NULL ,
CHANGE COLUMN `CALLNUM` `call_num` VARCHAR(50) NOT NULL ,
CHANGE COLUMN `INTEREST` `interest` VARCHAR(200) NULL DEFAULT NULL ,
CHANGE COLUMN `JOINDATE` `join_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ;

ALTER TABLE `dev`.`HUGO_EVENT_REPLY`
CHANGE COLUMN `NICKNAME` `nick_name` VARCHAR(50) NOT NULL ,
CHANGE COLUMN `WRITEDATE` `write_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ,
CHANGE COLUMN `CONTENT` `content` VARCHAR(2000) NOT NULL ,
CHANGE COLUMN `EVENT_IDX` `event_idx` BIGINT NOT NULL ;

ALTER TABLE `dev`.`HUGO_EVENT_LIKE`
CHANGE COLUMN `LIKE_IDX` `like_idx` BIGINT NOT NULL ,
CHANGE COLUMN `LIKE_YN` `like_yn` INT NOT NULL DEFAULT '0' ,
CHANGE COLUMN `EVENT_IDX` `event_idx` BIGINT NOT NULL ,
CHANGE COLUMN `ID` `id` VARCHAR(50) NOT NULL ;

ALTER TABLE `dev`.`HUGO_EVENT_IMAGE`
CHANGE COLUMN `IMG_IDX` `img_idx` BIGINT NOT NULL ,
CHANGE COLUMN `IMG_NAME` `img_name` VARCHAR(100) NOT NULL ,
CHANGE COLUMN `EVENT_IDX` `event_idx` BIGINT NOT NULL ;

ALTER TABLE `dev`.`HUGO_EVENT_BOARD`
CHANGE COLUMN `EVENT_IDX` `event_idx` BIGINT NOT NULL ,
CHANGE COLUMN `TITLE` `title` VARCHAR(1000) NOT NULL ,
CHANGE COLUMN `CONTENT` `content` VARCHAR(4000) NULL DEFAULT NULL ,
CHANGE COLUMN `EVENT_PERIOD` `event_period` VARCHAR(50) NULL DEFAULT NULL ,
CHANGE COLUMN `ID` `id` VARCHAR(50) NOT NULL ,
CHANGE COLUMN `OFILE` `ofile` VARCHAR(200) NULL DEFAULT NULL ,
CHANGE COLUMN `LIKE_COUNT` `like_count` BIGINT NOT NULL DEFAULT '0' ,
CHANGE COLUMN `VISIT_COUNT` `visit_count` BIGINT NOT NULL DEFAULT '0' ,
CHANGE COLUMN `CREATE_DATE` `create_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ,
CHANGE COLUMN `WRITEHEADER` `write_header` VARCHAR(50) NULL DEFAULT NULL ,
CHANGE COLUMN `BOARDER` `boarder` VARCHAR(50) NOT NULL DEFAULT 'runningEvent' ;

ALTER TABLE `dev`.`HUGO_DECLARATION`
CHANGE COLUMN `DECLA_IDX` `decla_idx` BIGINT NOT NULL ,
CHANGE COLUMN `REASON` `reson` VARCHAR(50) NULL DEFAULT NULL ,
CHANGE COLUMN `ID` `id` VARCHAR(50) NULL DEFAULT NULL ,
CHANGE COLUMN `EVENT_REPLY_IDX` `event_reply_idx` BIGINT NULL DEFAULT NULL ,
CHANGE COLUMN `CONTENTS` `content` VARCHAR(4000) NULL DEFAULT NULL ;

ALTER TABLE `dev`.`HUGO_EVENT_BOARD`
RENAME TO  `dev`.`HUGO_BOARD` ;

ALTER TABLE `dev`.`HUGO_EVENT_IMAGE`
RENAME TO  `dev`.`HUGO_BOARD_IMAGE` ;

ALTER TABLE `dev`.`HUGO_EVENT_LIKE`
CHANGE COLUMN `event_idx` `board_idx` BIGINT NOT NULL , RENAME TO  `dev`.`hugo_board_like` ;

ALTER TABLE `dev`.`HUGO_BOARD_IMAGE`
CHANGE COLUMN `event_idx` `board_idx` BIGINT NOT NULL ;

ALTER TABLE `dev`.`HUGO_EVENT_REPLY`
CHANGE COLUMN `EVENT_REPLY_IDX` `board_reply_idx` BIGINT NOT NULL ,
CHANGE COLUMN `event_idx` `board_idx` BIGINT NOT NULL , RENAME TO  `dev`.`hugo_board_reply` ;

ALTER TABLE `dev`.`HUGO_DECLARATION`
CHANGE COLUMN `event_reply_idx` `board_reply_idx` BIGINT NULL DEFAULT NULL ;

ALTER TABLE `dev`.`HUGO_BOARD`
CHANGE COLUMN `event_idx` `board_idx` BIGINT NOT NULL ;

ALTER TABLE `dev`.`HUGO_USER_INFO`
CHANGE COLUMN `birth_dat` `birth_day` DATE NOT NULL ;

ALTER TABLE `dev`.`HUGO_BOARD`
CHANGE COLUMN `board_idx` `board_idx` BIGINT NOT NULL AUTO_INCREMENT ;

ALTER TABLE `dev`.`HUGO_BOARD`
CHANGE COLUMN `boarder` `boarder` VARCHAR(50) NOT NULL DEFAULT 'freeBoard' ;

ALTER TABLE `dev`.`hugo_board_like`
CHANGE COLUMN `like_idx` `like_idx` BIGINT NOT NULL AUTO_INCREMENT ;

ALTER TABLE `dev`.`hugo_board_reply`
CHANGE COLUMN `board_reply_idx` `board_reply_idx` BIGINT NOT NULL AUTO_INCREMENT ;

ALTER TABLE `dev`.`HUGO_USER_INFO`
ADD UNIQUE INDEX `nick_name_UNIQUE` (`nick_name` ASC) VISIBLE;

DROP TABLE `dev`.`HUGO_DECLARATION`;

CREATE TABLE `dev`.`hugo_declaration` (
  `declaration_idx` BIGINT NOT NULL AUTO_INCREMENT,
  `reason` VARCHAR(100) NOT NULL,
  `id` VARCHAR(50) NOT NULL,
  `board_reply_idx` BIGINT NOT NULL,
  `content` VARCHAR(4000) NULL,
  PRIMARY KEY (`declaration_idx`));

  ALTER TABLE `dev`.`HUGO_USER_INFO`
  ADD COLUMN `idx` BIGINT NOT NULL AUTO_INCREMENT AFTER `join_date`,
  ADD UNIQUE INDEX `idx_UNIQUE` (`idx` ASC) VISIBLE;

  ALTER TABLE `dev`.`HUGO_BOARD`
  ADD COLUMN `filecode` VARCHAR(45) NULL AFTER `boarder`;

  ALTER TABLE `dev`.`HUGO_BOARD`
  CHANGE COLUMN `filecode` `file_code` VARCHAR(45) NULL DEFAULT NULL ;