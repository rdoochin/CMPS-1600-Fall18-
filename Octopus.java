//====================================
// Created: Bob Roos, 3 October 2013
// Modified: Anastasia Kurdia, 11 January 2016
// Objects in the Octopus class have an age, a weight, a name, and a favorite
// cooking utensil. Methods are provided for retrieving each of these values
// and for setting all but the name to new values. (We aren't allowed to change
// the Octopus's name, so there is no method for this)
//=====================================
public class Octopus
{
    // INSTANCE VARIABLES:
    private int age;         // this Octopus's age, in years
    private double weight;   // this Octopus's weight, in pounds
    private String name;     // this Octopus's name
    private Utensil utensil; // this Octopus's favorite kitchen utensil

    // CONSTRUCTOR:
    //---------------------------------
    // Construct an Octopus with a given name n; all other
    // instance variables are assigned default values.
    //---------------------------------
    public Octopus(String n, int a)
    {
        name = n;         // store the name in the appropriate instance variable
        age = a;         // we don't know the Octopus's age yet
        weight = -1;      // we don't know the Octopus's weight yet
        utensil = null;   // we don't know the Octopus's favoriet utensil yet
    }

    // METHODS:

    //--------------------------------
    // Set the Octopus's age to the value stored in parameter a:
    //--------------------------------
    public void setAge(int a)
    {
        age = a;
    }


    //--------------------------------
    // Return the Octopus's age:
    //--------------------------------
    public int getAge()
    {
        return age;
    }


    //--------------------------------
    // Set the Octopus's weight to the value stored in parameter w:
    //--------------------------------
    public void setWeight(double w)
    {
        weight = w;
    }

    //--------------------------------
    // Return the Octopus's weight:
    //--------------------------------
    public double getWeight()
    {
        return weight;
    }

    //--------------------------------
    // Set the Octopus's favorite utensil to the value stored in parameter u:
    //--------------------------------
    public void setUtensil(Utensil u)
    {
        utensil = u;
    }

    //--------------------------------
    // Return the Octopus's favorite utensil:
    //--------------------------------
    public Utensil getUtensil()
    {
        return utensil;
    }

    //--------------------------------
    // Return the Octopus's name:
    //--------------------------------
    public String getName()
    {
        return name;
    }

    //--------------------------------
    // Return a short description of this octopus; here,
    // we'll just use the name
    //---------------------------------
    public String toString()
    {
        return "An octopus named " + name;
    }
}
