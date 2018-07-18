package util.db;

/**
 * [VO테이블 사고과정]
 * -사원정보를 관리하는 테이블이 있다. emp집합
 * -emp집합에는 8개의 컬럼이 있다.
 * -8개의 컬럼을 따로 받으려면 변수를 8개 선언해야 된다.
 * -그런데 VO를 사용하면 주소번지 하나에 8개 값을 모두 넘길 수 또는 받을 수 있다.
 * -변수선언은 private로 하였다.
 * 		-왜?? 직접변조를 막기위해서!!
 * 		-그렇다면 값을 어떻게 담고 꺼내오지??
 * -그래서 getter와 setter메소드는 접근제한자를 public으로 하였다.
 * 		-담을 땐 setXXX()메소드로 담는다
 * 		-꺼낼 땐 getXXX()메소드로 꺼낸다
 * 
 * [VO작성방법]
 * 1. 서버의 테이블에 있는 Script자료로부터 데이터집합의 컬럼정보를 받아온다.
 * 2. 컬럼명 소문자변경 : Ctrl+shift+Y
 * 3. 일괄편집 : Ctrl+shift+A
 * 4. 초기화 ( 0, null, false etc...)
 * 5. getter,setter 생성
 * 
 * [유사한 디자인패턴 : 아래 2가지 패턴은 같다]
 * 1. VO	: Value Object
 * 2. DTO	: Data Transfer Object
 * ● 테이블에 존재하지 않는 컬럼이더라도 개발자가 업무처리에 필요하면 VO에 변수를 추가할 수 있다.
 */

public class EmpVO {
	
	private int		empno    = 0;
	private String	ename    = null;
	private String	job      = null;
	private int		mgr      = 0;
	private String	hiredate = null;
	private double	sal      = 0;
	private double	comm     = 0;
	private int		deptno   = 0;
	//개발자가, 입력된 신호가 무엇인지 파악하기 위한 VO변수 추가..INSERT | SELECT | UPDATE | DELETE
	private String	command	 = null;
	
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getMgr() {
		return mgr;
	}
	public void setMgr(int mgr) {
		this.mgr = mgr;
	}
	public String getHiredate() {
		return hiredate;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public double getSal() {
		return sal;
	}
	public void setSal(double sal) {
		this.sal = sal;
	}
	public double getComm() {
		return comm;
	}
	public void setComm(double comm) {
		this.comm = comm;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	
}
