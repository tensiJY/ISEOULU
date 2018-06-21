create table tbl_getjob(
    j_no number,	--	일련번호
    j_title varchar2(2000),	--제목
    j_body varchar2(2000),-- comment 본문
    j_link varchar2(4000), -- 링크
    j_isShow char(1) default 'Y',	-- 삭제여부
    j_creDate Date default SYSDATE, -- 등록일
    constraint pk_jno primary key(j_no),
    constraint ch_ji check (j_isShow in('Y', 'N'))
);



-- 동의어 생성
create or replace public synonym getjob for tbl_getjob;
-- 다른계정에서 getjob 으로 접근 가능..
