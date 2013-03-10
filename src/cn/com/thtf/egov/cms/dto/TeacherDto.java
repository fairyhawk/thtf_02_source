package cn.com.thtf.egov.cms.dto;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TeacherDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6019766866438066206L;

	private Long teacherid;

	private String teachername;

	private String teacherpassword;

	private Long flag;

	/** default constructor */
	public TeacherDto() {
	}

	/** full constructor */
	public TeacherDto(String teachername, String teacherpassword, Long flag) {
		this.teachername = teachername;
		this.teacherpassword = teacherpassword;
		this.flag = flag;
	}

	// Property accessors

	public Long getTeacherid() {
		return this.teacherid;
	}

	public void setTeacherid(Long teacherid) {
		this.teacherid = teacherid;
	}

	public String getTeachername() {
		return this.teachername;
	}

	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}

	public String getTeacherpassword() {
		return this.teacherpassword;
	}

	public void setTeacherpassword(String teacherpassword) {
		this.teacherpassword = teacherpassword;
	}

	public Long getFlag() {
		return this.flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	public String setPaa(String name) {
		return "hello" + name;
	}

	@SuppressWarnings("unchecked")
	public static void main(String args[]) {
		Class myClass = TeacherDto.class;
		Class[] types = new Class[] { String.class, String.class, Long.class };
		try {
			Constructor con = myClass.getConstructor(types);
			Object[] objs = new Object[] { "a", "b", 10l };
			TeacherDto teacher = (TeacherDto) con.newInstance(objs);
			System.out.println(teacher.teachername);

			Field field = myClass.getDeclaredField("teachername");
			field.set(teacher, "sxf");
			System.out.println(field.get(teacher));

			Class[] types1 = new Class[] { String.class };
			Method me = myClass.getMethod("setPaa", types1);
			Object[] objs1 = new Object[] { "123" };
			String pass = me.invoke(teacher, objs1).toString();
			System.out.println(pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}