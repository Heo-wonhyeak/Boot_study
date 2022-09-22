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




