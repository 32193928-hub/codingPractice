
-- --------------------------------------------------------
-- 그룹함수(다중행함수)
-- --------------------------------------------------------

# 모든 사원의 월급여합, 월급여평균, 사원수 조회
SELECT	sum(sal), avg(sal), count(*)
FROM	emp;
 

# 커미션이 정해진 모든 사원들의 커미션합, 커미션평균 조회
SELECT	SUM(comm), AVG(comm)
FROM	EMP;

# 상사(관리자)가 있는 사원수 조회
SELECT  count(mgr)
FROM	emp;

# 상사(관리자)인 사원의 수
SELECT 	count(distinct mgr)
FROM	emp;

# 부서배치 받은 모든 사원수, 사원이 소속된 부서의 수 조회           
SELECT 	COUNT(deptno), 
		COUNT(DISTINCT deptno) 
FROM	EMP;

# 부서별 직무 조합의 수 조회
SELECT	COUNT(DISTINCT DEPTNO,JOB)
FROM	EMP;

# 부서별 사원수, 월급여평균  조회
SELECT		deptno, count(*), avg(sal)
FROM		emp
GROUP BY 	deptno;

# 같은 입사월을 갖는 사원들의 입사월, 사원수, 월급여평균 조회(입사월 기준 오름차순 정렬)
SELECT 		month(hiredate),count(*), AVG(sal)
FROM   		emp
GROUP BY	month(hiredate)
ORDER BY	1;


# 부서별 직무별 사원수 조회
SELECT		deptno,job,count(*)
FROM		emp
GROUP BY 	deptno,job;

# 부서별 사원수가 5명 이상인 부서번호와 인원 조회
SELECT 		deptno, COUNT(*) cnt
FROM		EMP
GROUP BY 	deptno
HAVING		COUNT(*) >= 5;

# 직무별 평균 급여가 3000이상인 직무와 평균급여 조회
SELECT 		JOB,AVG(SAL)
FROM 		EMP
GROUP BY 	JOB
HAVING 		AVG(SAL) >= 3000;

# 같은 달에 입사한 사원수 조회, 단,사원수가 2명이상인 경우 
SELECT 		month(hiredate),count(*)
FROM   		emp
GROUP BY	month(hiredate)
HAVING		count(*)>=2
ORDER BY	1;

# 같은 달에 입사한  사원수와 평균월급여 조회, 단,사원수가 2명이상이며 평균월급여가 2000이상인 경우(입사월 기준 오름차순 정렬)
SELECT 		month(hiredate),count(*) cnt,avg(sal)
FROM   		emp
GROUP BY	month(hiredate)
HAVING		count(*) >=2 AND avg(sal) >=2000
ORDER BY	1;
        
# 사원평균월급여가 최대인 부서번호와 월평균급여 조회
SELECT 		DEPTNO, AVG(SAL)
FROM 		EMP
GROUP BY 	DEPTNO
ORDER BY 	2 desc
LIMIT 1;

# 총계 roll up 
# 부서별 사원들의 급여합 조회하며 전사원들의 월급여합 총계 함께 생성
SELECT		deptno,sum(sal)
FROM		emp
GROUP BY 	deptno WITH ROLLUP;

# 소계, 총계 rollup
# 부서별 직무별 사원들의 급여합 조회하며 부서별 소계, 전사원들의 월급여합 총계 함께 생성
SELECT		deptno,job,sum(sal)
FROM		emp
GROUP BY 	deptno,job WITH ROLLUP;


# grouping()
# 부서별 직무별 사원들의 급여합 조회하며 부서별 소계, 전사원들의 월급여합 총계 함께 생성
SELECT		IF(grouping(deptno)=0,deptno,'총계'),IF(grouping(deptno)=1,'총계',IF(grouping(job)=0,IFNULL(job,'없음'),'소계')),sum(sal)
FROM		emp
GROUP BY 	deptno,job WITH ROLLUP;		

# 부서별 직무별 월급여합 피봇테이블로 조회
-- 출력형태--
-- deptno clerk		manager		president		analyst		salesman
-- 10    1300		2450		5000
-- 20    1900		2975						6000
--  ...
SELECT		deptno,
				SUM(case job when 'CLERK' then sal  end) CLERK,
				SUM(case job when 'MANAGER' then sal  end) MANAGER,
				SUM(case job when 'PRESIDENT' then sal  end) PRESIDENT,
				SUM(case job when 'ANALYST' then sal  end) ANALYST,
				SUM(case job when 'SALESMAN' then sal  end) SALESMAN,
			sum(sal)  -- 부서원의 총 급여 합 
FROM		emp
GROUP BY	deptno;