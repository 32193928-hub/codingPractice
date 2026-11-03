USE ssafydb;

-- ------------------------------------------------
-- 단일행 서브 쿼리
-- ------------------------------------------------

# 이름이 'SMITH'인 사원과 같은 부서에 근무하는 사원이름, 부서번호 조회

select ename, deptno from emp deptno where deptno IN (select deptno from emp where ename='SMITH');

# 이름이 'SMITH'인 사원보다 나중에 입사한 사원이름, 입사일 조회                      

select ename, hiredate from emp where hiredate>(select hiredate from emp where ename='SMITH');

# 이름이 'SMITH'인 사원의 급여보다 많은 급여를 받는 사원이름, 월급여 조회

select ename, sal from emp where sal>(select sal from emp where ename='SMITH');

-- ------------------------------------------------
-- 다중행 서브 쿼리
-- ------------------------------------------------

# 상사(관리자)인 사원번호, 사원이름 조회

select empno, ename, '관리자' as 직책 from emp where empno IN(select MGR from emp);

# 상사(관리자)가 아닌 사원번호, 사원이름 조회

select empno, ename, '관리자 아님' as 비고 from emp where empno NOT IN(select MGR from emp where mgr is not null);

# SALESMAN의 최저월급여보다 월급여가 많은 사원이름, 월급여 조회

select ename, sal from emp where sal>ANY (select sal from emp where job='SALESMAN') order by sal;

# SALESMAN의 최대월급여보다 월급여가 적은 사원이름, 월급여 조회

select ename,sal from emp where sal<ANY (select sal from emp where job='SALESMAN') order by sal;
        
# SALESMAN의 최대월월급여보다 급여가 많은 사원이름, 월급여 조회

select ename, sal from emp where sal>ALL (select sal from emp where job='SALESMAN') order by sal;

# SALESMAN의 최저월급여보다 급여가 적은 사원이름, 월급여 조회

select ename,sal from emp where sal<ALL (select sal from emp where job='SALESMAN') order by sal;



-- ------------------------------------------------
-- EXISTS 연산자
-- ------------------------------------------------

# 상사(관리자)인 사원번호, 사원이름 조회 

select empno, ename from emp e1 where EXISTS (select 1 from emp e2 where e1.empno=e2.MGR);

# 상사(관리자)가 아닌 사원번호, 사원이름 조회 

select empno, ename, MGR from emp e1 where NOT EXISTS (select 1 from emp e2 where e1.empno=e2.MGR);

#self JOIN

select distinct e1.empno, e1.ename from emp e1 join emp e2 on (e1.empno = e2.mgr);
 
-- ------------------------------------------------
-- pairwise 서브쿼리
-- ------------------------------------------------

# 각 부서별로 최대월급여를 받는 사원이름, 월급여, 부서번호 조회


select e1.ename, e2.sal, e2.deptno from (select MAX(sal) sal, deptno from emp group by deptno) e2, emp e1 where e1.deptno = e2.deptno and e1.sal = e2.sal;


-- ------------------------------------------------
-- 스칼라 서브쿼리
-- ------------------------------------------------

# 사원이름, 월급여, 부서번호, 자신의 부서의 평균 월급여와 전사원 평균 월급여 조회

select e.ename, e.sal, e.deptno, (select avg(sal) from emp where deptno = e.deptno) as dept_avg_sal,(select avg(sal) from emp) as company_avg_sal from emp e;




-- ------------------------------------------------
-- 인라인 뷰 
-- ------------------------------------------------

# 각 부서에서 최대급여를 받는 사원번호, 월급여, 사원이름 조회

select e.empno, e.sal, e.ename from emp e join (select deptno, max(sal) as max_sal from emp group by deptno) m on e.deptno = m.deptno and e.sal = m.max_sal;



# 부서별, 직무별 사원 수 피봇테이블 조회(서브쿼리(inline-VIEW) 이용버전)

select deptno, count(clerk) as clerk, count(manager) as manager, count(president) as president, count(analyst) as analyst, count(salesman) as salesman
from (
    select deptno,
           case when job = 'CLERK' then empno end as clerk,
           case when job = 'MANAGER' then empno end as manager,
           case when job = 'PRESIDENT' then empno end as president,
           case when job = 'ANALYST' then empno end as analyst,
           case when job = 'SALESMAN' then empno end as salesman
    from emp
) e
group by deptno;



# 부서별, 직무별 월급여 평균 피봇테이블 조회(서브쿼리(inline-VIEW) 이용버전)

select deptno, avg(clerk) as clerk, avg(manager) as manager, avg(president) as president, avg(analyst) as analyst, avg(salesman) as salesman
from (
    select deptno,
           case when job = 'CLERK' then sal end as clerk,
           case when job = 'MANAGER' then sal end as manager,
           case when job = 'PRESIDENT' then sal end as president,
           case when job = 'ANALYST' then sal end as analyst,
           case when job = 'SALESMAN' then sal end as salesman
    from emp
) e
group by deptno;



# 부서별, 직무별 사원수 조회 피봇테이블 with 총계 조회 (서브쿼리(inline-VIEW) 이용버전)

select case when grouping(deptno) = 1 then '총계' else deptno end as deptno,
       count(clerk) as clerk,
       count(manager) as manager,
       count(president) as president,
       count(analyst) as analyst,
       count(salesman) as salesman,
       count(*) as total
from (
    select deptno,
           case when job = 'CLERK' then empno end as clerk,
           case when job = 'MANAGER' then empno end as manager,
           case when job = 'PRESIDENT' then empno end as president,
           case when job = 'ANALYST' then empno end as analyst,
           case when job = 'SALESMAN' then empno end as salesman
    from emp
) e
group by deptno with rollup;




-- ---------------------------------------------------------------- 
-- 집합 연산자
-- ---------------------------------------------------------------- 

# 주문을 했거나 이벤트를 신청한 고객번호와 주문/이벤트 타입 조회

select member_id, '주문' as type
from orders
where member_id is not null
union all
select member_id, '이벤트' as type
from event_registrations
where member_id is not null;



# 주문을 했거나 이벤트를 신청한 고객번호 중복 없이 조회

select member_id
from orders
where member_id is not null
union
select member_id
from event_registrations
where member_id is not null;



# 주문도 하고 이벤트도 신청한 고객번호 조회

select distinct o.member_id
from orders o
where o.member_id is not null
  and exists (
      select 1
      from event_registrations e
      where e.member_id = o.member_id
  );



# 주문은 했으나 이벤트는 한번도 신청하지 않은 고객번호 조회

select distinct o.member_id
from orders o
where o.member_id is not null
  and not exists (
      select 1
      from event_registrations e
      where e.member_id = o.member_id
  );



# 이벤트는 신청했으나 주문은 한번도 하지 않은 고객번호 조회

select distinct e.member_id
from event_registrations e
where e.member_id is not null
  and not exists (
      select 1
      from orders o
      where o.member_id = e.member_id
  );


