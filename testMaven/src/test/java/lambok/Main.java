package lambok;

public class Main {
	public static void main(String[] args) {
		Student stu = Student.builder().name("Ravi").build();
		System.out.println(stu.getName());
	}

}
