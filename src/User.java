public class User {
    private int Id;

    private String FirstName;

    private String LastName;

    private int Age;

    public User(int Id, String FirstName, String LastName, int Age)
    {
        this.Id = Id;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Age = Age;
    }

    @Override
    public String toString()
    {
        return super.toString() + "\nFirst Name: " + FirstName + "\nLast Name: " + LastName + "\nAge: " + Age;
    }

}
