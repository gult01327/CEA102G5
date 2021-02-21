CREATE DATABASE IF NOT EXISTS myProject;
USE myProject;

-- 以下為會員 -- 

CREATE TABLE MEMBER_INFO (
MEM_ID INT AUTO_INCREMENT NOT NULL,
MEM_NAME VARCHAR(10),
MEM_ACCOUNT VARCHAR(20),
MEM_PASSWORD VARCHAR(20),
MEM_PHONE VARCHAR(10),
MEM_EMAIL VARCHAR(30),
MEM_BONUS INT,
MEM_STATUS BOOLEAN,
MEM_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT MEMBER_INFO_MEM_ID_PK PRIMARY KEY (MEM_ID)
);

INSERT INTO MEMBER_INFO (MEM_NAME,MEM_ACCOUNT,MEM_PASSWORD,MEM_PHONE,MEM_EMAIL,MEM_STATUS) VALUES ('周杰倫','a12345','a1234','0933456789','jc@gmail.com',0);
INSERT INTO MEMBER_INFO (MEM_NAME,MEM_ACCOUNT,MEM_PASSWORD,MEM_PHONE,MEM_EMAIL,MEM_STATUS) VALUES ('方文山','b432432','faad54','0912524354','dsd@gmail.com',0);
INSERT INTO MEMBER_INFO (MEM_NAME,MEM_ACCOUNT,MEM_PASSWORD,MEM_PHONE,MEM_EMAIL,MEM_STATUS) VALUES ('蔡依林','c65634','hhjr4','0937943256','ffa@qq.com',0);
INSERT INTO MEMBER_INFO (MEM_NAME,MEM_ACCOUNT,MEM_PASSWORD,MEM_PHONE,MEM_EMAIL,MEM_STATUS) VALUES ('侯佩岑','d245432','adv33','0987564358','thf@yahoo.com',0);
INSERT INTO MEMBER_INFO (MEM_NAME,MEM_ACCOUNT,MEM_PASSWORD,MEM_PHONE,MEM_EMAIL,MEM_STATUS) VALUES ('蕭敬騰','eekh675','aadf','0922874359','adf@163.com',0);

-- 以下為後台管理員 -- 

create table ADMIN_INFO (
	ADM_ID				INT PRIMARY KEY AUTO_INCREMENT,
	ADM_NAME			varchar(100),
	ADM_ACCOUNT 		varchar(20),
	ADM_PASSWORD 		varchar(20),
    MEM_TIME 			timestamp DEFAULT CURRENT_TIMESTAMP
);

create table FUNCTION_INFO (
	FUN_ID				INT PRIMARY KEY AUTO_INCREMENT,
	FUN_NAME			varchar(30)
);

create table AUTHORITY (
	ADM_ID				INT NOT NULL,
	FUN_ID				INT NOT NULL,
    CONSTRAINT fk_AUTHORITY_ADMIN_INFO
    FOREIGN KEY (ADM_ID) references ADMIN_INFO(ADM_ID),
    CONSTRAINT FK_AUTHORITY_FUNCTION_INFO
    FOREIGN KEY (FUN_ID) references FUNCTION_INFO(FUN_ID),
	PRIMARY KEY (ADM_ID, FUN_ID)
);

-- 以下為商城 --

CREATE TABLE COMMODITY_CATEGORY(
COMC_ID INT AUTO_INCREMENT,
COMC_NAME VARCHAR(10),
CONSTRAINT COMMODITY_CATEGORY_COM_ID_PK PRIMARY KEY(COMC_ID)
);

CREATE TABLE COMMODITY(
COM_ID INT AUTO_INCREMENT NOT NULL,
COMC_ID INT NOT NULL,
COM_NAME VARCHAR(10),
COM_PRICE INT,
COM_PICTURE BLOB,
COM_CONTENT VARCHAR(60),
COM_SALES INT,
COM_STATUS BOOLEAN,
COM_WEIGHT INT,
COM_UNIT VARCHAR(10),
COM_CAL DECIMAL(5,2),
COM_CARB DECIMAL(5,2),
COM_PRO DECIMAL(5,2),
COM_FAT DECIMAL(5,2),
COM_PROP VARCHAR(10),
COM_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
CONSTRAINT COMMODITY_COM_ID_PK PRIMARY KEY (COM_ID),
CONSTRAINT COMMODITY_COMC_ID FOREIGN KEY (COMC_ID) REFERENCES COMMODITY_CATEGORY(COMC_ID)
);

CREATE TABLE ORDER_MASTER(
ORDM_ID INT AUTO_INCREMENT NOT NULL,
MEM_ID INT NOT NULL,
MEMR_ID INT NOT NULL,
ORDM_PRICE INT,
ORDM_PAY BOOLEAN,
ORDM_SHIP BOOLEAN,
ORDM_DISCOUNT BOOLEAN,
ORDM_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
CONSTRAINT ORDER_MASTER_ORDM_ID PRIMARY KEY (ORDM_ID),
CONSTRAINT ORDER_MASTER_MEM_ID FOREIGN KEY(MEM_ID) REFERENCES MEMBER_INFO(MEM_ID)
);

CREATE TABLE MEMBER_RECIPIENT(
MEMR_ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
MEM_ID INT NOT NULL,
MEMR_NAME VARCHAR(10),
MEMR_PHONE INT,
MEMR_ADDRESS VARCHAR(30),
CONSTRAINT MEMBER_RECIPIENT_MEM_ID FOREIGN KEY(MEM_ID) REFERENCES MEMBER_INFO(MEM_ID)
);

CREATE TABLE ORDER_DETAIL(
ORDM_ID INT NOT NULL,
COM_ID  INT NOT NULL,
ORDD_COUNT INT NOT NULL,
ORDD_PRICE INT,
ORDD_RETURN BOOLEAN,
ORDD_REASON VARCHAR(60),
ORDD_RTIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
CONSTRAINT ORDER_DETAIL_ORDM_ID_COM_ID_PK PRIMARY KEY(ORDM_ID,COM_ID),
CONSTRAINT ORDER_DETAIL_ORDM_ID_FK FOREIGN KEY (ORDM_ID) REFERENCES ORDER_MASTER(ORDM_ID),
CONSTRAINT ORDER_DETAIL_COM_ID_FK FOREIGN KEY (COM_ID) REFERENCES COMMODITY(COM_ID)
);

CREATE TABLE CART_DETAIL(
MEM_ID INT  NOT NULL,
COM_ID INT  NOT NULL,
CARD_COUNT INT,
CARD_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT CART_DETAIL_MEM_ID_COM_ID_PK PRIMARY KEY(MEM_ID,COM_ID),
CONSTRAINT CART_DETAIL_MEM_ID_FK FOREIGN KEY (MEM_ID) REFERENCES MEMBER_INFO(MEM_ID),
CONSTRAINT CART_DETAIL_COM_ID_FK FOREIGN KEY (COM_ID) REFERENCES COMMODITY(COM_ID)
);

CREATE TABLE COMMODITY_BOARD(
COMB_ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
COM_ID INT NOT NULL,
MEM_ID INT NOT NULL,
COMB_MESSAGE VARCHAR(60),
COMB_STATUS BOOLEAN,
COMB_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT COMMODITY_BOARD_COM_ID_FK FOREIGN KEY(COM_ID) REFERENCES COMMODITY (COM_ID),
CONSTRAINT COMMODITY_BOARD_MEM_ID_FK FOREIGN KEY(MEM_ID) REFERENCES MEMBER_INFO(MEM_ID)
);

CREATE TABLE COMMODITY_FAVORITE(
MEM_ID INT NOT NULL,
COM_ID INT NOT NULL,
CONSTRAINT COMMODITY_FAVORITE_MEM_ID_COM_ID_PK PRIMARY KEY(MEM_ID, COM_ID),
CONSTRAINT COMMODITY_FAVORITE_MEM_ID_FK FOREIGN KEY (MEM_ID) REFERENCES MEMBER_INFO(MEM_ID),
CONSTRAINT COMMODITY_FAVORITE_COM_ID_FK FOREIGN KEY (COM_ID) REFERENCES COMMODITY(COM_ID)
);

CREATE TABLE COMMODITY_SERVICE(
COMS_ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
MEM_ID INT NOT NULL,
COM_ID INT NOT NULL,
COMS_MESSAGE VARCHAR(50),
COMS_DIRECTION BOOLEAN,
COMS_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT COMMODITY_SERVICE_MEM_ID_FK FOREIGN KEY (MEM_ID) REFERENCES MEMBER_INFO (MEM_ID),
CONSTRAINT COMMODITY_SERVICE_COM_ID_FK FOREIGN KEY (COM_ID) REFERENCES COMMODITY (COM_ID)
);

-- 以下為食譜 --

CREATE TABLE RECIPE(
REC_ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
MEM_ID INT NOT NULL,
REC_NAME VARCHAR(10),
REC_PICTURE_1 BLOB,
REC_PICTURE_2 BLOB,
REC_PICTURE_3 BLOB,
REC_VIDEO BLOB,
REC_FUNCTION VARCHAR(20),
REC_CAL DECIMAL(5,2),
REC_CARB DECIMAL(5,2),
REC_PROC DECIMAL(5,2),
REC_FAT DECIMAL(5,2),
REC_STATUS BOOLEAN,
REC_BONUS INT NOT NULL,
REC_CONTENT VARCHAR(100),
REC_SIZE INT(2),
REC_COOKTIME INT,
REC_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
REC_ADATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT RECIPE_MEM_ID_FK FOREIGN KEY (MEM_ID) REFERENCES MEMBER_INFO (MEM_ID)
);

CREATE TABLE RECIPE_STEP(
RECS_ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
REC_ID INT NOT NULL,
RECS_CONTENT VARCHAR(100),
RECS_PICTURE BLOB,
CONSTRAINT RECIPE_STEP_REC_ID_FK FOREIGN KEY (REC_ID) REFERENCES RECIPE(REC_ID)
);

CREATE TABLE RECIPE_INGREDIENTS(
REC_ID INT NOT NULL,
COM_ID INT NOT NULL,
RECI_NUMS INT,
CONSTRAINT RECIPE_INGREDIENTS_REC_ID_COM_ID_PK PRIMARY KEY (REC_ID,COM_ID),
CONSTRAINT RECIPE_INGREDIENTS_REC_ID_FK FOREIGN KEY (REC_ID) REFERENCES RECIPE (REC_ID),
CONSTRAINT RECIPE_INGREDIENTS_COM_ID_FK FOREIGN KEY (COM_ID) REFERENCES COMMODITY (COM_ID)
);

CREATE TABLE RECIPE_FAVORITE (
MEM_ID INT NOT NULL,
REC_ID INT NOT NULL,
CONSTRAINT RECIPE_FAVORITE_MEM_ID_REC_ID_PK PRIMARY KEY (MEM_ID,REC_ID),
CONSTRAINT RECIPE_FAVORITE_MEM_ID_FK FOREIGN KEY (MEM_ID) REFERENCES MEMBER_INFO (MEM_ID),
CONSTRAINT RECIPE_FAVORITE_REC_ID_FK FOREIGN KEY (REC_ID) REFERENCES RECIPE (REC_ID)
);

CREATE TABLE RECIPE_BOARD(
RECB_ID INT  PRIMARY KEY AUTO_INCREMENT NOT NULL,
REC_ID INT NOT NULL,
MEM_ID INT NOT NULL,
RECB_CONTENT VARCHAR(60),
RECB_STATUS BOOLEAN,
RECB_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT RECIPE_BOARD_REC_ID_FK FOREIGN KEY (REC_ID) REFERENCES RECIPE (REC_ID),
CONSTRAINT RECIPE_BOARD_MEM_ID_FK FOREIGN KEY (MEM_ID) REFERENCES MEMBER_INFO(MEM_ID) 
);

CREATE TABLE RECIPE_REPORT (
RECR_ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
REC_ID INT NOT NULL,
MEM_ID INT NOT NULL,
RECR_CONTENT VARCHAR(50),
RECR_REPLY VARCHAR(50),
RECR_STATUS BOOLEAN,
RECR_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE RECIPEB_REPORT(
RECB_ID INT NOT NULL,
MEM_ID INT NOT NULL,
RECBR_CONTENT VARCHAR(60),
RECBR_REPLY VARCHAR(60),
RECBR_STATUS INT NOT NULL,
RECBR_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT RECIPEB_REPORT_RECB_ID_MEM_ID_PK PRIMARY KEY (RECB_ID,MEM_ID),
CONSTRAINT RECIPEB_REPORT_RECB_ID_FK FOREIGN KEY (RECB_ID) REFERENCES RECIPE_BOARD (RECB_ID),
CONSTRAINT RECIPEB_REPORT_MEM_ID_FK FOREIGN KEY (MEM_ID) REFERENCES MEMBER_INFO (MEM_ID)
);

-- 以下為課程 --


create table TALENT (
	TAL_ID				INT PRIMARY KEY AUTO_INCREMENT,
	TAL_NAME			varchar(60)
);

create table COACH (
	COA_ID				INT PRIMARY KEY AUTO_INCREMENT,
	COA_NAME			varchar(100),
	COA_SEX 			varchar(10),
	COA_PICTURE 		BLOB,
	COA_STATUS 			BOOLEAN
);


create table COACH_TALENT (
	COA_ID				INT NOT NULL,
	TAL_ID				INT NOT NULL,
    CONSTRAINT fk_COACHTALENT_COACH
	FOREIGN KEY (COA_ID) references COACH(COA_ID),
    CONSTRAINT fk_COACHTALENT_TALENT
    FOREIGN KEY (TAL_ID) references TALENT(TAL_ID),
	PRIMARY KEY (COA_ID, TAL_ID)
);

create table EXPERIENCE (
	EXP_ID				INT PRIMARY KEY AUTO_INCREMENT,
	COA_ID				INT,
    EXP_CONTENT			varchar(60),
    CONSTRAINT fk_EXPERIENCE_COACH
	FOREIGN KEY (COA_ID) references COACH(COA_ID)
);

create table LESSON (
	LES_ID				INT PRIMARY KEY AUTO_INCREMENT,
	COA_ID				int,
    LES_DATE			date,
    LES_TIME			varchar(11),
    LES_BIGIN			date,
    LES_END				date,
    LES_AVAILABLE		INT,
    LES_ALREADY			INT,
    LES_PRICE			INT,
    LES_STATUS			BOOLEAN,
    CONSTRAINT fk_LESSON_COACH
    FOREIGN KEY (COA_ID) references COACH(COA_ID)
);

create table LESSON_RESERVATION (
	LES_ID				INT,
	MEM_ID				INT,
    LESR_STATUS			BOOLEAN,
    LESR_REASON			varchar(60),
    LESR_TIME			timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_LESSONRESERVATION_LESSEON
	FOREIGN KEY (LES_ID) references LESSON(LES_ID),
    CONSTRAINT fk_LESSONRESERVATION_MEMBER_INFO
    FOREIGN KEY (MEM_ID) references MEMBER_INFO(MEM_ID),
	PRIMARY KEY (LES_ID, MEM_ID)
);

create table LESSON_FAVORITES (
	LES_ID				INT,
	MEM_ID				INT,
    CONSTRAINT fk_LESSONFAVORITES_LESSEON
	FOREIGN KEY (LES_ID) references LESSON(LES_ID),
    CONSTRAINT fk_LESSONFAVORITES_MEMBERINFO
    FOREIGN KEY (MEM_ID) references MEMBER_INFO(MEM_ID),
	PRIMARY KEY (LES_ID, MEM_ID)
);


create table LESSON_BOARD (
	LESB_ID				INT PRIMARY KEY AUTO_INCREMENT,
	MEM_ID				INT,
    COA_ID				INT,
    TAL_ID				INT,
    LESB_CONTENT		varchar(60),
    LESB_TIME			timestamp ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    LESB_STATUS			BOOLEAN,
    CONSTRAINT fk_LESSONBOARD_COACH
	FOREIGN KEY (COA_ID) references COACH(COA_ID),
    CONSTRAINT fk_LESSONBOARD_TALENT
    FOREIGN KEY (TAL_ID) references TALENT(TAL_ID),
    CONSTRAINT fk_LESSONBOARD_MEMBERINFO
    FOREIGN KEY (MEM_ID) references MEMBER_INFO(MEM_ID)
);



create table LESSON_REPORT (
	LESR_ID				int PRIMARY KEY AUTO_INCREMENT,
	LES_ID				INT,
	MEM_ID				INT,
    LESR_CONTENT		varchar(60),
    LESR_REPLY			varchar(60),
    LESR_STATUS			int,
    LESR_TIME			timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_LESSONREPORT_LESSEON
	FOREIGN KEY (LES_ID) references LESSON(LES_ID),
    CONSTRAINT fk_LESSONREPORT_MEMBERINFO
    FOREIGN KEY (MEM_ID) references MEMBER_INFO(MEM_ID)
);

create table LESSON_ADVISORY (
	LESA_ID				int PRIMARY KEY AUTO_INCREMENT,
	LES_ID				INT,
	MEM_ID				INT,
    LESA_CONTENT		varchar(60),
    LESA_DIRECTION		BOOLEAN,
    LESR_TIME			timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_LESSONADVISORY_LESSEON
	FOREIGN KEY (LES_ID) references LESSON(LES_ID),
    CONSTRAINT fk_LESSONADVISORY_MEMBERINFO
    FOREIGN KEY (MEM_ID) references MEMBER_INFO(MEM_ID)
);