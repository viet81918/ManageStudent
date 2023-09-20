
package Model;


public class Students implements Comparable<Students> {
    private String ID;
    private String name;
    private String semester;
    private String course;
    public Students() {
    }
    public Students(String ID, String name, String semester, String course) {
        super();
        this.ID = ID;
        this.name = name;
        this.semester = semester;
        this.course = course;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Students{" + "ID=" + ID + ", name=" + name + ", semester=" + semester + ", course=" + course + '}';
    }

    @Override
  
    public int compareTo(Students t) {
        return t.name.compareTo(this.name);
    }
    
}
