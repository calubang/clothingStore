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
values ('Administrator', 'ȫ�浿', '123q#we', '010', '1234', '9876', 'Admin', sysdate, '34343', '����Ư���� ���ǵ��� �������� ��ȸ��� 17��', 'honggildong', 'naver.com');
insert into fook_manager(manager_id, name, password, phone_first, phone_middle, phone_last, job_id, hire_date, address_zip, address, email, email_domain)
values ('Clerk1', '������', 'eidud928', '010', '2345', '8765', 'Clerk', sysdate, '43378', '��⵵ ����� ���յ� 254����', 'seosaengwon', 'hanmail.net');
insert into fook_manager(manager_id, name, password, phone_first, phone_middle, phone_last, job_id, hire_date, address_zip, address, email, email_domain)
values ('Clerk2', '�̹���', 'cjkjd46!2', '010', '3456', '7654', 'Clerk', sysdate, '67890', '������ ��ô�� ������ ����', 'leebangheon', 'naver.com');
insert into fook_manager(manager_id, name, password, phone_first, phone_middle, phone_last, job_id, hire_date, address_zip, address, email, email_domain)
values ('Clerk3', '������', 'djsd984u24s', '010', '4567', '6543', 'Clerk', sysdate, '25727', '��û�ϵ� û�ֽ� ��籸 ������ 173-6', 'jeongdojeon', 'google.com');
insert into fook_manager(manager_id, name, password, phone_first, phone_middle, phone_last, job_id, hire_date, address_zip, address, email, email_domain)
values ('Clerk4', '�豸', 'kkjfg%$sk', '010', '5678', '5432', 'Clerk', sysdate, '92783', '���󳲵� ��õ�� ���ʸ� ��ġ��', 'kimku', 'hanmail.net');
insert into fook_manager(manager_id, name, password, phone_first, phone_middle, phone_last, job_id, hire_date, address_zip, address, email, email_domain)
values ('Clerk5', '�ڻ��', 'sdjsjf98248', '010', '6789', '4321', 'Clerk', sysdate, '23985', '���ֵ� �������� ������ �����Ϸ� 433', 'parksangmyeon', 'google.com');
insert into fook_manager(manager_id, name, password, phone_first, phone_middle, phone_last, job_id, hire_date, address_zip, address, email, email_domain)
values ('Newbie1', '�̼���', 'zgzg761', '010', '7890', '3210', 'Newbie', sysdate, '86748', '���������� ������ ������ 218', 'leesoonjae', 'google.com');
insert into fook_manager(manager_id, name, password, phone_first, phone_middle, phone_last, job_id, hire_date, address_zip, address, email, email_domain)
values ('Newbie2', '�ű�', 'sfju394f89', '010', '8901', '2109', 'Newbie', sysdate, '64921', '�뱸������ ���� ���η� 459', 'shinku', 'naver.com');
insert into fook_manager(manager_id, name, password, phone_first, phone_middle, phone_last, job_id, hire_date, address_zip, address, email, email_domain)
values ('Newbie3', '�����', 'kudfg908dws', '010', '9012', '1098', 'Newbie', sysdate, '10198', '��걤���� �ϱ� ȿ���� 587-1', 'kimduhan', 'google.com');


-- CATEGORY
insert into fook_category(category_id, category_name) values (1, '�ѽ�');
insert into fook_category(category_id, category_name) values (2, '�߽�');
insert into fook_category(category_id, category_name) values (3, '�Ͻ�');
insert into fook_category(category_id, category_name) values (4, '���');

-- FOOD
insert into fook_food(food_id, food_name, category_id, price, food_point, food_description, image_address, manager_id, create_date, food_enable)
values (food_fid_seq.nextval, '������', 1, 7000, 70, '�ż��� ��ä�� ���.', 'bokeumbap.jpg', 'Newbie1', sysdate, 'y');
insert into fook_food(food_id, food_name, category_id, price, food_point, food_description, image_address, manager_id, create_date, food_enable)
values (food_fid_seq.nextval, '�ȵ�����', 1, 7500, 75, '���� �������� ������ ����.', 'andongkuksi.jpg', 'Newbie2', sysdate, 'y');
insert into fook_food(food_id, food_name, category_id, price, food_point, food_description, image_address, manager_id, create_date, food_enable)
values (food_fid_seq.nextval, '������Į����', 1, 7000, 70, '�������� �� ������.', 'bajirakcalkuksu.jpg', 'Newbie3', sysdate, 'y');
insert into fook_food(food_id, food_name, category_id, price, food_point, food_description, image_address, manager_id, create_date, food_enable)
values (food_fid_seq.nextval, '�ҹ��Ұ��', 1, 7900, 79, '���Ĵ��� �α� �޴�.', 'ddookbaegibulgogi.jpg', 'Clerk1', sysdate, 'y');
insert into fook_food(food_id, food_name, category_id, price, food_point, food_description, image_address, manager_id, create_date, food_enable)
values (food_fid_seq.nextval, '���ܺ����', 1, 7500, 75, '�ٸ��� ������ �±Ⱑ ������.', 'dolsotbibimbap.jpg', 'Clerk2', sysdate, 'y');
insert into fook_food(food_id, food_name, category_id, price, food_point, food_description, image_address, manager_id, create_date, food_enable)
values (food_fid_seq.nextval, '������', 1, 9000, 90, '������ 1��� �ѿ츦 ���.', 'galbitang.jpg', 'Administrator', sysdate, 'y');
insert into fook_food(food_id, food_name, category_id, price, food_point, food_description, image_address, manager_id, create_date, food_enable)
values (food_fid_seq.nextval, '�����', 2, 5000, 50, '���� ��Ÿ�� ����.', 'jajangmyeon.jpg', 'Clerk4', sysdate, 'y');
insert into fook_food(food_id, food_name, category_id, price, food_point, food_description, image_address, manager_id, create_date, food_enable)
values (food_fid_seq.nextval, '���ĵκ�', 2, 7000, 70, '�߱��� �κ� �丮.', 'mapatofu.jpg', 'Clerk3', sysdate, 'y');
insert into fook_food(food_id, food_name, category_id, price, food_point, food_description, image_address, manager_id, create_date, food_enable)
values (food_fid_seq.nextval, '��ä��', 2, 8000, 80, '¬���� ��ä �丮.', 'japchaebap.jpg', 'Clerk5', sysdate, 'y');
insert into fook_food(food_id, food_name, category_id, price, food_point, food_description, image_address, manager_id, create_date, food_enable)
values (food_fid_seq.nextval, '����������', 2, 13000, 130, '���ҷ� ����� �İ��� �̱���.', 'chapssaltangsuyook.jpg', 'Clerk5', sysdate, 'y');
insert into fook_food(food_id, food_name, category_id, price, food_point, food_description, image_address, manager_id, create_date, food_enable)
values (food_fid_seq.nextval, '«��', 2, 8000, 80, 'ĮĮ�� ������ ��ǰ��.', 'jjambbong.jpg', 'Clerk2', sysdate, 'y');
insert into fook_food(food_id, food_name, category_id, price, food_point, food_description, image_address, manager_id, create_date, food_enable)
values (food_fid_seq.nextval, '�ҹ�', 3, 7000, 70, '�޹��� ��.', 'soba.jpg', 'Clerk1', sysdate, 'y');
insert into fook_food(food_id, food_name, category_id, price, food_point, food_description, image_address, manager_id, create_date, food_enable)
values (food_fid_seq.nextval, '�˹�', 3, 8000, 80, '�Ծȿ��� ���� ������ �İ��� ��ǰ��.', 'albap.jpg', 'Clerk1', sysdate, 'y');
insert into fook_food(food_id, food_name, category_id, price, food_point, food_description, image_address, manager_id, create_date, food_enable)
values (food_fid_seq.nextval, '���', 3, 8500, 85, '������ ������ �����.', 'donkats.jpg', 'Clerk3', sysdate, 'y');
insert into fook_food(food_id, food_name, category_id, price, food_point, food_description, image_address, manager_id, create_date, food_enable)
values (food_fid_seq.nextval, '�������쵿', 3, 5000, 50, '���� �Ϻ��� �쵿.', 'gasoudong.jpg', 'Clerk4', sysdate, 'y');
insert into fook_food(food_id, food_name, category_id, price, food_point, food_description, image_address, manager_id, create_date, food_enable)
values (food_fid_seq.nextval, '��ġȸ����', 3, 18000, 180, '��ġ 100% ���.', 'chamchihoideopbap.jpg', 'Newbie2', sysdate, 'y');
insert into fook_food(food_id, food_name, category_id, price, food_point, food_description, image_address, manager_id, create_date, food_enable)
values (food_fid_seq.nextval, '������ũ', 4, 9500, 95, '��������� �Ұ�⸦ ���.', 'steak.jpg', 'Administrator', sysdate, 'y');
insert into fook_food(food_id, food_name, category_id, price, food_point, food_description, image_address, manager_id, create_date, food_enable)
values (food_fid_seq.nextval, '�丶���ġ���İ�Ƽ', 4, 8500, 85, '�丶��� ��ġ�� ����.', 'tomatokimchispaghetti.jpg', 'Clerk2', sysdate, 'y');
insert into fook_food(food_id, food_name, category_id, price, food_point, food_description, image_address, manager_id, create_date, food_enable)
values (food_fid_seq.nextval, 'Ŭ��������ġ', 4, 9000, 90, '�ѳ� �Ļ�� ������.', 'clubsandwitch.jpg', 'Newbie1', sysdate, 'y');
insert into fook_food(food_id, food_name, category_id, price, food_point, food_description, image_address, manager_id, create_date, food_enable)
values (food_fid_seq.nextval, '���̾�Ʈ����', 4, 14000, 140, '3~4���� �Ա⿡ ������ ����.', 'giantpizza.jpg', 'Administrator', sysdate, 'y');
INSERT INTO "FOOK"."FOOK_FOOD" (FOOD_ID, FOOD_NAME, CATEGORY_ID, PRICE, FOOD_POINT, FOOD_DESCRIPTION, IMAGE_ADDRESS, MANAGER_ID, CREATE_DATE, FOOD_ENABLE) VALUES ('6', '�׽�Ʈ1', '1', '6000', '60', '������ �� ���1', 'ilsic.jpg', 'Admin123', TO_DATE('2019-04-12 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'y');
INSERT INTO "FOOK"."FOOK_FOOD" (FOOD_ID, FOOD_NAME, CATEGORY_ID, PRICE, FOOD_POINT, FOOD_DESCRIPTION, IMAGE_ADDRESS, MANAGER_ID, CREATE_DATE, FOOD_ENABLE) VALUES ('7', '�׽�Ʈ2', '2', '6000', '60', '������ �� ���2', 'jajangmyeon.jpg', 'Admin123', TO_DATE('2019-04-12 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'y');
INSERT INTO "FOOK"."FOOK_FOOD" (FOOD_ID, FOOD_NAME, CATEGORY_ID, PRICE, FOOD_POINT, FOOD_DESCRIPTION, IMAGE_ADDRESS, MANAGER_ID, CREATE_DATE, FOOD_ENABLE) VALUES ('8', '�׽�Ʈ3', '3', '6000', '60', '������ �� ���3', 'joongsic.jpg', 'Admin123', TO_DATE('2019-04-12 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'y');
INSERT INTO "FOOK"."FOOK_FOOD" (FOOD_ID, FOOD_NAME, CATEGORY_ID, PRICE, FOOD_POINT, FOOD_DESCRIPTION, IMAGE_ADDRESS, MANAGER_ID, CREATE_DATE, FOOD_ENABLE) VALUES ('9', '�׽�Ʈ4', '4', '6000', '60', '������ �� ���4', 'junjoobibimbab.jpg', 'Admin123', TO_DATE('2019-04-12 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'y');
INSERT INTO "FOOK"."FOOK_FOOD" (FOOD_ID, FOOD_NAME, CATEGORY_ID, PRICE, FOOD_POINT, FOOD_DESCRIPTION, IMAGE_ADDRESS, MANAGER_ID, CREATE_DATE, FOOD_ENABLE) VALUES ('10', '�׽�Ʈ5', '1', '6000', '60', '������ �� ���5', 'soba.jpg', 'Admin123', TO_DATE('2019-04-12 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'y');
INSERT INTO "FOOK"."FOOK_FOOD" (FOOD_ID, FOOD_NAME, CATEGORY_ID, PRICE, FOOD_POINT, FOOD_DESCRIPTION, IMAGE_ADDRESS, MANAGER_ID, CREATE_DATE, FOOD_ENABLE) VALUES ('11', '�׽�Ʈ6', '2', '6000', '60', '������ �� ���6', 'steak.jpg', 'Admin123', TO_DATE('2019-04-12 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'y');
INSERT INTO "FOOK"."FOOK_FOOD" (FOOD_ID, FOOD_NAME, CATEGORY_ID, PRICE, FOOD_POINT, FOOD_DESCRIPTION, IMAGE_ADDRESS, MANAGER_ID, CREATE_DATE, FOOD_ENABLE) VALUES ('12', '�׽�Ʈ7', '3', '6000', '60', '������ �� ���7', 'yangsic.jpeg', 'Admin123', TO_DATE('2019-04-12 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'y');


-- USER
insert into fook_user(user_id, password, name, phone_first, phone_middle, phone_last, user_point, password_quiz, password_answer, join_date, secession_date, enable)
values ('a1s2d3', '1q2w3e', '�̼���', '010', '1234', '5678', 0, 'Country?', 'South Korea', sysdate, NULL, 'y');
insert into fook_user(user_id, password, name, phone_first, phone_middle, phone_last, user_point, password_quiz, password_answer, join_date, secession_date, enable)
values ('dfkjhg987', 'dfjhjh2', '������', '010', '3456', '7890', 10000, 'City?', 'Seoul', sysdate, NULL, 'y');
insert into fook_user(user_id, password, name, phone_first, phone_middle, phone_last, user_point, password_quiz, password_answer, join_date, secession_date, enable)
values ('dxcvowej34', '2309fjsdjfh', '������', '010', '7948', '2948', 300, 'Nation?', 'East Asia', sysdate, sysdate, 'n');
insert into fook_user(user_id, password, name, phone_first, phone_middle, phone_last, user_point, password_quiz, password_answer, join_date, secession_date, enable)
values ('jb287dhcb', 'drtlse@#', '������', '010', '3489', '5666', 3000, 'First Number?', '010', sysdate, sysdate, 'n');
insert into fook_user(user_id, password, name, phone_first, phone_middle, phone_last, user_point, password_quiz, password_answer, join_date, secession_date, enable)
values ('sosuf7548', 'dj34897', '������', '010', '2907', '8956', 8000, 'Second Number?', '2907', sysdate, NULL, 'y');
insert into fook_user(user_id, password, name, phone_first, phone_middle, phone_last, user_point, password_quiz, password_answer, join_date, secession_date, enable)
values ('dh48dh', 'cjcjsd@#$', '��������', '010', '7455', '2348', 1500, 'Last NUmber?', '2348', sysdate, NULL, 'y');
insert into fook_user(user_id, password, name, phone_first, phone_middle, phone_last, user_point, password_quiz, password_answer, join_date, secession_date, enable)
values ('sdfg9s82', 'sjsddgsd87d', '��Ǿ�', '010', '7356', '9034', 4200, 'Job?', 'Basketball player', sysdate, NULL, 'y');
insert into fook_user(user_id, password, name, phone_first, phone_middle, phone_last, user_point, password_quiz, password_answer, join_date, secession_date, enable)
values ('xx8z72', 'bbkg9048', '����', '010', '0389', '2361', 9300, 'How many children?', '2 childs', sysdate, NULL, 'y');
insert into fook_user(user_id, password, name, phone_first, phone_middle, phone_last, user_point, password_quiz, password_answer, join_date, secession_date, enable)
values ('calubang', '123456', '�Ⱥ���', '010', '8927', '6383', 5000, 'Nation?', 'East Asia', sysdate, sysdate, 'y');

-- PAYMENT
insert into fook_payment(payment_id, user_id, payment_date, request_number, total_price, save_point, used_point, card, cash, payment_state)
values (payment_pid_seq.nextval, 'a1s2d3', sysdate, '0001', 50000, 500, 0, 25000, 25000, '0');
insert into fook_payment(payment_id, user_id, payment_date, request_number, total_price, save_point, used_point, card, cash, payment_state)
values (payment_pid_seq.nextval, 'dfkjhg987', sysdate, '0002', 150000, 1500, 0, 125000, 25000, '0');
insert into fook_payment(payment_id, user_id, payment_date, request_number, total_price, save_point, used_point, card, cash, payment_state)
values (payment_pid_seq.nextval, 'dxcvowej34', sysdate, '0003', 80000, 800, 0, 25000, 55000, '0');
insert into fook_payment(payment_id, user_id, payment_date, request_number, total_price, save_point, used_point, card, cash, payment_state)
values (payment_pid_seq.nextval, 'jb287dhcb', sysdate, '0004', 70000, 700, 5000, 10000, 55000, '0');
insert into fook_payment(payment_id, user_id, payment_date, request_number, total_price, save_point, used_point, card, cash, payment_state)
values (payment_pid_seq.nextval, 'sosuf7548', sysdate, '0005', 105000, 1050, 10000, 85000, 10000, '0');
insert into fook_payment(payment_id, user_id, payment_date, request_number, total_price, save_point, used_point, card, cash, payment_state)
values (payment_pid_seq.nextval, 'dh48dh', sysdate, '0006', 10000, 100, 0, 10000, 0, '0');
insert into fook_payment(payment_id, user_id, payment_date, request_number, total_price, save_point, used_point, card, cash, payment_state)
values (payment_pid_seq.nextval, 'sdfg9s82', sysdate, '0007', 66500, 665, 1000, 60000, 5000, '0');
insert into fook_payment(payment_id, user_id, payment_date, request_number, total_price, save_point, used_point, card, cash, payment_state)
values (payment_pid_seq.nextval, 'xx8z72', sysdate, '0008', 78000, 780, 8000, 15000, 55000, '0');

-- PAYMENT_DETAIL
insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('0', '7', '�����', 5000, 3, 50);
insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('0', '12', '�ҹ�', 7000, 2, 70);

insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('1', '1', '������', 6000, 3, 60);
insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('1', '7', '�����', 5000, 2, 50);
insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('1', '12', '�ҹ�', 7000, 1, 70);
insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('1', '17', '������ũ', 9500, 4, 95);

insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('2', '1', '������', 6000, 5, 60);
insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('2', '12', '�ҹ�', 7000, 3, 70);
insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('2', '17', '������ũ', 9500, 1, 95);

insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('3', '2', '�ȵ�����', 7500, 3, 75);
insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('3', '19', 'Ŭ��������ġ', 9000, 2, 90);
insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('3', '11', '«��', 8000, 1, 80);
insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('3', '8', '���ĵκ�', 7000, 2, 70);
insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('3', '5', '���ܺ����', 7500, 4, 75);
insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('3', '10', '����������', 13000, 1, 130);

insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('4', '16', '��ġȸ����', 18000, 1, 180);

insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('5', '16', '�丶���ġ���İ�Ƽ', 8500, 1, 85);
insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('5', '9', '��ä��', 8000, 3, 80);
insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('5', '5', '���ܺ����', 7500, 2, 75);
insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('5', '4', '�ҹ��Ұ��', 7900, 1, 79);

insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('6', '14', '���', 8500, 3, 85);
insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('6', '15', '�������쵿', 5000, 4, 50);

insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('7', '11', '«��', 8000, 3, 80);
insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('7', '13', '�˹�', 8000, 1, 80);

insert into fook_payment_detail(payment_id, food_id, food_name, price, count, point)
values ('8', '3', '������Į����', 7000, 8, 70);


COMMIT;
