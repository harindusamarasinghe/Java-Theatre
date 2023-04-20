public class Person {
    private final String name;
    private final String surname;
    private final String email;

    //setters
//    public void setName(String name){
//        this.name=name;
//    }
//    public void setSurname(String surname){
//        this.surname=surname;
//    }
//    public void setEmail(String email){
//        this.email=email;
//    }

    //getters
    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public String getEmail(){
        return email;
    }
    Person(String name, String surname,String email){
        this.name=name;
        this.surname=surname;
        this.email=email;
    }
}
