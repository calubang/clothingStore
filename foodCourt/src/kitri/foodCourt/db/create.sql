ALTER TABLE fook_user
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

ALTER TABLE fook_manager
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

ALTER TABLE fook_job
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

ALTER TABLE fook_food
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

ALTER TABLE fook_category
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

ALTER TABLE fook_payment
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

ALTER TABLE fook_payment_detail
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

DROP INDEX PK_fook_user;

DROP INDEX PK_fook_manager;

DROP INDEX PK_fook_job;

DROP INDEX food;

DROP INDEX categories;

DROP INDEX PK_fook_payment;

DROP INDEX PK_fook_payment_detail;

/* fook_user */
DROP TABLE fook_user 
	CASCADE CONSTRAINTS;

/* fook_manager */
DROP TABLE fook_manager 
	CASCADE CONSTRAINTS;

/* fook_job */
DROP TABLE fook_job 
	CASCADE CONSTRAINTS;

/* fook_food */
DROP TABLE fook_food 
	CASCADE CONSTRAINTS;

/* fook_category */
DROP TABLE fook_category 
	CASCADE CONSTRAINTS;

/* fook_payment */
DROP TABLE fook_payment 
	CASCADE CONSTRAINTS;

/* fook_payment_detail */
DROP TABLE fook_payment_detail 
	CASCADE CONSTRAINTS;

/* fook_user */
CREATE TABLE fook_user (
	user_id VARCHAR2(32) NOT NULL, /* ����ID */
	password VARCHAR2(32), /* ��й�ȣ */
	name VARCHAR2(32), /* �̸� */
	phone_first VARCHAR2(3), /* �ڵ�����ȣ1 */
	phone_middle VARCHAR2(4), /* �ڵ�����ȣ2 */
	phone_last VARCHAR2(4), /* �ڵ�����ȣ3 */
	user_point NUMBER, /* ��밡������Ʈ */
	password_quiz VARCHAR2(80), /* ��й�ȣ���� */
	password_answer VARCHAR2(80), /* ��й�ȣ�亯 */
	join_date DATE, /* ������ */
	secession_date DATE, /* Ż���� */
	enable VARCHAR2(1) /* Ȱ��ȭ���� */
);

COMMENT ON TABLE fook_user IS 'fook_user';

COMMENT ON COLUMN fook_user.user_id IS '����ID';

COMMENT ON COLUMN fook_user.password IS '��й�ȣ';

COMMENT ON COLUMN fook_user.name IS '�̸�';

COMMENT ON COLUMN fook_user.phone_first IS '�ڵ�����ȣ1';

COMMENT ON COLUMN fook_user.phone_middle IS '�ڵ�����ȣ2';

COMMENT ON COLUMN fook_user.phone_last IS '�ڵ�����ȣ3';

COMMENT ON COLUMN fook_user.user_point IS '��밡������Ʈ';

COMMENT ON COLUMN fook_user.password_quiz IS '��й�ȣ����';

COMMENT ON COLUMN fook_user.password_answer IS '��й�ȣ�亯';

COMMENT ON COLUMN fook_user.join_date IS '������';

COMMENT ON COLUMN fook_user.secession_date IS 'Ż����';

COMMENT ON COLUMN fook_user.enable IS 'Ȱ��ȭ����';

CREATE UNIQUE INDEX PK_fook_user
	ON fook_user (
		user_id ASC
	);

ALTER TABLE fook_user
	ADD
		CONSTRAINT PK_fook_user
		PRIMARY KEY (
			user_id
		);

/* fook_manager */
CREATE TABLE fook_manager (
	manager_id VARCHAR2(32) NOT NULL, /* ������ID */
	name VARCHAR2(32), /* �̸� */
	password VARCHAR2(32), /* ��й�ȣ */
	phone_first VARCHAR2(3), /* �ڵ�����ȣ1 */
	phone_middle VARCHAR2(4), /* �ڵ�����ȣ2 */
	phone_last VARCHAR2(4), /* �ڵ�����ȣ3 */
	job_id VARCHAR2(32), /* �����ڵ� */
	hire_date DATE, /* �Ի��� */
	address_zip VARCHAR2(6), /* �����ȣ */
	address VARCHAR2(160), /* �ּ� */
	email VARCHAR2(32), /* �̸��� */
	email_domain VARCHAR2(32) /* �̸���_������ */
);

COMMENT ON TABLE fook_manager IS 'fook_manager';

COMMENT ON COLUMN fook_manager.manager_id IS '������ID';

COMMENT ON COLUMN fook_manager.name IS '�̸�';

COMMENT ON COLUMN fook_manager.password IS '��й�ȣ';

COMMENT ON COLUMN fook_manager.phone_first IS '�ڵ�����ȣ1';

COMMENT ON COLUMN fook_manager.phone_middle IS '�ڵ�����ȣ2';

COMMENT ON COLUMN fook_manager.phone_last IS '�ڵ�����ȣ3';

COMMENT ON COLUMN fook_manager.job_id IS '�����ڵ�';

COMMENT ON COLUMN fook_manager.hire_date IS '�Ի���';

COMMENT ON COLUMN fook_manager.address_zip IS '�����ȣ';

COMMENT ON COLUMN fook_manager.address IS '�ּ�';

COMMENT ON COLUMN fook_manager.email IS '�̸���';

COMMENT ON COLUMN fook_manager.email_domain IS '�̸���_������';

CREATE UNIQUE INDEX PK_fook_manager
	ON fook_manager (
		manager_id ASC
	);

ALTER TABLE fook_manager
	ADD
		CONSTRAINT PK_fook_manager
		PRIMARY KEY (
			manager_id
		);

/* fook_job */
CREATE TABLE fook_job (
	job_id VARCHAR2(32) NOT NULL, /* ����ID */
	job_name VARCHAR2(64), /* ���� */
	grade_level VARCHAR2(1) /* ���ȵ�� */
);

COMMENT ON TABLE fook_job IS 'fook_job';

COMMENT ON COLUMN fook_job.job_id IS '����ID';

COMMENT ON COLUMN fook_job.job_name IS '����';

COMMENT ON COLUMN fook_job.grade_level IS '���ȵ��';

CREATE UNIQUE INDEX PK_fook_job
	ON fook_job (
		job_id ASC
	);

ALTER TABLE fook_job
	ADD
		CONSTRAINT PK_fook_job
		PRIMARY KEY (
			job_id
		);

/* fook_food */
CREATE TABLE fook_food (
	food_id VARCHAR2(64) NOT NULL, /* ����ID */
	food_name VARCHAR2(64), /* �����̸� */
	category_id NUMBER(2) NOT NULL, /* ī�װ�ID */
	price NUMBER(6), /* ���� */
	food_point NUMBER(6), /* ����Ʈ */
	food_description VARCHAR2(1024), /* ���� */
	image_address VARCHAR2(128), /* �̹����ּ� */
	manager_id VARCHAR2(32), /* ������ID */
	create_date DATE DEFAULT sysdate, /* ������ */
	food_enable VARCHAR2(1) /* Ȱ��ȭ���� */
);

COMMENT ON TABLE fook_food IS 'fook_food';

COMMENT ON COLUMN fook_food.food_id IS 'food_id�� ī�װ����̵� + Ǫ����� + ������¥ ����
ex) 1_bibimbap_190101';

COMMENT ON COLUMN fook_food.food_name IS '�����̸�';

COMMENT ON COLUMN fook_food.category_id IS '1 : korean, 2 : Chinese, ...';

COMMENT ON COLUMN fook_food.price IS '����';

COMMENT ON COLUMN fook_food.food_point IS '����Ʈ';

COMMENT ON COLUMN fook_food.food_description IS '����';

COMMENT ON COLUMN fook_food.image_address IS '�̹����ּ�';

COMMENT ON COLUMN fook_food.manager_id IS '������ID';

COMMENT ON COLUMN fook_food.create_date IS '������';

COMMENT ON COLUMN fook_food.food_enable IS 'Ȱ��ȭ����';

CREATE UNIQUE INDEX food
	ON fook_food (
		food_id ASC
	);

ALTER TABLE fook_food
	ADD
		CONSTRAINT food
		PRIMARY KEY (
			food_id
		);

/* fook_category */
CREATE TABLE fook_category (
	category_id NUMBER(2) NOT NULL, /* ī�װ�ID */
	category_name VARCHAR2(64) /* ī�װ�_�̸� */
);

COMMENT ON TABLE fook_category IS 'fook_category';

COMMENT ON COLUMN fook_category.category_id IS 'ī�װ�ID';

COMMENT ON COLUMN fook_category.category_name IS 'korean, chinese';

CREATE UNIQUE INDEX categories
	ON fook_category (
		category_id ASC
	);

ALTER TABLE fook_category
	ADD
		CONSTRAINT categories
		PRIMARY KEY (
			category_id
		);

/* fook_payment */
CREATE TABLE fook_payment (
	payment_id VARCHAR2(32) NOT NULL, /* ����ID */
	user_id VARCHAR2(32) NOT NULL, /* ����ID */
	payment_date DATE DEFAULT sysdate, /* ������ */
	request_number NUMBER(4), /* ��û��ȣ */
	total_price NUMBER, /* �Ѱ��� */
	save_point NUMBER DEFAULT 0, /* ��ȹ������Ʈ */
	cash NUMBER DEFAULT 0, /* ���ݱݾ� */
	card NUMBER DEFAULT 0, /* ī��ݾ� */
	used_point NUMBER DEFAULT 0, /* �����û������Ʈ */
	payment_state VARCHAR2(1) /* �������� */
);

COMMENT ON TABLE fook_payment IS 'fook_payment';

COMMENT ON COLUMN fook_payment.payment_id IS '��¥ + ������(4�ڸ�)
ex) 1904010001';

COMMENT ON COLUMN fook_payment.user_id IS '����ID';

COMMENT ON COLUMN fook_payment.payment_date IS '������';

COMMENT ON COLUMN fook_payment.request_number IS '��û��ȣ';

COMMENT ON COLUMN fook_payment.total_price IS '�Ѱ���';

COMMENT ON COLUMN fook_payment.save_point IS '��ȹ������Ʈ';

COMMENT ON COLUMN fook_payment.cash IS '���ݱݾ�';

COMMENT ON COLUMN fook_payment.card IS 'ī��ݾ�';

COMMENT ON COLUMN fook_payment.used_point IS '�����û������Ʈ';

COMMENT ON COLUMN fook_payment.payment_state IS 'ȯ��(1)
�����Ϸ�(0)';

CREATE UNIQUE INDEX PK_fook_payment
	ON fook_payment (
		payment_id ASC
	);

ALTER TABLE fook_payment
	ADD
		CONSTRAINT PK_fook_payment
		PRIMARY KEY (
			payment_id
		);

/* fook_payment_detail */
CREATE TABLE fook_payment_detail (
	payment_id VARCHAR2(32) NOT NULL, /* ����_ID */
	food_id VARCHAR2(64) NOT NULL, /* ����ID */
	food_name VARCHAR2(64), /* �����̸� */
	price NUMBER, /* ���� */
	count NUMBER, /* ���� */
	point NUMBER DEFAULT 0 /* ȹ������Ʈ */
);

COMMENT ON TABLE fook_payment_detail IS 'fook_payment_detail';

COMMENT ON COLUMN fook_payment_detail.payment_id IS '����_ID';

COMMENT ON COLUMN fook_payment_detail.food_id IS '����ID';

COMMENT ON COLUMN fook_payment_detail.food_name IS '�����̸�';

COMMENT ON COLUMN fook_payment_detail.price IS '����';

COMMENT ON COLUMN fook_payment_detail.count IS '����';

COMMENT ON COLUMN fook_payment_detail.point IS 'ȹ������Ʈ';

CREATE UNIQUE INDEX PK_fook_payment_detail
	ON fook_payment_detail (
		payment_id ASC,
		food_id ASC
	);

ALTER TABLE fook_payment_detail
	ADD
		CONSTRAINT PK_fook_payment_detail
		PRIMARY KEY (
			payment_id,
			food_id
		);
-- ������ ���� �� ����
DROP SEQUENCE food_fid_seq;
DROP SEQUENCE payment_pid_seq;

CREATE SEQUENCE food_fid_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 99999
    MINVALUE 1
    NOCYCLE;

CREATE SEQUENCE payment_pid_seq 
    START WITH 1
    INCREMENT BY 1 
    MAXVALUE 999999 
    MINVALUE 0
    NOCYCLE;

-- JOB
insert into fook_job(job_id, job_name, grade_level) values ('Admin', '������', 'H');
insert into fook_job(job_id, job_name, grade_level) values ('Clerk', '����', 'M');
insert into fook_job(job_id, job_name, grade_level) values ('Newbie', '����', 'L');

-- MANAGER
insert into fook_manager(manager_id, name, password, phone_first, phone_middle, phone_last, job_id, hire_date, address_zip, address, email, email_domain)
values ('Admin123', 'ȫ�浿', '123qwe', '010', '1234', '5678', 'Admin', sysdate, '34343', 'Seoul yeouido', 'kitri', 'naver.com');
insert into fook_manager(manager_id, name, password, phone_first, phone_middle, phone_last, job_id, hire_date, address_zip, address, email, email_domain)
values ('Clerk123', '������', '1a2s3d4f', '010', '2345', '6789', 'Clerk', sysdate, '43438', 'Seoul yeongdeungpogu', 'c234', 'hanmail.net');
insert into fook_manager(manager_id, name, password, phone_first, phone_middle, phone_last, job_id, hire_date, address_zip, address, email, email_domain)
values ('Newbie123', '�����', 'skujhf723', '010', '3456', '7890', 'Newbie', sysdate, '29843', 'Seoul jonglogu', '2396sdf', 'google.com');

-- CATEGORY
insert into fook_category(category_id, category_name) values (1, '�ѽ�');
insert into fook_category(category_id, category_name) values (2, '�߽�');
insert into fook_category(category_id, category_name) values (3, '�Ͻ�');
insert into fook_category(category_id, category_name) values (4, '���');

-- FOOD
insert into fook_food(food_id, food_name, category_id, price, food_point, food_description, image_address, manager_id, create_date, food_enable)
values (food_fid_seq.nextval, '������', 1, 6000, 60, '�ż��� ��ä�� ���.', '/kitri/foodCourt/management/menu/image/bokeumbap.jpg', 'Clerk123', sysdate, 'y');
insert into fook_food(food_id, food_name, category_id, price, food_point, food_description, image_address, manager_id, create_date, food_enable)
values (food_fid_seq.nextval, '�����', 2, 5000, 50, '���� ��Ÿ�� ����.', '/kitri/foodCourt/management/menu/image/jajangmyeon.jpg', 'Clerk123', sysdate, 'y');
insert into fook_food(food_id, food_name, category_id, price, food_point, food_description, image_address, manager_id, create_date, food_enable)
values (food_fid_seq.nextval, '�ҹ�', 3, 7000, 70, '�޹��� ��.', '/kitri/foodCourt/management/menu/image/soba.jpg', 'Clerk123', sysdate, 'y');
insert into fook_food(food_id, food_name, category_id, price, food_point, food_description, image_address, manager_id, create_date, food_enable)
values (food_fid_seq.nextval, '������ũ', 4, 9500, 95, '��������� �Ұ�⸦ ���.', '/kitri/foodCourt/management/menu/image/steak.jpg', 'Admin123', sysdate, 'y');
INSERT INTO "FOOK"."FOOK_FOOD" (FOOD_ID, FOOD_NAME, CATEGORY_ID, PRICE, FOOD_POINT, FOOD_DESCRIPTION, IMAGE_ADDRESS, MANAGER_ID, CREATE_DATE, FOOD_ENABLE) VALUES ('6', '�׽�Ʈ1', '1', '6000', '60', '������ �� ���1', '/img/food/ilsic.jpg', 'Admin123', TO_DATE('2019-04-12 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'y');
INSERT INTO "FOOK"."FOOK_FOOD" (FOOD_ID, FOOD_NAME, CATEGORY_ID, PRICE, FOOD_POINT, FOOD_DESCRIPTION, IMAGE_ADDRESS, MANAGER_ID, CREATE_DATE, FOOD_ENABLE) VALUES ('7', '�׽�Ʈ2', '2', '6000', '60', '������ �� ���2', '/img/food/jajangmyeon.jpg', 'Admin123', TO_DATE('2019-04-12 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'y');
INSERT INTO "FOOK"."FOOK_FOOD" (FOOD_ID, FOOD_NAME, CATEGORY_ID, PRICE, FOOD_POINT, FOOD_DESCRIPTION, IMAGE_ADDRESS, MANAGER_ID, CREATE_DATE, FOOD_ENABLE) VALUES ('8', '�׽�Ʈ3', '3', '6000', '60', '������ �� ���3', '/img/food/joongsic.jpg', 'Admin123', TO_DATE('2019-04-12 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'y');
INSERT INTO "FOOK"."FOOK_FOOD" (FOOD_ID, FOOD_NAME, CATEGORY_ID, PRICE, FOOD_POINT, FOOD_DESCRIPTION, IMAGE_ADDRESS, MANAGER_ID, CREATE_DATE, FOOD_ENABLE) VALUES ('9', '�׽�Ʈ4', '4', '6000', '60', '������ �� ���4', '/img/food/junjoobibimbab.jpg', 'Admin123', TO_DATE('2019-04-12 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'y');
INSERT INTO "FOOK"."FOOK_FOOD" (FOOD_ID, FOOD_NAME, CATEGORY_ID, PRICE, FOOD_POINT, FOOD_DESCRIPTION, IMAGE_ADDRESS, MANAGER_ID, CREATE_DATE, FOOD_ENABLE) VALUES ('10', '�׽�Ʈ5', '1', '6000', '60', '������ �� ���5', '/img/food/soba.jpg', 'Admin123', TO_DATE('2019-04-12 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'y');
INSERT INTO "FOOK"."FOOK_FOOD" (FOOD_ID, FOOD_NAME, CATEGORY_ID, PRICE, FOOD_POINT, FOOD_DESCRIPTION, IMAGE_ADDRESS, MANAGER_ID, CREATE_DATE, FOOD_ENABLE) VALUES ('11', '�׽�Ʈ6', '2', '6000', '60', '������ �� ���6', '/img/food/steak.jpg', 'Admin123', TO_DATE('2019-04-12 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'y');
INSERT INTO "FOOK"."FOOK_FOOD" (FOOD_ID, FOOD_NAME, CATEGORY_ID, PRICE, FOOD_POINT, FOOD_DESCRIPTION, IMAGE_ADDRESS, MANAGER_ID, CREATE_DATE, FOOD_ENABLE) VALUES ('12', '�׽�Ʈ7', '3', '6000', '60', '������ �� ���7', '/img/food/yangsic.jpeg', 'Admin123', TO_DATE('2019-04-12 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'y');


-- USER
insert into fook_user(user_id, password, name, phone_first, phone_middle, phone_last, user_point, password_quiz, password_answer, join_date, secession_date, enable)
values ('a1s2d3', '1q2w3e', '�̼���', '010', '1234', '5678', 0, 'Country?', 'South Korea', sysdate, NULL, 'y');
insert into fook_user(user_id, password, name, phone_first, phone_middle, phone_last, user_point, password_quiz, password_answer, join_date, secession_date, enable)
values ('dfkjhg987', 'dfjhjh2', '������', '010', '3456', '7890', 10000, 'City?', 'Seoul', sysdate, NULL, 'y');
insert into fook_user(user_id, password, name, phone_first, phone_middle, phone_last, user_point, password_quiz, password_answer, join_date, secession_date, enable)
values ('dxcvowej34', '2309fjsdjfh', '������', '010', '7948', '2948', 300, 'Nation?', 'East Asia', sysdate, sysdate, 'n');

-- PAYMENT
insert into fook_payment(payment_id, user_id, payment_date, request_number, total_price, save_point, used_point, card, cash, payment_state)
values (payment_pid_seq.nextval, 'a1s2d3', sysdate, '0001', 50000, 500, 0, 25000, 25000, '0');
insert into fook_payment(payment_id, user_id, payment_date, request_number, total_price, save_point, used_point, card, cash, payment_state)
values (payment_pid_seq.nextval, 'dfkjhg987', sysdate, '0002', 150000, 1500, 0, 125000, 25000, '0');
insert into fook_payment(payment_id, user_id, payment_date, request_number, total_price, save_point, used_point, card, cash, payment_state)
values (payment_pid_seq.nextval, 'dxcvowej34', sysdate, '0003', 80000, 800, 0, 25000, 55000, '0');

-- PAYMENT_DETAIL
insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('1', '72', '������', 6000, 3, 60);
insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('1', '73', '�����', 5000, 2, 50);
insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('1', '74', '�ҹ�', 7000, 1, 70);
insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('1', '75', '������ũ', 9500, 4, 95);

insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('0', '73', '�����', 5000, 3, 50);
insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('0', '74', '�ҹ�', 7000, 2, 70);

insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('2', '72', '������', 6000, 5, 60);
insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('2', '74', '�ҹ�', 7000, 3, 70);
insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('2', '75', '������ũ', 9500, 1, 95);

COMMIT;
