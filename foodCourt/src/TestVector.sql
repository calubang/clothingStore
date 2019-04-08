DROP TABLE manager;
DROP TABLE job;
DROP TABLE food;
DROP TABLE category;

CREATE TABLE job (
	job_id VARCHAR2(32) NOT NULL, /* ����ID */
	job_name VARCHAR2(64), /* ���� */
	grade_level VARCHAR2(1), /* ���ȵ�� */
    CONSTRAINT job_job_id_pk PRIMARY KEY (job_id)
);

-- ���� ��
insert into job(job_id, job_name, grade_level) values ('Admin', '������', 'H');
insert into job(job_id, job_name, grade_level) values ('Clerk', '����', 'M');
insert into job(job_id, job_name, grade_level) values ('Newbie', '����', 'L');

-- Ȯ��
SELECT * FROM job;


CREATE TABLE manager (
	manager_id VARCHAR2(32) NOT NULL, /* ������ID */
	name VARCHAR2(32) NOT NULL, /* �̸� */
	password VARCHAR2(32) NOT NULL, /* ��й�ȣ */
	phone_first VARCHAR2(3) NOT NULL, /* �ڵ�����ȣ1 */
	phone_middle VARCHAR2(4) NOT NULL, /* �ڵ�����ȣ2 */
	phone_last VARCHAR2(4) NOT NULL, /* �ڵ�����ȣ3 */
	job_id VARCHAR2(32), /* �����ڵ� */
	hire_date DATE DEFAULT sysdate, /* �Ի��� */
	address_zip VARCHAR2(6), /* �����ȣ */
	address VARCHAR2(160), /* �ּ� */
	email VARCHAR2(32), /* �̸��� */
	email_domain VARCHAR2(32), /* �̸���_������ */
    CONSTRAINT manager_manager_id_pk PRIMARY KEY (manager_id),
    CONSTRAINT manager_job_id_fk foreign key(job_id) references job(job_id)
);

-- ���� ��
insert into manager(manager_id, name, password, phone_first, phone_middle, phone_last, job_id, hire_date, address_zip, address, email, email_domain)
values ('Admin123', 'ȫ�浿', '123qwe', '010', '1234', '5678', 'Admin', sysdate, '34343', 'Seoul yeouido', 'kitri', 'naver.com');
insert into manager(manager_id, name, password, phone_first, phone_middle, phone_last, job_id, hire_date, address_zip, address, email, email_domain)
values ('Clerk123', '������', '1a2s3d4f', '010', '2345', '6789', 'Clerk', sysdate, '43438', 'Seoul yeongdeungpogu', 'c234', 'hanmail.net');
insert into manager(manager_id, name, password, phone_first, phone_middle, phone_last, job_id, hire_date, address_zip, address, email, email_domain)
values ('Newbie123', '�����', 'skujhf723', '010', '3456', '7890', 'Newbie', sysdate, '29843', 'Seoul jonglogu', '2396sdf', 'google.com');

-- Ȯ��
SELECT * FROM manager;


CREATE TABLE category (
	category_id NUMBER(2) NOT NULL, /* ī�װ�ID */
	category_name VARCHAR2(64) NOT NULL, /* ī�װ�_�̸� */
    CONSTRAINT category_category_id_pk PRIMARY KEY (category_id)
);

-- ���� ��
insert into category(category_id, category_name) values (1, '�ѽ�');
insert into category(category_id, category_name) values (2, '�߽�');
insert into category(category_id, category_name) values (3, '�Ͻ�');
insert into category(category_id, category_name) values (4, '���');

-- Ȯ��
SELECT * FROM category;


CREATE TABLE food (
	food_id VARCHAR2(64) NOT NULL, /* ����ID */
	food_name VARCHAR2(64) NOT NULL, /* �����̸� */
	category_id NUMBER(2) NOT NULL, /* ī�װ�ID */
	price NUMBER(6) NOT NULL, /* ���� */
	point NUMBER(6), /* ����Ʈ (���� /100) - Default ������ �ȵż� default �� ���� */
	description VARCHAR2(1024), /* ���� */
	image_address VARCHAR2(128) NOT NULL, /* �̹����ּ� */
	manager_id VARCHAR2(32), /* ������ID */
	create_date DATE DEFAULT sysdate, /* ������ */
	enable VARCHAR2(1) DEFAULT 'y', /* Ȱ��ȭ���� */
    CONSTRAINT food_food_id_pk PRIMARY KEY (food_id),
    CONSTRAINT food_category_id_fk foreign key(category_id) references category(category_id),
    CONSTRAINT food_manager_id_fk foreign key(manager_id) references manager(manager_id)
);

-- Sequence
CREATE SEQUENCE food_fid_seq START WITH 0 INCREMENT BY 1 MAXVALUE 999999 MINVALUE 0;

DROP SEQUENCE food_fid_seq;

-- ���� ��
insert into food(food_id, food_name, category_id, price, point, description, image_address, manager_id, create_date, enable)
values (to_char(food_fid_seq.nextval, '999999'), '������', 1, 6000, 60, '�ż��� ��ä�� ���.', '/kitri/foodCourt/management/menu/image/bokeumbap.jpg', 'Clerk123', sysdate, 'y');
insert into food(food_id, food_name, category_id, price, point, description, image_address, manager_id, create_date, enable)
values (to_char(food_fid_seq.nextval, '999999'), '�����', 2, 5000, 50, '���� ��Ÿ�� ����.', '/kitri/foodCourt/management/menu/image/jajangmyeon.jpg', 'Clerk123', sysdate, 'y');
insert into food(food_id, food_name, category_id, price, point, description, image_address, manager_id, create_date, enable)
values (to_char(food_fid_seq.nextval, '999999'), '�ҹ�', 3, 7000, 70, '�޹��� ��.', '/kitri/foodCourt/management/menu/image/soba.jpg', 'Clerk123', sysdate, 'y');
insert into food(food_id, food_name, category_id, price, point, description, image_address, manager_id, create_date, enable)
values (to_char(food_fid_seq.nextval, '999999'), '������ũ', 4, 9500, 95, '��������� �Ұ�⸦ ���.', '/kitri/foodCourt/management/menu/image/steak.jpg', 'Admin123', sysdate, 'y');

-- Ȯ��
SELECT * FROM food;

commit;