
-- --------------------------------------------------------
-- DML
-- --------------------------------------------------------

DESC EMP;

-- INSERT
# 새로운 사원 등록 - 단일행
INSERT INTO emp
VALUES (8000,'TAEHEE','MANAGER',NULL,DEFAULT,(SELECT MAX(hisal) FROM salgrade),1000,20);

# 새로운 사원 등록(사원번호, 사원이름, 월급여) - 다중행
INSERT INTO emp(empno, ename, sal)
VALUES (8001,'JANE',4500),
       (8002,'TOM', 4700);

-- UPDATE
# 사번이 8001번인 사원의 부서번호를 30번으로 수정
UPDATE emp
SET    deptno = 30
WHERE  empno = 8001;

# 사번이 8002번인 사원의 부서번호와 직무를 8000번 사원과 같게 수정
UPDATE  emp e1
JOIN    emp e2 ON e2.empno = 8000   
SET     e1.deptno = e2.deptno,
        e1.job    = e2.job
WHERE   e1.empno = 8002;

-- insert or update
# 8003번에 3500의 월급여를 받는 MICKY 사원이 없으면 추가하고 있으면 CLERK 직무와 부서번호 30번으로 UPDATE
INSERT INTO EMP(empno,ename,sal)
VALUES (8003,'MICKY',3500)
ON DUPLICATE KEY UPDATE job = 'CLERK', deptno = 30;

-- DELETE
# 8000번 이상 사번을 갖는 사원 삭제
DELETE FROM emp  WHERE empno >= 8000;


-- --------------------------------------------------------
-- DDL
-- --------------------------------------------------------

-- database 생성
CREATE DATABASE testdb;

-- database 캐릭터 셋 확인
SELECT  schema_name, default_character_set_name, default_collation_name
FROM    information_schema.schemata
WHERE   schema_name = 'testdb';


use testdb;
DROP TABLE IF EXISTS test_emp;
# test_emp 테이블 생성

CREATE TABLE test_emp 
( empno     INT PRIMARY key AUTO_INCREMENT,
  ename     VARCHAR(20) NOT NULL,
  email     VARCHAR(20) UNIQUE,
  sal       FLOAT DEFAULT 0,
  age       INT CHECK (age BETWEEN 1 AND 100), 
  deptno    INT,
  Foreign Key (deptno) REFERENCES SSAFYDB.dept(deptno) ); 


# 생성된 test_emp의 사원번호, 사원이름, 월급여, 부서번호를 emp테이블에서 조회한 결과로 insert
INSERT INTO test_emp(empno, ename, sal, deptno)
    SELECT empno, ename, sal, deptno
    FROM   ssafydb.emp;

# 기존 테이블을 이용하여 구조 복사하는 test_emp2 테이블 생성(WITH NO DATA)
CREATE TABLE test_emp2 LIKE	test_emp;
SELECT COUNT(*) FROM test_emp2;

# 제약 조건 확인
SELECT * FROM information_schema.table_constraints WHERE table_name = 'test_emp2' or table_name = 'test_emp';


# 서브쿼리를 이용하여 구조 복사하는 test_emp3 테이블 생성(WITH  DATA)
CREATE TABLE test_emp3
AS SELECT * FROM test_emp;

SELECT COUNT(*) FROM test_emp3; -- 레코드 복사됨

# 제약 조건 확인
SELECT * FROM information_schema.table_constraints WHERE table_name = 'test_emp3' or table_name = 'test_emp';


# teat_emp2 테이블에 phone컬럼 age컬럼 뒤에 추가
ALTER TABLE test_emp2
ADD phone char(10) after age;


# teat_emp2테이블의 phone 컬럼 데이터 타입 수정(char 11) 및 not null제약 조건 추가
ALTER TABLE test_emp2
MODIFY phone char(11) not null;

# test_emp2테이블 phone컬럼 데이터 타입 수정(char 12) 및 컬럼명 tel로 수정
ALTER TABLE test_emp2
CHANGE COLUMN  phone tel char(12) not null;

# test_emp2 테이블 tel 컬럼명 phone으로변경
ALTER TABLE test_emp2
RENAME COLUMN tel TO phone;

# test_emp2테이블 phone 컬럼 삭제
ALTER TABLE test_emp2
DROP phone;


# testemp2 테이블 이름 new_emp로 변경
ALTER TABLE test_emp2 RENAME new_emp;


# test_emp3 레코드 삭제 by truncate
TRUNCATE TABLE test_emp3;

# testdb database 삭제
DROP DATABASE testdb;

-- -------------------------------------------------------------------------
-- VIEW
-- -------------------------------------------------------------------------



-- CASE1) SQL 가독성 및 재사용을 위한 경우
# 피봇 테이블

USE SSAFYDB;

# 기존 : 부서별, 직무별 사원 수 조회(서브쿼리(inline-VIEW) 이용버전)
SELECT	    deptno,
            COUNT(CLERK) CLERK,
            COUNT(MANAGER) MANAGER,
            COUNT(PRESIDENT) PRESIDENT,
            COUNT(ANALYST) ANALYST,
            COUNT(SALESMAN) SALESMAN
FROM	    (
                SELECT	deptno,
                        IF(job='CLERK', sal, null) CLERK,
                        IF(job='MANAGER', sal, null) MANAGER,
                        IF(job='PRESIDENT', sal, null) PRESIDENT,
                        IF(job='ANALYST', sal, null) ANALYST,
                        IF(job='SALESMAN', sal, null) SALESMAN
                FROM	emp
            ) e
GROUP BY     deptno;

# inline-VIEW버전 피봇 테이블 VIEW 생성 버전으로 변경
CREATE OR REPLACE VIEW v_emp_dept_job_sal
AS
    SELECT  deptno,
            IF(job='CLERK', sal, null) CLERK,
            IF(job='MANAGER', sal, null) MANAGER,
            IF(job='PRESIDENT', sal, null) PRESIDENT,
            IF(job='ANALYST', sal, null) ANALYST,
            IF(job='SALESMAN', sal, null) SALESMAN
    FROM	emp;

# 위의 뷰 생성버전으로 피봇테이블 변경
# 부서별 직무별 사원수 조회 
SELECT		deptno,
            COUNT(CLERK) CLERK,
            COUNT(MANAGER) MANAGER,
            COUNT(PRESIDENT) PRESIDENT,
            COUNT(ANALYST) ANALYST,
            COUNT(SALESMAN) SALESMAN
FROM		v_emp_dept_job_sal
GROUP BY 	deptno;

# 부서별 직무별 사원들의 월평균 급여 조회 
SELECT		deptno,
            AVG(CLERK) CLERK,
            AVG(MANAGER) MANAGER,
            AVG(PRESIDENT) PRESIDENT,
            AVG(ANALYST) ANALYST,
            AVG(SALESMAN) SALESMAN
FROM		v_emp_dept_job_sal
GROUP BY 	deptno;


-- CASE2) 보안상 특정 레코드나 컬럼만 조회하도록
# 10번부서의 사원들의 사번, 이름, 부서번호, 월급여만 조회하는 v_emp2뷰 생성
CREATE OR REPLACE VIEW v_emp2(eno, enm, dno, sal)
AS	SELECT 	empno,ename,deptno,sal
	FROM	emp
    WHERE	deptno = 10;

# v_emp2뷰 사용하여 데이터 조회
SELECT eno,enm,dno,sal FROM v_emp2;

# v_emp2뷰 사용하여 데이터 삽입 
INSERT INTO v_emp2 VALUES(9099,'ssafy', 40, 5000);
INSERT INTO v_emp2 VALUES(9080,'ssafy2', 10, 5000);


# 10번 부서에서 근무하는 사원들의 사원번호, 사원이름, 부서번호, 월급여 조회하는 v_emp2 뷰 생성
# with check option
CREATE OR REPLACE VIEW v_emp2(eno, enm, dno, sal)
AS	SELECT 	empno,ename,deptno,sal
	FROM	emp
    WHERE	deptno = 10
WITH CHECK OPTION;

# v_emp2 뷰를 이용한 데이터 삽입 시도
INSERT INTO v_emp2 VALUES(9001,'ssafy4', 10, 5000);
INSERT INTO v_emp2 VALUES(9000,'ssafy3', 40, 5000); -- error

# v_emp2뷰를 이용한 데이터 수정 시도
UPDATE  v_emp2
SET     dno = 30 -- error
WHERE   eno = 9001;


-- 뷰와 조인 함께 활용
# v_emp2 정보 조회시 부서명 함께 확인
SELECT  e.enm, e.dno, d.dname
FROM    v_emp2 e
JOIN    dept   d ON ( e.dno = d.deptno );


# 부서별 최대 급여 조회하는 v_emp_maxsal뷰 생성
CREATE OR REPLACE VIEW v_emp_maxsal
AS SELECT deptno, MAX(sal) max_sal FROM emp group by deptno;

# 자신의 부서에서 최대급여를 받는 사원 조회 뷰 활용
SELECT  empno, ename, e.deptno, sal
FROM    emp e
JOIN    v_emp_maxsal v ON (e.deptno = v.deptno and e.sal = v.max_sal);



    