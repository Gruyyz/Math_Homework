
/**
 * 玩家实体类，因为这个类用于记录信息，所以称为实体类或者pojo类
 * @author Administrator
 *
 */
public class Player {
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 口令
	 */
	private String password;
	/**
	 * 游戏次数
	 */
	private int count;
	/**
	 * 玩家积分
	 */
	private int score;
	
	/**
	 * 玩家状态
	 */
	private String state;
	
	/**
	 * 缺省构造函数
	 */
	public Player() {}
	/**
	 * 带参数的构造函数
	 * @param name
	 * @param password
	 * @param count
	 * @param score
	 * @param state
	 */
	public Player(String name,String password,int count,int score,String state) {
		this.name = name;
		this.password = password;
		this.count = count;
		this.score = score;
		this.state = state;
	}
	/**
	 * 将当前对象的信息转换为可显示的字符串
	 */
	@Override
	public String toString() {
		return name+","+password+","+count+","+score+","+state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
