USE ssafydb;

-- --------------------------------------------------------
-- DML
-- --------------------------------------------------------

DESC EMP;

-- INSERT
# 새로운 사원 등록 - 단일행



# 새로운 사원 등록(사원번호, 사원이름, 월급여) - 다중행


-- UPDATE
# 사번이 8001번인 사원의 부서번호를 30번으로 수정



# 사번이 8002번인 사원의 부서번호와 직무를 8000번 사원과 같게 수정



-- insert or update
# 8003번에 3500의 월급여를 받는 MICKY 사원이 없으면 추가하고 있으면 CLERK 직무와 부서번호 30번으로 UPDATE



-- DELETE
# 8000번 이상 사번을 갖는 사원 삭제



-- --------------------------------------------------------
-- DDL
-- --------------------------------------------------------
show variables like 'character_set_database';
-- testdb database 생성



-- testdb database 캐릭터 셋 확인



use testdb;

# test_emp 테이블 생성(사원번호,이름,이메일,월급여,나이,부서번호)
# 사원번호(empno): 정수, PK 자동증가키
# 이름(ename) : 가변길이문자열최대20자, 필수 
# 이메일(email): 가변길이문자열최대20자, 고유값  
# 월급여(sal) : 실수, 기본값 0
# 나이(age) : 정수, 1~100사이의 값 허용
# 부서번호(deptno) : 정수, FK(dept테이블의 deptno)


# 생성된 test_emp의 사원번호, 사원이름, 월급여, 부서번호를 ssafydb.emp테이블에서 조회한 결과로 insert



# 기존 테이블 test_emp 이용하여 구조 복사하는 test_emp2 테이블 생성(WITH NO DATA)

create table test_emp2 like emp;

show create table emp;
show create table test_emp2;

select count(*) from test_emp2;

# test_emp, test_emp2 테이블 제약 조건 확인



# 서브쿼리를 이용하여 구조 복사하는 test_emp3 테이블 생성(WITH  DATA)



# test_emp, test_emp3 테이블 제약 조건 확인



# test_emp2 테이블에 phone컬럼 age컬럼 뒤에 추가



# test_emp2 테이블의 phone 컬럼 데이터 타입 수정(char 11) 및 not null제약 조건 추가



# test_emp2테이블 phone컬럼 데이터 타입 수정(char 12) 및 컬럼명 tel로 수정



# test_emp2 테이블 tel 컬럼명 phone으로변경



# test_emp2테이블 phone 컬럼 삭제



# testemp2 테이블 이름 new_emp로 변경



# test_emp3 레코드 삭제 by truncate



# testdb database 삭제



-- -------------------------------------------------------------------------
-- VIEW
-- -------------------------------------------------------------------------



-- CASE1) SQL 가독성 및 재사용을 위한 경우
# 피봇 테이블

USE SSAFYDB;

# 기존 : 부서별, 직무별 사원 수 조회(서브쿼리(inline-VIEW) 이용버전)




# inline-VIEW버전 피봇 테이블 VIEW 생성 버전으로 변경
# step1-뷰 생성

create or replace view v_emp_dept_job_sal as
select deptno, empno, ename, sal,
case job when 'CLERK' then sal end as CLERK,
case job when 'MANAGER' then sal end as MANAGER,
case job when 'PRESIDENT' then sal end as PRESIDENT,
case job when 'ANALYST' then sal end as ANALYST,
case job when 'SALESMAN' then sal end as SALESMAN
from emp;

select *
from v_emp_dept_job_sal;

insert into emp(empno, ename, sal, deptno) values(9999, 'TAEHEE', 12000, 20);

delete from emp where empno = 9999;

# step2-위의 뷰 생성버전으로 피봇테이블 변경
select deptno, count(CLERK), count(MANAGER), count(PRESIDENT), count(ANALYST), count(SALESMAN)
from v_emp_dept_job_sal
group by deptno with rollup;




# 부서별 직무별 사원들의 월평균 급여 조회 

select deptno, avg(CLERK), avg(MANAGER), avg(PRESIDENT), avg(ANALYST), avg(SALESMAN)
from v_emp_dept_job_sal
group by deptno with rollup;


-- CASE2) 보안상 특정 레코드나 컬럼만 조회하도록
# 10번부서의 사원들의 사번, 이름, 부서번호, 월급여만 조회하는 v_emp2뷰 생성

create or replace view v_emp2 as select empno, ename, deptno, sal from emp where deptno = 10;

# v_emp2뷰 사용하여 데이터 조회

select * from emp;

# v_emp2뷰 사용하여 데이터 삽입 

insert into v_emp2 values(9999, 'TAEHEE', 10, 12000);
insert into v_emp2 values(9997, 'ssafy', 20, 15000);


# 10번 부서에서 근무하는 사원들의 사원번호, 사원이름, 부서번호, 월급여 조회하는 v_emp2 뷰 생성
# with check option

create or replace view v_emp2 as select empno, ename, deptno, sal from emp where deptno = 10
with check option;


# v_emp2 뷰를 이용한 데이터 삽입 시도




# v_emp2뷰를 이용한 데이터 수정 시도




-- 뷰와 조인 함께 활용
# v_emp2 정보 조회시 부서명 함께 확인




# 부서별 최대 급여 조회하는 v_emp_maxsal뷰 생성




# 자신의 부서에서 최대급여를 받는 사원 조회 뷰 활용


