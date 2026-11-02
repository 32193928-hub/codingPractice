set names utf8mb4;
USE ssafydb;
DROP TABLE IF EXISTS students;
CREATE TABLE students (
    student_id CHAR(7) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE ,
    gender ENUM('M', 'F') ,
    campus ENUM('광주', '구미', '대전', '부울경', '서울') NOT NULL,
    class_no INT CHECK (class_no BETWEEN 1 AND 5) NOT NULL,
    ord_no INT NOT NULL
);

INSERT INTO students(student_id,name, email, gender, campus, class_no, ord_no) VALUES 
('A004648', '김태희', 'uvaab.hhnac@yahoo.com', 'F', '서울', 2, 1),
('B004770', '이우혁', 'jybij.orwcm@gmail.com', 'F', '서울', 1, 2),
('B004924', '한윤연', 'yekuh.lcvet@yahoo.com', 'F', '구미', 1, 2),
('B004827', '최수빈', 'tvgyc.xeekn@naver.com', 'M', '광주', 3, 2),
('C004877', '정하빈', 'ybsst.fejmp@gmail.com', 'F', '대전', 4, 3),
('C004919', '이수현', 'ddguc.jftdc@naver.com', 'M', '부울경', 3, 3),
('C004855', '윤태성', 'psxfk.gumzd@hotmail.com', 'M', '부울경', 2, 3),
('C004613', '최태호', 'pibic.swshw@yahoo.com', 'M', '대전', 3, 3),
('C004709', '서윤지', 'wupjf.mkwlh@yahoo.com', 'F', '대전', 5, 3),
('C004781', '이영지', 'glrmv.gjkda@naver.com', 'M', '광주', 1, 3),
('C004937', '이지선', 'ushbb.hirxc@gmail.com', 'M', '대전', 2, 3),
('C004663', '강지성', 'mlncb.wfhtq@naver.com', 'M', '대전', 5, 3),
('D004904', '윤서준', 'lkbwb.ubgap@naver.com', 'F', '부울경', 3, 4),
('D004625', '이지호', 'hlyff.lqtvl@outlook.com', 'F', '구미', 3, 4),
('D004930', '강민수', 'bedbc.uqcpm@daum.net', 'F', '대전', 4, 4),
('D004955', '장영성', 'rjqzm.dagxq@daum.net', 'F', '부울경', 3, 4),
('D004750', '김민혁', 'fbfgf.qjzjx@naver.com', 'M', '부울경', 4, 4),
('D004981', '최하빈', 'zvetq.qyper@gmail.com', 'M', '광주', 1, 4),
('D004676', '윤우지', 'nhttk.pesor@gmail.com', 'F', '구미', 3, 4),
('D004723', '박우현', 'hocor.qxdcq@daum.net', 'M', '광주', 2, 4),
('D004806', '김수지', 'avirx.dagse@naver.com', 'M', '대전', 5, 4),
('E004997', '이태빈', 'wtwcd.mgpyl@daum.net', 'F', '부울경', 2, 5),
('E004646', '박하연', 'orkar.qhwre@outlook.com', 'F', '서울', 2, 5),
('E004978', '장윤준', 'iwxvq.njkdq@outlook.com', 'M', '구미', 2, 5),
('E004862', '한하혁', 'baqkg.xorsz@naver.com', 'F', '구미', 1, 5),
('E004885', '강우연', 'hrgwd.qhhtu@yahoo.com', 'M', '부울경', 2, 5),
('E004877', '최수진', 'sqgtn.xcxvu@gmail.com', 'F', '서울', 5, 5),
('E004760', '강하진', 'syebz.rbcng@outlook.com', 'M', '광주', 4, 5),
('E004692', '정민진', 'vkhln.ouzfz@naver.com', 'M', '광주', 5, 5),
('E004753', '이예지', 'sudqp.rffcj@daum.net', 'F', '대전', 1, 5),
('F004863', '박영성', 'gkhol.thbzr@gmail.com', 'M', '서울', 1, 6),
('F004608', '강서수', 'hsirq.ikbiy@hotmail.com', 'M', '대전', 2, 6),
('F004942', '장도준', 'omlol.fzkrp@hotmail.com', 'F', '서울', 2, 6),
('F004813', '최서준', 'iwuoz.abasw@daum.net', 'M', '광주', 4, 6),
('F004823', '김민수', 'drvwn.whawj@hotmail.com', 'M', '서울', 3, 6),
('F004739', '이서지', 'wbjkf.wouwx@yahoo.com', 'F', '광주', 5, 6),
('F004827', '이도지', 'bkmsj.ecwdg@yahoo.com', 'F', '서울', 3, 6),
('F004990', '정도진', 'huoja.rrzvc@gmail.com', 'F', '서울', 4, 6),
('F004930', '박우지', 'lpeob.tliqe@naver.com', 'M', '구미', 3, 6),
('G004614', '윤하혁', 'nxdyj.amhda@naver.com', 'F', '부울경', 5, 7),
('G004885', '최윤빈', 'lojzv.trxwe@hotmail.com', 'M', '구미', 1, 7),
('G004971', '한서연', 'hroap.damxp@yahoo.com', 'M', '광주', 5, 7),
('G004795', '김도지', 'ianra.kfbjj@naver.com', 'F', '부울경', 5, 7),
('G004860', '김도성', 'xvqnc.tvjij@gmail.com', 'M', '광주', 3, 7),
('G004975', '김서빈', 'qdzbk.yhrqm@yahoo.com', 'M', '대전', 2, 7),
('G004824', '강도준', 'aghyw.bdvxm@naver.com', 'F', '서울', 2, 7),
('G004702', '장영수', 'ruofh.iigfo@hotmail.com', 'M', '대전', 4, 7),
('H004987', '서영수', 'blfcx.exbxh@gmail.com', 'F', '부울경', 4, 8),
('H004803', '박민호', 'mtfld.nzrua@daum.net', 'M', '대전', 5, 8),
('H004602', '정수호', 'hbwey.zqfge@naver.com', 'F', '광주', 3, 8),
('H004995', '최민진', 'otlac.kuwwf@naver.com', 'F', '부울경', 2, 8),
('H004887', '장수지', 'dmqoo.dkunn@hotmail.com', 'M', '서울', 3, 8),
('H004702', '이민수', 'bvdzc.fokot@outlook.com', 'M', '광주', 2, 8),
('H004662', '박하성', 'dmlrc.xbktm@daum.net', 'F', '대전', 1, 8),
('H004983', '최우지', 'vbfuo.rnqei@naver.com', 'M', '광주', 1, 8),
('I004681', '박민호', 'vdfkg.vwgxs@yahoo.com', 'M', '대전', 1, 9),
('I004686', '이윤지', 'wmona.blugy@outlook.com', 'F', '서울', 4, 9),
('I004871', '장우연', 'wspkc.zbosr@naver.com', 'F', '광주', 4, 9),
('I004793', '최수진', 'qtiwj.rtggl@naver.com', 'M', '부울경', 3, 9),
('I004792', '윤수성', 'kawtk.idzrg@outlook.com', 'F', '구미', 4, 9),
('I004605', '한태지', 'sxqys.amnfb@hotmail.com', 'M', '대전', 1, 9),
('I004908', '정하현', 'wgatt.lndqg@daum.net', 'F', '대전', 1, 9),
('I004879', '강서현', 'vycaf.hkoht@hotmail.com', 'F', '구미', 4, 9),
('I004897', '윤수연', 'otmqk.xzgnp@hotmail.com', 'F', '부울경', 5, 9),
('I004733', '한하성', 'buuwm.acfvu@gmail.com', 'F', '부울경', 5, 9),
('J004674', '윤도호', 'ppuro.wkwdi@outlook.com', 'M', '구미', 3, 10),
('J004862', '박하연', 'yllar.fwcxd@naver.com', 'M', '구미', 3, 10),
('J004849', '정지윤', 'sncfl.ntswe@gmail.com', 'F', '대전', 3, 10),
('J004987', '박민준', 'kozmd.cokih@naver.com', 'F', '부울경', 3, 10),
('J004773', '이지수', 'gaxbq.feugf@hotmail.com', 'M', '부울경', 3, 10),
('J004864', '박하혁', 'ismti.qnqxq@daum.net', 'F', '대전', 2, 10),
('J004784', '김지수', 'ikgui.rqgvb@daum.net', 'M', '부울경', 4, 10),
('J004644', '장윤연', 'acmyf.emofo@outlook.com', 'F', '광주', 5, 10),
('J004656', '서민혁', 'quylh.bfewg@hotmail.com', 'F', '부울경', 2, 10),
('K004887', '정수성', 'xxdrj.hwrkh@gmail.com', 'F', '부울경', 5, 11),
('K004600', '윤우지', 'buthk.bxkkl@naver.com', 'F', '서울', 5, 11),
('K004661', '박민호', 'djgxb.wmjjl@yahoo.com', 'M', '광주', 1, 11),
('K004701', '장수지', 'anurn.nrybn@gmail.com', 'F', '서울', 4, 11),
('K004854', '박민빈', 'akyrj.cumwy@yahoo.com', 'F', '구미', 1, 11),
('K004616', '장민성', 'undev.gcubi@gmail.com', 'F', '부울경', 1, 11),
('K004771', '장서빈', 'pzpxr.ageci@hotmail.com', 'M', '광주', 1, 11),
('K004979', '정서빈', 'hgmmp.ijssq@yahoo.com', 'F', '광주', 3, 11),
('K004673', '윤수혁', 'zmdaj.fmtue@naver.com', 'F', '대전', 2, 11),
('K004838', '최지혁', 'mftnj.ewhoc@gmail.com', 'F', '부울경', 4, 11),
('K004832', '윤수준', 'dnlpa.xzvqe@gmail.com', 'M', '광주', 1, 11),
('K004651', '최우빈', 'kompi.txjfo@outlook.com', 'M', '대전', 3, 11),
('K004749', '정민수', 'fszpi.evpub@gmail.com', 'F', '광주', 2, 11),
('L004832', '이우수', 'mxkmf.scrjf@outlook.com', 'F', '광주', 5, 12),
('L004938', '장윤현', 'leocp.lnsik@naver.com', 'F', '대전', 4, 12),
('L004993', '최우혁', 'jbosz.gkcuc@yahoo.com', 'F', '대전', 2, 12),
('L004609', '박수호', 'etfby.fhctj@yahoo.com', 'M', '부울경', 5, 12),
('L004662', '장민준', 'nsewe.cjpny@hotmail.com', 'F', '부울경', 5, 12),
('L004768', '박태혁', 'bxaka.gafhh@naver.com', 'M', '서울', 3, 12),
('L004829', '김민혁', 'qgfsv.zjvvu@gmail.com', 'M', '광주', 1, 12),
('L004717', '박윤현', 'gijrh.imhdc@yahoo.com', 'M', '광주', 1, 12),
('M004919', '김우준', 'muhqa.cgtoy@daum.net', 'F', '서울', 3, 13),
('M004896', '김태진', 'gekid.yrszx@gmail.com', 'F', '대전', 1, 13),
('M004743', '박서혁', 'vrwsc.zcrot@yahoo.com', 'F', '부울경', 3, 13),
('M004787', '박태수', 'xytrb.mqpng@naver.com', 'F', '구미', 3, 13),
('M004655', '강민연', 'tdwif.ivioa@daum.net', 'F', '광주', 5, 13);

SET SESSION cte_max_recursion_depth = 1000000;

DROP TABLE IF EXISTS users;

CREATE TABLE users
(
	no INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20),
    age INT UNSIGNED,
    gender CHAR(1)
);

INSERT INTO users(name,age,gender)
WITH RECURSIVE cte(n) 
AS (
	SELECT 1
    UNION ALL
    SELECT n+1 FROM cte WHERE n<100000
)
SELECT  CONCAT('user',LPAD(n,6,'0')), 
		CAST(RAND()*101 AS UNSIGNED)+20, 
        IF(n%2=1,'M','F') 
FROM cte;