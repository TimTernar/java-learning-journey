enum Category
{
    bronze,silver,gold
}

public class User {
    private final int Id;

    private final String FirstName;

    private final String LastName;

    private final int Age;

    private final Category category;



    public User(int Id, String FirstName, String LastName, int Age, Category category)
    {
        this.Id = Id;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Age = Age;
        this.category = category;
    }

    @Override
    public String toString()
    {
        return super.toString() + "\nFirst Name: " + FirstName + "\nLast Name: " + LastName + "\nAge: " + Age + "\nCategory :" + category;
    }

}
