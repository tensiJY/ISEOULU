create table tbl_getjob(
    j_no number,	--	�Ϸù�ȣ
    j_title varchar2(2000),	--����
    j_body varchar2(2000),-- comment ����
    j_link varchar2(4000), -- ��ũ
    j_isShow char(1) default 'Y',	-- ��������
    j_creDate Date default SYSDATE, -- �����
    constraint pk_jno primary key(j_no),
    constraint ch_ji check (j_isShow in('Y', 'N'))
);



-- ���Ǿ� ����
create or replace public synonym getjob for tbl_getjob;
-- �ٸ��������� getjob ���� ���� ����..
