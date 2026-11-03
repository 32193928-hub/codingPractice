USE SSAFYDB;

-- -------------------------------------------------------------
-- EQUI JOIN
-- -------------------------------------------------------------

# 사원이름과 부서이름 조회
SELECT	ename, dname
FROM	emp
JOIN	dept  USING(deptno); 

-- join ~ on : 테이블이름 활용
SELECT	ename, dname
FROM	emp
JOIN	dept   ON(emp.deptno = dept.deptno);

	-- join ~ on : 테이블별칭 활용
	SELECT	e.ename, d.dname
	FROM	emp 	e
	JOIN	dept 	d  ON(e.deptno = d.deptno);

-- 함축형
SELECT	ename, dname
FROM	emp,  dept
WHERE	emp.deptno = dept.deptno;

-- error
# 사원이름과 부서이름, 부서번호 조회
SELECT	ename, dname, deptno
FROM	emp
JOIN	dept  USING(deptno); 

-- error
SELECT	ename, dname, deptno
FROM	emp, dept
WHERE	emp.deptno = dept.deptno;


# 직무가 CLERK인 직원의 이름 ,직무, 부서이름 조회
SELECT 	ename, job, dname
FROM 	emp
JOIN 	dept  USING(deptno)
WHERE 	job = 'CLERK';

SELECT 	ename, job, dname
FROM 	emp e
JOIN 	dept d ON(e.deptno = d.deptno)
WHERE 	job = 'CLERK';

SELECT 	e.ename, e.job, d.dname
FROM 	emp e, dept d
WHERE 	e.deptno = d.deptno
AND 	e.job = 'CLERK';

-- -------------------------------------------------------------
-- NON-EQUI JOIN
-- -------------------------------------------------------------

# 사원의 이름, 월급여, 급여 등급 조회
SELECT		e.ename, e.sal, s.grade
FROM		emp e
JOIN		salgrade s ON(e.sal BETWEEN s.losal AND s.hisal)
ORDER BY 	3;


-- -------------------------------------------------------------
-- OUTER JOIN
-- -------------------------------------------------------------

-- TEST DATA INSERT

  	INSERT INTO emp(empno, ename, sal, deptno, hiredate) 
	VALUES(9998,'TAEHEE',12000,20,now());
	INSERT INTO emp(empno, ename, sal, hiredate)
	VALUES(9999,'SSAFY',7000,now());
 


# 월급여 구간을 벗어나는 사원도 포함 하여 사원 이름, 월급여, 급여 등급 조회
SELECT		e.ename, e.sal, s.grade
FROM		emp e
LEFT JOIN	salgrade s ON(e.sal BETWEEN s.losal AND s.hisal)
ORDER BY 	3;


# 부서 배치 받지 못한 사원까지 포함하여 모든 사원 이름, 부서 이름 조회
SELECT		ename, dname
FROM		emp
LEFT JOIN	dept  USING(deptno); 


# 부서 배치 받지 못한 사원까지 포함하여 모든 사원 이름, 부서 이름 조회(부서 이름 없을 경우 '해당부서없음')
SELECT		e.ename, ifnull(d.dname , '해당부서없음')
FROM		emp e
LEFT JOIN	dept d ON(e.deptno = d.deptno);


SELECT			e.ename, ifnull(d.dname , '해당부서없음')
FROM			emp e
LEFT OUTER JOIN dept d ON (e.deptno = d.deptno);

# 소속 사원이 없는 모든 부서를 포함하여 사원이름, 부서이름 조회
SELECT			 	ename, dname
FROM			 	emp 
RIGHT OUTER JOIN 	dept USING(deptno);


# 월급여에 해당하는  급여등급구간이 존재하지 않는 사원도 부서배치 받지 못한 사원도  조회(outer join)
SELECT		e.ename, e.sal, d.dname,s.grade
FROM	    emp e
LEFT JOIN	dept d ON (e.deptno = d.deptno)
LEFT JOIN   salgrade s ON (e.sal BETWEEN s.losal AND s.hisal)
ORDER BY	3;


-- -------------------------------------------------------------
-- NATURAL JOIN
-- -------------------------------------------------------------

# 모든 사원의 사원이름과 부서이름 조회
SELECT			ename, dname
FROM			emp
NATURAL JOIN	dept;


-- -------------------------------------------------------------
--  CROSS JOIN
-- -------------------------------------------------------------

# 사원이름과 부서이름 CROSS JOIN
SELECT 		ename, dname
FROM		emp, dept;

SELECT 		ename, dname
FROM		emp
CROSS JOIN 	dept;


-- -------------------------------------------------------------
--  SELF JOIN
-- -------------------------------------------------------------

# 모든 사원의 이름과 자신의 상사(관리자) 이름을 함께 조회
SELECT  e.ename 사원이름 , m.ename 상사이름
FROM	emp e, emp m
WHERE	e.mgr = m.empno;

SELECT  e.ename 사원이름 , m.ename 상사이름
FROM	emp e
JOIN	emp m ON(e.mgr = m.empno);

# 상사(관리자)인 사원의 사번과 이름 조회
SELECT   DISTINCT m.empno, m.ename
FROM	 emp m
JOIN	 emp e ON(m.empno = e.mgr);

SELECT  DISTINCT m.empno, m.ename
FROM	emp m, emp e
WHERE	m.empno = e.mgr;

# 모든 사원의 이름과 자신의 상사(관리자) 이름을 함께 조회(단, 상사(관리자)가 없는 사원까지 모두 조회)
# 상사가 없을경우 '없음'으로 나타낸다.
SELECT  		 e.ename 직원이름, ifnull(m.ename ,'없음') 상사이름
FROM			 emp e
LEFT OUTER JOIN	 emp m ON(e.mgr = m.empno);