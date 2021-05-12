package demo.pattern.annotation;

/**
 * @author 张烈文
 */

@CourseInfoAnnotation(courseName = "math", courseTag = "wqweq", courseProfile = "")

public class ImoocCourse {


    @PersonInfoAnnotation(name = "ray", language = {"java", "c"})
    private String author;

    @CourseInfoAnnotation(courseName = "chinese", courseTag = "wqrqa", courseProfile = "")
    public void getCourseInfo() {


    }
}
