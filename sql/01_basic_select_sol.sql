USE ssafydb;

# 현 데이터베이스의 모든 테이블 조회
SHOW TABLES;

select * from emp;

# 테이블 구조 확인
DESC DEPT;
DESCRIBE EMP; 

-- --------------------------------------------------------
-- 기본 조회
-- --------------------------------------------------------

# 모든 사원의 모든 정보(열) 조회
SELECT 	EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO
FROM 		EMP;

SELECT 	*
FROM 		EMP;

# 모든 사원의 특정 정보(열) 조회
SELECT 	EMPNO, ENAME, JOB
FROM 		EMP;

# 모든 사원의 이름, 1년급여,  커미션, 1년급여+커미션 조회
SELECT 	ENAME, SAL*12, COMM,(SAL*12)+COMM
FROM 		EMP;

# 모든 사원의 이름, 1년급여,  커미션, 1년급여+커미션 조회 : 별칭 붙이기
SELECT 	ENAME AS Name, SAL*12 AS 1년급여, SAL*12+IFNULL(COMM,0) "1년 소득" ,  sal*12+coalesce(comm,0)
FROM 		EMP;

# 모든 사원들이 속해 있는 부서 번호 조회
SELECT 	DISTINCT DEPTNO
FROM 		EMP;
-- WHERE DEPTNO IS NOT NULL;

# 모든 사원이 속한 부서와 각 부서의 직무 조회
SELECT 	DISTINCT DEPTNO, JOB
FROM 		EMP;

-- --------------------------------------------------------
-- 조건절
-- --------------------------------------------------------

# 20번 부서에 속한 사원이름, 부서번호 조회
SELECT 	ENAME, DEPTNO
FROM 		EMP
WHERE 	DEPTNO = 20;

# 직무가 CLERK인 사원이름, 직무, 입사일 조회
SELECT 	ENAME, JOB, HIREDATE
FROM 		EMP
WHERE 	JOB = 'CLERK';

# 월급여가 2000보다 많이 받는 사원이름, 월급여 조회
SELECT 	ENAME, SAL
FROM 		EMP
WHERE 	SAL > 2000; 

# 월급여가 1000이상 2000이하인 사원이름, 월급여 조회
SELECT 	ENAME, SAL
FROM 		EMP
WHERE 	SAL BETWEEN 1000 AND 2000;

# 10,20번 부서에 근무하는 사원이름, 부서번호, 월급여 조회
SELECT 	ENAME, DEPTNO, SAL
FROM 		EMP
WHERE 	DEPTNO IN (10,20);

# 10번 부서에서 MANAGER직무를 담당하거나 20번 부서에서 CLERK직무를 담당하고 있는 사원이름,부서번호,직무,월급여 조회
SELECT 	ENAME,  DEPTNO, JOB, SAL
FROM 		EMP
WHERE 	(DEPTNO, JOB) IN ((10,'MANAGER'),(20,'CLERK'));

# 이름이 S로 시작하는 사원이름, 월급여 조회
SELECT 	ENAME, SAL
FROM 		EMP
WHERE 	ENAME LIKE 'S%';

# 이름안에 S를 포함하는 사원이름, 월급여 조회
SELECT 	ENAME, SAL
FROM 		EMP
WHERE 	ENAME LIKE '%S%';

# 이름이 5글자이고 S로 끝나는 사원이름 조회
SELECT 	ENAME
FROM 		EMP
WHERE 	ENAME LIKE '____S';

# 이름의 두번째 글자가 A인 사원번호, 사원이름 조회
SELECT 	EMPNO, ENAME
FROM 		EMP
WHERE 	ENAME LIKE '_A%';

# 이름에 _가 포함된 사원번호, 이름 조회
SELECT 	EMPNO, ENAME
FROM 		EMP
WHERE  	ENAME LIKE '%\_%'; -- _ 를 ESCAPE 처리.(기본 ESCAPE 문자 \)
-- WHERE  	ENAME LIKE '%#_%' ESCAPE '#' ; -- # 를 ESCAPE문자로 처리.(기본 ESCAPE 문자 \)

# COMM이 NULL인 사원이름, 커미션 조회
SELECT 	ENAME, COMM
FROM 		EMP
WHERE 	COMM IS NULL;

# 10번 부서에 속하지 않으며 월급여가 2000보다 많이 받는 사원이름,부서번호,월급여 조회
SELECT 	ENAME, DEPTNO, SAL
FROM 		EMP
WHERE 	DEPTNO <> 10 AND 	SAL > 2000;

# 10,20번 부서에 속하는 사원이름, 부서번호 조회(논리 연산자 활용)
SELECT 	ENAME, DEPTNO, SAL
FROM 		EMP
WHERE 	DEPTNO = 10 OR 	DEPTNO = 20;

# 월급여가 1000이상 2000이하인 사원이름, 월급여 조회(논리 연산자 활용)
SELECT 	ENAME, SAL
FROM 		EMP
WHERE 	SAL >= 1000
AND 		SAL <= 2000;

# 20번 부서에 속하지 않는 사원이름, 부서번호 조회(논리 연산자 활용)
SELECT 	ENAME, DEPTNO
FROM 		EMP
WHERE 	NOT DEPTNO = 20;

# 000101-3010224 주민번호에서 성별 알아내기(case 연산자 활용)
select	'000101-3010224' ,   
			case  substr('000101-3010224',8,1) 
			   when '1'  then '남'
			   when '3'  then '남'
			   else '여'
			end 성별;     

# 사원번호, 사원이름, 월급여, 월급여가 0~2000미만 : C등급, 2000~4000미만 : B등급, 4000~ : A등급으로 조회

select empno,ename,sal,
		case 
			when sal>=4000 then 'A'
			when sal>=2000 then 'B'
			else 'C'
		end 등급
from emp;


-- --------------------------------------------------------
-- 정렬
-- --------------------------------------------------------

# 사원번호, 이름, 부서번호를 부서번호 기준으로 오름차순 정렬
SELECT 		EMPNO, ENAME, DEPTNO, SAL
FROM 			EMP
ORDER BY 	DEPTNO; 

# 사원번호, 이름, 부서번호, 월급여를 부서번호가 빠른 순으로, 같은 부서안에서는 월급여를 많이 받는 순으로 사원 조회
SELECT 		EMPNO, ENAME, DEPTNO, SAL AS 월급여
FROM 			EMP
ORDER BY 	3, 월급여 DESC;

# 가장 많은 월급여를 받는 상위 5명 사원이름, 월급여 조회
SELECT 		ENAME, SAL
FROM 			EMP
ORDER BY SAL DESC
LIMIT 5 OFFSET 0;

# 가장 많은 월급여를 받는 상위 5명 이후 5명의 사원이름, 월급여 조회
SELECT 		ENAME, SAL
FROM 			EMP
ORDER BY SAL DESC
LIMIT 5 OFFSET 5;