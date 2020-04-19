package Client;

public class MsCenter {
	private String msg = null;
	private Login login = null;
	private Signup join = null;
	private ClientChat ch = null;
	MsCenter(ClientChat ch,String msg) {
		this.ch=ch;
		checkMsg(msg);
	}

	private void checkMsg(String msg) {
		if(msg.contains("member")) {
			join = new Signup(ch);
			join.membercheck(msg);
		}else if(msg.contains("login")){
			login = new Login(ch);
			login.loginresult(msg);
		}else if(msg.contains("check")) {
			join = new Signup(ch);
			join.idchk(msg);
		}else if(msg.contains("add")) {
			add.Streamset(msg);
		}
		
	}
	private void orderMsg(String Omsg) {
	if(msg.contains("add")) {
		add.Streamset(Omsg);
	}
	
	
}
