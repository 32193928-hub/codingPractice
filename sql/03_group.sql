USE ssafydb;

-- --------------------------------------------------------
-- 그룹함수(다중행함수)
-- --------------------------------------------------------

# 모든 사원의 월급여합, 월급여평균, 사원수 조회

select sum(all sal), avg(all sal), count(*), count(distinct sal) from emp; 

# 커미션이 정해진 모든 사원들의 커미션합, 커미션평균 조회

select sum(comm), avg(comm) from emp;

# 상사(관리자)가 있는 사원수 조회

select count(MGR) from emp;

# 상사(관리자)인 사원수 조회

select count(distinct MGR) from emp;

# 부서배치 받은 모든 사원수, 사원이 소속된 부서의 수 조회 

select count(deptno), count(distinct deptno) from emp;

# 부서별 직무 조합의 수 조회

select count(distinct job, deptno) from emp;

# 부서별 사원수, 월급여평균 조회

select count(*), avg(sal) from emp group by deptno;

# 같은 입사월을 갖는 사원들의 입사월, 사원수 , 월급여평균 조회(입사월 기준 오름차순 정렬)

select month(hiredate), count(*), avg(sal) from emp group by month(hiredate) order by month(hiredate) asc;

# 부서별 직무별 사원수 조회

select * from emp;
select deptno,  job, count(*) from emp group by deptno, job order by deptno asc;

# 부서별 사원수가 5명 이상인 부서번호와 인원 조회

select deptno, count(*) from emp group by deptno having count(*)>=5;

# 직무별 평균 급여가 3000이상인 직무와 평균급여 조회

select job, avg(sal) from emp group by job having avg(sal)>=3000;

# 같은 달에 입사한 사원수 조회, 단,사원수가 2명이상인 경우 

select month(hiredate), count(*) from emp group by month(hiredate) having count(*)>=2;

# 같은 달에 입사한  사원수와 평균월급여 조회, 단,사원수가 2명이상이며 평균월급여가 2000이상인 경우(입사월 기준 오름차순 정렬)

select month(hiredate), count(*), avg(sal) from emp group by month(hiredate) having count(*)>=2&&avg(sal)>=2000;

# 사원평균월급여가 최대인 부서번호와 월평균급여 조회

select deptno, avg(sal) from emp group by deptno order by avg(sal) desc limit 1;

# 총계 roll up
# 부서별 사원들의 급여합 조회하며 전사원들의 월급여합 총계 함께 생성

 select deptno, sum(sal) from emp group by deptno with rollup;

# 소계, 총계 rollup
# 부서별 직무별 사원들의 급여합 조회하며 부서별 소계, 전사원들의 월급여합 총계 함께 생성

select deptno, job, sum(sal) from emp group by deptno, job with rollup;

# grouping()
# 부서별 직무별 사원들의 급여합 조회하며 부서별 소계, 전사원들의 월급여합 총계 함께 생성

select
case grouping(deptno) when 0 then deptno when 1 then '집계' end as deptno,
case grouping(job) when 0 then job  when 1 then '집계' end as job,
sum(sal)
from emp 
group by deptno, job with rollup;

# 부서별 직무별 월급여합 피봇테이블로 조회
-- 출력형태--
-- deptno clerk		manager		president		analyst		salesman
-- 10    1300		2450		5000
-- 20    1900		2975						6000
--  ...

-- select deptno, job, sum(sal) from emp group by deptno, job with rollup;

select deptno,
sum(case job when 'CLERK' then sal end) as CLERK,
sum(case job when 'MANAGER' then sal end) as MANAGER,
sum(case job when 'PRESIDENT' then sal end) as PRESIDENT,
sum(case job when 'ANALYST' then sal end) as ANALYST,
sum(case job when 'SALESMAN' then sal end) as SALESMAN
from emp
group by deptno with rollup;

select deptno, 
count(case job when 'CLERK' then sal end) as CLERK,
count(case job when 'MANAGER' then sal end) as MANAGER,
count(case job when 'PRESIDENT' then sal end) as PRESIDENT,
count(case job when 'ANALYST' then sal end) as ANALYST,
count(case job when 'SALESMAN' then sal end) as SALESMAN
from emp
group by deptno with rollup;
